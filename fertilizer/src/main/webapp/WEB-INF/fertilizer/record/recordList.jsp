<!doctype html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

	<script src="/js/jquery-1.11.3.min.js"></script>
	<link href="/css/datepicker/foundation-datepicker.css" rel="stylesheet" type="text/css">
	<script src="/js/datepicker/foundation-datepicker.js"></script>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>setting - FertilizerList</title>
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
	if(msg != ""){
		alert(msg)
	}
});

</script>
</head>
<body>
<div class="box-positon">
	<form action="/fertilizer/getValveRecord.do" method="get" >
	<div class="rpos">当前位置: 
		<select id="fertilizerId" name="fertilizerId" onchange="changeFertilizer()" style="background-color:#FEFFD0 ">
			<c:forEach items="${fertilizers }" var="fertilizer">
				<option value="${fertilizer.fertilizerId }" <c:if test="${fertilizerId == fertilizer.fertilizerId }">selected="selected"</c:if>>${fertilizer.fertilizerName }</option>
			</c:forEach>
		</select>
		开始时间:<input type="text" name="start" value="" id="start">
		结束时间:<input type="text" name="end" value="" id="end">
		<input type="submit" value="确定">
	</div>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
	<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
		<thead class="pn-lthead">
			<tr>
				
				<th style="height:30px;font-size:18px;">时间</th>
				<th style="height:30px;font-size:18px;">阀号</th>
				<th style="height:30px;font-size:18px;">状态</th>
				<th style="height:30px;font-size:18px;">EC (ms/cm)</th>
				<th style="height:30px;font-size:18px;">PH</th>
				<th style="height:30px;font-size:18px;">实时流量 (m³/h)</th>
				<th style="height:30px;font-size:18px;">液位 (cm)</th>
				<th style="height:30px;font-size:18px;">累计流量 (m³/h)</th>
				<th style="height:30px;font-size:18px;">管道压力 (kPa)</th>
			</tr>
		</thead>
		<tbody id="tbody" class="pn-ltbody"  align="center">
		<c:forEach items="${valveRecords}" var="record" >
				<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
					<td align="center" style="height:30px;font-size:18px;">
						<fmt:formatDate type="both" value="${record.datetime }" />
					</td>
					<td align="center" style="height:30px;font-size:18px;">
						${record.number }
					</td>
					<td align="center" style="height:30px;font-size:18px;">
						<c:if test="${record.value == 1}">开&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
						<c:if test="${record.value == 0}">&nbsp;&nbsp;&nbsp;&nbsp;关</c:if>
					</td>
					<td align="center" style="height:30px;font-size:18px;">
						${record.fertilizerData.ec }
					</td>
					<td align="center" style="height:30px;font-size:18px;">
						${record.fertilizerData.ph}
					</td>
					<td align="center" style="height:30px;font-size:18px;">
						${record.fertilizerData.rateFlow}
					</td>
					<td align="center" style="height:30px;font-size:18px;">
						${record.fertilizerData.liquidLevel}
					</td>
					<td align="center" style="height:30px;font-size:18px;">
						${record.fertilizerData.totalIrrigation}
					</td>
					<td align="center" style="height:30px;font-size:18px;">
						${record.fertilizerData.pipePressure}
					</td>
				</tr>
		</c:forEach>
		</tbody>
	</table>

</div>
<div id="bg"></div>

<script type="text/javascript">

$('#start').fdatepicker({
	format: 'yyyy-mm-dd hh:ii:ss',
	pickTime: true
});
$('#end').fdatepicker({
	format: 'yyyy-mm-dd hh:ii:ss',
	pickTime: true
});
function changeFertilizer(){
	var fertilizerId = $("#fertilizerId").val();
	window.location.href="/fertilizer/getValveRecord.do?fertilizerId="+fertilizerId;
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