<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta chaset="UTF-8">
<%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
<!-- Add bootstrap css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap/bootstrap.min.css">
<script src="<%=request.getContextPath() %>/bootstrap/js/modernizr.min.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/vendor/tabcomplete.min.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/vendor/livefilter.min.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/vendor/src/bootstrap-select.js"></script>
<!--<script src="<%=request.getContextPath() %>/bootstrap/js/vendor/src/filterlist.js"></script>-->
<script src="<%=request.getContextPath() %>/bootstrap/js/plugins.js"></script>
<title></title>
<script type="text/javascript">
    function doSubmit(){
    	//验证所选老师是否已选或重复选
    	var teacher = new Array($("#teacherOne").val(),$("#teacherTwo").val(),$("#teacherThree").val(),$("#teacherFour").val(), $("#teacherFive").val());
    	var t = teacher.join(",")+","; 
    	var teachers = "";
    	for ( var i = 0; i < teacher.length; i++) {
    		if(!teacher[i]  && (teacher[i] == null || teacher[i] == "")){
    			alertTipMsg("请选择第"+(i+1)+"位最喜欢的老师!");
    			return;  		
    		}
    		if(t.replace(teacher[i]+",","").indexOf(teacher[i]+",")>-1){ 
				alertTipMsg("所选择老师不能重复!");
				return;
			} 
			teachers = (teachers + teacher[i]) + (((i + 1)== teacher.length) ? "":","); 
		}
		//所选收获最大的课程
		var harvestLessons = new Array($("#harvest-lesson-one").val(),$("#harvest-lesson-two").val(),$("#harvest-lesson-three").val(),$("#harvest-lesson-four").val(), $("#harvest-lesson-five").val());
    	var hvl = harvestLessons.join(",")+","; 
    	var harvestLessonsArray = "";
    	for ( var i = 0; i < harvestLessons.length; i++) {
    		if(!harvestLessons[i]  && (harvestLessons[i] == null || harvestLessons[i] == "")){
    			alertTipMsg("请选择第"+(i+1)+"门收获最大的课程!");
    			return;  		
    		}
    		if(hvl.replace(harvestLessons[i]+",","").indexOf(harvestLessons[i]+",")>-1){ 
				alertTipMsg("所选择收获最大的课程不能重复!");
				return;
			}
			harvestLessonsArray = (harvestLessonsArray + harvestLessons[i]) + (((i + 1)== harvestLessons.length) ? "":",");  
		}
		//所选收获最难掌握的课程
		var hardLessons = new Array($("#hard-lesson-one").val(),$("#hard-lesson-two").val(),$("#hard-lesson-three").val(),$("#hard-lesson-four").val(), $("#hard-lesson-five").val());
    	var hl = hardLessons.join(",")+","; 
    	var hardLessonsArray = "";
    	for ( var i = 0; i < hardLessons.length; i++) {
    		if(!hardLessons[i]  && (hardLessons[i] == null || hardLessons[i] == "")){
    			alertTipMsg("请选择第"+(i+1)+"门最难掌握的课程!");
    			return;  		
    		}
    		if(hl.replace(hardLessons[i]+",","").indexOf(hardLessons[i]+",")>-1){ 
				alertTipMsg("所选择最难掌握的课程不能重复!");
				return;
			} 
			hardLessonsArray = (hardLessonsArray + hardLessons[i]) + (((i + 1)== hardLessons.length) ? "":",");  
		}
		var studyLevel = $("#studyLevel").val();
		if(!studyLevel && (studyLevel == null || studyLevel == "")){
			alertTipMsg("请选择对大学学习的满意程度!");
			return;
		}
		var majorLevel = $("#majorLevel").val();
		if(!majorLevel && (majorLevel == null || majorLevel == "")){
			alertTipMsg("请选择对大学专业的认可程度!");
			return;
		}
		var opinion = $("#opinion").val();
		var opinion1 = $("#opinion1").val();
		var opinion2 = $("#opinion2").val();
		var opinion3 = $("#opinion3").val();
		//if(!opinion && (opinion == null || opinion == "")){
		//	alertTipMsg("请输入对学校几点意见与建议!");
		//	return;
		//}
		removeTipMsg();
		//alert(harvestLessonsArray);
    	saveMyEvaluation($("#pjid").val(),teachers,harvestLessonsArray,hardLessonsArray,studyLevel,majorLevel,opinion,opinion1,opinion2,opinion3);
    }
    //提交评价
    function saveMyEvaluation(pjid,teachers,harvestLessons,hardLessons,studyLevel,majorLevel,opinion,opinion1,opinion2,opinion3){
    	$.post("<%=request.getContextPath()%>/evaluation/mg_saveMyEvaluation.html",
    			{pjid:pjid,teachers:teachers,harvestLessons:harvestLessons,hardLessons:hardLessons,study:studyLevel,major:majorLevel,opinion:opinion,opinion1:opinion1,opinion2:opinion2,opinion3:opinion3},
	            function(data) {
	              if(data.success){
	                location.href = "<%=request.getContextPath()%>/evaluation/mg_myEvaluation.html";
	              }else{
	                //showWarning(data.text);
	                alertTipMsg("系统异常");
	              }
	            },"json");
    }
    //弹出消息提醒
    function alertTipMsg(msg){
    	$("#alertMessage").html(msg);
    	$("#doSubmit").attr("data-am-modal","{target: '#my-alert'}");
    }
    //去除消息提醒
    function removeTipMsg(){
    	$("#alertMessage").html("");
		$("#doSubmit").removeAttr("data-am-modal");
    }
