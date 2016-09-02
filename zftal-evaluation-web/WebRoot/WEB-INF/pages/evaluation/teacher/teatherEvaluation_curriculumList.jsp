<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="code" uri="/WEB-INF/code.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini" %>
    <script type="text/javascript">
    $(function() {
      // 前一天课程
      $("#topday").click(function() {
      	/*if ($("select[name='tolQuery.semester']").val() != "" && $.trim($("#schoolyear").val()).length == 0) {
            alert("选择学期时必须选择学年");
            return false;
          }*/
        $("#day").val("topday");
        $("#search").submit();
      });
      
      // 今天课程
      $("#nowday").click(function() {
        $("#day").val("nowday");
        $("#search").submit();
      });
      
      // 后一天课程
      $("#nextday").click(function() {
        $("#day").val("nextday");
        $("#search").submit();
      });
    
        fillRows("20", "", "", false);//填充空行
    });

    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/teacher_curriculumList.html" name="search" id="search" method="post">
      <input type="hidden" id="day" name="day" value="${day }"/>
      <input type="hidden" id="firstDay" name="firstDay" value="${firstDay }"/>
      <input type="hidden" id="lastDay" name="lastDay" value="${lastDay }"/>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>课程列表</span>
        </h3>
        <!--标题end-->
        <div class="searchtab">
          <table width="100%" border="0">
          	<tbody>
            <tr>
            	<th>开课学院</th>
              <td>
                <ct:codePicker name="curriculumQuery.kkxy" catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${curriculumQuery.kkxy }" width="100"/>
              </td>
              <th>教学楼</th>
              <td>
                <select name="jsl" style="width:80px;">
                  <option value=""></option>
                </select>
              </td>
              <th>上课教室</th>
              <td>
                <select name="curriculumQuery.skdd" style="width:80px;">
                  <option value=""></option>
                </select>
              </td>
            </tr>
          </tbody>
            <tfoot>
              <tr>
                <td colspan="6">
                  <div class="btn">
                    <button class="btn_cx" type="button" id="topday">前一天</button>
                    <button class="btn_cx" type="button" id="nowday">今天</button>
                    <button class="btn_cx" type="button" id="nextday">后一天</button>
                  </div>
                </td>
              </tr>
            </tfoot>
          </table>
        </div>
        <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>课程表列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td></td>
                <td>课程名称</td>
                <td>课程时间</td>
                <td>课程节次</td>
                <td>开课学院</td>
                <td>授课教师</td>
                <td>上课地点</td>
                <!--<td>上课班级</td>-->
                <td>所属专业</td>
                <td>单双周</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${openLessonList}" var="info" varStatus="st">
              <tr name="tr">
                <td> </td>
                <td>${info.kcmc }</td>
                <td>${info.kcsj }</td>
                <td>${info.kcjc }</td>
                <td><ct:codeParse code="${info.kkxy }" catalog="<%=ICodeConstants.DM_DEF_ORG %>" /></td>
                <td>${info.rkls }</td>
                <td>${info.skdd }</td>
                <!--<td>${info.bjid }</td>-->
                <td>${info.sszy }</td>
                <td>${info.dsz }</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
      <ct:page pageList="${openLessonList }" query="${curriculumQuery }" queryName="curriculumQuery"/>
      </div>
    </form>
  </body>
</html>
