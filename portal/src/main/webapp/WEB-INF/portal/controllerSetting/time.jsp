<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>时间</title>
<style >
	#controllerId {
		height: 30px;
		font-size: 15px;
		margin-left: 80px;
	}

	input.target{
		height:30px ;
		font-size: 25px;
		text-align: center;
		background-color: #FBF9EC;
	}
	
</style>
<script type="text/javascript">
$(function(){
	 var msg = "${msg}";
		if(msg!=null && msg != ''){
			alert(msg);
		} 
});
function changeController(){
	var controllerId = $("#controllerId").val();
	window.location.href="/controllerSetting/toTargetValue.do?controllerId="+controllerId;
}
</script>

</head>

<body >
<form action="/controllerSetting/updateTargetValue.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:时间设置</div>
	<select name="controllerId" id="controllerId" onchange="changeController()" style="margin-left: 10px"> 
		<c:forEach items="${controllers }" var="controller">
			<option value="${controller.controllerId }" <c:if test="${controllerId == controller.controllerId }">selected = "selected"</c:if> style="" >${controller.houseName }</option>
		</c:forEach>
	</select>
	<div style="float: right;margin-right: 50px;">
		<input type="submit" value="确定"/>
	</div>
</div>

<div class="body-box" >
	<div style="background-color: #F0F7FD;margin:0 auto;width:820;">
		<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;">
			<tr>
				<td>年</td>
				<td>月</td>
				<td>日</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>时</td>
				<td>分</td>
				<td>秒</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>星期</td>
			</tr>
			<tr>
				<td><input class = "target" type="text" name="fogValues" value="" size="1"/></td>
				<td><input class = "target" type="text" name="fogValues" value="" size="1"/></td>
				<td><input class = "target" type="text" name="fogValues" value="" size="1"/></td>
				<td></td>
				<td><input class = "target" type="text" name="fogValues" value="" size="1"/></td>
				<td><input class = "target" type="text" name="fogValues" value="" size="1"/></td>
				<td><input class = "target" type="text" name="fogValues" value="" size="1"/></td>
				<td></td>
				<td><input class = "target" type="text" name="fogValues" value="" size="1"/></td>
			</tr>
		</table>
	</div>	
</div>
</form>
</body>
</html>