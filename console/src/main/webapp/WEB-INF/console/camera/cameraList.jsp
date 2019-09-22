<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/console/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Management - CameraList</title>
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
	height:30px;
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
	</div>
	<div class="clear"></div>
	<div style="float:right">
		<input type="button" value="添加" onclick="addCamera()"/>
	</div>
</div>
<div class="body-box">
	
	<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
		<thead class="pn-lthead">
			<tr>
				<th style="width: 70px">编号</th>
				<th >名称</th>
				<th >网络端口号</th>
				<th >用户名</th>
				<th >密码</th>
				<th >端口号</th>
				<th >(温室号)温室名</th>
				<th >NVR</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody id="tbody" class="pn-ltbody"  align="center">
		<c:forEach items="${cameraList}" var="camera" >
			<form action="/camera/saveOrUpdateCamera.do" method="post">
				<tr bgcolor="#ffffff" onmouseover="this.bgColor='#C1F2A0'" onmouseout="this.bgColor='#ffffff'">
					<td align="center">
						<input type="hidden" name="zoneId" value="${zoneId }" />
						<input type="hidden" name="cameraId" value="${camera.cameraId }" />
						<input type="text" name="cameraNumber" value="${camera.cameraNumber }" 
						style="width: 70px;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="cameraName" value="${camera.cameraName }" 
						style="width:90%;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="cameraHttpPort" value="${camera.cameraHttpPort }" 
						style="width:90%;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="cameraUsername" value="${camera.cameraUsername }" 
						style="width:90%;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="cameraPassword" value="${camera.cameraPassword }" 
						style="width:90%;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="cameraPort" value="${camera.cameraPort }" 
						style="width:90%;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<select name="houseId" style="height: 30px;background: none;border: 0px;" onmouseover="inputOver(this)" onmouseout="inputOut(this)">
							<option value="">--请选择温室--</option>
							<c:forEach items="${houseList }" var="house">
								<option value="${house.houseId }" <c:if test="${camera.houseId == house.houseId }">selected="selected"</c:if> >(${house.houseNumber }) ${house.houseName }</option>
							</c:forEach>
						</select>
					</td>
					<td align="center">
						<select name="nvrId" style="height: 30px;background: none;border: 0px;" onmouseover="inputOver(this)" onmouseout="inputOut(this)">
							<option value="">--请选择NVR--</option>
							<c:forEach items="${nvrList }" var="nvr">
								<option value="${nvr.nvrId }" <c:if test="${camera.nvrId == nvr.nvrId }">selected="selected"</c:if> >${nvr.nvr_name }</option>
							</c:forEach>
						</select>
					</td>
					<td align="center">
						<input type="button" value="删除" onclick="delCamera(${camera.cameraId })"/>||
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
               添加摄像头
		<div id="btnclose" onclick="cancel();" style="float: right; margin-right:10px; cursor:pointer;" >×</div>
	</div>
	<form action="/camera/saveOrUpdateCamera.do" method="post">
		<table style="width: 100%; height:250px;font-size:18px">
			
		    <tr>
		        <td style="text-align: right;">
		        	<input type="hidden" name="zoneId" value="${zoneId }"/> 摄像头编号：</td>
		        <td ><input type="text" class="input-text" name="cameraNumber"  style="height: 30px"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">摄像头名称：</td>
				<td ><input type="text" class="input-text" name="cameraName" style="height: 30px;"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">网络端口号：</td>
				<td ><input type="text" class="input-text" name="cameraHttpPort" style="height: 30px;"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">用户名：</td>
				<td ><input type="text" class="input-text" name="cameraUsername" style="height: 30px;"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">密码：</td>
				<td ><input type="text" class="input-text" name="cameraPassword" style="height: 30px;"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">端口号：</td>
				<td ><input type="text" class="input-text" name="cameraPort" style="height: 30px;"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">温室：</td>
				<td >
					<select name="houseId" >
						<option value="" style="background-color: #FEFFD0;">--请选择温室--</option>
						<c:forEach items="${houseList }" var="house">
							<option value="${house.houseId }" >(${house.houseNumber }) ${house.houseName }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">NVR：</td>
				<td >
					<select name="nvrId" >
						<option value="" style="background-color: #FEFFD0;">--请选择NVR--</option>
						<c:forEach items="${nvrList }" var="nvr">
							<option value="${nvr.nvrId }" >${nvr.nvr_name }</option>
						</c:forEach>
					</select>
				</td>
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
function changeZone(){
	var zoneId = $("#zone").val();
	window.location.href="/camera/cameraList.do?zoneId="+zoneId;
}
function cancel(){
	$("#bg").hide();
	$("#addDiv").hide();
}
function addCamera(){
	$("#bg").show();
	$("#addDiv").show();
}
function delCamera(id){
	var del = confirm("确定要删除该摄像头吗?");
	if(del){
		var zoneId = $("#zone").val();
		$.ajax({
			url:"/camera/delCamera.do",
			type:"POST",
			data:{"cameraId":id},
			success:function(data){
				window.location.href = "/camera/cameraList.do?zoneId="+zoneId;
			}
		})
	}
}
function inputOver(i){
	/* i.style.border="1px solid black"; */
	i.style.background="#FEFFD0";
}
function inputOut(i){
	i.style.background = "none"; 
}


</script>
</body>
</html>