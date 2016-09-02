<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ct" uri="/custom-code"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <script type="text/javascript">
      $(function() {
        $("#save").click(function() {
          $.post('<%=request.getContextPath() %>/basedata/basedata_save.html', $('#editform').serialize(), function(data){
            if(data.success) {
              refreshList();
            } else {
              alert(data);
            }
          }, "json");

          return false;
        });
        
        $("#cancel").click(function(){
          divClose();
          return false;
        });
      });
    </script>
  </head>

  <body>
    <form id="editform">
      <div class="tab">
        <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
          <thead>
            <tr>
              <th colspan="2">
                <span>修改课程分类</span>
              </th>
            </tr>
          </thead>
          <tfoot>
            <tr>
              <td colspan="2">
                <div class="bz">"<span class="red">*</span>"为必填项</div>
                <div class="btn">
                  <button type="button" id="save">保 存</button>
                  <button type="button" id="cancel">取 消</button>
                </div>
              </td>
            </tr>
          </tfoot>
          <tbody>
            <tr>
            	<th>
                <span class="red"></span>课程代码
              </th>
              <td>
              	${calssType.kcdm }
                <input name="calssType.kcdm" value="${calssType.kcdm }" type="hidden" />
              </td>
           	</tr>
            <tr>
              <th>
                <span class="red"></span>课程名称
              </th>
              <td>
              	${calssType.kczwmc }
              </td>
            </tr>
            
            <tr>
              <th>
                <span class="red">*</span>课程分类一
              </th>
              <td>
                <select name="calssType.fldm" style="width: 200px">
                	<option value="">请选择</option>
                  <c:forEach items="${typeList}" var="d">
                  <option value="${d.guid }" >${d.description }</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red"></span>课程分类二
              </th>
              <td>
                <select name="calssType.fldm2" style="width: 200px">
                	<option value="">请选择</option>
                  <c:forEach items="${typeList}" var="d">
                  <option value="${d.guid }" >${d.description }</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>三级单位
              </th>
              <td>
                <select name="calssType.sjdw" style="width: 200px">
                	<option value="">请选择</option>
                  	<c:forEach items="${orgList}" var="d">
                  	<option value="${d.guid }" >${d.description }</option>
                  	</c:forEach>
                </select>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </form>
  </body>
</html>