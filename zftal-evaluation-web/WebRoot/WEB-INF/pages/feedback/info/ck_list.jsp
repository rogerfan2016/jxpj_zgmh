<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="code" uri="/WEB-INF/code.tld" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/feedback/xxyfk.js"></script>
    <script type="text/javascript">
      $(function() {
        // 查询
        $("#search_btn").click(function() {
          $("#search").submit();
        });
        fillRows("20", "", "", false);//填充空行
      });
    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/feedback/info_todayCkList.html" name="search" id="search" method="post">
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>24小时课程表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
              	<td width="10%" style="text-align:center;">操作</td>
                <td width="5%" style="text-align:center;">课程名称</td>
                <td width="5%" style="text-align:center;">课程节次</td>
                <td width="5%" style="text-align:center;">单双周</td>
                <td width="5%" style="text-align:center;">星期几</td>
                <td width="5%" style="text-align:center;">教室名称</td>
                <td width="5%" style="text-align:center;">授课教师</td>
                <td width="5%" style="text-align:center;">开始时间</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${ckList}" var="info" varStatus="st">
              <tr name="tr" align="center"">
              	<td><a onclick="feedback('${info.kcid}','${info.globalid}');">反馈</a></td>
              	<td>${info.kcmc}</td>
                <td>${info.kcjc}</td>
                <td>${info.dsz}</td>
                <td>${info.dayofweek}</td>
                <td>${info.skdd}</td>
                <td>${info.rkls}</td>
              	<td>${info.kssj}</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        </div>
    </form>
  </body>
</html>