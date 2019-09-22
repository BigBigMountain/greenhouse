<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>外遮阳</title>
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
	window.location.href="/controllerSetting/toExSunshade.do?controllerId="+controllerId;
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
<form action="/controllerSetting/updateExSunshade.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:外遮阳设置</div>
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
	<div style="background-color: #F0F7FD;margin:auto;width:500;">
		<div style="float: left;font-size: 20px;background-color: #AAD7F3;margin: 20px;border: 1px solid blue">
			<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD">
				<tr>
					<td align="center" colspan="2" >
						有效性:
						<select name= "exSunshadeValues" class="effe" >
							<option value="1" <c:if test="${values[0] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[0] != 1}">selected="selected"</c:if> >×</option>
						</select>
					</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td align="center">展开通道:<input type="text" name="exSunshadeValues" value="${values[1] }" size="1" class="target"/></td>
					<td align="center">合拢通道:<input type="text" name="exSunshadeValues" value="${values[2] }" size="1" class="target"/></td>
				</tr>
				<tr>
					<td colspan="2">依据光照:</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td align="center">
						<select id="out" name= "exSunshadeValues" class="effe" onchange="changeout()">
							<option value="1" <c:if test="${values[3] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[3] != 1}">selected="selected"</c:if> >×</option>
						</select>
						室外光照
					</td>
					<td align="center">
						<select id="in" name= "exSunshadeValues" class="effe" onchange="changein()">
							<option value="1" <c:if test="${values[4] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[4] != 1}">selected="selected"</c:if> >×</option>
						</select>
						室内光照
					</td>
				</tr>
				<c:forEach begin="1" end="3" step="1" var="i">
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td align="center">光照${i }&nbsp;&gt;<input type="text" name="exSunshadeValues" value="${values[5+(i-1)*2] }" size="2" class="target"/>时 </td>
					<td align="center">外遮阳展开到<input type="text" name="exSunshadeValues" value="${values[6+(i-1)*2] }" size="1" class="target"/>%位置 </td>
				</tr>
				</c:forEach>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td align="center">滞回带:<input type="text" name="exSunshadeValues" value="${values[11] }" size="1" class="target"/></td>
					<td align="center">确认时间:<input type="text" name="exSunshadeValues" value="${values[12] }" size="1" class="target"/>秒</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">全程运行时间:<input type="text" name="exSunshadeValues" value="${values[13] }" size="1" class="target"/>秒</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">机构防冲击保护时间:<input type="text" name="exSunshadeValues" value="${values[14] }" size="1" class="target"/>秒</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">室外风速&nbsp;&gt;<input type="text" name="exSunshadeValues" value="${values[15] }" size="1" class="target"/>m/s时,外遮阳合拢,保护外遮阳</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">风速超标结束后,继续保持风保护状态:<input type="text" name="exSunshadeValues" value="${values[16] }" size="1" class="target"/>秒</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">有雨雪天气,合拢外遮阳,保护外遮阳</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="2">
						<input type="text" name="exSunshadeValues" value="${values[17] }" size="1" class="hour"/>:
						<input type="text" name="exSunshadeValues" value="${values[18] }" size="1" class="minute"/>~
						<input type="text" name="exSunshadeValues" value="${values[19] }" size="1" class="hour"/>:
						<input type="text" name="exSunshadeValues" value="${values[20] }" size="1" class="minute"/>
						时间段内禁止展开外遮阳
					</td>
				</tr>
			</table>
		</div>
	</div>	
</div>
</form>
</body>
</html>