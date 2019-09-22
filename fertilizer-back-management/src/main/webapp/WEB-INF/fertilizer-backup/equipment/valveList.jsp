<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fertilizer-backup/head.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>setting - tvalveList</title>
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
	<div class="rpos">当前位置: 阀控器列表 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名:
		<select id="user" name="userId" onchange="changeuser()" style="background-color:#FEFFD0 ">
			<c:forEach items="${userList }" var="user">
				<option value="${user.userId }" <c:if test="${userId == user.userId }">selected="selected"</c:if>>${user.username }</option>
			</c:forEach>
		</select>
		<b>&nbsp;&nbsp;>>&nbsp;&nbsp;施肥机:</b>
		<select id="fertilizer" name="fertilizerId" onchange="changefertilizer()" style="background-color:#FEFFD0 ">
			<c:forEach items="${fertilizerList }" var="fertilizer">
				<option value="${fertilizer.fertilizerId }" <c:if test="${fertilizerId == fertilizer.fertilizerId }">selected="selected"</c:if> >${fertilizer.fertilizerName }</option>
			</c:forEach>
		</select>
	</div>
	<div style="float:right;margin:6 20">
		<input type="button" value="一键保存" onclick="saveAll()" style="line-height:30px"/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="添加阀" onclick="addtvalve()" style="line-height:30px"/>
	</div>
	<div class="clear"></div>
</div>
<div class="body-box">
	<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
		<thead class="pn-lthead">
			<tr>
				<th >阀号</th>
				<th >阀控器mac地址</th>
				<th >网关</th>
				<th >阀控器类别</th>
				<th >阀控器通道</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody id="tbody" class="pn-ltbody"  align="center">
		
		<c:forEach items="${valveList}" var="tvalve" >
			<form action="/equipment/saveOrUpdateValve.do" method="post">
				<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
					<td align="center">
						<input type="hidden" name="userId" value="${userId }"/>
						<input type="hidden" name="fertilizerId" value="${fertilizerId }"/>
						<input type="hidden" name="tvalveId" value="${tvalve.tvalveId }" />
						<input type="text" name=number value="${tvalve.number }" style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="emac" value="${tvalve.emac }" class="input-text"  style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<select name="gmac" style="height: 30px" >
							<option value=""></option>
							<c:forEach items="${gateWayList }" var="gateWay"> 
			        			<option value="${gateWay.macCode}" <c:if test="${gateWay.macCode == tvalve.gmac }">selected=selected</c:if> >${gateWay.gateWayName }</option>
							</c:forEach>
			        	</select>
					</td>
					<td align="center">
						<select name="eid" style="height: 30px" >
			        		<option value="9" <c:if test="${tvalve.eid == 9 }">selected=selected</c:if> >双控电磁阀</option>
			        		<option value="6" <c:if test="${tvalve.eid == 6 }">selected=selected</c:if> >单控电磁阀</option>
			        		<!-- <option value="2">气象站</option> -->
			        	</select>
					</td>
					<td align="center">
						<input type="text" name="position" value="${tvalve.position }" style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="button" onclick="deltvalve(${tvalve.tvalveId })" value="删除"/>||
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
	<form action="/equipment/saveOrUpdateValve.do" method="post">
		<table style="width: 100%; height:250px;font-size:18px">
			<tr>
		        <td style="text-align: right;">
					<input type="hidden" name="userId" value="${userId }"/>
					<input type="hidden" name="fertilizerId" value="${fertilizerId }"/>
		      		 阀号：
				</td>
		        <td ><input type="text" name="number" class="input-text" style="height: 30px" /></td>
			</tr>
			<tr>
		        <td style="text-align: right;">  阀控器mac地址： </td>
		        <td ><input type="text" name="emac" class="input-text" style="height: 30px" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">网关：</td>
		        <td >
		        	<select name="gmac" style="height: 30px" >
		        		<c:forEach items="${gateWayList}" var="gateWay"> 
		        			<option value="${gateWay.macCode}" >${gateWay.gateWayName }</option>
						</c:forEach>
		        	</select>
		        </td>
			</tr>
			<tr>
				<td style="text-align: right;">阀控器类别：</td>
				<td >
					<select name="eid" style="height: 30px" >
		        		<option value="9">双控电磁阀</option>
		        		<option value="6">单控电磁阀</option>
		        		<!-- <option value="2">气象站</option> -->
		        	</select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">阀控器通道：</td>
				<td ><input type="text"  name="position" class="input-text" style="height: 30px"/></td>
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
	var userId = $("#user").val();
	window.location.href="/equipment/valveList.do?userId="+userId;
}
function changefertilizer(){
	var userId = $("#user").val();
	var fertilizerId = $("#fertilizer").val();
	window.location.href="/equipment/valveList.do?userId="+userId+"&fertilizerId="+fertilizerId;
}
function cancel(){
	$("#bg").hide();
	$("#addDiv").hide();
}
function addtvalve(){
	$("#bg").show();
	$("#addDiv").show();
}
function deltvalve(id){

	var del = confirm("确定要删除该设备吗?");
	if(del){
		var userId = $("#user").val();
		var fertilizerId = $("#fertilizer").val();
		$.ajax({
			url:"/equipment/delValve.do",
			type:"POST",
			data:{"tvalveId":id},
			success:function(data){
				if(data != ""){
					alert(data);
				}
				window.location.href ="/equipment/valveList.do?userId="+userId+"&fertilizerId="+fertilizerId;
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
/* function saveAll(){
	$.ajax({
		//几个参数需要注意一下
		type: "POST",//方法类型
		dataType: "json",//预期服务器返回的数据类型
		url: "/equipment/saveOrUpdateValveList.do" ,//url
		data: $('#allValves').serialize(),
		success: function (result) {
		    console.log(result);//打印服务端返回的数据(调试用)
		    if (result.resultCode == 200) {
		        alert("SUCCESS");
		    }
		    
		},
		error : function() {
		    alert("异常！");
		}
	});
} */
</script>
</body>
</html>