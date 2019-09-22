<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/console/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>setting - EquipmentList</title>
<style type="text/css">
#bg {
	display: none;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: #000000;
	opacity:0.4; 
	filter: alpha(opacity=40); 
	-moz-opacity: 0.7;
	}
#addDiv {
	display: none;
	position: absolute;
	top:30%;bottom:70%;margin:auto;
	left:0;right:0;
	width: 400px;
	height: 400px;
	background-color: #ffffff;
	}
.input-text {
	width:90%;
	font-size:18px;
	}
</style>
<script type="text/javascript">
$(function(){
	var msg="${msg}";
	console.log(msg);
	if(null != msg && msg!=""){
		alert(msg);
	}
});

</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 
		<select id="zone" name="zoneId" onchange="changeZone()" style="background-color:#FEFFD0 ">
			<c:forEach items="${zoneList }" var="zone">
				<option value="${zone.zoneId }" <c:if test="${zoneId == zone.zoneId }">selected="selected"</c:if>>${zone.zoneName }</option>
			</c:forEach>
		</select>
		<b>>></b>
		<select id="house" name="houseId" onchange="changeHouse()" style="background-color:#FEFFD0 ">
			<c:forEach items="${houseList }" var="house">
				<option value="${house.houseId }" <c:if test="${houseId == house.houseId }">selected="selected"</c:if> >${house.houseName }</option>
			</c:forEach>
		</select>
	</div>
	<div style="float:right;">
		<input type="button" value="添加设备" onclick="addEquipment()" style="line-height:25px"/>
	</div>
	<div class="clear"></div>
</div>
<div class="body-box">
	<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
		<thead class="pn-lthead">
			<tr>
				<th >设备编号</th>
				<th >设备名称</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody id="tbody" class="pn-ltbody"  align="center">
		<c:forEach items="${equipmentList}" var="equipment" >
			<form action="/equipment/saveOrUpdateEquipment.do" method="post">
				<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
					<td align="center">
						<input type="hidden" name="zoneId" value="${zoneId }"/>
						<input type="hidden" name="houseId" value="${houseId }"/>
						<input type="hidden" name="equipmentId" value="${equipment.equipmentId }" />
						<input type="text" name="equipmentCode" value="${equipment.equipmentCode }" style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="equipmentName" value="${equipment.equipmentName }" style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="button" onclick="delEquipment(${equipment.equipmentId })" value="删除"/>||
						<input type="submit" value="更新"/>
					</td>
				</tr>
			</form>
		</c:forEach>
		</tbody>
	</table>

</div>
<div id="bg"></div>
<div id="addDiv">
	<div style="width: 100%; color: #000000; text-align: center; background-color:#A2D5F3; font-size:18px; height:36px; line-height:36px; vertical-align:middle;">
               添加设备
		<div id="btnclose" onclick="cancel();" style="float: right; margin-right:10px; cursor:pointer;" >×</div>
	</div>
	<form action="/equipment/saveOrUpdateEquipment.do" method="post">
		<table style="width: 100%; height:250px;font-size:18px">
			<tr>
		        <td style="text-align: right;">
					<input type="hidden" name="zoneId" value="${zoneId }"/>
					<input type="hidden" name="houseId" value="${houseId }"/>
					<input type="hidden" id="equipmentCode" name="equipmentCode" />
		      		 设备类别：
				</td>
		        <td >
		        	<select id="equipmentType" style="height: 30px" onblur="getEquipmentCode()">
		        		<option value="A">A (展开/停止/合拢)</option>
		        		<option value="B">B (开/关/停)</option>
		        		<option value="C">C (开/停)</option>
		        	</select>
		        </td>
			</tr>
			<tr>
				<td style="text-align: right;">编号(数字)：</td>
		        <td ><input type="text" id="equipmentNumber" class="input-text" style="height: 30px" onblur="getEquipmentCode()"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">设备名称：</td>
				<td ><input type="text" class="input-text" name="equipmentName" style="height: 30px"/></td>
			</tr>
			<tr>
		        <td colspan="2" style="text-align: center">
					<input type="button" value="取消" onclick="cancel()"/>
				
					<input type="submit" value="保存"/>
		        </td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
function getEquipmentCode(){
	var equipmentType = $("#equipmentType").val(),
		equipmentNumber = $("#equipmentNumber").val();
	var equipmentCode = equipmentType+equipmentNumber;
	$("#equipmentCode").val(equipmentCode);
		
}
function changeZone(){
	var zoneId = $("#zone").val();
	window.location.href="/equipment/equipmentList.do?zoneId="+zoneId;
}
function changeHouse(){
	var zoneId = $("#zone").val();
	var houseId = $("#house").val();
	window.location.href="/equipment/equipmentList.do?zoneId="+zoneId+"&houseId="+houseId;
}
function cancel(){
	$("#bg").hide();
	$("#addDiv").hide();
}
function addEquipment(){
	$("#bg").show();
	$("#addDiv").show();
}
function delEquipment(id){

	var del = confirm("确定要删除该设备吗?");
	if(del){
		var zoneId = $("#zone").val();
		var houseId = $("#house").val();
		$.ajax({
			url:"/equipment/delEquipment.do",
			type:"POST",
			data:{"equipmentId":id},
			success:function(data){
				alert(data);
				window.location.href ="/equipment/equipmentList.do?zoneId="+zoneId+"&houseId="+houseId;
			}
		})
	}
}

function mouseover(i){
	i.bgColor='#eeeeee'
}
function mouseout(i){
	i.bgColor='#ffffff'
} 
function inputOver(i){
	i.style.background="#FEFFD0";
}
function inputOut(i){
	i.style.border="0px";
	i.style.background="none"; 
}

</script>
</body>
</html>