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
        
        // 导出
        $("#btn_dc").click(function() {
          window.open("<%=request.getContextPath()%>/evaluation/clearData_exportZbxdfList.html?" + $("#search").serialize());
        });
        
        fillRows("20", "", "", false);//填充空行
      });
      
      // 查看饼图漏斗图图表
      function viewZbxdfByPieChart(xn,xq,kcdm,xkkh,jszgh,pjh) {
        showWindow("单项指标得分对比图表", "<%=request.getContextPath()%>/evaluation/clearData_viewZbxdfByPieChart.html?query.xn="+xn+"&query.xq="+xq+"&query.kcdm="+kcdm+"&query.xkkh="+xkkh+"&query.jszgh="+jszgh+"&query.pjh="+pjh, "750","480");
      }
      
      // 查看柱状图折线图图表
      function viewZbxdfByBarChart(xn,xq,kcdm,xkkh,jszgh,pjh) {
        showWindow("单项指标得分对比图表", "<%=request.getContextPath()%>/evaluation/clearData_viewZbxdfByBarChart.html?query.xn="+xn+"&query.xq="+xq+"&query.kcdm="+kcdm+"&query.xkkh="+xkkh+"&query.jszgh="+jszgh+"&query.pjh="+pjh, "750","480");
      }
      
      function refreshList() {
        var form = $("#search");
        form.attr("action", "<%=request.getContextPath()%>/evaluation/clearData_searchZbxdfList.html");
        form.submit();
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
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学年</th>
              <td>
                <select name="query.xn" style="width: 100px">
                  <option value="" selected>全部</option>
                  <option value="2014-2015" <c:if test="${query.xn eq '2014-2015' }">selected</c:if>>2014-2015</option>
                  <option value="2015-2016" <c:if test="${query.xn eq '2015-2016' }">selected</c:if>>2015-2016</option>
                  <option value="2016-2017" <c:if test="${query.xn eq '2016-2017' }">selected</c:if>>2016-2017</option>
                </select>
              </td>
              <th>学期</th>
              <td>
                <select name="query.xq" style="width: 100px">
                  <option value="" selected>全部</option>
                  <option value="1" <c:if test="${query.xq eq '1' }">selected</c:if>>第1学期</option>
                  <option value="2" <c:if test="${query.xq eq '2' }">selected</c:if>>第2学期</option>
                </select>
              </td>
              <th>课程名称</th>
              <td>
                <input name="query.kcmc" value="${query.kcmc }" type="text" style="width: 100px;" class=""text_nor"/>
              </td>
              <th>教师姓名</th>
              	<td>
                	<input name="query.jsxm" value="${query.jsxm }" type="text" style="width: 100px;" class=""text_nor"/>
              	</td>
            </tr>
            <tr>
              	<th>开课学院</th>
              	<td>
                	<input name="query.yx" value="${query.yx }" type="text" style="width: 100px;" class=""text_nor"/>
              	</td>
              	<th>单项指标</th>
              	<td colspan="5">
              		<select name="query.pjh">
              			<option value="" selected>全部</option>
              		<c:forEach items="${zbxlb }" var="info" varStatus="st">
                		<option value="${info.pjh}" <c:if test="${info.pjh eq query.pjh }">selected</c:if>>[${info.zbfl}]${info.pjnr}</option>
                	</c:forEach>
                	</select>
              	</td>
             </tr>
          </tbody>
          <tfoot>
            <td colspan="8">
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
          <span>教师单项指标得分情况列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
              	<td>对比结果</td>
              	<td>得分</td>
                <td>学年</td>
                <td>学期</td>
                <td>选课课号</td>
                <td>课程代码</td>
                <td>课程名称</td>
                <td>开课学院</td>
                <td>教师职工号</td>
                <td>教师姓名</td>
                <td>课程分类</td>
                <td>一级指标</td>
                <td>指标项</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
              	<td>
                <a href="#" onclick="viewZbxdfByBarChart('${info.xn}','${info.xq}','${info.kcdm }','${info.xkkh }','${info.jszgh }','${info.pjh }');">
                	<img src="../image/ico_zzt.png" alt="" />
                </a>
                <a href="#" onclick="viewZbxdfByPieChart('${info.xn}','${info.xq}','${info.kcdm }','${info.xkkh }','${info.jszgh }','${info.pjh }');">
                	<img src="../image/ico_bt.png" alt="" />
                </a>
                </td>
                <td>${info.pjdf }
                <td>${info.xn }</td>
                <td>第${info.xq }学期</td>
                <td>${info.xkkh }</td>
                <td>${info.kcdm }</td>
                <td>${info.kcmc }</td>
                <td>${info.yx }</td>
                <td>${info.jszgh }
                <td>${info.jsxm }
                <td>${info.zbfl }
                <td>${info.yjzbmc }
                <td>${info.pjnr }
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${pageList }" query="${query }" queryName="query"/>
      </div>
    </form>
  </body>
</html>