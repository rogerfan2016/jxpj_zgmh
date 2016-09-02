<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta chaset="UTF-8">
<%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
<style type="text/css">
	.tChose{ overflow: hidden; padding: 0 0.7rem 0.4rem 0.7rem;}
	.tChose li{ float: left; padding: 0.2rem; margin-top: 0.4rem; margin-right: 0.4rem;  border:1px solid #ccc; font-size: 0.62rem; color: #ccc; border-radius: 0.7rem; }
	.tChose li.changeOn{ border-color:#ff5a54 ; color:#ff5a54 ;}
</style>
<script src="../js/public.js" type="text/javascript"></script>
</head>
<body>
<script type="text/x-handlebars-template" id="amz-tpl">
    {{>header header}}
</script>
<form id="editform">
	<input name="kcid" type="hidden" value="${kcid}"/>
    <input name="globalid" type="hidden" value="${globalid}"/>
	<div style="padding:15px;">
      <label for="doc-select-1">信息分类：</label>
      <select id="xxfl" name="model.xxfl">
        <option value="0">学生类</option>
        <option value="1">课程类</option>
        <option value="2">教师类</option>
		<option value="3">教学环境保障类</option>
      </select>
    </div>
	<div style="padding:15px;">
	  <label for="doc-select-1">信息类型：</label>
      <label class="am-radio-inline am-warning">
        <input type="radio"  value="0" data-am-ucheck name="model.xxlx" checked> 普通
      </label>
      <label class="am-radio-inline am-warning">
        <input type="radio" value="1" data-am-ucheck name="model.xxlx"> 紧急
      </label>
    </div>
	<div style="padding:15px;">
	  <label for="doc-ds-ipt-1">内容类型：</label>
      <label class="am-radio-inline am-warning">
        <input type="radio"  value="0" data-am-ucheck name="model.xxnrlx" checked> 表扬
      </label>
      <label class="am-radio-inline am-warning">
        <input type="radio" value="1" data-am-ucheck name="model.xxnrlx"> 意见/建议
      </label>
	  <label class="am-radio-inline am-warning">
        <input type="radio" value="2" data-am-ucheck name="model.xxnrlx"> 紧急事件
      </label>
    </div>
    <div style="padding:5px;">
    	<ul class="tChose">
        	<li isOn='false'>效果很好1</li>
        	<li isOn='false'>效果很好2</li>
        	<li isOn='false'>效果很好3</li>
        	<li isOn='false'>效果很好4</li>
    	</ul>
    </div>
    <div style="padding:15px;">
		<label for="doc-ds-ipt-1">信息内容：</label>
        <textarea id="xxnr" name="model.xxnr" rows="5" style="border-radius:10px;" class="am-form-field" placeholder="告诉我们您的想法，我们会做的更好"></textarea>
    </div>
</form>
    <div style="padding:0 25px;">
        <div id="saveFeedBack" style="border-radius:10px; display:block;" class="am-btn am-btn-success">提交</div>
    </div>
    <div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
        <div class="am-modal-dialog">
            <div class="am-modal-bd">
                请输入信息内容！
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn">确定</span>
            </div>
        </div>
    </div>
	<div class="am-modal am-modal-alert" tabindex="-1" id="success">
        <div class="am-modal-dialog">
            <div class="am-modal-bd">
                提交成功，感谢你的支持！
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn">确定</span>
            </div>
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
    
    data.header.content.title = "信息员反馈";
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);
    
    $(function () {

        // 提交
        $("#saveFeedBack").click(function() {
          if ($.trim($("#xxnr").val()).length == 0) {
            $("#my-alert").modal();
            return false;
          }
          
          	var arr=[]
	    	// 评价标签内容
	        $('[isOn=true]').each(function(index) {
	            var crg=$(this).text();
	            arr.push(crg);
	            return arr
	        })
	        alert(arr.join(';'));
        
          $.post("<%=request.getContextPath()%>/feedback/info_saveFeedbackInfo.html",
            $("#editform").serialize(), function(data) {
              if(data){
                $("#success").modal();
                window.location.href = "<%=request.getContextPath()%>/evaluation/mobile_curriculumToday.html";
              }else{
                showWarning(data.text);
              }
            },"json");
          return false;
        });
        
        // 评价标签选择
        $(".tChose li").on("touchstart",function(){
	        var isc=$(this).attr('isOn');
	        if(isc=="false"){
	            $(this).addClass('changeOn');
	            $(this).attr('isOn','true');
	        }else{
	            $(this).removeClass('changeOn');
	            $(this).attr('isOn','false');
	        }
	        
	    })
    });
</script>
<%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>