<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>1#通风窗</title>
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
	select{
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
	window.location.href="/controllerSetting/toWindow1.do?controllerId="+controllerId;
}
function changeWindow(i){
	window.location.href="/controllerSetting/toWindow"+i+".do";
}
</script>

</head>

<body >
<form action="/controllerSetting/updateWindow1.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:1#通风窗设置</div>
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
	<div style="background-color: #F0F7FD;margin:auto;width:940;">
		<div style="float:left;width: 100%;height: 50px;margin-top: 30px">
			<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;" width="100%"><tr>
				<td align="center"><input type="button" value="1#通风窗" onclick="changeWindow(1)" style="font-size: 20px;" disabled="disabled"/></td>
				<td align="center"><input type="button" value="2#通风窗" onclick="changeWindow(2)" style="font-size: 20px;"/></td>
				<td align="center"><input type="button" value="侧通风窗" onclick="changeWindow('Side')" style="font-size: 20px;"/></td>
			</tr></table>
		</div>
		<div style="float: left;font-size: 20px;background-color: #AAD7F3;margin: 20px;border: 1px solid blue;">
			<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;">
				<tr>
					<td>有效性:
					<select name= "windowValues" class="effe" >
						<option value="1" style="background-color: green" <c:if test="${values[0] == 1}">selected="selected"</c:if> >√</option>
						<option value="0" style="background-color: yellow" <c:if test="${values[0] != 1}">selected="selected"</c:if> >×</option>
					</select></td>
				</tr>
				<tr>
					<td>通风窗朝向:
					<select name= "windowValues" class="direction">
						<option value="1" <c:if test="${values[1] != 1}">selected="selected"</c:if> >东</option>
						<option value="2" <c:if test="${values[1] == 1}">selected="selected"</c:if> >南</option>
						<option value="3" <c:if test="${values[1] == 2}">selected="selected"</c:if> >西</option>
						<option value="4" <c:if test="${values[1] == 3}">selected="selected"</c:if> >北</option>
					</select></td>
				</tr>
				<tr><td>"打开"通道:<input type="text" name="windowValues" value="${values[2] }" class="target"/></td></tr>
				<tr><td>"关闭"通道:<input type="text" name="windowValues" value="${values[3] }" class="target"/></td></tr>
				<tr><td>全程运行时间:<input type="text" name="windowValues" value="${values[4] }" class="target"/>秒</td></tr>
				<tr><td>机构防冲击保护时间:<input type="text" name="windowValues" value="${values[5] }" class="target"/>秒</td></tr>
				<tr><td>1.启动开窗降温功能:
					<select name= "windowValues" class="effe">
						<option value="1" <c:if test="${values[6] == 1}">selected="selected"</c:if> >√</option>
						<option value="0" <c:if test="${values[6] != 1}">selected="selected"</c:if> >×</option>
					</select></td></tr>
				<tr><td>室内&gt;室外:<input type="text" name="windowValues" value="${values[7] }" class="target"/>℃时打开通风窗</td></tr>
				<tr><td>2.启动开窗除湿功能:
					<select name= "windowValues" class="effe">
						<option value="1" <c:if test="${values[8] == 1}">selected="selected"</c:if> >√</option>
						<option value="0" <c:if test="${values[8] != 1}">selected="selected"</c:if> >×</option>
					</select></td></tr>
				<tr><td>室内&lt;目标:<input type="text" name="windowValues" value="${values[9] }" class="target"/>℃时禁止开窗除湿,保证温度正常</td></tr>
			</table>
		</div>
		<div style="float: left;font-size: 20px;background-color: #AAD7F3;margin: 20px;border: 1px solid blue;">
			<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;">
				<tr><td colspan="2">1#通风窗的保护限制</td></tr>
				<tr>
					<td><select name= "windowValues" class="effe">
							<option value="1" <c:if test="${values[10] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[10] != 1}">selected="selected"</c:if> >×</option>
						</select>
					</td>
					<td>1.室外温度&lt;=<input type="text" name="windowValues" value="${values[11] }" class="target"/>℃时禁止开窗 </td>
				</tr>
				<tr>
					<td><select name= "windowValues" class="effe">
							<option value="1" <c:if test="${values[12] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[12] != 1}">selected="selected"</c:if> >×</option>
						</select>
					</td>
					<td>2.迎风风速&gt;=<input type="text" name="windowValues" value="${values[13] }" class="target"/>m/s时禁止开窗 </td>
				</tr>
				<tr>
					<td></td>
					<td>&nbsp;&nbsp;&nbsp;背风风速&gt;=<input type="text" name="windowValues" value="${values[14] }" class="target"/>m/s时禁止开窗 </td>
				</tr>
				<tr>
					<td colspan="2">风速超标结束后,继续保持风保护状态<input type="text" name="windowValues" value="${values[15] }" class="target"/>秒 </td>
				</tr>
				<tr>
					<td><select name= "windowValues" class="effe">
							<option value="1" <c:if test="${values[16] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[16] != 1}">selected="selected"</c:if> >×</option>
						</select>
					</td>
					<td>3.补CO2时,通风窗最大位置&lt;=<input type="text" name="windowValues" value="${values[17] }" class="target"/>%</td>
				</tr>
				<tr>
					<td><select name= "windowValues" class="effe">
							<option value="1" <c:if test="${values[18] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[18] != 1}">selected="selected"</c:if> >×</option>
						</select>
					</td>
					<td>4.加湿时,通风窗最大位置&lt;=<input type="text" name="windowValues" value="${values[19] }" class="target"/>%</td>
				</tr>
				<tr>
					<td><select name= "windowValues" class="effe">
							<option value="1" <c:if test="${values[20] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[20] != 1}">selected="selected"</c:if> >×</option>
						</select>
					</td>
					<td>5.上喷喷淋启动时,关闭通风窗</td>
				</tr>
				<tr>
					<td><select name= "windowValues" class="effe">
							<option value="1" <c:if test="${values[21] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[21] != 1}">selected="selected"</c:if> >×</option>
						</select>
					</td>
					<td>6.风扇湿帘运行时,关闭通风窗</td>
				</tr>
				<tr>
					<td><select name= "windowValues" class="effe">
							<option value="1" <c:if test="${values[22] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[22] != 1}">selected="selected"</c:if> >×</option>
						</select>
					</td>
					<td>7.降雨时,自动关闭通风窗</td>
				</tr>
			</table>
		</div>
		<div style="clear: both;"></div>
	</div>	
</div>
</form>
</body>
</html>