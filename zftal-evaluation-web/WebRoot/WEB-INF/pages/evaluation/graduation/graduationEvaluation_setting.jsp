<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="code" uri="/WEB-INF/code.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
      $(function() {
      	// 本科毕业评价设置保存
        $("#save").click(function() {
        
          if($.trim($("#kssj").val()).length == 0) {
            alert("评价开始时间（开始）不得为空，请重新输入！");
            return false;
          }
          if($.trim($("#jssj").val()).length == 0) {
            alert("评价开始时间（结束）不得为空，请重新输入！");
            return false;
          }
          
          $.post('<%=request.getContextPath() %>/evaluation/graduation_editSetting.html', $('#editform').serialize(), function(data){
            var callback = function(){
              location.href = "<%=request.getContextPath() %>/evaluation/graduation_getSetting.html";
            };
            processDataCall(data, callback);
          }, "json");

          return false;
        });
        
        // 专科毕业评价设置保存
        $("#save2").click(function() {
        
          if($.trim($("#kssj2").val()).length == 0) {
            alert("评价开始时间（开始）不得为空，请重新输入！");
            return false;
          }
          if($.trim($("#jssj2").val()).length == 0) {
            alert("评价开始时间（结束）不得为空，请重新输入！");
            return false;
          }
          
          $.post('<%=request.getContextPath() %>/evaluation/graduation_editSetting.html', $('#editform2').serialize(), function(data){
            var callback = function(){
              location.href = "<%=request.getContextPath() %>/evaluation/graduation_getSetting.html";
            };
            processDataCall(data, callback);
          }, "json");

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
                <span>本科毕业评价设置</span>
              </th>
            </tr>
          </thead>
          <tfoot>
            <tr>
              <td colspan="2">
                <div class="bz">"<span class="red">*</span>"为必填项</div>
                <div class="btn">
                  <button type="button" id="save">保 存</button>
                </div>
              </td>
            </tr>
          </tfoot>
          <tbody>
            <tr>
              <th>
                <span class="red">*</span>学制
              </th>
              <td>
              	<select id="xz" name="model.xz" >
              		<option value="4" <c:if test="${model.xz eq '4'}">selected="selected" </c:if>>4年制</option>
              	</select>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>评价时间
              </th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="model.kssj" id="kssj" value="${model.kssj }" style="width: 100px;" />
                &nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="model.jssj" id="jssj" value="${model.jssj }" style="width: 100px;" />
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </form>
    
    <form id="editform2">
      <div class="tab">
        <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
          <thead>
            <tr>
              <th colspan="2">
                <span>专科毕业评价设置</span>
              </th>
            </tr>
          </thead>
          <tfoot>
            <tr>
              <td colspan="2">
                <div class="bz">"<span class="red">*</span>"为必填项</div>
                <div class="btn">
                  <button type="button" id="save2">保 存</button>
                </div>
              </td>
            </tr>
          </tfoot>
          <tbody>
            <tr>
              <th>
                <span class="red">*</span>学制
              </th>
              <td>
              	<select id="xz2" name="model2.xz" >
              		<option value="3" <c:if test="${model2.xz eq '3'}">selected="selected" </c:if>>3年制</option>
              	</select>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>评价时间
              </th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="model2.kssj" id="kssj2" value="${model2.kssj }" style="width: 100px;" />
                &nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="model2.jssj" id="jssj2" value="${model2.jssj }" style="width: 100px;" />
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </form>
  </body>
</html>