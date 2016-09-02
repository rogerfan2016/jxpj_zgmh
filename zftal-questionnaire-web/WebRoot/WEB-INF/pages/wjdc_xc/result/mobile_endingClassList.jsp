<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta chaset="UTF-8">
<%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
</head>
<body>
<script type="text/x-handlebars-template" id="amz-tpl">
{{>header header}}
</script>
<div data-am-widget="tabs" class="am-tabs am-tabs-default">
	<ul class="am-tabs-nav am-cf">
	    <li class="<c:if test='${query.status eq "0"}'>am-active</c:if>"><a href="javascript:;" status="0">未评价</a></li>
	    <li class="<c:if test='${query.status eq "1"}'>am-active</c:if>"><a href="javascript:;" status="1">已评价</a></li>
	</ul>
	<form id="wjListForm" action="<%=request.getContextPath()%>/inspection_mobile/result_endingClassList.html" method="post">
     <input type="hidden" name="query.status" id="djzt" value="${query.status}"/>
     <input type="hidden" name="type" id="type" value="${type}"/>
     <c:if test="${empty pageList}">
     <div id="liNotFind" class="survey_list" style="padding-top:30px;text-align:center;height:80px;display:none;">
       <span style="color:green">暂无记录</span>
     </div>
     </c:if>
	<div data-am-widget="list_news" class="am-list-news am-list-news-default" >
        <div class="am-list-news-bd">
			<ul class="am-list">
				<c:forEach items="${pageList}" var="p" varStatus="st">
	                <li class="am-g am-list-item-dated">
	                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
	                        <tr>
	                            <td valign="center" width="70%">
	                            	<b>课程名称：</b>${p.kcmc }<br>
	                                <b>开课学院：</b>${p.kkxy }<br>
	                               	<b>授课教师：</b>${p.jsxm }<br>
	                               	<c:if test='${query.status eq "1"}'>
	                               	<b>评价时间：</b><s:date name="dcsj" format="yyyy-MM-dd HH:mm:ss"/>${p.dcsj }<br>
	                               	<b>评价结果：</b><font color="red"><c:if test="${p.zf eq null}">0</c:if>${p.zf }</font>分<br>
	                               	</c:if>
	                            </td>
			                	<td align="center" valign="center">
			                		<c:if test="${p.status=='0'}">
			                			<c:if test="${p.rwzt=='1'}">
			                                <button type="button" onclick="yhdj('${p.id}','${p.kcdm}')" style="border-radius:10px;" class="am-btn am-btn-success am-round">我要评价</button>
			                            </c:if>
			                            <c:if test="${p.rwzt!='1'}">
			                        		<button type="button" style="border-radius:10px;" class="am-btn am-btn-default am-round">未开始</button>
			                        	</c:if>
			                        </c:if>
			                        <c:if test="${p.status=='1'}">
			                         	<button type="button" onclick="yhdj('${p.id}','${p.kcdm}')" style="border-radius:10px;" class="am-btn am-btn-secondary am-round">查看</button>
			                    	</c:if>
			                	</td>
	                        </tr>
	                    </table>
	                </li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<table cellSpacing="0" cellPadding="0" width="100%" class="pageTage">
	  <tr>
	    <td align="right" style="padding-left: 20px"><div id="previous"><a onclick="wjdc_xc_result_toPager(${pageInfo.page<2?pageInfo.page:pageInfo.page-1});" href="#">上一页</a></div></td>
	    <td width="30%" align="center">第<span style="color:red;">${pageInfo.page}</span>页/共<span id="totalPage" style="color:red;">${pageInfo.lastPage }</span>页</td>
	    <td align="left" style="padding-right: 20px"><div id="next"><a onclick="wjdc_xc_result_toPager(${pageInfo.page<pageInfo.lastPage?pageInfo.page+1:pageInfo.page});" href="#">下一页</a></div></td>
	  </tr>
	</table>
	</form>
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
    
    data.header.content.title = "${title}";
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);
    
    //用户答卷
    function yhdj(id,kcdm){
   		location.href="<%=request.getContextPath() %>/inspection_mobile/result_endingClassDetail.html?type=${type}&inspectionTaskResult.id="+id+"&inspectionTaskResult.kcdm="+kcdm;
  	}

   	$(function(){
 		$(".am-tabs-nav").find("a").each(function(){
    		if($(this).attr("status")=="${query.status}"){
      			$(this).parent("li").addClass("nav_active");
           	}
       		$(this).click(function(){
           		$("#djzt").val($(this).attr("status"));
            	$("#wjListForm").submit();
     		});
  		});               
	});

	function wjdc_xc_result_toPager(page){
     	if ($("#nowPage").val() == page) {
        	return false;
      	}
       	$("#nowPage").val(page);
     	$("#wjListForm").submit();
	}
</script>
<%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>