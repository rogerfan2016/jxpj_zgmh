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
<script type="text/x-handlebars-template" id="amz-tpl">
    {{>header header}}
</script>

    <div data-am-widget="list_news" class="am-list-news am-list-news-default" >
        <div class="am-list-news-bd">
            <ul class="am-list">
            <c:if test="${!sfxxy }">
            	<li class="am-g am-list-item-dated">
					<font color="red" size="5px"><b>提醒：</b>对不起，您还不是信息员！</font>
				</li>
			</c:if>
<c:forEach items="${feedBackPageList}" var="model" varStatus="st">
                <li class="am-g am-list-item-dated">
                	<input type="hidden" name="xxid" value="${model.xxid }"/>
                                <b>课程名称：</b>${model.kcmc }[ <font style="color: red">${model.skjsxm }</font> ]<br>
                                <b>反馈内容：</b> ${model.xxnr }<br>  
                                <b>反馈时间：</b><fmt:formatDate value="${model.cjsj }" pattern="yyyy-MM-dd HH:mm:ss"/><br>
           						<b>处理环节：</b>
           							<c:if test="${model.clhj eq '0'}">单位管理员处理</c:if>
                                	<c:if test="${model.clhj eq '1'}">责任人/单位处理</c:if>
                                	<c:if test="${model.clhj eq '2'}">单位管理员反馈</c:if>
                                	<c:if test="${model.clhj eq '3'}">信息员评价</c:if>
                                	<c:if test="${model.clhj eq '4'}">完成</c:if><br>   
							<c:if test="${model.zt eq '2'}">
								<button type="button" name="addFeedBack" onclick="veiwFeedBack('${model.xxid}');" class="am-btn am-btn-warning am-btn-block">反馈评价</button>
							</c:if>
							<c:if test="${model.zt != '2'}">
								<button type="button" name="veiwFeedBack" onclick="veiwFeedBack('${model.xxid}');" class="am-btn am-btn-primary am-btn-block">查看详情</button>
                			</c:if>
                </li>
              
</c:forEach>
            </ul>
        </div>
    </div>
	<%@ include file="/WEB-INF/pages/mobile/bottomTag.jsp"%>

<script type="text/javascript"> 

    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);
    var leftc = [];
    var obj = {};
    obj.link = "javascript:history.go(-1);";
    obj.icon = "chevron-left";
    leftc.push(obj);
    
    data.header.content.title = "反馈列表";
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);

    // 查看反馈
    function veiwFeedBack(globalid) {
        location.href = _path + "/feedback/m_veiwFeedback.html?globalid=" + globalid;
    }
</script>
<%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>