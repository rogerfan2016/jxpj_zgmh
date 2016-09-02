<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="code" uri="/WEB-INF/code.tld" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/feedback/xxygl.js"></script>
    <script type="text/javascript">
      $(function() {
        // 查询
        $("#search_btn").click(function() {
          $("#search").submit();
        });
        
        fillRows("20", "", "", false);//填充空行
        
        bdan();  //toolbar function
      });
    </script>
  </head>
  <body>
	<div class="toolbox">
		<!-- 加载当前菜单栏目下操作     -->
		<div class="buttonbox">
			<ul id="but_ancd">
				<li>
					<a href="javascript:void(0);" id="btn_tj" class="btn_zj">添加 </a>
				</li>
				<li>
					<a href="javascript:void(0);" id="btn_dr" class="btn_zj">导入 </a>
				</li>
				<li>
					<a href="javascript:void(0);" id="btn_xg" class="btn_xg">修改 </a>
				</li>
				<li>
					<a href="javascript:void(0);" id="btn_sc" class="btn_sc">删除 </a>
				</li>
				<li>
					<a href="javascript:void(0);" id="btn_mb" class="btn_ck">模板下载</a>
				</li>
			</ul>
		</div>
	</div>
    <form action="<%=request.getContextPath()%>/feedback/staff_staffSearchList.html" name="search" id="search" method="post">
      <input name="phase" type="hidden" value="${phase }"/>
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学号</th>
              <td>
                <input type="text" class="text_nor" name="staffQuery.xh" id="xh" value="${staffQuery.xh}" style="width:100px;" />
              </td>
              <th>姓名</th>
              <td>
                <input type="text" class="text_nor" name="staffQuery.xm" id="xm" value="${staffQuery.xm}" style="width:100px;" />
              </td>
              <th>学院</th>
              <td>
              	<ct:codePicker name="staffQuery.xy" catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${staffQuery.xy }" width="100"/>
             </td>
             <th>所属组</th>
              <td>
              		<ct:codePicker name="staffQuery.ssz" catalog="<%=ICodeConstants.DM_DEF_XXYSSZ %>" code="${staffQuery.ssz }" width="100"/>
              </td>
            </tr>
            <tr>
              <th>是否组长</th>
              <td>
                <select name="staffQuery.sfzz" style="width:100px;">
                  <option value="">全部</option>
                  <option value="0" <c:if test="${staffQuery.sfzz eq '0'}">selected</c:if>>否</option>
                  <option value="1" <c:if test="${staffQuery.sfzz eq '1'}">selected</c:if>>是</option>
                </select>
              </td>
              <th>学年</th>
              <td>
                <ct:codePicker name="staffQuery.xn" catalog="<%=ICodeConstants.DM_DEF_XN %>" code="${staffQuery.xn }" width="100"/>
              </td>
              <th>学	期</th>
              <td>
                <select name="staffQuery.xq" style="width:100px;">
                  <option value="">全部</option>
                  <option value="1" <c:if test="${staffQuery.xq eq '1'}">selected</c:if>>第1学期</option>
                  <option value="2" <c:if test="${staffQuery.xq eq '2'}">selected</c:if>>第2学期</option>
                </select>
              </td>
            </tr>
          </tbody>
          <tfoot>
            <td colspan="12">
              <div class="btn">
                <button class="brn_cx" type="button" id="search_btn" >查询</button>
              </div>
            </td>
          </tfoot>
        </table>
      </div>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>信息员列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
              	<td width="2%" style="text-align:center;"><input id="selectAll" name="selectAll" type="checkbox"/></td>
              	<td width="2%" style="text-align:center;">序号</td>
                <td width="10%" style="text-align:center;">学号</td>
                <td width="10%" style="text-align:center;">姓名</td>
                <td width="5%" style="text-align:center;">性别</td>
                <td width="10%" style="text-align:center;">行政班</td>
                <td width="15%" style="text-align:center;">学院</td>
                <td width="15%" style="text-align:center;">专业</td>
                <td width="10%" style="text-align:center;">所属组</td>
                <td width="5%" style="text-align:center;">是否组长</td>
                <td width="5%" style="text-align:center;">学年</td>
                <td width="5%" style="text-align:center;">学期</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${staffPageList}" var="info" varStatus="st">
              <tr name="tr" align="center"">
              	<td><input name="id" type="checkbox" value="${info.id }"/></td>
              	<td>${st.index + 1 }</td>
              	<td>${info.xh }</td>
                <td>${info.xm }</td>
                <td>${info.xb }</td>
                <td>${info.xzb }</td>
                <td>${info.xy }</td>
                <td>${info.zymc }</td>
              	<td>${info.ssz }</td>
              	<td>
                  <c:if test="${info.sfzz eq '0' }">否</c:if>
                  <c:if test="${info.sfzz eq '1' }">是</c:if>
                </td>
                <td>${info.xn }</td>
              	<td>
              		<c:if test="${info.xq eq '1'}">第1学期</c:if>
              		<c:if test="${info.xq eq '2'}">第2学期</c:if>
              	</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${staffPageList }" query="${staffQuery }" queryName="staffQuery" />
      </div>
    </form>
  </body>
</html>