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
</script>

    <div data-am-widget="list_news" class="am-list-news am-list-news-default" >
        <div class="am-list-news-bd">
            <ul class="am-list">
<c:forEach items="${teachingEntities}" var="model" varStatus="st">
                <li class="am-g am-list-item-dated">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td valign="center" width="70%">
                                <input type="hidden" name="globalid" value="${model.globalid }"/>
           						<b>课程名称：</b>${model.kcmc }<br>                   
								<b>任课老师：</b>${model.rkls }<br>
                                <b>上课地点：</b>${model.skdd }<br>
                                <b>上课时间：</b>${model.kcsj }<br>
								<b>上课节次：</b>${model.kcjc }<br>
							</td>
							<td align="center" valign="center">
							<c:if test="${model.zt eq '0' }">
                            		<button type="button" name="gotoKp" onclick="doStarEvaluation('${model.globalid }');" style="border-radius:10px;" class="am-btn am-btn-success">我要评价</button>
							</c:if>
							<!--<c:if test="${model.zt eq '1' }">
									<button type="button" name="gotoKp" onclick="doEvaluation('${model.xwjid}', '${model.pjryid}', '${model.pjid}');" style="border-radius:10px;" class="am-btn am-btn-secondary">查看评价</button>
							</c:if>
							--><c:if test="${sfxxy }">
								<c:if test="${model.sffk eq '0'}">
								</br></br><button type="button" name="addFeedBack" onclick="doFeedBack('${model.globalid}');" style="border-radius:10px;" class="am-btn am-btn-warning">信息反馈</button>
								</c:if>
								<!--<c:if test="${model.sffk eq '1'}">
								</br></br><button type="button" name="veiwFeedBack" onclick="veiwFeedBack('${model.globalid}','${model.pjryid}');" style="border-radius:10px;" class="am-btn am-btn-secondary">查看反馈</button>
								</c:if>
							--></c:if>
							</td>
                        </tr>
                    </table>
                </li>
				<li class="am-g am-list-item-dated">
					<font color="red" size="3px"><b>提醒：</b>请在上课后24小时内完成评价！</font>
				</li>
</c:forEach>
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
    
    data.header.content.title = "评价课程信息";
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);
  
  	// 评教
    function doEvaluation(wjid, djrid, pjid) {
        location.href = _path + "/wjdc_mobile/stgl_yhdj.html?wjModel.wjid=" + wjid + "&wjModel.djrid=" + djrid + "&wjModel.pjid=" + pjid;
    }
    // 五星评教
    function doStarEvaluation(wjid, djrid, pjid) {
        location.href = _path + "/evaluation/mobile_starEvaluation.html?globalid=" + globalid;
    }
    // 信息反馈
    function doFeedBack(globalid) {
        location.href = _path + "/feedback/m_addFeedback.html?globalid=" + globalid;
    }
    // 查看反馈
    function veiwFeedBack(globalid,pjryid) {
        location.href = _path + "/feedback/m_veiwFeedback.html?globalid=" + globalid + pjryid;
    }
</script>
<%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>