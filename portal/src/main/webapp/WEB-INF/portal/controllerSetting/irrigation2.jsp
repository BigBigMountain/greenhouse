<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>灌溉控制</title>
<style >
	#controllerId {
		height: 30px;
		font-size: 15px;
		margin-left: 80px;height: 33px;
		font-size: 15px;
	}
	input.target{
		height:25px ;
		font-size: 20px;
		text-align: center;
		border: 0px;
		/* background-color: #F0F7FD; */
	}
	input.hour{
		height:25px ;
		font-size: 20px;
		text-align: right;
		border: 0px;
		/* background-color: #F0F7FD; */
	}
	input.min{
		height:25px ;
		font-size: 20px;
		text-align: left;
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
	$("input").attr("size","1");
	
	var msg = "${msg}";
	if(msg!=null && msg != ''){
		alert(msg);
	} 
});
function changeController(){
	var controllerId = $("#controllerId").val();
	window.location.href="/controllerSetting/toIrrigation2.do?controllerId="+controllerId;
}
function topage(i){
	window.location.href="/controllerSetting/toIrrigation"+i+".do";
}
</script>

</head>

<body >
<form action="/controllerSetting/updateIrrigation2.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:1#灌溉阀</div>
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
	<div style="background-color: #F0F7FD;margin:auto;width:850;">
		<div style="float: left;font-size: 20px;margin: 20px;">
			<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
				<tr>
					<td align="center"><input type="button" onclick="topage(1)" value="1#灌溉阀" /></td>
					<td align="center"><input type="button" onclick="topage(2)" value="2#灌溉阀" disabled="disabled"/></td>
					<td align="center"><input type="button" onclick="topage(3)" value="3#灌溉阀" /></td>
					<td align="center"><input type="button" onclick="topage(4)" value="4#灌溉阀" /></td>
					<td align="center"><input type="button" onclick="topage('R')" value="轮灌控制" /></td>
				</tr>
				<tr>
					<td align="center" colspan="5" >
						有效性:
						<select name= "irrigationValues" class="effe" >
							<option value="1" <c:if test="${values[0] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[0] != 1}">selected="selected"</c:if> >×</option>
						</select>
					</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td align="center" colspan="2">输出通道:<input type="text" name="irrigationValues" value="${values[1] }"  class="target"/></td>
					<td align="center" colspan="3">
						<select name= "irrigationValues" class="effe" >
							<option value="1" <c:if test="${values[2] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[2] != 1}">selected="selected"</c:if> >×</option>
						</select>采取轮灌方式</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="5" align="center">
					<table  cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
						<tr>
							<td>有效星期:</td>
							<c:forTokens items="一,二,三,四,五,六,日" delims="," var="var" varStatus="status">
								<td style="padding: 0px 15px 0px 0px;">周${var }<select name= "irrigationValues" class="effe" >
									<option value="1" <c:if test="${values[3+status.index] == 1}">selected="selected"</c:if> >√</option>
									<option value="0" <c:if test="${values[3+status.index] != 1}">selected="selected"</c:if> >×</option>
								</select></td>
							</c:forTokens>
						</tr>
					</table>
				</td></tr>
				
				<tr><td colspan="5">
				<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td ><select name= "irrigationValues" class="effe" >
							<option value="1" <c:if test="${values[10] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[10] != 1}">selected="selected"</c:if> >×</option>
						</select>1.根据土壤湿度:</td>
					<td>湿度下限值<input type="text" name="irrigationValues" value="${values[11] }"  class="target"/></td>
					<td>灌溉时间<input type="text" name="irrigationValues" value="${values[12] }"  class="target"/>秒.</td>
					<td>间隔时间<input type="text" name="irrigationValues" value="${values[13] }"  class="target"/>秒.</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td><select name= "irrigationValues" class="effe" >
							<option value="1" <c:if test="${values[14] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[14] != 1}">selected="selected"</c:if> >×</option>
						</select>2.根据温室湿度:</td>
					<td>湿度下限值<input type="text" name="irrigationValues" value="${values[15] }"  class="target"/></td>
					<td>灌溉时间<input type="text" name="irrigationValues" value="${values[16] }"  class="target"/>秒.</td>
					<td>间隔时间<input type="text" name="irrigationValues" value="${values[17] }"  class="target"/>秒.</td>
				</tr>
				<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
					<td colspan="5" align="left">
						<select name= "irrigationValues" class="effe" >
							<option value="1" <c:if test="${values[18] == 1}">selected="selected"</c:if> >√</option>
							<option value="0" <c:if test="${values[18] != 1}">selected="selected"</c:if> >×</option>
						</select>3.每天4个灌溉时间点:
					</td>
				</tr>
				<tr >
					<td colspan="5" align="center">
						<table cellspacing="5" cellpadding="1" style="font-size: 20px;background-color: #F0F7FD;width: 80%">
						<c:forEach begin="0" end="3" var="i">
							<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
								<td>时间${i+1 }:</td>
								<td><input type="text" name="irrigationValues" value="${values[19+3*i] }" class="hour"/>&nbsp;:
									<input type="text" name="irrigationValues" value="${values[20+3*i] }" class="min"/></td>
								<td>灌溉时间:
									<input type="text" name="irrigationValues" value="${values[21+3*i] }"  class="target"/>秒.</td>
							</tr>
						</c:forEach>
						</table>
					</td>
				</tr>
				</table></td></tr>
			</table>
		</div>
	</div>
</div>
</form>
</body>
</html>