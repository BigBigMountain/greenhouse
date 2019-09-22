<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/console/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>setting - climatic</title>
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
	<div class="rpos">当前位置: 区域管理 </div>
	<div class="clear"></div>
	<div style="float:right">
		<input type="button" value="添加" onclick="addZone()"/>
	</div>
</div>

<div class="body-box">
	
	<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
		<thead class="pn-lthead">
			<tr>
				<th >区域编号</th>
				<th >区域名称</th>
				<th >地址</th>
				<th >ip</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody id="tbody" class="pn-ltbody"  align="center">
		<c:forEach items="${zoneList}" var="zone" >
			<form action="/zone/saveOrUpdateZone.do" method="post">
				<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
					<td align="center">
						<input type="hidden" name="zoneId" value="${zone.zoneId }" />
						${zone.zoneId }
					</td>
					<td align="center">
						<input type="text" name="zoneName" value="${zone.zoneName }" style="width:90%;height:30px;font-size:18px;border: 0px;background: none; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="zoneAddr" value="${zone.zoneAddr }" style="width:90%;height:30px;font-size:18px;border: 0px;background: none; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="zoneIp" value="${zone.zoneIp }" style="height:30px;font-size:18px;border: 0px;background: none; "onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="button" onclick="delZone(${zone.zoneId })" value="删除"/>||<input type="submit" value="保存"/>
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
               添加区域
		<div id="btnclose" onclick="cancel();" style="float: right; margin-right:10px; cursor:pointer;" >×</div>
	</div>
	<form action="/zone/saveOrUpdateZone.do" method="post">
		<table style="width: 100%; height:250px;font-size:18px">
		    <tr>
		        <td style="text-align: right;">区域名称：</td>
		        <td ><input type="text" class="input-text" name="zoneName"  style="height: 30px"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">区域地址：</td>
				<td ><input type="text" class="input-text" name="zoneAddr" style="height: 30px"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">IP地址 ：</td>
				<td><input type="text" class="input-text" name="zoneIp" style="height: 30px"/></td>
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
function cancel(){
	$("#bg").hide();
	$("#addDiv").hide();
}
function addZone(){
	$("#bg").show();
	$("#addDiv").show();
}
function delZone(id){
	var del = confirm("确定要删除该区域吗?");
	if(del){
		$.ajax({
			url:"/zone/delZone.do",
			type:"POST",
			data:{"zoneId":id},
			success:function(data){
				alert(data);
				window.location.href = "/zone/zoneList.do";
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
	i.style.border="1px solid black";
	i.style.background="#ffffff";
}
function inputOut(i){
	i.style.border="0px";
	i.style.background="none"; 
}

</script>
</body>
</html>