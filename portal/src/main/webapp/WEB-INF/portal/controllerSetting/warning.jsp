<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>报警</title>
<style >
	#controllerId {
		height: 30px;
		font-size: 15px;
		margin-left: 80px;
	}
	
	input.target{
		height:30px ;
		font-size: 20px;
		text-align: center;
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
	 var msg = "${msg}";
		if(msg!=null && msg != ''){
			alert(msg);
		} 
});
function changeController(){
	var controllerId = $("#controllerId").val();
	window.location.href="/controllerSetting/toWarning.do?controllerId="+controllerId;
}

</script>

</head>

<body >
<form action="/controllerSetting/updateWarning.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:报警设置</div>
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
	<div style="background-color: #F0F7FD;margin:auto;width:720;">
		<div style="float: left;font-size: 20px;background-color: #AAD7F3;margin: 20px;border: 1px solid blue">
			<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD">
						<tr>
							<td colspan="2" align="center">输出通道: <input type="text" name="warningValues" value="${values[0] }" size="1" class="target" /></td>
						</tr>
				<c:forTokens items="室内温度,室内湿度,室内CO2" delims="," var="type" varStatus="typeStatus">
					<c:forTokens items="最高值:,最低值:" delims="," var="limit" varStatus="limitStatus">
						<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
							<td >
								<select name= "warningValues" class="effe">
									<option value="1" <c:if test="${values[1+4*limitStatus.index+8*typeStatus.index] == 1}">selected="selected"</c:if> >√</option>
									<option value="0" <c:if test="${values[1+4*limitStatus.index+8*typeStatus.index] != 1}">selected="selected"</c:if> >×</option>
								</select>
							</td>
							<td align="center" width="200px">${type }</td>
							<td>${limit }<input type="text" name="warningValues" value="${values[2+limitStatus.index*4+typeStatus.index*8] }" size="1" class="target" style="background-color: #FBF9EC"/> </td>
							<td align="center">滞回带</td>
							<td align="center"><input type="text" name="warningValues" value="${values[3+limitStatus.index*4+typeStatus.index*8] }" size="1" class="target"/> </td>
							<td>确认时间</td>
							<td align="center"><input type="text" name="warningValues" value="${values[4+limitStatus.index*4+typeStatus.index*8] }" size="1" class="target"/> </td>
							<td>秒</td>
						</tr>
					</c:forTokens>
				</c:forTokens>
				<tr>
					<td colspan="8" align="center"> 
						<div style="float: right;">
							有效性:<select name= "warningValues" class="effe" >
								<option value="1" <c:if test="${values[25]+limitStatus.index*2+typeStatus.index*3 != 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[25]+limitStatus.index*2+typeStatus.index*3 != 1}">selected="selected"</c:if> >×</option>
							</select>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div style="clear: both;"></div>
	</div>	
</div>
</form>
</body>
</html>