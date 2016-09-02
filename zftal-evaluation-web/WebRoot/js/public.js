// JavaScript Document
function touchStart(){
	$(this).children("span").show();
	}
function touchEnd(){
	$(this).children("span").hide();
	}
function touchMove(){
	$(this).children("span").hide();
	}
//选择星星
    $(".pjxx ul li").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -2rem");
    });
    $(".pjxx ul li:eq(1)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -4rem");
    });
    $(".pjxx ul li:eq(2)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -6.04rem");
    });
    $(".pjxx ul li:eq(3)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -8.04rem");
    });
    $(".pjxx ul li:eq(4)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -10.06rem");
    });
    //选择星星
    $(".pj1 ul li:eq(0)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -2rem");
    });
    $(".pj1 ul li:eq(1)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -4rem");
    });
    $(".pj1 ul li:eq(2)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -6.04rem");
    });
    $(".pj1 ul li:eq(3)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -8.04rem");
    });
    $(".pj1 ul li:eq(4)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -10.06rem");
    });
    //选择星星
    $(".pj2 ul li:eq(0)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -2rem");
    });
    $(".pj2 ul li:eq(1)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -4rem");
    });
    $(".pj2 ul li:eq(2)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -6.04rem");
    });
    $(".pj2 ul li:eq(3)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -8.04rem");
    });
    $(".pj2 ul li:eq(4)").on("touchstart", function() {
        $(this).parent('ul').css("backgroundPosition","0 -10.06rem");
    });