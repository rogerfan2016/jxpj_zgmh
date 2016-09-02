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
    <script type="text/javascript">
      $(function() {
	        // 查询
	        $("#search_btn").click(function() {
	          $("#search").submit();
	        });
	        
	        fillRows("20", "", "", false);//填充空行
	        
	        // 导出
	       $("#btn_dc").click(function() {
	         window.open("<%=request.getContextPath()%>/feedback/handle_personExportFeedBack.html?" + $("#search").serialize());
	       });
      });
      
       //反馈信息处理
      function personHandleFeedback(xxid){
      		var url=_path+'/feedback/handle_personHandleFeedbackView.html?xxid='+xxid;
			showWindow('信息处理',url,600,350);
      }
     
      //反馈信息详情
      function feedBackDetail(xxid){
      		var url=_path+'/feedback/info_feedBackDetail.html?xxid='+xxid;
			showWindow('信息详情',url,600,420);
      }
      
    </script>
  </head>
  <body>
  	<div class="toolbox">
      <div class="buttonbox">
        <ul>
          <li>
            <a id="btn_dc" class="btn_dc">导出记录</a>
          </li>
        </ul>
      </div>
    </div>
    <form action="<%=request.getContextPath()%>/feedback/handle_personHandleFeedBackList.html" name="search" id="search" method="post">
      <input name="phase" type="hidden" value="${phase }"/>
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学年</th>
              <td>
                <ct:codePicker name="query.xn" catalog="<%=ICodeConstants.DM_DEF_XN %>" code="${query.xn }" width="80"/>
              </td>
              <th>学期</th>
              <td>
                <select name="query.xq" style="width:80px;">
                  <option value="">全部</option>
                  <option value="1" <c:if test="${query.xq eq '1'}">selected</c:if>>第1学期</option>
                  <option value="2" <c:if test="${query.xq eq '2'}">selected</c:if>>第2学期</option>
                </select>
              </td>
              <th>信息分类</th>
              <td>
                <select name="query.xxfl" style="width:100px;">
                  <option value=""></option>
                  <option value="0" <c:if test="${query.xxfl eq '0'}">selected</c:if>>学生类</option>
                  <option value="1" <c:if test="${query.xxfl eq '1'}">selected</c:if>>课程类</option>
                  <option value="2" <c:if test="${query.xxfl eq '2'}">selected</c:if>>教师类</option>
                  <option value="3" <c:if test="${query.xxfl eq '3'}">selected</c:if>>教学环境保障类</option>
                </select>
              </td>
             <th>内容类型</th>
              <td>
                <select name="query.xxnrlx" style="width:100px;">
                  <option value=""></option>
                  <option value="0" <c:if test="${query.xxnrlx eq '0'}">selected</c:if>>表扬</option>
                  <option value="1" <c:if test="${query.xxnrlx eq '1'}">selected</c:if>>意见/建议</option>
                  <option value="2" <c:if test="${query.xxnrlx eq '2'}">selected</c:if>>紧急事件</option>
                </select>
              </td>
              <th>状态</th>
              <td>
                <select name="query.zt" style="width:80px;">
                  <option value=""></option>
                  <option value="0" <c:if test="${query.zt eq '0'}">selected</c:if>>未处理</option>
                  <option value="1" <c:if test="${query.zt eq '1'}">selected</c:if>>已处理</option>
                </select>
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
          <span>反馈信息列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
              	<td width="10%" style="text-align:center;">操作</td>
              	<td width="5%" style="text-align:center;">学年</td>
              	<td width="5%" style="text-align:center;">学期</td>
                <td width="10%" style="text-align:center;">信息分类</td>
                <td width="10%" style="text-align:center;">信息类型</td>
                <td width="5%" style="text-align:center;">内容类型</td>
                <td width="10%" style="text-align:center;">课程名称</td>
                <td width="15%" style="text-align:center;">开课学院</td>
                <td width="10%" style="text-align:center;">教师名称</td>
                <td width="10%" style="text-align:center;">教师所在单位</td>
                <td width="5%" style="text-align:center;">处理环节</td>
                <td width="5%" style="text-align:center;">状态</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${feedBackPageList}" var="info" varStatus="st">
              <tr name="tr" align="center"">
              	<td>
              		<c:if test="${info.clhj eq '1' }"><a id="handle" class="btn_xg" onclick="personHandleFeedback('${info.xxid}');"><font color="blue">处理</font></a></c:if>
              		<c:if test="${info.clhj != '1' }"><a id="detail" class="btn_xg" onclick="feedBackDetail('${info.xxid}');"><font color="blue">查看</font></a></c:if>
              	</td>
              	<td>${info.xn}</td>
              	<td>
              		<c:if test="${info.xq eq '1'}">第1学期</c:if>
              		<c:if test="${info.xq eq '2'}">第2学期</c:if>
              	</td>
              	<td>
              		<c:if test="${info.xxfl eq '0' }">学生类</c:if>
                  	<c:if test="${info.xxfl eq '1' }">课程类</c:if>
                  	<c:if test="${info.xxfl eq '2' }">教师类</c:if>
                  	<c:if test="${info.xxfl eq '3' }">教学环境保障类</c:if>
              	</td>
                <td>
                	<c:if test="${info.xxlx eq '0' }">普通</c:if>
                  	<c:if test="${info.xxlx eq '1' }">紧急</c:if>
                </td>
                <td>
                	<c:if test="${info.xxnrlx eq '0' }">表扬</c:if>
                  	<c:if test="${info.xxnrlx eq '1' }">意见/建议</c:if>
                  	<c:if test="${info.xxnrlx eq '2' }">紧急事件</c:if>
                </td>
                <td>${info.kcmc}</td>
                <td>
                	<ct:codeParse catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${info.kkxy}" />
                </td>
                <td>${info.skjsxm}</td>
                <td>${info.jsszdw}</td>
                <td>
              		<c:if test="${info.clhj eq '0' }">单位管理员筛选</c:if>
                  	<c:if test="${info.clhj eq '1' }">责任人/单位处理</c:if>
                  	<c:if test="${info.clhj eq '2' }">单位管理员反馈</c:if>
                  	<c:if test="${info.clhj eq '3' }">信息员评价</c:if>
                  	<c:if test="${info.clhj eq '4' }">完成</c:if>
              	</td>
              	<td>
                  <c:if test="${info.zt eq '0' }">已提交</c:if>
                  <c:if test="${info.zt eq '1' }">处理中</c:if>
                  <c:if test="${info.zt eq '2' }">已反馈</c:if>
                  <c:if test="${info.zt eq '3' }">已评价</c:if>
                  <c:if test="${info.zt eq '4' }">已退回</c:if>
                </td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${feedBackPageList }" query="${query }" queryName="query" />
      </div>
    </form>
  </body>
</html>