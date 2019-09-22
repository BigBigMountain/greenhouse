<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>目标值</title>
<style >

	#controllerId {
		height: 30px;
		font-size: 15px;
		margin-left: 80px;
	}
	input.hour {
		height:30px ;
		font-size: 20px;
		text-align: right;
		background-color: #FBF9EC;
	}
	input.minute{
		height:30px ;
		font-size: 20px;
		background-color: #FBF9EC;
	}
	input.target{
		height:30px ;
		font-size: 20px;
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
	<div class="rpos">当前位置:目标值设置</div>
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
	<div style="background-color: #F0F7FD;margin:auto;width:1020;">
		
		<c:forTokens items="目标温度/℃:,目标湿度/%:,目标CO2/PPM:" delims="," var="name" varStatus="status">
			<div style="float:left;font-size: 20px;background-color: #AAD7F3;margin: 20px;border: 1px solid blue;width:auto">
				${name }
				<table cellspacing="0" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD">
					<tr>
						<td ></td>
						<td align="center" colspan="3" >起始时间</td>
						<td width="30px"></td>
						<td align="center" colspan="3">结束时间</td>
						<td width="30px"></td>
						<td align="center">目标值</td>
					</tr>
					<c:forEach begin="0" end="3" step="1" var="i">
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td  width="120px">时间段${i+1 }:</td>
						<td align="center"><input type="text" name="targetValues" value="${values[0+i*5+20*status.index] }" size="1" class="hour"/> </td>
						<td>:</td>
						<td align="center"><input type="text" name="targetValues" value="${values[1+i*5+20*status.index] }" size="1" class="minute"/> </td>
						<td></td>
						<td align="center"><input type="text" name="targetValues" value="${values[2+i*5+20*status.index] }" size="1" class="hour"/> </td>
						<td>:</td>
						<td align="center"><input type="text" name="targetValues" value="${values[3+i*5+20*status.index] }" size="1" class="minute"/> </td>
						<td></td>
						<td align="center"><input type="text" name="targetValues" value="${values[4+i*5+20*status.index] }" size="1" class="target"/> </td>
					</tr>
					</c:forEach>
					
				</table>
			</div>
			<c:if test="${status.count==2 }"><div style="clear: left;"></div></c:if>
		</c:forTokens>
			<div style="float:left;font-size: 20px;background-color: #AAD7F3;margin: 20px;border: 1px solid blue;width:460">
				目标土壤温度/℃  、土壤湿度/%:
				<table cellspacing="0" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD" width="100%" >
					<tr >
						<td width="50px"></td>
						<td width="160px" ></td>
						<td align="center">目标值</td>
						<td width="50px"></td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td ></td>
						<td >1#土壤温度:</td>
						<td align="center"><input type="text" name="targetValues" value="${values[60] }" size="1" class="target"/></td>
						<td></td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td ></td>
						<td>2#土壤温度:</td>
						<td align="center"><input type="text" name="targetValues" value="${values[61] }" size="1" class="target"/></td>
						<td ></td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td></td>
						<td>1#土壤湿度:</td>
						<td align="center"><input type="text" name="targetValues" value="${values[62] }" size="1" class="target"/></td>
						<td ></td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td ></td>
						<td>2#土壤湿度:</td>
						<td align="center"><input type="text" name="targetValues" value="${values[63] }" size="1" class="target"/></td>
						<td ></td>
					</tr>
				</table>
			</div>
		
	<div style="clear: both;"></div>
	</div>	
</div>
</form>
</body>
</html>