<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
        $("#save_btn").click(function(){
            $.post("<%=request.getContextPath()%>/feedback/staff_saveStaff.html",
                $("#form_edit").serialize(),function(data){
                    if(data){
                    	closeWin();
                        window.location.href = window.location.href;
                    }else{
                        showWarning("保存失败");
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
                	<input name="staffEntity.id" type="hidden" value="${entity.id }"/>
                    <tr>
                        <th width="30%">
                            <span class="red">*</span>学生
                        </th>
                        <td>
                            <ct:selectPerson id="staffEntity.xh" name="staffEntity.xh" single="true" width="160px" value="${entity.xh}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="red">*</span>所属组
                        </th>
                        <td>
                            <ct:codePicker name="staffEntity.ssz" catalog="<%=ICodeConstants.DM_DEF_XXYSSZ %>" code="${entity.ssz }" width="160"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="red">*</span>是否组长
                        </th>
                        <td>
                            <select name="staffEntity.sfzz" style="width:160px;">
			                  <option value=""></option>
			                  <option value="0" <c:if test="${entity.sfzz eq '0'}">selected</c:if>>否</option>
			                  <option value="1" <c:if test="${entity.sfzz eq '1'}">selected</c:if>>是</option>
			                </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="red">*</span>学年
                        </th>
                        <td>
				              <ct:codePicker name="staffEntity.xn" catalog="<%=ICodeConstants.DM_DEF_XN %>" code="${entity.xn }" width="160"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="red">*</span>学期
                        </th>
                        <td>
                            <select name="staffEntity.xq" style="width:160px;">
			                  <option value="">全部</option>
			                  <option value="1" <c:if test="${entity.xq eq '1'}">selected</c:if>>第1学期</option>
			                  <option value="2" <c:if test="${entity.xq eq '2'}">selected</c:if>>第2学期</option>
			                </select>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>
</body>
</html>