<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>CO2补气</title>
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
	window.location.href="/controllerSetting/toCO2.do?controllerId="+controllerId;
}
</script>
</head>
<body >
<form action="/controllerSetting/updateCO2.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:CO2补气</div>
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
	<div style="background-color: #F0F7FD;margin:auto;width:970;">
		<div style="float: left;font-size: 20px;background-color: #AAD7F3;margin: 20px;border: 1px solid blue;">
			<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;">
				<tr>
					<td>有效性:
					<select name= "CO2Values">
						<option value="1" style="background-color: green" <c:if test="${values[0] == 1}">selected="selected"</c:if> >√</option>
						<option value="0" style="background-color: yellow" <c:if test="${values[0] != 1}">selected="selected"</c:if> >×</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					输出通道:<input type="text" name="CO2Values" value="${values[1] }" class="target"/>
					</td>
				</tr>
				<tr><td>实测CO2浓度 &nbsp;&lt;=目标CO2浓度-CO2偏置控制量：<input type="text" name="CO2Values" value="${values[2] }" class="target"/></td></tr>
				<tr><td>补气过程中，实测CO2浓度&nbsp;&gt;=目标CO2浓度-CO2偏置控制量+滞回带:<input type="text" name="CO2Values" value="${values[3] }" class="target"/>结束补气;
						确认时间:<input type="text" name="CO2Values" value="${values[4] }" class="target"/>秒</td></tr>
				<tr><td>补气时,补气阀采用间歇工作方式:开<input type="text" name="CO2Values" value="${values[5] }" class="target"/>秒,
						关<input type="text" name="CO2Values" value="${values[6] }" class="target"/>秒</td></tr>
				<tr><td>在温室或者光照不理想的情况下,光合作用较弱时,无需补充CO2.</td></tr>
				<tr><td>
					<select name= "CO2Values" class="effe">
						<option value="1" <c:if test="${values[7] == 1}">selected="selected"</c:if> >√</option>
						<option value="0" <c:if test="${values[7] != 1}">selected="selected"</c:if> >×</option>
					</select>
					室内平均温度 &nbsp;&gt;=<input type="text" name="CO2Values" value="${values[8] }" class="target"/>℃时,允许补气;
					滞回带:<input type="text" name="CO2Values" value="${values[9] }" class="target"/>℃;
					确认时间:<input type="text" name="CO2Values" value="${values[10] }" class="target"/>秒;
				</td></tr>
				<tr><td>
					<select name= "CO2Values" class="effe">
						<option value="1" <c:if test="${values[11] == 1}">selected="selected"</c:if> >√</option>
						<option value="0" <c:if test="${values[11] != 1}">selected="selected"</c:if> >×</option>
					</select>
					室外光照 &nbsp;&gt;=<input type="text" name="CO2Values" value="${values[12] }" class="target"/>LUX时,允许补气;
					滞回带:<input type="text" name="CO2Values" value="${values[13] }" class="target"/>LUX;
					确认时间:<input type="text" name="CO2Values" value="${values[14] }" class="target"/>秒;
				</td></tr>
			</table>
		</div>
		<div style="clear: both;"></div>
	</div>	
</div>
</form>
</body>
</html>