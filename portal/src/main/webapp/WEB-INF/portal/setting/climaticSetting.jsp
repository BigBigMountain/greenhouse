<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/portal/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>setting - climatic</title>
<script type="text/javascript">
$(function(){
	 var msg = "${msg}";
		if(msg!=null && msg != ''){
			alert(msg);
		}
});

</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 气象设置 </div>
	<div class="clear"></div>
</div>

<div class="body-box">
<form action="/setting/saveOrUpdateClimaticSetting.do" method="post">
	<fieldset style="padding:10px;margin-left:10px;border:3px solid #7F9DB9;">
		<div style="float: left;padding:10px;">
			<input type="hidden" name="id" value="${climaticSetting.id }" />
			<input type="hidden" name="zoneId" value="${user.zoneId }" />
				网关IP:<input type="text" name="ip" value="${climaticSetting.ip }"/>
				端口号:<input type="text" name="port" value ="${climaticSetting.port }"/>
		</div>
		<div style="float: right">
			<input type="submit"  value="保存设置"/>
			<input type="button" onclick="addNode()" value="添加节点"/>
		</div>
	</fieldset>
	<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
		<thead class="pn-lthead">
			<tr>
				<th >ID</th>
				<th >网关节点地址</th>
				<th >通道一</th>
				<th >通道二</th>
				<th >通道三</th>
				<th >通道四</th>
			</tr>
		</thead>
		<tbody id="tbody" class="pn-ltbody"  align="center">
		<c:forEach items="${climaticSetting.nodes }" var="node" varStatus="status">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#96E85F'" onmouseout="this.bgColor='#ffffff'">
				<td align="center">
					<input type="hidden" name = "nodes[${status.index }].id" value="${node.id }" /><p>${node.id }</p>
				</td>
				<td align="center">
					<input type="text" name = "nodes[${status.index }].nodePosition" value="${node.nodePosition }" size="5" style="text-align: center;"/>
				</td>
				<td align="center">
					<select  name="nodes[${status.index }].channel1">
						<option value="">--请选择类型--</option>
						<c:forEach items="${climaticDataTypes }" var="type">
							<option value="${type.propertyName }" <c:if test="${node.channel1 == type.propertyName }">selected="selected"</c:if>>${type.name }</option>
						</c:forEach>
					</select>
				</td>
				<td align="center">
					<select name="nodes[${status.index }].channel2">
						<option value="">--请选择类型--</option>
						<c:forEach items="${climaticDataTypes }" var="type">
							<option value="${type.propertyName }" <c:if test="${node.channel2 == type.propertyName }">selected="selected"</c:if>>${type.name }</option>
						</c:forEach>
					</select>
				</td>
				<td align="center">
					<select name="nodes[${status.index }].channel3">
						<option value="">--请选择类型--</option>
						<c:forEach items="${climaticDataTypes }" var="type">
							<option value="${type.propertyName }" <c:if test="${node.channel3 == type.propertyName }">selected="selected"</c:if>>${type.name }</option>
						</c:forEach>
					</select>
				</td>
				<td align="center">
					<select  name="nodes[${status.index }].channel4">
						<option value="">--请选择类型--</option>
						<c:forEach items="${climaticDataTypes }" var="type">
							<option value="${type.propertyName }" <c:if test="${node.channel4 == type.propertyName }">selected="selected"</c:if>>${type.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</form>

</div>
<script type="text/javascript">
function addNode(){
	var tbody=$("#tbody"),
		index=$("#tbody tr").length;
//		index=tbody
	tbody.append(
			'<tr bgcolor="#ffffff" onmouseover="mouseover(this)" onmouseout="mouseout(this)" >'+
				'<td><input type="hidden" name = "nodes['+index+'].id" /><label>未保存</label></td>'+
				'<td><input type="text" name = "nodes['+index+'].nodePosition"  /></td>'+
				<c:forEach begin="1" end="4" step="1" var="i" >
				'<td><select name="nodes['+index+'].channel'+${i}+'" >'+
						'<option value="">--请选择类型--</option>'+
					<c:forEach items="${climaticDataTypes }" var="type">
						'<option value="${type.propertyName }" >${type.name }</option>'+
					</c:forEach>
				'</select></td>'+
				</c:forEach>
			'</tr>'
	);
}
/* onmouseover="mouseover(this)" mouseout="mouseout(this)"*/
function mouseover(i){
	
	i.bgColor='#eeeeee'
}
function mouseout(i){
	i.bgColor='#ffffff'
} 

</script>
</body>
</html>