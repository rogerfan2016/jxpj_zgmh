<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib prefix="ct" uri="/custom-code"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <style>
        .ui-autocomplete{
            z-index:12001;
            width: 500px
        }
    </style>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/globalweb/comm/dateformat.js"></script>
    <script type="text/javascript">
	    function closeWin(){
	        $(".ymPrompt_close").click();
	     }
    </script>
</head>
<body>
    <form id="form_edit">
        <div class="tab">
            <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
            	<input name="model.xxid" type="hidden" value="${xxid}"/>
                <tfoot>
                    <tr>
                        <td colspan="6">
                            <div class="btn">
                                <button id="cancel" type="button" onclick="closeWin()">关 闭</button>
                            </div>
                        </td>
                    </tr>
                </tfoot>
                <tbody>
                	<tr>
                		<th>信息分类</th>
                		<td>
                			<c:if test="${model.xxfl eq '0' }">学生类</c:if>
		                  	<c:if test="${model.xxfl eq '1' }">课程类</c:if>
		                  	<c:if test="${model.xxfl eq '2' }">教师类</c:if>
		                  	<c:if test="${model.xxfl eq '3' }">教学环境保障类</c:if>
                		</td>
                		<th>信息类型</th>
                		<td>
                			<c:if test="${model.xxlx eq '0' }">普通</c:if>
                  			<c:if test="${model.xxlx eq '1' }">紧急</c:if>
                		</td>
                		<th>内容类型</th>
                		<td>
                			<c:if test="${model.xxnrlx eq '0' }">表扬</c:if>
		                  	<c:if test="${model.xxnrlx eq '1' }">意见/建议</c:if>
		                  	<c:if test="${model.xxnrlx eq '2' }">紧急事件</c:if>
                		</td>
                	</tr>
                	<tr>
                		<th>课程名称</th><td>${model.kcmc}</td>
                		<th>开课学院</th><td><ct:codeParse catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${model.kkxy}" /></td>
                		<th>状态</th>
                		<td>
                		  <c:if test="${model.zt eq '0' }">已提交</c:if>
		                  <c:if test="${model.zt eq '1' }">处理中</c:if>
		                  <c:if test="${model.zt eq '2' }">已反馈</c:if>
		                  <c:if test="${model.zt eq '3' }">已评价</c:if>
                		</td>
                	</tr>
                	<tr>
                		<th>教师名称</th><td>${model.skjsxm}</td><th>教师所在单位</th><td>${model.jsszdw}</td>
                		<th>创建时间</th><td><fmt:formatDate value="${model.cjsj}" pattern="yyyy-MM-dd" /> </td>
                	</tr>
                	<tr>
                		<th>处理环节</th>
                		<td>
	                		<c:if test="${model.clhj eq '0' }">单位管理员筛选</c:if>
		                  	<c:if test="${model.clhj eq '1' }">责任人/单位处理</c:if>
		                  	<c:if test="${model.clhj eq '2' }">单位管理员反馈</c:if>
		                  	<c:if test="${model.clhj eq '3' }">信息员评价</c:if>
		                  	<c:if test="${model.clhj eq '4' }">完成</c:if>
                		</td>
                		<th>处理人</th>
                		<td colspan="3">
	                		${model.clr}
                		</td>
                	</tr>
                	<tr>
                		<th>信息内容</th>
                		<td colspan="5">
                        	<textarea name="model.xxnr" id="xxnr" style="width:400px;height:40px;">${model.xxnr}</textarea>
                		</td>
                	</tr>
                    <tr>
                        <th>反馈结果</th>
                        <td colspan="5">
                        	<textarea name="model.fkjg" id="fkjg" style="width:400px;height:40px;">${model.fkjg}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>评价结果</th>
                        <td colspan="5">
                        	<textarea name="model.pjjg" id="pjjg" style="width:400px;height:40px;">${model.pjjg}</textarea>
                        </td>
                    </tr>
                	<tr><td colspan="6"></td></tr>
                	<tr>
                		<td colspan="6">
	                		<div style="width:100%;height:45px;overflow:auto">
	                			<c:forEach var="item" items="${feedBackLogEntitys}" varStatus="status"> 
	                				<fmt:formatDate value="${item.czsj}" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;
	                				【环节】
										<c:if test="${item.clhj eq '0' }">单位管理员筛选</c:if>
					                  	<c:if test="${item.clhj eq '1' }">责任人/单位处理</c:if>
					                  	<c:if test="${item.clhj eq '2' }">单位管理员反馈</c:if>
					                  	<c:if test="${item.clhj eq '3' }">信息员评价</c:if>
					                  	<c:if test="${item.clhj eq '4' }">完成</c:if>
									【内容】${item.rznr}   <br/>
								</c:forEach> 
	                		</div>
                		</td>
                	</tr>
                </tbody>
            </table>
        </div>
    </form>
</body>
</html>