</script>

</head>
<body <c:if test="${massage != ''}">onload="alertTipMsg('${massage}')"</c:if>>
<script type="text/x-handlebars-template" id="amz-tpl">
    {{>header header}}
</script>

<c:if test="${model.zt != '1'}">
<div data-am-sticky="{animation: 'slide-bottom'}">
	<c:if test="${massage != ''}">
		<button class="am-btn am-btn-danger am-btn-block">${massage}</button>
	</c:if>
</div>
<form class="am-form" id="editform" >
	<input type="hidden" id="pjid" name="resultModel.pjid" value="${model.id }">
  			<div class="col-sm-1">
				<h3>1、<font color="red" size="5px"><b>*</b></font> 大学中我最喜欢的五位教师</h3>				
				<div class="row">
					<div class="col-sm-1">
						<div id="teacher1" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第一位老师=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-teacher1">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-teacher1" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${teacherList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.jsxm }" data-value="${info.jszgh }">${info.jsxm }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="teacherOne" name="teacher1" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<div id="teacher2" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第二位老师=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-teacher2">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-teacher2" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${teacherList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.jsxm }" data-value="${info.jszgh }">${info.jsxm }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="teacherTwo" name="teacher2" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<div id="teacher3" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第三位老师=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-teacher3">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-teacher3" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${teacherList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.jsxm }" data-value="${info.jszgh }">${info.jsxm }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="teacherThree" name="teacher3" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<div id="teacher4" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第四位老师=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-teacher4">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-teacher4" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${teacherList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.jsxm }" data-value="${info.jszgh }">${info.jsxm }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="teacherFour" name="teacher4" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-5">
						<div id="teacher5" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第五位老师=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-teacher5">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-teacher5" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${teacherList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.jsxm }" data-value="${info.jszgh }">${info.jsxm }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="teacherFive" name="teacher5" value="">
						</div>
					</div>
				</div>
			</div>		
      		<div class="col-sm-1">
				<h3>2、<font color="red" size="5px"><b>*</b></font> 大学中我收获最大的五门课程</h3>				
				<div class="row">
					<div class="col-sm-2">
						<div id="harvest-lesson-1" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第一门课程=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-harvest-lesson-1">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-harvest-lesson-1" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${lessonList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.kcmc }" data-value="${info.kcdm }">${info.kcmc }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="harvest-lesson-one" name="harvest-lesson-1" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<div id="harvest-lesson-2" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第二门课程=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-harvest-lesson-2">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-harvest-lesson-2" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${lessonList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.kcmc }" data-value="${info.kcdm }">${info.kcmc }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="harvest-lesson-two" name="harvest-lesson-2" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<div id="harvest-lesson-3" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第三门课程=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-harvest-lesson-3">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-harvest-lesson-3" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${lessonList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.kcmc }" data-value="${info.kcdm }">${info.kcmc }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="harvest-lesson-three" name="harvest-lesson-3" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<div id="harvest-lesson-4" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第四门课程=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-harvest-lesson-4">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-harvest-lesson-4" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${lessonList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.kcmc }" data-value="${info.kcdm }">${info.kcmc }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="harvest-lesson-four" name="harvest-lesson-4" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-5">
						<div id="harvest-lesson-5" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第五门课程=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-harvest-lesson-5">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-harvest-lesson-5" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${lessonList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.kcmc }" data-value="${info.kcdm }">${info.kcmc }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="harvest-lesson-five" name="harvest-lesson-5" value="">
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-1">
				<h3>3、<font color="red" size="5px"><b>*</b></font> 大学中我认为最难掌握的五门课程</h3>				
				<div class="row">
					<div class="col-sm-1">
						<div id="hard-lesson-1" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第一门课程=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-hard-lesson-1">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-hard-lesson-1" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${lessonList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.kcmc }" data-value="${info.kcdm }">${info.kcmc }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="hard-lesson-one" name="hard-lesson-1" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<div id="hard-lesson-2" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第二门课程=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-hard-lesson-2">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-hard-lesson-2" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${lessonList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.kcmc }" data-value="${info.kcdm }">${info.kcmc }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="hard-lesson-two" name="hard-lesson-2" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<div id="hard-lesson-3" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第三门课程=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-hard-lesson-3">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-hard-lesson-3" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${lessonList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.kcmc }" data-value="${info.kcdm }">${info.kcmc }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="hard-lesson-three" name="hard-lesson-3" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<div id="hard-lesson-4" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第四门课程=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-hard-lesson-4">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-hard-lesson-4" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${lessonList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.kcmc }" data-value="${info.kcdm }">${info.kcmc }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="hard-lesson-four" name="hard-lesson-4" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-5">
						<div id="hard-lesson-5" class="selectpicker" data-live="true">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
							<span class="placeholder">-=请选择第五门课程=-</span>
							<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<div class="live-filtering" data-clear="true" data-autocomplete="true" data-keys="true">
									<label class="sr-only" for="input-hard-lesson-5">在列表中搜索</label>
									<div class="search-box">
										<div class="input-group">
											<span class="input-group-addon" id="search-icon3">
											<span class="fa fa-search"></span>
											<a href="#" class="fa fa-times hide filter-clear"><span class="sr-only">清除过滤器</span></a>
											</span>
											<input type="text" placeholder="Search in the list" id="input-hard-lesson-5" class="form-control live-search" aria-describedby="search-icon3" tabindex="1" />
										</div>
									</div>
									<div class="list-to-filter">
										<ul class="list-unstyled">
											<c:forEach items="${lessonList}" var="info" varStatus="st">
												<li class="filter-item items" data-filter="${info.kcmc }" data-value="${info.kcdm }">${info.kcmc }</li>
											</c:forEach>
										</ul>
										<div class="no-search-results">
											<div class="alert alert-warning" role="alert"><i class="fa fa-warning margin-right-sm"></i>没有搜索到 <strong>"<span></span>"</strong>结果！</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="hard-lesson-five" name="hard-lesson-5" value="">
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-1">
		      	<h3>4、<font color="red" size="5px"><b>*</b></font> 我对大学学习的满意程度 </h3>
		      	<div class="row">
					<div class="col-sm-8">
						<div id="study" class="selectpicker">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
								<span class="placeholder">-=请选择一项=-</span>
								<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<ul class="list-unstyled">
									<li class="items" data-value="非常满意">非常满意</li>
									<li class="items" data-value="满意">满意</li>
									<li class="items" data-value="一般">一般</li>
									<li class="items" data-value="不满意">不满意</li>
									<li class="items" data-value="非常不满意">非常不满意</li>
								</ul>
							</div>
							<input type="hidden" id="studyLevel" name="study" value="">
						</div>
					</div>
				</div>
		    </div>
		    <div class="col-sm-1">
		      	<h3>5、<font color="red" size="5px"><b>*</b></font> 我对专业的认可程度</h3>
		      <div class="row">
					<div class="col-sm-8">
						<div id="major" class="selectpicker">
							<button data-id="prov" type="button" class="btn btn-lg btn-block btn-default dropdown-toggle">
								<span class="placeholder">-=请选择一项=-</span>
								<span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<ul class="list-unstyled">
									<li class="items" data-value="非常认可">非常认可</li>
									<li class="items" data-value="认可">认可</li>
									<li class="items" data-value="一般认可">一般认可</li>
									<li class="items" data-value="不认可">不认可</li>
									<li class="items" data-value="非常不认可">非常不认可</li>
								</ul>
							</div>
							<input type="hidden" id="majorLevel" name="major" value="">
						</div>
					</div>
				</div>
		    </div>
			<div class="col-sm-1">
		      <h3>6、请写出你想对母校说的话</h3>
		      <textarea id="opinion" name="resultModel.opinion" minlength="10" rows="4" maxlength="500"></textarea>
		    </div>
		    <div class="col-sm-1">
		      <h3>7、请写出你想对学院说的话</h3>
		      <textarea id="opinion1" name="resultModel.opinion1" minlength="10" rows="4" maxlength="500"></textarea>
		    </div>
		    <div class="col-sm-1">
		      <h3>8、请写出你想对专业说的话</h3>
		      <textarea id="opinion2" name="resultModel.opinion2" minlength="10" rows="4" maxlength="500"></textarea>
		    </div>
		    <div class="col-sm-1">
		      <h3>9、请写出你想对教师说的话</h3>
		      <textarea id="opinion3" name="resultModel.opinion3" minlength="10" rows="4" maxlength="500"></textarea>
		    </div>
			</br>
    		<div data-am-sticky="{animation: 'slide-bottom'}"></div>
