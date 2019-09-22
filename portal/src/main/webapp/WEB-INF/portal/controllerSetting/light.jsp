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
		/* background-color: #F0F7FD; */
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
	window.location.href="/controllerSetting/toLight.do?controllerId="+controllerId;
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
<form action="/controllerSetting/updateLight.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:补光灯设置</div>
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
		<div style="float: left;font-size: 20px;margin: 20px;">
			<fieldset style="border: 1px solid blue;">
				<legend>依据时间补光</legend>
				<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
					<tr>
						<td align="center">
							有效性:
							<select name= "lightValues" class="effe" >
								<option value="1" <c:if test="${values[0] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[0] != 1}">selected="selected"</c:if> >×</option>
							</select>
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">
							<table cellspacing="10" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
								<tr onmouseover="this.bgColor='yellow'" onmouseout="this.bgColor='#F0F7FD'">
									<td align="center" >输出通道:&nbsp;&nbsp;</td>
									<td align="center" >1#灯:<input type="text" name="lightValues" value="${values[1] }"  class="target"/></td>
									<td align="center" style="padding: 0px 0px 0px 15px;">2<font size="5">#</font>灯:<input type="text" name="lightValues" value="${values[2] }"  class="target"/></td>
									<td align="center" style="padding: 0px 0px 0px 15px;">3#灯:<input type="text" name="lightValues" value="${values[3] }"  class="target"/></td>
									<td align="center" style="padding: 0px 0px 0px 15px;">4#灯:<input type="text" name="lightValues" value="${values[4] }"  class="target"/></td>
									<td align="center" style="padding: 0px 0px 0px 15px;">5#灯:<input type="text" name="lightValues" value="${values[5] }"  class="target"/></td>
									<td align="center" style="padding: 0px 0px 0px 15px;">6#灯:<input type="text" name="lightValues" value="${values[6] }"  class="target"/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">
							<table  cellspacing="0" cellpadding="0" border="0"  style="font-size: 20px;background-color: #F0F7FD;">
								<thead>
									<tr>
										<td align="center"></td>
										<td align="center" style="padding: 0px 0px 0px 15px;"> 1#灯</td>
										<td align="center" style="padding: 0px 0px 0px 15px;"> 2#灯</td>
										<td align="center" style="padding: 0px 0px 0px 15px;"> 3#灯</td>
										<td align="center" style="padding: 0px 0px 0px 15px;"> 4#灯</td>
										<td align="center" style="padding: 0px 0px 0px 15px;"> 5#灯</td>
										<td align="center" style="padding: 0px 0px 0px 15px;"> 6#灯</td>
									</tr>
								</thead>
								<c:forEach begin="0" end="4" var="i">
									<tr onmouseover="this.bgColor='yellow'" onmouseout="this.bgColor='#F0F7FD'">
										<td >${i+1 }.
											<input type="text" name="lightValues" value="${values[7+i*10] }"  class="hour"/>
											:<input type="text" name="lightValues" value="${values[8+i*10] }"  class="minute"/>
											--<input type="text" name="lightValues" value="${values[9+i*10] }"  class="hour"/>
											:<input type="text" name="lightValues" value="${values[10+i*10] }"  class="minute"/>
										</td>
									<c:forEach begin="0" end="5" var="j">
										<td align="center" style="padding: 0px 0px 0px 15px;">
											<select name= "lightValues" class="effe" >
												<option value="1" <c:if test="${values[11+i*10+j] == 1}">selected="selected"</c:if> >√</option>
												<option value="0" <c:if test="${values[11+i*10+j] != 1}">selected="selected"</c:if> >×</option>
											</select>
										</td>
									</c:forEach>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">
							<select name= "lightValues" class="effe" >
								<option value="1" <c:if test="${values[57] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[57] != 1}">selected="selected"</c:if> >×</option>
							</select>
							室外光照&nbsp;&gt;=<input type="text" name="lightValues" value="${values[58] }"  class="target"/>是,强制关闭.&nbsp;&nbsp;
							滞回带:<input type="text" name="lightValues" value="${values[59] }"  class="target"/>&nbsp;&nbsp;
							确认时间:<input type="text" name="lightValues" value="${values[60] }"  class="target"/>秒
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">
							<select name= "lightValues" class="effe" >
								<option value="1" <c:if test="${values[61] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[61] != 1}">selected="selected"</c:if> >×</option>
							</select>
							顺序开灯时,各灯之间的延时时间
							<input type="text" name="lightValues" value="${values[62] }"  class="target"/>秒
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">
							<select name= "lightValues" class="effe" >
								<option value="1" <c:if test="${values[63] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[63] != 1}">selected="selected"</c:if> >×</option>
							</select>
							室内温度过高,出现高温报警时,自动关闭所有补光灯,减少热源
						</td>
					</tr>
				</table>
			</fieldset>
		</div>
		
		<div style="float: left;font-size: 20px;margin: 20px;">
			<fieldset style="border: 1px solid blue">
				<legend>依据光照补光</legend>
				<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD">	
					<tr>
						<td colspan="2" align="center">
							有效性:
							<select name= "lightValues" class="effe" >
								<option value="1" <c:if test="${values[64] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[64] != 1}">selected="selected"</c:if> >×</option>
							</select>
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">
							<select id="out" name= "lightValues" class="effe" onchange="changeout()">
								<option value="1" <c:if test="${values[65] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[65] != 1}">selected="selected"</c:if> >×</option>
							</select>
							室外光照
						</td>
						<td align="center">
							<select id="in" name= "lightValues" class="effe" onchange="changein()">
								<option value="1" <c:if test="${values[66] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[66] != 1}">selected="selected"</c:if> >×</option>
							</select>
							室内光照
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td colspan="2" align="center">光照&nbsp;&lt;=<input type="text" name="lightValues" value="${values[67] }"  class="target"/>LUX时启动相应的补光灯</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">滞回带:<input type="text" name="lightValues" value="${values[68] }"  class="target"/></td>
						<td align="center">确认时间:<input type="text" name="lightValues" value="${values[69] }"  class="target"/>秒</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td colspan="2">
							<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
								<tr onmouseover="this.bgColor='yellow'" onmouseout="this.bgColor='#F0F7FD'">
									<td >补光灯分组号:</td>
									<c:forEach begin="0" end="5" var="i">
									<td style="padding: 0px 0px 0px 15px;">
										<select name= "lightValues" class="effe" >
											<option value="1" <c:if test="${values[70+i] == 1}">selected="selected"</c:if> >√</option>
											<option value="0" <c:if test="${values[70+i] != 1}">selected="selected"</c:if> >×</option>
										</select>${i+1 }#灯
									</td>
									</c:forEach>
								</tr>
							</table>
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td colspan="2" align="center">
							当室外光照&nbsp;&gt;<input type="text" name="lightValues" value="${values[76] }"  class="target"/>
							并且室内外光照差&nbsp;&gt;<input type="text" name="lightValues" value="${values[77] }"  class="target"/>时,
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td colspan="2" align="center">
							判断为阴影遮挡室内光照传感器,此时临时屏蔽补光灯控制.
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td colspan="2" align="center">
							阴影状态保持时间&nbsp;&gt;<input type="text" name="lightValues" value="${values[78] }"  class="target"/>秒,阴影状态取消.
						</td>
					</tr>
				</table>
			</fieldset>
		</div>
	</div>	
</div>
</form>
</body>
</html>