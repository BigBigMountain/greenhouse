<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>环流风扇</title>
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
		border: 0px;
		/* background-color: #F0F7FD; */
	}
	input.hour {
		height:25px ;
		font-size: 20px;
		text-align: right;
		border: 0px;
		background-color: #F0F7FD;
	}
	input.minute{
		height:25px ;
		font-size: 20px;
		border: 0px;
		background-color: #F0F7FD;
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
	$("input").attr("size","1");
	var msg = "${msg}";
	if(msg!=null && msg != ''){
		alert(msg);
	} 
});
function changeController(){
	var controllerId = $("#controllerId").val();
	window.location.href="/controllerSetting/toSirculatingFan.do?controllerId="+controllerId;
}
</script>

</head>

<body >
<form action="/controllerSetting/updateSirculatingFan.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:环流风扇设置</div>
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
	<div style="background-color: #F0F7FD;margin:auto;width:860;">
		<div style="float: left;font-size: 20px;margin: 20px;">
			<fieldset style="border: 1px solid blue;">
				<legend>依据时间</legend>
				<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
					<tr>
						<td align="center">
							有效性:
							<select name= "sirculatingFanValues" class="effe" >
								<option value="1" <c:if test="${values[0] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[0] != 1}">selected="selected"</c:if> >×</option>
							</select>
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">输出通道:<input type="text" name="sirculatingFanValues" value="${values[1] }"  class="target"/></td>
					</tr>
					<tr >
						<td align="center">
							<table  cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
								<c:forEach begin="0" end="3" var="i">
									<tr onmouseover="this.bgColor='yellow'" onmouseout="this.bgColor='#F0F7FD'">
										<td >${i+1 }.</td>
										<td ><input type="text" name="sirculatingFanValues" value="${values[2+i*4] }"  class="hour"/></td>
										<td >:</td>
										<td ><input type="text" name="sirculatingFanValues" value="${values[3+i*4] }"  class="minute"/></td>
										<td >打开,</td>
										<td ><input type="text" name="sirculatingFanValues" value="${values[4+i*4] }"  class="hour"/></td>
										<td >:</td>
										<td ><input type="text" name="sirculatingFanValues" value="${values[5+i*4] }"  class="minute"/></td>
										<td >关闭</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">
							<select name= "sirculatingFanValues" class="effe" >
								<option value="1" <c:if test="${values[18] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[18] != 1}">selected="selected"</c:if> >×</option>
							</select>
							加湿启动时,同时运行环流风扇;
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">
							<select name= "sirculatingFanValues" class="effe" >
								<option value="1" <c:if test="${values[19] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[19] != 1}">selected="selected"</c:if> >×</option>
							</select>
							除湿启动时,同时运行环流风扇;
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">
							<select name= "sirculatingFanValues" class="effe" >
								<option value="1" <c:if test="${values[20] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[20] != 1}">selected="selected"</c:if> >×</option>
							</select>
							补光运行时,同时运行环流风扇;
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">
							<select name= "sirculatingFanValues" class="effe" >
								<option value="1" <c:if test="${values[21] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[21] != 1}">selected="selected"</c:if> >×</option>
							</select>
							CO2补气时,同时运行环流风扇;
						</td>
					</tr>
				</table>
			</fieldset>
		</div>
		
		<div style="float: left;font-size: 20px;margin: 20px;">
			<fieldset style="border: 1px solid blue">
				<legend>依据两点温度差控制</legend>
				<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD">	
					<tr>
						<td colspan="2" align="center">
							有效性:
							<select name= "sirculatingFanValues" class="effe" >
								<option value="1" <c:if test="${values[22] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[22] != 1}">selected="selected"</c:if> >×</option>
							</select>
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">两点温差&nbsp;&gt;=<input type="text" name="sirculatingFanValues" value="${values[23] }"  class="target"/>℃时启动环流风扇</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">滞回带:<input type="text" name="sirculatingFanValues" value="${values[24] }"  class="target"/></td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">确认时间:<input type="text" name="sirculatingFanValues" value="${values[25] }"  class="target"/>秒</td>
					</tr>
				</table>
			</fieldset>
		</div>
	</div>	
</div>
</form>
</body>
</html>