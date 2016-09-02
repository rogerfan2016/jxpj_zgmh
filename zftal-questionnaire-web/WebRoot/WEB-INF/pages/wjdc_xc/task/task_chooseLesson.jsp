<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript">
      $(function() {
      
        // 查询
        $("#search_btn").click(function() {
          $("#search").submit();
        });
        
        fillRows("10", "", "", false);//填充空行
      });
      
      // 提交预约
      function doSubmit(globalid) {
        $.post("<%=request.getContextPath() %>/inspection/task_update.html?inspectionTask.id=${inspectionTask.id}&inspectionTask.pjdx=" + globalid, function(data){
          if (data.success) {
          	window.close();
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
    <c:if test="${isSet eq 'N'}">
      <div class="page_prompt">
        <div class="page_promptcon">
          <h3>温馨提示：</h3>
          <p><font color="#0f5dc2">没有开放预约听课申请</font></p>
        </div>
        <p>&nbsp;</p>
      </div>
    </c:if>
      <form action="<%=request.getContextPath()%>/inspection/task_chooseLesson.html" name="search" id="search" method="post">
      <input type="hidden" name="taskId" value="${taskId }"/>
        <div class="searchtab">
          <table width="100%" border="0">
            <tbody>
              <tr>
                <th>授课老师姓名</th>
                <td>
                  <input type="text" class="text_nor" name="query.xm" id="rklsxm" value="${query.xm}" style="width: 100px;" />
                </td>
                <th>课程名称</th>
                <td>
                  <input type="text" class="text_nor" name="query.kcmc" id="kcmc" value="${query.kcmc}" style="width: 100px;" />
                </td>
                <th>课程节次</th>
                <td>
                  	<select name="query.kcjc">
                  		<option value="">全部</option>
              			<option value="1" <c:if test="${query.kcjc eq '1' }">selected</c:if>>第1-2节</option>
                        <option value="3" <c:if test="${query.kcjc eq '3' }">selected</c:if>>第3-4节</option>
                        <option value="5" <c:if test="${query.kcjc eq '5' }">selected</c:if>>第5-6节</option>
                        <option value="7" <c:if test="${query.kcjc eq '7' }">selected</c:if>>第7-8节</option>
                        <option value="9" <c:if test="${query.kcjc eq '9' }">selected</c:if>>第9-10节</option>
                    </select>
                </td>
              </tr>
              <tr>
                <th>开课学院</th>
                <td colspan="5">
                	<ct:codePicker name="query.kkxy" catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${query.kkxy }" width="100"/>
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
            <span>选课列表</span>
          </h3>
          <!--标题end-->
          <div class="con_overlfow">
            <table width="100%" class="dateline tablenowrap">
              <thead id="list_head">
                <tr>
                  <td>序号</td>
                  <td>操作</td>  
                  <td>上课时间</td>
                  <td>上课地点</td>
                  <td>星期</td>
                  <td>课程名称</td>
                  <td>任课老师</td>
                  <td>课程节次</td>
                  <td>开课学院</td>
                  <td>行政班</td>
                  <td>学年</td>
                  <td>学期</td>
                  <td>专业</td>
                </tr>
              </thead>
              <tbody id="list_body">
                <c:forEach items="${lessonList}" var="info" varStatus="st">
                <tr name="tr">
                  <td>${st.index + 1 }</td>
                  <td>
                    <a style="color:#074695" href="#" onclick="doSubmit('${info['GLOBALID']}');">选择听课</a>
                  </td>
                  <td>${info['KCSJ'] }</td>
                  <td>${info['SKDD'] }</td>
                  <td>${info['DAYOFWEEK'] }</td>
                  <td>${info['KCMC'] }</td>
                  <td>${info['RKLS'] }</td>
                  <td>${info['KCJC'] }</td>
                  <td><ct:codeParse catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${info['KKXY'] }" /></td>
                  <td>${info['BJID'] }</td>
                  <td>${info['SCHOOLYEAR'] }</td>
                  <td>
                    <c:if test="${info.semester eq '0' }">上学期</c:if>
                    <c:if test="${info.semester eq '1' }">下学期</c:if>
                  </td>
                  <td>${info['SSZY'] }</td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
          <ct:page pageList="${lessonList }"  query="${query }" queryName="query"/>
        </div>
      </form>
  </body>
</html>