</form>
<c:if test="${massage == ''}">
	<button onclick="doSubmit()" id="doSubmit" class="am-btn am-btn-warning am-btn-block">提交评价</button>
</c:if>
   
<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
  <div class="am-modal-dialog">
    <!-- <div class="am-modal-hd">Amaze UI</div> -->
    <div class="am-modal-bd" id="alertMessage"></div>
    <div class="am-modal-footer">
      <span class="am-modal-btn">确定</span>
    </div>
  </div>
</div>
</c:if>	 

<c:if test="${model.zt eq '1'}">
	<div data-am-sticky="{animation: 'slide-bottom'}">
		<c:if test="${massage != ''}">
		<button class="am-btn am-btn-success am-btn-block">您已经提交毕业前评价！</button>
		</c:if>
	</div>
	<div data-am-widget="list_news" class="am-list-news am-list-news-default" >
        <div class="am-list-news-bd">
            <ul class="am-list">
                <li class="am-g am-list-item-dated">
					<h3>1、大学中我最喜欢的五位教师</h3>
					<h4>答案：${teachers }</h4>
				</li>
				<li class="am-g am-list-item-dated">
					<h3>2、大学中我收获最大的五门课程</h3>
					<h4>答案：${harvestLessons }</h4>
				</li>
				<li class="am-g am-list-item-dated">
					<h3>3、大学中我认为最难掌握的五门课程</h3>
					<h4>答案：${hardLessons }</h4>
				</li>
				<li class="am-g am-list-item-dated">
					<h3>4、我对大学学习的满意程度</h3>
					<h4>答案：${study }</h4>
				</li>
				<li class="am-g am-list-item-dated">
					<h3>5、我对专业的认可程度</h3>
					<h4>答案：${major }</h4>
				</li>
				<li class="am-g am-list-item-dated">
					<h3>6、请写出你想对母校说的话</h3>
					<h4>答案：${opinion }</h4>
				</li>
				<li class="am-g am-list-item-dated">
					<h3>7、请写出你想对学院说的话</h3>
					<h4>答案：${opinion1 }</h4>
				</li>
				<li class="am-g am-list-item-dated">
					<h3>8、请写出你想对专业说的话</h3>
					<h4>答案：${opinion2 }</h4>
				</li>
				<li class="am-g am-list-item-dated">
					<h3>9、请写出你想对教师说的话</h3>
					<h4>答案：${opinion3 }</h4>
				</li>
			</ul>
		</div>
	</div>
</c:if>	

<script type="text/javascript"> 

    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);
    var leftc = [];
    var obj = {};
    obj.link = "javascript:history.go(-1);";
    obj.icon = "chevron-left";
    leftc.push(obj);
    
    data.header.content.title = "毕业前评价";
    data.header.content.left = leftc;
    var html = template(data);
    $tpl.before(html);
    
    
</script>
 <%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>