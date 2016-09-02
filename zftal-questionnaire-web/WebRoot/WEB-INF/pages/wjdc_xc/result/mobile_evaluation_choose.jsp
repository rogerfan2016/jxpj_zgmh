<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<form action="" data-am-validator>
		<select name="query.jxl" onchange="initSkdd()" data-am-selected required>
    		<option value=""></option>
    		<c:forEach items="${jxlList }" var="item">
				<option value="${item.jxl }" ${item.jxl == query.jxl ? "selected" : "" }>${item.jxl }</option>
			</c:forEach>
  		</select>
  		<select name="query.skdd" data-am-selected required>
			<option value=""></option>
  		</select>
  		<p>
    		<button class="am-btn am-btn-primary">查询</button>
   		</p>
    </form>
    <div data-am-widget="list_news" class="am-list-news am-list-news-default" >
        <div class="am-list-news-bd">
            <ul class="am-list">
<c:forEach items="${teachingEntities}" var="model" varStatus="st">
                <li class="am-g am-list-item-dated">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td valign="center" width="70%">
                                <input type="hidden" name="globalid" value="${model.globalid }"/>
           	课程名称：${model.kcmc }<br>                   
			任课老师：${model.rkls }<br>
                                上课地点：${model.skdd }<br>
                                上课时间：${model.kcsj }<br>
			上课节次：${kcjc }节<br>
							</td>
							<c:if test="${lx eq 'teacher'}">
                            <td align="center" valign="center"><button type="button" name="gotoKp" style="border-radius:10px;" class="am-btn am-btn-success">我要评价</button></td>
							</c:if>
                        </tr>
                    </table>
                </li>
</c:forEach>
            </ul>
        </div>
    </div>
</script>

<script type="text/javascript"> 

    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);
    var leftc = [];
    var obj = {};
    obj.link = "javascript:history.go(-1);";
    obj.icon = "chevron-left";
    leftc.push(obj);
    
    data.header.content.title = "听课评价";
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);
    
    $(function () {
        // 评教
        $("[name='gotoKp']").click(function() {
          location.href = "<%=request.getContextPath()%>/inspection_mobile/result_create.html?type=${type}&globalid=" + $(this).closest("tr").find("[name='globalid']").val();
          return false;
        });
    });
    
    //初始化教室select
		function initSkdd(){
			var jxl=$("select[name='query.jxl']").val();
			$("select[name='query.skdd']").html("<option value=''></option>");
			if(classify==""){
				return;
			}
			$.post("<%=request.getContextPath()%/inspection_mobile/result_skddList.html",{"query.jxl":jxl},function(data){
				var appendHtml="";
				$.each(data,function(i,item){
					appendHtml+="<option value='"+item.skdd+"'>"+item.skdd+"</option>";
				});
				$("select[name='query.skdd']").append(appendHtml);
				$("select[name='query.skdd']").val('${query.skdd}');
			});
		}
</script>
<%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>