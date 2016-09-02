<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="code" uri="/WEB-INF/code.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
     <%@ include file="/commons/hrm/head.ini" %>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
    $(function(){
        // 查询
        $("#search_btn").click(function() {
        	if($.trim($("input[name='query.xy']").val()).length == 0) {
     			alert("请输入学院查询条件！");
     			return false;
        	}
          	refreshList();
        });
        
        // 导出
        $("#btn_dc").click(function() {
          window.open("<%=request.getContextPath()%>/inspection/task_exportSumResult.html?" + $("#search").serialize());
        });
        
        fillRows("20", "", "", false);//填充空行
    });
    
    function refreshList(){
        var form = $("#search");
        form.attr("action", "<%=request.getContextPath()%>/inspection/task_sumMemberResult.html");
        form.submit();
    }
    
    function searchMember(zt,memberid){
        window.open("<%=request.getContextPath() %>/inspection/task_searchMember.html?query.id="+id);
    }
    
    </script>
  </head>
  <body>

  <div class="toolbox">
      <div class="buttonbox">
        <ul>
          <li>
            <a id="btn_dc" class="btn_dc">导出</a>
          </li>
        </ul>
      </div>
    </div>
 <form action="" name="search" id="search" method="post">
 <input type="hidden" name="type" value="${type}"/>
 <input type="hidden" name="rwjb" value="${rwjb}"/>
<div class="searchtab">
    <table width="100%" border="0">
      <tbody>
        <tr> 
        <th >姓名</th>
          <td >
            <input type="text" style="width:100px" class="text_nor" name="query.xm" value="${query.xm }"/>
          </td>
        <th>行政班</th>
          <td>
            <input type="text" style="width:100px" class="text_nor" name="query.xzb" value="${query.xzb }"/>
          </td>
		<th>专业</th>
          <td>
            <input type="text" style="width:100px" class="text_nor" name="query.zy" value="${query.zy }"/>
          </td>
        </tr>
        <tr> 
         <th>学院</th>
          <td>
            <input type="text" style="width:100px" class="text_nor" name="query.xy" value="${query.xy }"/>
          </td>
          <th>状态</th>
          <td colspan="3">
            	<select name="query.zt" style="width:100px;">
                  <option value="0" <c:if test="${query.zt eq '0'}">selected</c:if>>从未评价</option>
                  <option value="1" <c:if test="${query.zt eq '1'}">selected</c:if>>部分评价</option>
                  <option value="2" <c:if test="${query.zt eq '2'}">selected</c:if>>完成评价</option>
                </select>
          </td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="6">
            <div class="btn">
              <button class="btn_cx" name="search_btn" id="search_btn" type="button">查 询</button>
            </div>
          </td>
        </tr>
      </tfoot>
    </table>
  </div>
        <div class="formbox">
<!--标题start-->
    <h3 class="datetitle_01">
        <span>评价汇总查询<font color="#0457A7" style="font-weight:normal;">（说明：评价总数量为0，说明学生从未打开过结课评价页面）</font></span>
    </h3>
<!--标题end-->
    <div class="con_overlfow">
        <table width="100%" class="dateline tablenowrap" id="tiptab">
                <thead id="list_head">
                    <tr>
                        <td>学年</td>
                        <td>学期</td>
                        <td>学号/职工号</td>
                        <td>姓名</td>
                        <td>行政班</td>
                        <td>专业</td>
                        <td>学院</td>
                        <td>评价总数量</td>
                        <td>已评价数量</td>
                        <td>未评价数量</td>
                    </tr>
                </thead>
                <tbody id="list_body" >
                    <s:iterator value="taskMemberList" var="p" status="st">
	                    <tr name="tr">
	                        <td>${p.xn }</td>
	                        <td>${p.xq }</td>
	                        <td>${p.gh }</td>
	                        <td>${p.xm }</td>
	                        <td>${p.xzb }</td>
	                        <td>${p.zy }</td>
	                        <td>${p.xy }</td>
	                        <td>${p.pjzsl }</td>
	                        <td><span style="${p.ypjsl>0?'color:green':''}">${p.ypjsl}</span>
	                        <!--<a style="#074695" href="#" onclick="searchMember('1');">${p.ypjsl}</a>-->
	                        </td>
	                        <td><span style="${p.wpjsl>0?'color:red':''}">${p.wpjsl}</span>
	                        <!--<a style="#074695" href="#" onclick="searchMember('0');">${p.wpjsl}</a>-->
	                        </td>
	                    </tr>
                    </s:iterator>
                </tbody>
    </table>
    </div>
    <ct:page pageList="${taskMemberList }" query="${query }" queryName="query"/>
    </div>
      </form>
  </body>
</html>
