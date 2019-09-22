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
		margin-left: 80px;
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
	$("input").attr("size","1");
	
	var msg = "${msg}";
	if(msg!=null && msg != ''){
		alert(msg);
	} 
});
function changeController(){
	var controllerId = $("#controllerId").val();
	window.location.href="/controllerSetting/toIrrigationR.do?controllerId="+controllerId;
}
function topage(i){
	window.location.href="/controllerSetting/toIrrigation"+i+".do";
}
</script>

</head>

<body >
<form action="/controllerSetting/updateIrrigationR.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:轮灌控制</div>
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
	<div style="background-color: #F0F7FD;margin:auto;width:1000;">
		<div style="font-size: 20px;margin: 20px;">
		
			<table cellspacing="1" cellpadding="1" border="0"  style="font-size: 20px;margin: 0 auto;width: 80%">
				<tr>
					<td align="center"><input type="button" onclick="topage(1)" value="1#灌溉阀" /></td>
					<td align="center"><input type="button" onclick="topage(2)" value="2#灌溉阀" /></td>
					<td align="center"><input type="button" onclick="topage(3)" value="3#灌溉阀" /></td>
					<td align="center"><input type="button" onclick="topage(4)" value="4#灌溉阀" /></td>
					<td align="center"><input type="button" onclick="topage('R')" value="轮灌控制" disabled="disabled"/></td>
				</tr>
			</table>
			<table cellspacing="1" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
				<tr>
					<td>轮灌阀:</td>
					<c:forEach begin="0" end="15" var="i">
						<td align="center">${i+1 }#</td>
					</c:forEach>
				</tr>
				<tr>
					<td>输出通道:</td>
					<c:forEach begin="0" end="15" var="i">
						<td  align="center">
							<input type="text" name="irrigationValues" value="${values[0+i] }"  class="target"/>
						</td>
					</c:forEach>
				</tr>
				<c:forEach begin="0" end="3" var="i">	
					<tr><td colspan="17" align="center"><font color="blue">------------------------------------------------------------------------------------------------------------------------------------</font>
					</td></tr>
					<tr>
						<td colspan="4">${i+1 }#灌溉对应的轮灌控制:</td>
						<td colspan="4">灌溉时长:<input type="text" name="irrigationValues" value="${values[16+18*i] }"  class="target"/>秒</td>
						<td colspan="9">同时灌溉的阀的数量:<input type="text" name="irrigationValues" value="${values[17+18*i] }"  class="target"/>个</td>
					</tr>
					<tr>
						<td></td>
						<c:forEach begin="0" end="15" var="j">
							<td>${j+1 }#</td>
						</c:forEach>
					</tr>
					<tr>
						<td></td>
						<c:forEach begin="0" end="15" var="j">
							<td>
								<select name= "irrigationValues" class="effe" >
									<option value="1" <c:if test="${values[18+j+18*i] == 1}">selected="selected"</c:if> >√</option>
									<option value="0" <c:if test="${values[18+j+18*i] != 1}">selected="selected"</c:if> >×</option>
								</select>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</form>
</body>
</html>