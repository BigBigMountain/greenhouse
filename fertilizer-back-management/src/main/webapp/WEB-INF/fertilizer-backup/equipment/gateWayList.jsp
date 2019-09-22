<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fertilizer-backup/head.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>setting - GageWayList</title>
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
	if(msg!=""){
		alert(msg);
	}
});

</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 网关列表 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名:
		<select id="userId" name="userId" onchange="changeuser()" style="background-color:#FEFFD0 ">
			<c:forEach items="${userList }" var="user">
				<option value="${user.userId }" <c:if test="${userId == user.userId }">selected="selected"</c:if>>${user.username }</option>
			</c:forEach>
		</select>
		
	</div>
	<div style="float:right;">
		<input type="button" value="添加网关" onclick="addGateWay()" style="line-height:25px"/>
	</div>
	<div class="clear"></div>
</div>
<div class="body-box">
	<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
		<thead class="pn-lthead">
			<tr>
				<th >网关名称</th>
				<th >mac地址</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody id="tbody" class="pn-ltbody"  align="center">
		<c:forEach items="${gateWayList}" var="gateWay" >
			<form action="/equipment/saveOrUpdateGateWay.do" method="post">
				<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
					<td align="center">
						<input type="hidden" name="userId" value="${userId }"/>
						<input type="hidden" name="gateWayId" value="${gateWay.gateWayId }" />
						<input type="text" name="gateWayName" value="${gateWay.gateWayName }" class="input-text" style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="macCode" value="${gateWay.macCode }" class="input-text" style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="button" onclick="delEquipment(${gateWay.gateWayId })" value="删除"/>||
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
               添加网关
		<div id="btnclose" onclick="cancel();" style="float: right; margin-right:10px; cursor:pointer;" >×</div>
	</div>
	<form action="/equipment/saveOrUpdateGateWay.do" method="post">
		<table style="width: 100%; height:250px;font-size:18px">
			<tr>
		        <td style="text-align: right;">
					<input type="hidden" name="userId" value="${userId }"/>
		      		 网关名称：
				</td>
		        <td ><input type="text" name="gateWayName" class="input-text" style="height: 30px" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">mac地址：</td>
		        <td ><input type="text" name="macCode" class="input-text" style="height: 30px" /></td>
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

function changeuser(){
	var userId = $("#userId").val();
	window.location.href="/equipment/gateWayList.do?userId="+userId;
}
function cancel(){
	$("#bg").hide();
	$("#addDiv").hide();
}
function addGateWay(){
	$("#bg").show();
	$("#addDiv").show();
}
function delEquipment(id){

	var del = confirm("确定要删除该网关吗?");
	if(del){
		var userId = $("#userId").val();
		$.ajax({
			url:"/equipment/delGateWay.do",
			type:"POST",
			data:{"gateWayId":id},
			success:function(data){
				if(data != ""){
					alert(data);
				}
				window.location.href ="/equipment/gateWayList.do?userId="+userId;
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