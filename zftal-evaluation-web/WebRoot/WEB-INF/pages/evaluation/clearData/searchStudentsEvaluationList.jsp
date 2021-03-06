<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
          window.open("<%=request.getContextPath()%>/evaluation/clearData_exportClearData.html?" + $("#search").serialize());
        });
        
        fillRows("20", "", "", false);//填充空行
      });
      
      function refreshList() {
        var form = $("#search");
        form.attr("action", "<%=request.getContextPath()%>/evaluation/clearData_searchStudentsEvaluationList.html");
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
    	<input name="query.sfqx" value="${query.sfqx }" type="hidden" />
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学年</th>
              <td>
                <select name="query.xn" style="width: 130px">
                  <option value="" selected>全部</option>
                  <option value="2014-2015" <c:if test="${query.xn eq '2014-2015' }">selected</c:if>>2014-2015</option>
                  <option value="2015-2016" <c:if test="${query.xn eq '2015-2016' }">selected</c:if>>2015-2016</option>
                </select>
              </td>
              <th>学期</th>
              <td>
                <select name="query.xq" style="width: 130px">
                  <option value="" selected>全部</option>
                  <option value="1" <c:if test="${query.xq eq '1' }">selected</c:if>>第1学期</option>
                  <option value="2" <c:if test="${query.xq eq '2' }">selected</c:if>>第2学期</option>
                </select>
              </td>
              <th>课程名称</th>
              <td>
                <input name="query.kcmc" value="${query.kcmc }" type="text" style="width: 130px;" class=""text_nor"/>
              </td>
            </tr>
            <tr>
              <th>学号</th>
              <td>
                <input name="query.xh" value="${query.xh }" type="text" style="width: 130px;" class=""text_nor"/>
              </td>
              <th>学生姓名</th>
              <td>
                <input name="query.xsxm" value="${query.xsxm }" type="text" style="width: 130px;" class=""text_nor"/>
              </td>
              <th>教师所在院系</th>
              <td>
                <ct:codePicker name="query.yx" catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${query.yx }" width="100"/>
              </td>
             </tr>
             <tr>
              <th>教师姓名</th>
              <td colspan="5">
                <input name="query.jsxm" value="${query.jsxm }" type="text" style="width: 130px;" class=""text_nor"/>
              </td>
             </tr>
              <c:if test="${query.sfqx eq '1' }">
              <tr>
              <th>清洗条件</th>
              	<td colspan="5">
              		<c:set var="qxtjz" value="${qxtj }"/>
              		<c:forEach items="${list }" var="info" varStatus="st">
              		<c:set var="qxtj" value="${info.tjmc }"/>
                	<input name="query.qxtjz" value="${info.tjmc }" <c:if test="${fn:contains(qxtjz,qxtj)}"> checked="checked"  </c:if> type="checkbox" />${info.tjmc }
                	</c:forEach>
              	</td>
              </tr>
              </c:if>            
          </tbody>
          <tfoot>
            <td colspan="6">
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
          <span>学生评分结果列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>学年</td>
                <td>学期</td>
                <td>学号</td>
                <td>学生姓名</td>
                <td>课程名称</td>
                <td>职工号</td>
                <td>教师姓名</td>
                <td>院系</td>
                <td>分数</td>
                <td>学生成绩</td>
                <td>成绩备注</td>
                <td>绩点</td>
                <td>绝对值</td>
                <c:if test="${query.sfqx eq '1' }">
                <td>清洗条件</td>
                </c:if>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
                <td>${info.xn }</td>
                <td>第${info.xq }学期</td>
                <td>${info.xh }</td>
                <td>${info.xsxm }</td>
                <td>${info.kcmc }</td>
                <td>${info.jszgh }</td>
                <td>${info.jsxm }</td>
                <td>${info.yx }</td>
                <td>${info.zf }</td>
                <td>${info.cj }</td>
                <td>${info.cjbz }</td>
                <td>${info.jd }</td>
                <td>${info.jdz }</td>
                <c:if test="${query.sfqx eq '1' }">
                <td>${info.qxtj }</td>
                </c:if>
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