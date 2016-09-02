<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    isELIgnored="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="ct" uri="/custom-code"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <%@include file="/commons/hrm/head.ini"%>

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.ui.core.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/hrm/code.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/date.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/inputPrompt.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/imageUpload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/fileUpload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/lockTableTitle.js"></script>
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.ui.all.css" type="text/css" media="all" />

    <script type="text/javascript">
      $(function() {
        $("#btn_tb").click(function() {
          var procedureId = $("#procedureId").val();
          if (procedureId.length == 0) {
            alert("没有设置存储过程！");
            return false;
          }
          
          $.ajax({
            url:"<%=request.getContextPath() %>/basedata/basedata_doSynchronized.html",
            type:"post",
            dataType:"json",
            data:"procedureId=" + $("#procedureId").val(),
            success:function(data) {
              if (data["out_code"] != "0") {
                alert(data["out_message"]);
              } else {
                $("form:first").submit();
              }
            }
          });
          return false;
        });
        
        if ("${showMore}" == "1") {
          $("div.more--item_bottom a").removeClass().addClass("up");
          $("div.more--item_bottom a").text("收起");
          $("#total").css("display","block");
          $("#showMore").val("1");
          $("div.more--item_bottom a").toggle(
            hideMore,showMore
          );
        }else{
          $("div.more--item_bottom a").toggle(
            showMore,hideMore
          );
        }
        
        $("#selectBtn").click(function(){
          $("#selectForm").submit();
        });
        
        fillRows("15", "", "", false);
      });
      function showMore(){
        $(this).removeClass().addClass("up");
        $(this).text("收起");
        $("#total").slideDown();
        $("#showMore").val("1");
      }
      function hideMore(){
        $(this).removeClass().addClass("down");
        $(this).text("更多");
        $("#total").slideUp();
        $("#showMore").val("-1");
      }
   
      /*
      *排序回调函数
      */
      function callBackForSort(sortFieldName,asc){
        $("#sortFieldName").val(sortFieldName);
        $("#asc").val(asc);
        $("#selectForm").submit();
      }
    </script>
  </head>

  <body>
    <div class="formbox">
      <div class="toolbox">
        <div class="buttonbox">
          <ul>
            <li>
              <a id="btn_tb" class="btn_sx">同步数据</a>
            </li>
          </ul>
        </div>
      </div>
      <form action="<%=request.getContextPath()%>/basedata/basedata_synBaseData.html" name="selectForm" id="selectForm" method="post">
        <input type="hidden" id="classId" name="classId" value="${classId}"/>
        <input type="hidden" id="procedureId" name="procedureId" value="${procedureId}"/>
        <input type="hidden" id="sortFieldName" name="sortFieldName" value="${sortFieldName}"/>
        <input type="hidden" id="asc" name="asc" value="${asc}"/>
        <input type="hidden" id="showMore" name='showMore' value="${showMore }" />
        <c:if test="${viewables == null || fn:length(viewables) == 0}">
          <div class="page_prompt">
            <div class="page_promptcon">
              <h3>温馨提示：</h3>
              <p><font color="#0f5dc2">信息类【${query.clazz.name }】没有设置可显示的属性</font></p>
            </div>
            <p>&nbsp;</p>
          </div>
        </c:if>
        <c:if test="${fn:length(viewables) > 0}">
        <div class="searchtab">
          <div class="comp_con">  
            <div class="search_advanced" id="total" style="display: none;">
              <table width="100%" border="0"> 
                <tbody>
                  <c:forEach items="${conditions }" step="2" varStatus="vs">
                  <tr>
                    <c:forEach begin="${vs.index}" end="${vs.index + 1}" var="idx">
                    <c:if test="${fn:length(conditions) > idx }">
                    <c:set var="item" value="${conditions[idx] }"/>
                    <th>${item.name }</th>
                    <td> 
                      <c:choose>
                        <c:when test="${item.fieldType=='CODE'}">
                          <ct:codePicker name="${item.fieldName }" catalog="${item.codeId }" code="${valuesMap[item.fieldName] }" width="187"/>
                        </c:when>
                        <c:when test="${item.fieldType=='DATE' ||item.fieldType=='MONTH' ||item.fieldType=='YEAR' }">
                          <input type="text" name="${item.fieldName }" style="width: 85px;" class="Wdate" onfocus="WdatePicker({dateFmt:'${item.typeInfo.format}'})" value="${valuesMap[item.fieldName][0] }"/>&nbsp;至
                          <input type="text" name="${item.fieldName }" style="width: 85px;" class="Wdate" onfocus="WdatePicker({dateFmt:'${item.typeInfo.format}'})" value="${valuesMap[item.fieldName][1] }"/>
                        </c:when>
                        <c:when test="${item.fieldType=='SIGLE_SEL'}">
                          <select name="${item.fieldName }" style="width: 192px;">
                            <option value=""></option>
                            <option value="1" ${valuesMap[item.fieldName]=="1"?"selected=\"selected\"":""}>是</option>
                            <option value="0" ${valuesMap[item.fieldName]=="0"?"selected=\"selected\"":""}>否</option>
                          </select>
                        </c:when>
                        <c:otherwise>
                          <input type="text" fieldType="${item.fieldType }" name="${item.fieldName }" style="width: 190px;" value="${valuesMap[item.fieldName] }"/>
                        </c:otherwise>
                      </c:choose>
                    </td>
                    </c:if>
                    <c:if test="${fn:length(conditions) eq idx }">
                    <th></th>
                    <td></td>
                    </c:if>
                    </c:forEach>
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
            <!-- 伸缩按钮 -->
            <div class="more--item_bottom" style="clear:both;margin-bottom:5px"><p><a href="#" class="down">更多</a></p></div>
          </div>
          <table width="100%" border="0">
            <tfoot>
              <tr>
                <td>
                  <div class="btn">
                    <button class="btn_cx" type="button" id="selectBtn">查 询</button>
                  </div>
               </td>
              </tr>
           </tfoot>
          </table>
        </div>
        <h3 class="datetitle_01">
          <span>${query.clazz.name }</span>
        </h3>
        <div class="con_overlfow">
          <table summary="" class="dateline tablenowrap" align="" width="100%"  id="MyTable">
            <thead id="list_head">
              <tr>
                <c:forEach items="${viewables}" var="p">
                  <c:if test="${p.fieldName eq sortFieldName}">
                    <td class="sort_title_current_${asc }" id="${p.fieldName }">${p.name }</td>
                  </c:if>
                  <c:if test="${p.fieldName != sortFieldName}">
                    <td class="sort_title" id="${p.fieldName }">${p.name }</td>
                  </c:if>
                </c:forEach>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="entity">
                <tr name="list_tr" id="${entity.values['globalid']}">
                  <c:forEach items="${viewables}" var="infoProperty">
                    <td>${entity.viewHtml[infoProperty.fieldName]}</td>
                  </c:forEach>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${pageList }" />
        </c:if>
      </form>
    </div>
  </body>
</html>
