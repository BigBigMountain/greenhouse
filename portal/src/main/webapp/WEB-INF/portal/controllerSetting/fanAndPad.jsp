<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>风机湿帘</title>
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
	window.location.href="/controllerSetting/toFanAndPad.do?controllerId="+controllerId;
}
</script>

</head>

<body >
<form action="/controllerSetting/updateFanAndPad.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:风机湿帘设置</div>
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
	<div style="background-color: #F0F7FD;margin:auto;width:1050;">
		<div style="float: left;font-size: 20px;margin: 20px;">
			<fieldset style="border: 1px solid blue;">
				<legend>风机湿帘</legend>
				<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
					<tr>
						<td align="center" colspan="2" >
							有效性:
							<select name= "fanAndPadValues" class="effe" >
								<option value="1" <c:if test="${values[0] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[0] != 1}">selected="selected"</c:if> >×</option>
							</select>
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="right">一级风机输出通道:<input type="text" name="fanAndPadValues" value="${values[1] }"  class="target"/></td>
						<td align="right">二级风机输出通道:<input type="text" name="fanAndPadValues" value="${values[2] }"  class="target"/></td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="right">三级风机输出通道:<input type="text" name="fanAndPadValues" value="${values[3] }"  class="target"/></td>
						<td align="right">湿帘泵输出通道:<input type="text" name="fanAndPadValues" value="${values[4] }"  class="target"/></td>
					</tr>
				</table>
				<fieldset>
						<legend>风扇湿帘</legend>
					<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD">
						<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
							<td align="left" colspan="2">通风窗达到最大降温能力<input type="text" name="fanAndPadValues" value="${values[5]}" size="2" class="target"/>秒后,开始风机湿帘降温. </td>
						</tr>
						<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
							<td align="left" colspan="2">室温&nbsp;&gt;&nbsp;目标温度<input type="text" name="fanAndPadValues" value="${values[6]}" size="2" class="target"/>℃时,通风窗降温<font color="red" size="6">→</font>湿帘降温. </td>
						</tr>
						<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
							<td align="left" colspan="2">湿帘降温结束<input type="text" name="fanAndPadValues" value="${values[7]}" size="2" class="target"/>秒后,<font color="red" size="6">→</font>通风窗降温.确认时间:<input type="text" name="fanAndPadValues" value="${values[8]}" size="2" class="target"/>秒.</td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend >档位控制</legend>
				
					<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
						<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
							<td align="center" colspan="2">
								每经过<input type="text" name="fanAndPadValues" value="${values[9]}" size="2" class="target"/>秒,调整一个档位进行降温
							</td>
						</tr>
						<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
							<td align="center">最高档位:<input type="text" name="fanAndPadValues" value="${values[10]}" size="2" class="target"/></td>
							<td align="center">当前档位:<input type="hidden" name="fanAndPadValues" value="${values[11]}"/><label>&nbsp;&nbsp;${values[11]}</label></td>
						</tr>
						<tr>
							<c:forEach begin="0" end="1" var="c">
								<td align="center" >
									<table>
										<thead>
											<tr>
												<td></td>
												<td>一级</td>
												<td>二级</td>
												<td>三级</td>
												<td>湿帘</td>
											</tr>
										</thead>
										<tbody>
											<c:forEach begin="0" end="3" var="i">
												<tr onmouseover="this.bgColor='green'" onmouseout="this.bgColor='#F0F7FD'">
													<td>${i+1+c*4 }档</td>
													<c:forEach begin="0" end="3" var="j">
														<td>
															<select name="fanAndPadValues" class="effe" >
																<option value="1" <c:if test="${values[12+j+4*i+c*16] == 1 }">selected="selected"</c:if> >√</option>
																<option value="0" <c:if test="${values[12+j+4*i+c*16] == 0 }">selected="selected"</c:if> >×</option>
															</select>
														</td>
													</c:forEach>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
							</c:forEach>
						</tr>
						
						<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
							<td colspan="2">
								<select name= "fanAndPadValues" class="effe" >
									<option value="1" <c:if test="${values[44] == 1}">selected="selected"</c:if> >√</option>
									<option value="0" <c:if test="${values[44] != 1}">selected="selected"</c:if> >×</option>
								</select>
								1.室外温度&nbsp;&lt;&nbsp;<input type="text" name="fanAndPadValues" value="${values[45] }"  class="target"/>禁止开风机与湿帘
							</td>
						</tr>
						<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
							<td>滞回带:<input type="text" name="fanAndPadValues" value="${values[46] }"  class="target"/></td>
							<td>确认时间:<input type="text" name="fanAndPadValues" value="${values[47] }"  class="target"/>秒</td>
						</tr>
					</table>
				</fieldset>
			</fieldset>
		</div>
		<div style="float: left;font-size: 20px;margin: 20px;">
			<fieldset style="border: 1px solid blue">
				<legend>湿帘外翻窗</legend>
			
				<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD">	
					<tr>
						<td colspan="2" align="center">
							有效性:
							<select name= "fanAndPadValues" class="effe" >
								<option value="1" <c:if test="${values[48] == 1}">selected="selected"</c:if> >√</option>
								<option value="0" <c:if test="${values[48] != 1}">selected="selected"</c:if> >×</option>
							</select>
						</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td align="center">打开通道:<input type="text" name="fanAndPadValues" value="${values[49] }"  class="target"/></td>
						<td align="center">关闭通道:<input type="text" name="fanAndPadValues" value="${values[50] }"  class="target"/></td>
					</tr>
					
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td colspan="2" align="center">全程运行时间:<input type="text" name="fanAndPadValues" value="${values[51] }"  class="target"/>秒</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td colspan="2" align="center">机构防冲击保护时间:<input type="text" name="fanAndPadValues" value="${values[52] }"  class="target"/>秒</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td colspan="2" align="center">开风扇湿帘前,先开外翻窗,防止抽真空.</td>
					</tr>
					<tr onmouseover="this.bgColor='#D7EEDC'" onmouseout="this.bgColor='#F0F7FD'">
						<td colspan="2" align="center">风扇停止<input type="text" name="fanAndPadValues" value="${values[53] }"  class="target"/>秒后,自动关闭外翻窗</td>
					</tr>
				</table>
			</fieldset>
			
			温度的控温带宽:<input type="text" name="fanAndPadValues" value="${values[54] }"  class="target"/>确认时间:<input type="text" name="fanAndPadValues" value="${values[55] }"  class="target"/>
		</div>
	</div>	
</div>
</form>
</body>
</html>