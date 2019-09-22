<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>warming</title>
<style >
	#controllerId {
		height: 30px;
		font-size: 15px;
		margin-left: 80px;
	}
	input.target{
		height:25px ;
		font-size: 20px;
		text-align: center;
		background-color: #F0F7FD;
	}
	
</style>
<script type="text/javascript">
$(function(){
	$("input").attr("size","1");
	
	var msg = "${msg}";
	if(msg!=null && msg != ''){
		alert(msg);
	}
});
function changeController(){
	var controllerId = $("#controllerId").val();
	window.location.href="/controllerSetting/toWarming.do?controllerId="+controllerId;
}
</script>
</head>
<body >
<form action="/controllerSetting/updateWarming.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:加热控制</div>
	<select name="controllerId" id="controllerId" onchange="changeController()" > 
		<c:forEach items="${controllers }" var="controller">
			<option value="${controller.controllerId }" <c:if test="${controllerId == controller.controllerId }">selected = "selected"</c:if> style="" >${controller.houseName }</option>
		</c:forEach>
	</select>
	<div style="float: right;margin-right: 50px;">
		<input type="submit" value="确定"/>
	</div>
</div>

<div class="body-box" >
	<div style="background-color: #F0F7FD;margin:auto;width:930;">
		<div style="font-size: 20px;background-color: #AAD7F3;margin: 20px;border: 1px solid blue;">
			<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;">
				<tr>
					<td>有效性:
					<select name= "warmingValues">
						<option value="1" style="background-color: green" <c:if test="${values[0] == 1}">selected="selected"</c:if> >√</option>
						<option value="0" style="background-color: yellow" <c:if test="${values[0] != 1}">selected="selected"</c:if> >×</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					输出通道:<input type="text" name="warmingValues" value="${values[1] }" class="target"/>
					</td>
				</tr>
				<tr><td>室内温度 &nbsp;&lt;=目标温度-偏置温度<input type="text" name="warmingValues" value="${values[2] }" class="target"/>时,启动加热设备</td></tr>
				<tr><td>室内温度&nbsp;&gt;=目标温度-偏置温度+滞回温度<input type="text" name="warmingValues" value="${values[3] }" class="target"/>,停止加热.
						确认时间:<input type="text" name="warmingValues" value="${values[4] }" class="target"/>秒.</td></tr>
						
				<tr><td>
					结束加热后,继续保持<input type="text" name="warmingValues" value="${values[5] }" class="target"/>秒对通风窗的强制关闭
				</td></tr>
				<tr><td>
					<select name= "warmingValues" class="effe">
						<option value="1" <c:if test="${values[6] == 1}">selected="selected"</c:if> >√</option>
						<option value="0" <c:if test="${values[6] != 1}">selected="selected"</c:if> >×</option>
					</select>
					通风窗完全关闭后,才允许启动加热设备.
				</td></tr>
			</table>
		</div>
		<div style="clear: both;"></div>
	</div>	
</div>
</form>
</body>
</html>