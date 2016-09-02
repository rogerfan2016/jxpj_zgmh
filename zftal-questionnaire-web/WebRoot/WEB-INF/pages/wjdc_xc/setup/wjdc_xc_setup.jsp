<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini" %>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.ui.core.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.ui.all.css" type="text/css" media="all" />
    <script type="text/javascript">
    $(function() {
    	var key = '${config.key }';
    	
    	if(key == 'sql_append'){
    		document.getElementById("per").style.display="none";
			document.getElementById("sql").style.display="inline";
    	}else{
    		document.getElementById("sql").style.display="none";
			document.getElementById("per").style.display="inline";
		}
    });
    function groupView(val){
        var result = showTopWin( _path + "/inspection/config_" + val+".html?query.params['ywbm']=${config.ywbm}&query.params['ywjb']=${config.ywjb}&query.params['type']=${type}", 440, 470, null );
        if(!result){
            result=window.returnValue;
        }
        if( result == null ) return;
        var dl = $("#dl_"+val);
        var param="config.ywjb=${config.ywjb}&config.ywbm=${config.ywbm}&config.type=${type}&config.key="+val;
        for(var i=0;i<result.length;i++)
            param+="&valueList="+result[i][0];
        
        //alert(param);
        $.post("<%=request.getContextPath()%>/inspection/config_save.html",
            param,function(data){
                var callback = function(){
                	location.href="<%=request.getContextPath()%>/inspection/config_setup.html?ywjb=${config.ywjb}&ywbm=${config.ywbm}&type=${type}";
                };
                if(data.success){
                    processDataCall(data, callback);
                }else{
                    showWarning(data.text);
                }
                        
        },"json");
        return false;
    }
    function removeItem(val, param){
    	var p ="config.ywjb=${config.ywjb}&config.ywbm=${config.ywbm}&config.type=${type}&config.key="+param+"&config.value="+val;
    	 $.post("<%=request.getContextPath()%>/inspection/config_remove.html",
    	            p,function(data){
    	                if(data.success){
    	                	$("#"+param+"_"+val).closest("dd").remove();
    	                }else{
    	                    showWarning(data.text);
    	                }
    	                        
    	        },"json");
    }
    
    </script>
  </head>
  <body>
<div id="testID" >    
  <div class="tab">
    <table align="center" class="formlist">
    	
        <thead>
            <tr>
                <th colspan="4">
                    <span>评价基础设置<font color="#0f5dc2" style="font-weight:normal;"></font></span>
                </th>
            </tr>
        </thead>
        <tbody id="form1">
			<tr>
              <th><span class="red"></span>评价类型</th>
              <td>${config.type }
              	<input name="config.type" value="${config.type }" type="hidden"/>
              </td>
              <th><span class="red"></span>关系类型</th>
              <td>
              	<c:if test="${config.key eq 'personList' }">自定义人员配置</c:if>
              	<c:if test="${config.key eq 'sql_append' }">SQL脚本自动获取</c:if>
              	<input name="config.key" value="${config.key }" type="hidden"/>
              </td>
            </tr>
            <tr>
              <th><span class="red"></span>业务级别</th>
              <td>
              	<c:if test="${config.ywjb eq 'dept' }">学院</c:if>
              	<c:if test="${config.ywjb eq 'school' }">学校</c:if>
              	<input name="config.ywjb" value="${config.ywjb }" type="hidden"/>
              </td>
              <th><span class="red"></span>业务部门</th>
              <td>
              	<ct:codeParse catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${config.ywbm }" />
              	<input name="config.ywbm" value="${config.ywbm }" type="hidden"/>
              </td>
            </tr>
            <tr id='ALIAS_tr' class='tsdx_tr'>
                <th><span class="red"></span>评价模板	</th>
                <td colspan="3">
                     <div class="search_advanced" id="myTbody3">  
                       <div class="selected-attr after" style="float:none;min-height:30px;_height:30px">
                        <dl id ="dl_dcwjList">
                            <c:forEach items="${dcwjList}" var="p">
                            <dd> <a id="dcwjList_${p['WJID'] }" href="#"><h5>${p['WJMC'] }</h5>
                                <span class="close-icon" title="取消" onclick="removeItem('${p['WJID'] }','dcwjList')"></span></a>
                                <input type="hidden" value="${p['WJID'] }"></dd>
                            </c:forEach>
                        </dl>
                       </div>
                        <button onclick="groupView('dcwjList')" type="button">添加模板</button>
                    </div>
                </td>
            </tr>
            <c:if test="${config.key eq 'personList' }">
            <div id="per">
             <tr id='ALIAS_tr' class='tsdx_tr'>
                <th><span class="red">*</span>评价组成员</th>
                <td colspan=3">
                     <div class="search_advanced" id="myTbody3">  
                       <div class="selected-attr after" style="float:none;min-height:30px;_height:30px">
                        <dl id ="dl_personList">
                        <c:forEach items="${personList}" var="p">
                        <dd> <a id="personList_${p['GH'] }" href="#"><h5>${p['XM'] }</h5>
                                <span class="close-icon" title="取消" onclick="removeItem('${p['GH'] }','personList')"></span></a>
                                <input type="hidden" value="${p['GH'] }"></dd>
                        </c:forEach>
                        </dl>
                       </div>
                        <button onclick="groupView('personList')" type="button">添加人员</button>
                    </div>
                </td>
            </tr>
            </div>
          </c:if>
          <c:if test="${config.key eq 'sql_append' }">
            <tr>
            	<th><span class="red">*</span>评价人员SQL脚本</th>
				<td colspan="3">
					<textarea id="jxnr" name=config.append rows="3" disabled="disabled" style="width:100%;font-size:12px">${config.append }</textarea>
				</td>
			</tr>
			<tr>
            	<th><span class="red">*</span>评价对象SQL脚本</th>
				<td colspan="3">
					<textarea id="jxnr" name=config.value rows="3" disabled="disabled" style="width:100%;font-size:12px">${config.value }</textarea>
				</td>
			</tr>
		</c:if>
        </tbody>
    <c:if test="${config.key eq 'sql_append' }">    
    <tfoot>
      <tr>
        <td colspan="4">
            <div class="bz">"<span class="red">*</span>"为必填项</div>
            <!--<div class="btn">
                <button id="action" name="action" >提交保存</button>
            </div>-->
        </td>
      </tr>
    </tfoot>
    </c:if>
    </table>
    </div>
</div>
  </body>
</html>