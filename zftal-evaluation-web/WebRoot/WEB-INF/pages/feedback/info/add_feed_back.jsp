<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>

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
    	$("#xxlx").change(function(){
    		if($(this).val() == "1"){
    			$("#xxnrlx").val("2");
    		}
    	});
        $("#save_btn").click(function(){
        	if($.trim($("#xxfl").val()).length == 0){
        		alert("请选择信息分类!");
        		return false;
        	}
       		if($.trim($("#xxlx").val()).length == 0){
        		alert("请选择信息类型!");
        		return false;
        	}
        	if($.trim($("#xxnrlx").val()).length == 0){
        		alert("请选择信息内容分类");
        		return false;
        	}
        	if($.trim($("#xxnr").val()).length == 0){
        		alert("请输入信息内容!");
        		return false;
        	}
        
            $.post("<%=request.getContextPath()%>/feedback/info_saveFeedbackInfo.html",
                $("#form_edit").serialize(),function(data){
                    if(data){
                    	closeWin();
                        window.location.href = "<%=request.getContextPath()%>/feedback/info_todayCkList.html";
                    }else{
                        showWarning("反馈信息失败");
                    }
                            
            },"json");
            return false;
        });
    });

    function closeWin(){
        $(".ymPrompt_close").click();
     }
    </script>
</head>
<body>
    <form id="form_edit">
        <div class="tab">
            <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
                <tfoot>
                    <tr>
                        <td colspan="4">
                            <div class="bz">"<span class="red">*</span>"为必填项</div>
                            <div class="btn">
                                <button id="save_btn">保 存</button>
                                <button id="cancel" type="button" onclick="closeWin()">取 消</button>
                            </div>
                        </td>
                    </tr>
                </tfoot>
                <tbody>
                	<input name="kcid" type="hidden" value="${kcid}"/>
                	<input name="globalid" type="hidden" value="${globalid}"/>
                    <tr>
                        <th width="30%">
                            <span class="red">*</span>信息分类
                        </th>
                        <td>
                            <select id="xxfl" name="model.xxfl" style="width:160px;">
                            	<option value=""></option>
                            	<option value="0">学生类</option>
                            	<option value="1">课程类</option>
                            	<option value="2">教师类</option>
                            	<option value="3">教学环境保障类</option>
                            </select>
                        </td>
                        <th width="30%">
                            <span class="red">*</span>信息类型
                        </th>
                        <td>
                            <select id="xxlx" name="model.xxlx" style="width:160px;">
                            	<option value=""></option>
                            	<option value="0">普通</option>
                            	<option value="1">紧急</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="red">*</span>信息内容类型
                        </th>
                        <td colspan="3">
                            <select id="xxnrlx" name="model.xxnrlx" style="width:160px;">
                            	<option value=""></option>
                            	<option value="0">表扬</option>
                            	<option value="1">意见/建议</option>
                            	<option value="2">紧急事件</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="red">*</span>信息内容
                        </th>
                        <td colspan="3">
                        	<textarea name="model.xxnr" id="xxnr" style="width:400px;height:100px;"></textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>
</body>
</html>