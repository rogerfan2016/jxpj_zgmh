<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <!-- IChart单文件引入 -->
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/ichart/ichart.1.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    
    <script type="text/javascript">
      function doSearch() {
        // 查询
        	if($.trim($("input[name='query.nj']").val()).length == 0) {
	            alert("请选择毕业年份！");
	            return false;
	        }
          	$("#search").submit();
      }
    </script>
    
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/graduation_summary.html" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>毕业年份：</th>
              <td>
              <!-- 
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy'})" class="Wdate" name="query.nj" id="year" value="${query.nj}" style="width: 70px;" />
               -->
              	<input type="text" disabled="disabled" name="nj" value="${query.nj}" style="width: 70px;"/>
              	<input type="hidden" name="query.nj" value="${query.nj}"/>
              </td>
              <th>所在学院</th>
              <td>
                <ct:codePicker name="query.xy" catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${query.xy }" width="100"/>
              </td>
              <th>所学专业</th>
              <td>
                <ct:codePicker name="query.zymc" catalog="<%=ICodeConstants.DM_XB_ZYDMB %>" code="${query.zymc }" width="100"/>
              </td>
              <th>评价指标：</th>
              <td>
                <select onchange="doSearch()" name="query.zbx">
                  	<!--<c:forEach items="${indexEnums }" var="item">
						<option value="${item.key }" ${query.zbx==item.key?"selected=\"selected\"":"" }>${item.text }</option>
					</c:forEach>-->
                	<option value="teacher" ${query.zbx=='teacher'?"selected=\"selected\"":"" }>喜欢的教师</option>
                	<option value="harvestLesson" ${query.zbx=='harvestLesson'?"selected=\"selected\"":"" }>收获最大课程</option>
                	<option value="hardLesson" ${query.zbx=='hardLesson'?"selected=\"selected\"":"" }>最难的课程</option>
                	<option value="major" ${query.zbx=='major'?"selected=\"selected\"":"" }>专业认可度</option>
                	<option value="study" ${query.zbx=='study'?"selected=\"selected\"":"" }>学习满意度</option>
                </select>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
            <span>毕业前评价统计查询</span>
        </h3>
        <!--标题end-->
        <!-- 为IChart准备一个具备大小（宽高）的Dom -->
		<div id="canvasDiv"></div>
		<div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>评价结果</td>
                <td>评价数量</td>
                <td>评价总数</td>
                <td>占比(评价数量/评价总数)</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${summaryResult}" var="info" varStatus="st">
              <tr name="tr">
                <td>${info['DA'] }</td>
                <td>${info['PJS'] }</td>
                <td>${info['ZS'] }</td>
                <td>${info['ZB'] }%</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </form>
    <script type="text/javascript">
    	$(function(){
			var data = [${data}];
			var chart = new iChart.Pie3D({
				render : 'canvasDiv',
				data: data,
				title : {
					text : '${titleText}',
					height:40,
					fontsize : 30,
					color : '#282828'
				},
				footnote : {
					text : '',
					color : '#486c8f',
					fontsize : 12,
					padding : '0 38'
				},
				sub_option : {
					mini_label_threshold_angle : 40,//迷你label的阀值,单位:角度
					mini_label:{//迷你label配置项
						fontsize:20,
						fontweight:600,
						color : '#ffffff'
					},
					label : {
						background_color:null,
						sign:false,//设置禁用label的小图标
						padding:'0 4',
						border:{
							enable:false,
							color:'#666666'
						},
						fontsize:11,
						fontweight:600,
						color : '#4572a7'
					},
					border : {
						width : 2,
						color : '#ffffff'
					},
					listeners:{
						parseText:function(d, t){
							return d.get('value')+"%";//自定义label文本
						}
					} 
				},
				legend:{
					enable:true,
					padding:0,
					offsetx:120,
					offsety:50,
					color:'#3e576f',
					fontsize:20,//文本大小
					sign_size:20,//小图标大小
					line_height:28,//设置行高
					sign_space:10,//小图标与文本间距
					border:false,
					align:'left',
					background_color : null//透明背景
				},
				animation : true,//开启过渡动画
				animation_duration:800,//800ms完成动画 
				shadow : true,
				shadow_blur : 6,
				shadow_color : '#aaaaaa',
				shadow_offsetx : 0,
				shadow_offsety : 0,
				background_color:'#f1f1f1',
				align:'right',//右对齐
				offsetx:-100,//设置向x轴负方向偏移位置60px
				offset_angle:-90,//逆时针偏移120度
				width : 800,
				height : 400,
				radius:150
			});
			//利用自定义组件构造右侧说明文本
			chart.plugin(new iChart.Custom({
					drawFn:function(){
						//在右侧的位置，渲染说明文字
						chart.target.textAlign('start')
						.textBaseline('top')
						.textFont('600 20px Verdana')
						.fillText('${fillText}',120,80,false,'#be5985',false,24)
						.textFont('600 12px Verdana')
						.fillText('${fillText2}',120,160,false,'#999999');
					}
			}));
			
			chart.draw();
		});
	</script>
  </body>
</html>