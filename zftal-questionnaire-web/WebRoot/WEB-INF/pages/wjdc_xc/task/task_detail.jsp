<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <style>
        .ui-autocomplete{
            z-index:12001;
            width: 500px
        }
    </style>
    <script type="text/javascript">
    $(function(){

    	var mList=$(".mList");
        for(var i=0;i<mList.length;i++){
            $("#person_"+$(mList[i]).val()).attr("checked","checked");
            $("#person_"+$(mList[i]).val()).closest("p").attr("style","border: #ff8040 1px solid;background: white;line-height: 19px");
        }
        $("select[name='inspectionTask.wjid']").val("${inspectionTask.wjid}");
        $("#save_btn").click(function(){
            
            if(!check()){
                 return false;
            }
            var param=$("#form_edit").serialize();
            $.post("<%=request.getContextPath()%>/inspection/task_save.html",
                $("#form_edit").serialize(),function(data){
                    var callback = function(){
                        reload();
                    };
                    if(data.success){
                        processDataCall(data, callback);
                    }else{
                        showWarning(data.text);
                    }
                            
            },"json");
            return false;
        });
        
        $("#cancel").click(function(){
            divClose();
        });

        $("dd").click(function(e){
            if(!$(this).find("input:checkbox").is(":checked")){
                $(this).find("input:checkbox").attr("checked","checked");
                $(this).find("p").attr("style","border: #ff8040 1px solid;background: white;line-height: 19px");
            }else{
                $(this).find("input:checkbox").removeAttr("checked");
                $(this).find("p").attr("style","line-height: 19px");
            }
        });
        $(".personList").click(function(e){
            $(this).closest("dd").click();
        });
    });

    function check()
    {
    	if($("input[name='inspectionTask.taskDate']").length>0){
	    	var taskDate = $("input[name='inspectionTask.taskDate']").val();
	        var s = taskDate.split('-');
	        if(s.length<3){
	            alert("任务时间不能为空");
	            return false;
	        }
    	}
    	
    	if($("input[name='inspectionTask.rwmc']").val() == ''){
	        alert("任务名称不能为空");
	        return false;
    	}
    	
        $(".personList").removeAttr("name");
        var check=$(".personList:checked");
        for(var i=0;i<check.length;i++){
        	$(check[i]).attr("name","inspectionTask.memberList["+i+"].gh");
        }
        return true;
    }
    
    function remove(num){
        $("#day"+num).remove();
    }
    </script>
</head>
<body>
    <form id="form_edit">
        <div class="tab">
            <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
                <thead>
                    <tr>
                        <th colspan="2">
                            <span><font color="#0f5dc2" style="font-weight:normal;">评价任务信息</font></span>
                            <input type="hidden" name="inspectionTask.id" value="${inspectionTask.id }" />
                            <input type="hidden" name="inspectionTask.configType" value="${inspectionTask.configType }" />
                            <input type="hidden" name="inspectionTask.rwjb" value="${inspectionTask.rwjb }" />
                            <input type="hidden" name="inspectionTask.rwbm" value="${inspectionTask.rwbm }" />
                        </th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="2">
                            <div class="bz">"<span class="red">*</span>"为必填项</div>
                            <div class="btn">
                                <button id="save_btn" type="button">保 存</button>
                                <button id="cancel" type="button" onclick="divClose();">取 消</button>
                            </div>
                        </td>
                    </tr>
                </tfoot>
                <tbody>
                    <tr>
                        <th>
                            <span class="red">*</span>任务名称
                        </th>
                        <td>
                            <input type="text" name="inspectionTask.rwmc" value="${inspectionTask.rwmc }" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="red"></span>任务时间
                        </th>
                        <td><c:if test="${inspectionTask.id==null}">
                            <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="inspectionTask.taskDate"
                            value="${inspectionTask.taskDateStr }"/>
                            </c:if>
                            <c:if test="${inspectionTask.id!=null}">
                            ${inspectionTask.taskDateStr }
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="red"></span>评价对象类型
                        </th>
                        <td><select name="inspectionTask.pjdxlx">
                            	<option value="teacher" <c:if test="${inspectionTask.pjdxlx eq 'teacher' }">selected</c:if>>教师</option>
                            	<option value="lesson" <c:if test="${inspectionTask.pjdxlx eq 'lesson' }">selected</c:if>>课程</option>
                            </select>
                        </td>
                    </tr>
                    <c:if test="${inspectionTask.id != '' && inspectionTask.id != null }">
                    <tr>
                        <th>
                            <span class="red"></span>评价对象
                        </th>
                        <td>
                            ${inspectionTask.pjdx }
                        </td>
                    </tr>
                    </c:if>
                    <tr>
                        <th>
                            <span class="red"></span>评价问卷
                        </th>
                        <td>
                            <select name="inspectionTask.wjid">
                            	<option value="">按课程分类自动获取</option>
                                <c:forEach items="${dcwjList}" var="wj">
                                <option value="${wj['WJID'] }">${wj['WJMC'] }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                <c:if test="${config.key eq 'personList'}">
                    <tr id='ALIAS_tr' class='tsdx_tr'>
		                <th>评价人员安排<span class="red">*</span></th>
		                <td></td>
		            </tr>
		            <tr>
		                <td colspan="2">
		                <div class="search_advanced" id="myTbody3"> 
		                <c:if test="${config.key eq 'personList'}">
		                       <div class="selected-attr after" style="float:none;min-height:30px;_height:30px">
		                        <dl id ="dl_personList">
		                            <c:forEach items="${personList}" var="p">
		                                <dd><p style="line-height: 19px" ><input class="personList" type="checkbox" id="person_${p['GH'] }" value="${p['GH'] }" />
		                                ${p['XM'] }(${p['GH'] })</p></dd>
		                            </c:forEach>
		                        </dl>
		                            <c:forEach items="${inspectionTask.memberList}" var="member">
		                                <input class="mList" type="hidden" value="${member.gh }" />
		                            </c:forEach>
		                       </div>
		               	</c:if>
		               	</div>
		                </td>
                	</tr>
                </c:if>
                <c:if test="${config.key eq 'sql_append'}">
                	<tr id='ALIAS_tr' class='tsdx_tr'>
		                <th>评价人员安排<span class="red">*</span></th>
		                <td>按人员关系自动抓取考评人和被考评对象</td>
		            </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </form>
</body>
</html>