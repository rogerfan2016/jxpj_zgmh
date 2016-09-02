<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="code" uri="/WEB-INF/code.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
      $(function() {
        // 查询
        $("#search_btn").click(function() {
          $("#search").submit();
        });
        
        fillRows("20", "", "", false);//填充空行
      });
      
      // 清理
      function doAudit(globalid, status) {
        $.post('<%=request.getContextPath() %>/evaluation/graduation_changeStatus.html', 'globalid=' + globalid, function(data){
          var callback = function() {
            $("#search").submit();
          };
          if (data.success) {
            processDataCall(data, callback);
          } else {
            showWarning(data.text);
          }
        }, "json");

        return false;
      }

    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/graduation_searchList.html" name="search" id="search" method="post">
      <input name="phase" type="hidden" value="${phase }"/>
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学号</th>
              <td>
                <input type="text" class="text_nor" name="query.xh" id="xh" value="${query.xh}" style="width: 100px;" />
              </td>
              <th>姓名</th>
              <td>
                <input type="text" class="text_nor" name="query.xm" id="xm" value="${query.xm}" style="width: 100px;" />
              </td>
              <th>状态</th>
              <td>
                <select name="query.zt" style="width:100px;">
                  <option value=""></option>
                  <option value="0" <c:if test="${query.zt eq '0'}">selected</c:if>>未评价</option>
                  <option value="1" <c:if test="${query.zt eq '1'}">selected</c:if>>已评价</option>
                  <option value="2" <c:if test="${query.zt eq '2'}">selected</c:if>>已清理</option>
                </select>
              </td>
            </tr>
            <tr>
              <th>学院</th>
              <td>
              	<input type="text" class="text_nor" name="query.xy" id="xy" value="${query.xy}" style="width: 100px;" />
              </td>
              <th>专业</th>
              <td>
                <input type="text" class="text_nor" name="query.zymc" id="zymc" value="${query.zymc}" style="width: 100px;" />
              </td>
              <th>行政班</th>
              <td>
                <input type="text" class="text_nor" name="query.xzb" id="xzb" value="${query.xzb}" style="width: 100px;" />
              </td>
            </tr>
          </tbody>
          <tfoot>
            <td colspan="12">
              <div class="btn">
                <button class="brn_cx" type="button" id="search_btn" >查询</button>
              </div>
            </td>
          </tfoot>
        </table>
      </div>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>毕业评价列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
              	<td>序号</td>
              	<!--<td>操作</td>-->
              	<td>状态</td>
                <td>学号</td>
                <td>姓名</td>
                <td>性别</td>
                <td>学院</td>
                <td>专业</td>
                <td>行政班</td>
                <td>层次</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
              	<td>${st.index + 1 }</td>
              	<!--<td>
                  <c:if test="${info.zt != '0' }">无</c:if>
                  <c:if test="${info.zt eq '1' }">查看 清理</c:if>
                  <c:if test="${info.zt eq '2' }">恢复</c:if>
                </td>-->
              	<td>
                  <c:if test="${info.zt eq '0' }">未评价</c:if>
                  <c:if test="${info.zt eq '1' }">已评价</c:if>
                  <c:if test="${info.zt eq '2' }">已清理</c:if>
                </td>
                <td>${info.xh }</td>
                <td>${info.xm }</td>
                <td>${info.xb }</td>
                <td>${info.xy }</td>
                <td>${info.zymc }</td>
                <td>${info.xzb }</td>
                <td>${info.cc }</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${pageList }" query="${query }" queryName="query" />
      </div>
    </form>
  </body>
</html>