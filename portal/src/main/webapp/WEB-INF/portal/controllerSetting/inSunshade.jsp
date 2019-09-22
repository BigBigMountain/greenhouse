<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>内遮阳</title>
<style >
	#controllerId {
		height: 30px;
		font-size: 15px;
		margin-left: 80px;
	}
	input.hour {
		height:25px ;
		font-size: 20px;
		text-align: right;
		border: 1px;
		/* background-color: #F0F7FD; */
	}
	input.minute{
		height:25px ;
		font-size: 20px;
		border: 1px;
		/* background-color: #F0F7FD; */
	}
	input.target{
		height:25px ;
		font-size: 20px;
		text-align: center;
		border: 0px;
		/* background-color: #F0F7FD; */
	}
	select.effe{
		height:25px ;
		font-size: 15px;
		text-align: center;
		background-color: #F0F7FD;
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
	window.location.href="/controllerSetting/toInSunshade.do?controllerId="+controllerId;
}
function changein(){
	var value = $("#in").val();
	if(value == 0){
		$("#out").val(1);
	}
	if(value == 1){
		$("#out").val(0);
	}
}
function changeout(){
	var value = $("#out").val();
	if(value == 0){
		$("#in").val(1);
	}
	if(value == 1){
		$("#in").val(0);
	}
}
</script>

</head>

<body >
<form action="/controllerSetting/updateInSunshade.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:内遮阳设置</div>
	<select name="controllerId" id="controllerId" onchange="changeController()" style="margin-left: 10px"> 
		<c:forEach items="${controllers }" var="controller">
			<option value="${controller.controllerId }" <c:if test="${controllerId == controller.controllerId }">selected = "selected"</c:if> >${controller.houseName }</option>
		</c:forEach>
	</select>
	<div style="float: right;margin-right: 50px;">
		<input type="submit" value="确定"/>
	</div>
</div>

<div class="body-box" >
	<div style="background-color: #F0F7FD;margin:auto;width:940;">
	<div style="float: left;">
		<fieldset style="border: 1px solid blue;margin: 20px;">
			<legend>内遮阳</legend>
		
			<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD">
				<tr>
					<td align="center" colspan="2" >
						有效性:
						<select name= "inSunshadeValues" class="effe" >
							<option value="1" <c:if test="${values[0] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[0] != 1}">selected="selected"</c:if> >×</option>
						</select>
					</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td align="center">展开通道:<input type="text" name="inSunshadeValues" value="${values[1] }" size="1" class="target"/></td>
					<td align="center">合拢通道:<input type="text" name="inSunshadeValues" value="${values[2] }" size="1" class="target"/></td>
				</tr>
				<tr>
					<td colspan="2">依据光照:</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td align="center">
						<select id="out" name= "inSunshadeValues" class="effe" onchange="changeout()">
							<option value="1" <c:if test="${values[3] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[3] != 1}">selected="selected"</c:if> >×</option>
						</select>
						室外光照
					</td>
					<td align="center">
						<select id="in" name= "inSunshadeValues" class="effe" onchange="changein()">
							<option value="1" <c:if test="${values[4] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[4] != 1}">selected="selected"</c:if> >×</option>
						</select>
						室内光照
					</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td align="center">光照&nbsp;&gt;<input type="text" name="inSunshadeValues" value="${values[5] }" size="2" class="target"/>时 </td>
					<td align="center">内遮阳展开遮阳 </td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td>滞回带:<input type="text" name="inSunshadeValues" value="${values[6] }" size="1" class="target"/>LUX&nbsp;</td>
					<td>确认时间:<input type="text" name="inSunshadeValues" value="${values[7] }" size="1" class="target"/>秒</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">全程运行时间:<input type="text" name="inSunshadeValues" value="${values[8] }" size="1" class="target"/>秒</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">机构防冲击保护时间:<input type="text" name="inSunshadeValues" value="${values[9] }" size="1" class="target"/>秒</td>
				</tr>
				
			</table>
			</fieldset>
		</div>
		<div style="float: left;">
		<fieldset  style="border: 1px solid blue;margin: 20px;">
			<legend >内保温幕</legend>
			<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD">
				<tr>
					<td align="center" colspan="2" >
						有效性:
						<select name= "inSunshadeValues" class="effe" >
							<option value="1" <c:if test="${values[10] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[10] != 1}">selected="selected"</c:if> >×</option>
						</select>
					</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td align="center">展开通道:<input type="text" name="inSunshadeValues" value="${values[11] }" size="1" class="target"/></td>
					<td align="center">合拢通道:<input type="text" name="inSunshadeValues" value="${values[12] }" size="1" class="target"/></td>
				</tr>
				
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">全程运行时间:<input type="text" name="inSunshadeValues" value="${values[13] }" size="1" class="target"/>秒</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">机构防冲击保护时间:<input type="text" name="inSunshadeValues" value="${values[14] }" size="1" class="target"/>秒</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">
						
						<input type="text" name="inSunshadeValues" value="${values[15] }" size="1" class="hour"/>:
						<input type="text" name="inSunshadeValues" value="${values[16] }" size="1" class="minute"/>~
						<input type="text" name="inSunshadeValues" value="${values[17] }" size="1" class="hour"/>:
						<input type="text" name="inSunshadeValues" value="${values[18] }" size="1" class="minute"/>
						内强制展开
					</td>
				</tr>
				
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">1,当室内温度&gt;室外<input type="text" name="inSunshadeValues" value="${values[19] }" size="2" class="target"/>时 ,</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;内保温进入保温工作方式 </td>
				</tr>
				
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td align="center">滞回带:<input type="text" name="inSunshadeValues" value="${values[20] }" size="1" class="target"/>LUX</td>
					<td align="center">确认时间:<input type="text" name="inSunshadeValues" value="${values[21] }" size="1" class="target"/>秒</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">2,当光照&gt;<input type="text" name="inSunshadeValues" value="${values[22] }" size="2" class="target"/>时 ,内保温幕合拢 </td>
				</tr>
				
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td align="center">反之</td>
					<td align="center">展开保温 </td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td align="center">滞回带:<input type="text" name="inSunshadeValues" value="${values[23] }" size="1" class="target"/></td>
					<td align="center">确认时间:<input type="text" name="inSunshadeValues" value="${values[24] }" size="1" class="target"/>秒</td>
				</tr>
				
			</table>
		</fieldset>
		</div>
	</div>	
</div>
</form>
</body>
</html>