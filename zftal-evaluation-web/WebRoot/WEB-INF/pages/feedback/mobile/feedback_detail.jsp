<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta chaset="UTF-8">
<%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
<title></title>
</head>
<body>
<script id="changyan_mobile_js" charset="utf-8" type="text/javascript" 
	src="http://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id=cys5AyTbR&conf=prod_edd6e284ce8dd171ae0818d0da3c3250">
</script>
<script type="text/x-handlebars-template" id="amz-tpl">
    {{>header header}}
</script>
	<div data-am-widget="list_news" class="am-list-news am-list-news-default" >
        <div class="am-list-news-bd">
            <ul class="am-list">
                <li class="am-g am-list-item-dated">
                	<input type="hidden" name="xxid" value="${model.xxid }"/>
                                <b>信息分类：</b>
                                	<c:if test="${model.xxfl eq '0'}">学生类</c:if>
                                	<c:if test="${model.xxfl eq '1'}">课程类</c:if>
                                	<c:if test="${model.xxfl eq '2'}">教师类</c:if>
                                	<c:if test="${model.xxfl eq '3'}">教学环境保障类</c:if><br>  
                                <b>信息类型：</b>
                                	<c:if test="${model.xxlx eq '0'}">普通</c:if>
                                	<c:if test="${model.xxlx eq '1'}">紧急</c:if><br>   
                                <b>内容类型：</b>
                                	<c:if test="${model.xxnrlx eq '0'}">表扬</c:if>
                                	<c:if test="${model.xxnrlx eq '1'}">意见/建议</c:if>
                                	<c:if test="${model.xxnrlx eq '2'}">紧急事件</c:if><br>   
                                <b>信息状态：</b>
									<c:if test="${model.zt eq '0'}">已提交</c:if>
                                	<c:if test="${model.zt eq '1'}">处理中</c:if>
                                	<c:if test="${model.zt eq '2'}">已反馈</c:if>
                                	<c:if test="${model.zt eq '3'}">已评价</c:if>
                                	<c:if test="${model.zt eq '4'}">退回</c:if><br>  
								<b>反馈时间：</b><fmt:formatDate value="${model.cjsj }" pattern="yyyy-MM-dd HH:mm:ss"/><br>
								<b>信息内容：</b> ${model.xxnr }<br> 
                </li>
                <li class="am-g am-list-item-dated">      
					<b>处理环节：</b>
           							<c:if test="${model.clhj eq '0'}">单位管理员处理</c:if>
                                	<c:if test="${model.clhj eq '1'}">责任人/单位处理</c:if>
                                	<c:if test="${model.clhj eq '2'}">单位管理员反馈</c:if>
                                	<c:if test="${model.clhj eq '3'}">信息员评价</c:if>
                                	<c:if test="${model.clhj eq '4'}">完成</c:if><br> 
								<b>处理结果：</b> ${model.fkjg }<br> 
					<c:if test="${model.zt eq '4'}">
                		<label for="doc-ds-ipt-1">对处理结果是否满意？评价结果：${model.pjjg }</label>
                	</c:if>
                </li>
                <c:if test="${model.zt eq '3'}">
                <li class="am-g am-list-item-dated">  
					  </br><label for="doc-ds-ipt-1">对处理结果是否满意？</label></br>
				      <label class="am-radio-inline am-warning">
				        <input type="radio" value="满意" data-am-ucheck name="model.pjjg"> 满意
				      </label>
					  <label class="am-radio-inline am-warning">
				        <input type="radio" value="基本满意" data-am-ucheck name="model.pjjg"> 基本满意
				      </label>
				      <label class="am-radio-inline am-warning">
				        <input type="radio" value="不满意" data-am-ucheck name="model.pjjg"> 不满意
				      </label>
				      <button type="button" name="add" onclick="veiwFeedBack('${model.xxid}');" class="am-btn am-btn-warning am-btn-block">提交评价</button></br>
                </li>
                </c:if>
                
                <li class="am-g am-list-item-dated"> 
		        <c:forEach items="${model.feedBackLogList}" var="log" varStatus="st">
					 <article class="am-comment am-comment-highlight">
					  <a href="#">
					    <img src="../image/logo_zgmh.png" alt="" class="am-comment-avatar" width="48" height="48"/>
					  </a>
					  <div class="am-comment-main">
					  	<header class="am-comment-hd">
					      <div class="am-comment-meta">
					        <a href="#" class="am-comment-author">
					        <c:if test="${log.clhj eq '0'}"><h3 class="am-comment-title">单位管理员处理</h3></c:if>
		                	<c:if test="${log.clhj eq '1'}"><h3 class="am-comment-title">责任人/单位处理</h3></c:if>
		                	<c:if test="${log.clhj eq '2'}"><h3 class="am-comment-title">单位管理员反馈</h3></c:if>
		                	<c:if test="${log.clhj eq '3'}"><h3 class="am-comment-title">信息员评价</h3></c:if></a>
					         (<time datetime="" ><fmt:formatDate value="${log.czsj }" pattern="yyyy-MM-dd HH:mm:ss"/></time>)
					      </div>
					    </header>
					
					    <div class="am-comment-bd">
					      ${log.rznr }
					    </div>
					  </div>
					</article>
				</c:forEach>
                </li>
            </ul>
            
        </div>
    </div>
    
<script type="text/javascript"> 

    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);
    var leftc = [];
    var obj = {};
    obj.link = "javascript:history.go(-1);";
    obj.icon = "chevron-left";
    leftc.push(obj);
    
    data.header.content.title = "反馈详情";
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);

</script>
<%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>