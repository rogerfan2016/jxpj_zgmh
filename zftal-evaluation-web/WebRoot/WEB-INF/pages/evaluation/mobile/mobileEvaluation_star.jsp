<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0;"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<meta name="format-detection" content="telephone=no"/>
<meta name="format-detection" content="email=no"/>
<link href="../css/car_style.css" rel="stylesheet" type="text/css" />
<link href="../css/public.css" rel="stylesheet" type="text/css" />
<title></title>
</head>

<body>
<script>
    var ele=document.getElementsByTagName("html")[0],  
         size=document.body.clientWidth/640*50;
    ele.style.fontSize=size+"px"
</script>
<div class="whole">
	<input type="hidden" name="globalid" value="${globalid }"/>
	<!--头部信息-->
	<div class="head">
    	<div class="back"><a href="javascript:history.back(-1)"><img src="../image/back.png" width="100%" height="auto" /></a></div>
    	<div class="title">实时评价</div>
    </div>
    <!--头部结束-->
    
    <!--评价内容星星-->
    <div class="pjk">
	<div class="pjxx pj">
       <p>教学效果</p>
       <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
    <div class="pjxx pj1">
       <p>教学内容</p>
       <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
    </div>
    <!--评价内容星星结束-->
    <ul class="tChose">
        <li isOn='false'>效果很好1</li>
        <li isOn='false'>效果很好2</li>
        <li isOn='false'>效果很好3</li>
        <li isOn='false'>效果很好4</li>
    </ul>
    <!--评价内容-->
    	<div class="pjnr">
        	<div class="liuy">
            	<textarea name="" onKeyDown="textdown(event)"
    onKeyUp="textup()" onfocus="if(value=='请填写您的意见或建议内容'){value=''}"
    onblur="if (value ==''){value='请填写您的意见或建议内容'}">请填写您的意见或建议内容</textarea>
            </div>
            <div class="up_load">
                <div class="up_btn">
                	<input id="file" type="file" name="" value="" style="display:none;" />
                    <p id="tj" ontouchstart = "touchStart.call(this)" ontouchend="touchEnd.call(this)" ontouchmove="touchMove.call(this)">提交评价<span class="touch"></span></p>
                </div>
            </div>
        </div>
    <!--评价内容结束-->
	<!--评价成功遮罩-->
    <div class="f_mask" style="display:none;">
		<div class="sh_suc">
        	<img src="../image/pjcg.png" />
        </div>
    </div>	
<!-- 评价成功遮罩结束 -->

</div>
<script language="javascript" type="text/javascript" src="http://cdn.bootcss.com/jquery/1.7.2/jquery.min.js"></script> 
<script src="../js/public.js" type="text/javascript"></script>
<script>

$(function(){
    //点击弹出遮罩
    $("#tj").click(function(){
        $(".f_mask").fadeIn(300).delay(800).fadeOut(300);
        //拼接选中的评价
        var arr=[]
        $('[isOn=true]').each(function(index) {
            var crg=$(this).text();
            arr.push(crg);
            return arr
        })
        alert(arr.join(';'));
    }); 
    
    // 第一个评价
    $(".pj ul li").on("touchstart", function() {
        alert($(this).index()+1)
    });
    // 第二个评价
    $(".pj1 ul li").on("touchstart", function() {
        alert($(this).index()+1)
    });
	
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
</body>
</html>
