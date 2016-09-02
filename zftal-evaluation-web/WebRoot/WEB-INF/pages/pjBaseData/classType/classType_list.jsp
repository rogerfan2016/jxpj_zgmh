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
          refreshList();
        });
        
        // 修改
        $("#btn_xg").click(function() {
          var kcdm = $("input[name='kcdm']:checked");
          if (kcdm.length == 0) {
            alert("请先选中操作行");
            return false;
          }
          
          if (kcdm.length > 1) {
            alert("请选中1行操作");
            return false;
          }
          
          showWindow("修改课程分类", "<%=request.getContextPath()%>/basedata/basedata_editClassType.html?calssType.kcdm=" + kcdm.val(), "400","250");
        });
        
        fillRows("20", "", "", false);//填充空行
      });
      
      function refreshList() {
        var form = $("#search");
        form.submit();
      }
    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/basedata/basedata_classTypeList.html" name="search" id="search" method="post">
    	<div class="toolbox">
	      <div class="buttonbox">
	        <ul>
	          <li>
	            <a id="btn_xg" class="btn_xg">修改</a>
	          </li>
	        </ul>
	      </div>
	    </div>
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>课程名称</th>
              <td>
                <input type="text" class="text_nor" name="calssType.kczwmc" id="xh" value="${calssType.kczwmc}" style="width: 100px;" />
              </td>
              <td>
              <div class="btn">
                <button class="brn_cx" type="button" id="search_btn" >查询</button>
              </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>课程分类列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
              	<td width="4%"></td>
              	<td>课程代码</td>
                <td>课程名称</td>
                <td>课程分类</td>
                <td>课程分类2</td>
                <td>三级单位</td>
                <td>开课学院</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${classTypeList}" var="info" varStatus="st">
              <tr name="tr">
              	<td>
                  <input type="checkbox" name="kcdm" value="${info.kcdm }"/>
                </td>
                <td>${info.kcdm }</td>
                <td>${info.kczwmc }</td>
                <td><ct:codeParse catalog="<%=ICodeConstants.DM_XB_KCFL %>" code="${info.fldm }" /></td>
                <td><ct:codeParse catalog="<%=ICodeConstants.DM_XB_KCFL %>" code="${info.fldm2 }" /></td>
                <td><ct:codeParse catalog="<%=ICodeConstants.DM_XB_ZYDMB %>" code="${info.sjdw }" /></td>
                <td><ct:codeParse catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${info.kkxy }" /></td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${classTypeList }" query="${calssType }" queryName="calssType" />
      </div>
    </form>
  </body>
</html>