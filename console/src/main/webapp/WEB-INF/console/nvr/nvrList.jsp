<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/console/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Management - nvrList</title>
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
		<input type="button" value="添加" onclick="addnvr()"/>
	</div>
</div>
<div class="body-box">
	
	<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
		<thead class="pn-lthead">
			<tr>
				<th >名称</th>
				<th >IP</th>
				<th >端口号</th>
				<th >用户名</th>
				<th >密码</th>
				


				<th >操作</th>
			</tr>
		</thead>
		<tbody id="tbody" class="pn-ltbody"  align="center">
		<c:forEach items="${nvrList}" var="nvr" >
			<form action="/nvr/saveOrUpdateNvr.do" method="post">
				<tr bgcolor="#ffffff" onmouseover="this.bgColor='#C1F2A0'" onmouseout="this.bgColor='#ffffff'">
					<td align="center">
						<input type="hidden" name="zone_id" value="${zoneId }" />
						<input type="hidden" name="nvr_id" value="${nvr.nvr_id }" />
						<input type="text" name="nvr_name" value="${nvr.nvr_name }" 
						style="width: 70px;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="nvr_ipv4" value="${nvr.nvr_ipv4 }" 
						style="width:90%;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="nvr_port" value="${nvr.nvr_port }" 
						style="width:90%;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="nvr_username" value="${nvr.nvr_username }" 
						style="width:90%;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="nvr_password" value="${nvr.nvr_password }" 
						style="width:90%;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					
					
					<td align="center">
						<input type="button" value="删除" onclick="delnvr(${nvr.nvr_id })"/>||
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
               添加Nvr
		<div id="btnclose" onclick="cancel();" style="float: right; margin-right:10px; cursor:pointer;" >×</div>
	</div>
	<form action="/nvr/saveOrUpdateNvr.do" method="post">
		<table style="width: 100%; height:250px;font-size:18px">
			
		    <tr>
		        <td style="text-align: right;">
		        	<input type="hidden" name="zone_id" value="${zoneId }"/> Nvr名称：</td>
		        <td ><input type="text" class="input-text" name="nvr_name"  style="height: 30px"/></td>
			</tr>
		
			<tr>
				<td style="text-align: right;">IP地址：</td>
				<td ><input type="text" class="input-text" name="nvr_ipv4" style="height: 30px;"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">端口号：</td>
				<td ><input type="text" class="input-text" name="nvr_port" style="height: 30px;"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">用户名：</td>
				<td ><input type="text" class="input-text" name="nvr_username" style="height: 30px;"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">密码：</td>
				<td ><input type="text" class="input-text" name="nvr_password" style="height: 30px;"/></td>
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
	window.location.href="/nvr/nvrList.do?zoneId="+zoneId;
}
function cancel(){
	$("#bg").hide();
	$("#addDiv").hide();
}
function addnvr(){
	$("#bg").show();
	$("#addDiv").show();
}
function delnvr(id){
	var del = confirm("确定要删除该摄像头吗?");
	if(del){
		var zoneId = $("#zone").val();
		$.ajax({
			url:"/nvr/delnvr.do",
			type:"POST",
			data:{"nvr_id":id},
			success:function(data){
				console.log('aaaaaa');
				window.location.href = "/nvr/nvrList.do?zoneId="+zoneId;
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