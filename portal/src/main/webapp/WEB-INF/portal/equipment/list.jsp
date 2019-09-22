<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/portal/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>equipment-list</title>
<style>
#houseId {
		height: 30px;
		font-size: 15px;
	}
.left3{float:left;width:60px;background: #FF910C;font-weight:bold;} 
.center3{float:center;width:60px;background: lightblue;font-weight:bold;} 
.right3{float:right;width:60px;background: yellow;font-weight:bold;} 
.left2{float:left;width:80px;background: #FF910C;font-weight:bold;} 
.right2{float:right;width:80px;background: yellow;font-weight:bold;} 
</style>
<script type="text/javascript">
	function setState(equipmentId,equipmentState){
	//	alert(equipmentId);
		$.ajax({
			url:"/equipment/changeState.do",
			type:"post",
			data:{"id":equipmentId,"state":equipmentState}
		})
	//	alert(equipmentState);
		var houseId = $("#houseId").val();
		window.location.href="/equipment/listAll.do?houseId="+houseId;
	} 
	function changeHouse(){
		var houseId = $("#houseId").val();
		window.location.href="/equipment/listAll.do?houseId="+houseId;
	}
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 设备管理  - 列表</div>
	<div style="float: left;margin-left:60px;font-size: 15px">
		温室编号:
		<select id="houseId" name="houseId" onchange="changeHouse()"> 
			<c:forEach items="${houses }" var="house">
				<option value="${house.houseId }" <c:if test="${houseId == house.houseId }">selected="selected"</c:if>>${house.houseName }</option>
			</c:forEach>
		</select>
	</div>
	<div class="clear"></div>
</div>
<div class="body-box">
	
<form id="jvForm">
<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th>设备ID</th>
			<th>设备编号</th>
			<th>设备名称</th>
			<th width="180px">设备状态</th>
			<th width="140px">操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	<c:forEach items="${equipments }" var="equipment">
		<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" name="ids" value="${equipment.id }"/></td>
			<td>${equipment.id }</td>
			<td align="center">${equipment.equipmentCode }</td>
			<td align="center">${equipment.equipmentName }</td>
			<td align="center">
				<c:if test="${fn:contains(equipment.equipmentCode, 'A')}">
					<c:if test="${equipment.state==1 }"><div class="left3">  展开</div></c:if>
					<c:if test="${equipment.state==2 }"><div class="center3">合拢</div></c:if>
					<c:if test="${equipment.state==4 }"><div class="right3"> 停止</div></c:if>
				</c:if>
				<c:if test="${fn:contains(equipment.equipmentCode, 'B')}">
					<c:if test="${equipment.state==1 }"><div class="left3">  打开</div></c:if>
					<c:if test="${equipment.state==2 }"><div class="center3">关闭</div></c:if>
					<c:if test="${equipment.state==4 }"><div class="right3"> 停止</div></c:if>
				</c:if>
				<c:if test="${fn:contains(equipment.equipmentCode, 'C')}">
					<c:if test="${equipment.state==1 }"><div class="left2"> 打开</div></c:if>
					<c:if test="${equipment.state==0 }"><div class="right2">关闭</div></c:if>
				</c:if>
			</td>
			<td align="center">
				<c:if test="${fn:contains(equipment.equipmentCode, 'A')}">
					<input type="button" value="展开" onclick="setState(${equipment.id},1)"/>||<input type="button" value="合拢" onclick="setState(${equipment.id},2)"/>||<input type="button" value="停止" onclick="setState(${equipment.id},4)"/>
				</c:if>
				<c:if test="${fn:contains(equipment.equipmentCode, 'B')}">
					<input type="button" value="打开" onclick="setState(${equipment.id},1)"/>||<input type="button" value="关闭" onclick="setState(${equipment.id},2)"/>||<input type="button" value="停止" onclick="setState(${equipment.id},4)"/>
				</c:if>
				<c:if test="${fn:contains(equipment.equipmentCode, 'C')}">
					<!-- <a href="#" class="pn-opt">打开</a> | <a href="#" class="pn-opt">关闭</a>  -->
					<input type="button" value="打开" onclick="setState(${equipment.id},1)"/>||<input type="button" value="关闭" onclick="setState(${equipment.id},0)"/>
				</c:if>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</form>
</div>
</body>
</html>