<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>手动操作</title>
<style >
	.selected {
		width:20px;
		height:20px;
		background-image: url(/images/admin/selected.png);
		background-size:cover;
	}
	.unselected {
		width:20px;
		height:20px;
		background-image: url(/images/admin/unselected.png);
		background-size:cover;
	}
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
	
	var $val = $(".value");
	for(var i=0;i<$val.length;i++){
		var $box = $($val[i]).parent();
		var div = $box.children("div");
		var val = $box.children("input").val();
		if(val == 1){
			div.addClass("selected");
		} else {
			div.addClass("unselected");
		}
	}
	

});
function changeController(){
	var controllerId = $("#controllerId").val();
	window.location.href="/controllerSetting/toManualControl.do?controllerId="+controllerId;
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

function clickradiobox1(e){
	var $td = $(e).parent();
	var $radiobox1 = $td.children(".radiobox1");
	for(var i=0;i<$radiobox1.length;i++){
		var $div = $($radiobox1[i]).children(".radiodiv");
		$div.removeClass("selected");
		$div.addClass("unselected");
		$($radiobox1[i]).children(".value").val(0);
	}
	var $thisdiv = $(e).children(".radiodiv");
	$thisdiv.removeClass("unselected");
	$thisdiv.addClass("selected");
	$(e).children(".value").val(1);
}

function clickcheckbox1(e){
	var value = $(e).children(".value").val();
	if(value == 1){
		$(e).children(".value").val(0);
		$(e).children(".checkdiv").removeClass("selected");
		$(e).children(".checkdiv").addClass("unselected");
	}else{
		$(e).children(".value").val(1);
		$(e).children(".checkdiv").removeClass("unselected");
		$(e).children(".checkdiv").addClass("selected");
	}
}

</script>

</head>

<body >
<form action="/controllerSetting/updateManualControl.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:手动操作</div>
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
	<div style="background-color: #F0F7FD;margin:0 auto;width:850;font-size: 20px">
			<fieldset style="border: 1px solid blue;float: left;margin: 20px;">
			<div style="float:left">
				<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
				
				<c:forTokens items="外遮阳,内遮阳,内保温,1#通风窗,2#通风窗,湿帘外翻窗" delims="," var="name" varStatus="status">
					<tr>
						<td align="center">${name }:</td>
						<td align="center">
							<div class="radiobox1" onclick="clickradiobox1(this)" style="float:left;">
								<div class="radiodiv" style="float: left" ></div>
								自动|<input class="value" type="hidden" name="manualControlValues" value="${values[status.index * 3 + 0 ]}" />
							</div>
							
							<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
								<div class = "radiodiv" style="float: left"></div>
								手动<input class="value" type="hidden" name="manualControlValues" value="${values[status.index * 3 + 1 ]}" />
							</div>
						</td>
						<td>手动位置:<input type="text" class="target" name="manualControlValues" value="${values[status.index * 3 + 2 ]}" /></td>
					</tr>
				</c:forTokens>
				</table>
				<fieldset>
					<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto;">
						<tr>
							<td>
								风机湿帘:
							</td>
							<td>
								<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
									<div class = "radiodiv" style="float: left" ></div>
									自动|<input class="value" type="hidden" name="manualControlValues"  value="${values[18]}" />
								</div>
							
								<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
									<div class = "radiodiv" style="float: left"></div>
									手动开|<input class="value" type="hidden" name="manualControlValues" value="${values[19]}"/>
								</div>
								<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left; ">
									<div class = "radiodiv" style="float: left;"></div>
									手动关<input class="value" type="hidden" name="manualControlValues" value="${values[20]}" />
								</div>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div class="checkbox1" onclick="clickcheckbox1(this)" style="float:left;">
									<div class="checkdiv" style="float:left"></div>
									一级风扇<input class = "value" type="hidden" name="manualControlValues" value="${values[21]}"/>
								</div>
								<div class="checkbox1" onclick="clickcheckbox1(this)" style="float:left;">
									<div class="checkdiv" style="float:left"></div>
									二级风扇<input class = "value" type="hidden" name="manualControlValues" value="${values[22]}"/>
								</div>
							</td>
						</tr>
						<tr >
							<td></td>
							<td>
								<div class="checkbox1" onclick="clickcheckbox1(this)" style="float:left;">
									<div class="checkdiv" style="float:left"></div>
									三级风扇<input class = "value" type="hidden" name="manualControlValues" value="${values[23]}"/>
								</div>
								<div class="checkbox1" onclick="clickcheckbox1(this)" style="float:left;">
									<div class="checkdiv" style="float:left"></div>
									湿帘泵<input class = "value" type="hidden" name="manualControlValues" value="${values[24]}"/>
								</div>
							</td>
						</tr>
					</table>
				</fieldset>
				</div>
				<div style="float:left">
					<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
					<c:forTokens items="微雾,环流风扇,CO2补气,加热设备,上喷喷淋" delims="," var="name" varStatus="status">
						<tr>
							<td>${name }:</td>
							<td>
								<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
									<div class = "radiodiv" style="float: left" ></div>
									自动| <input class="value" type="hidden" name="manualControlValues" value="${values[25 + status.index*3 +0 ]}" />
								</div>
							
								<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
									<div class = "radiodiv" style="float: left"></div>
									手动开| <input class="value" type="hidden" name="manualControlValues"  value="${values[25 + status.index*3 +1 ]}" />
								</div>
								<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
									<div class = "radiodiv" style="float: left"></div>
									手动关<input class="value" type="hidden" name="manualControlValues"  value="${values[25 + status.index*3 +2 ]}" />
								</div>
							</td>
						</tr>
					</c:forTokens>
					</table>
					<fieldset>
					<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto;">
						<tr>
							<td>
								灌溉:
							</td>
							<td>
								<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
									<div class = "radiodiv" style="float: left" ></div>
									自动|<input class="value" type="hidden" name="manualControlValues" value="${values[40]}" />
								</div>
							
								<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
									<div class = "radiodiv" style="float: left"></div>
									手动开|<input class="value" type="hidden" name="manualControlValues" value="${values[41]}" />
								</div>
								<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
									<div class = "radiodiv" style="float: left"></div>
									手动关<input class="value" type="hidden" name="manualControlValues" value="${values[42]}" />
								</div>
							</td>
						</tr>
						<c:forEach begin="0" end="1" var="i">
						<tr>
							<td></td>
							<td>
								<c:forEach begin="0" end="1" var="j">
								<div class="checkbox1" onclick="clickcheckbox1(this)" style="float:left;">
									<div class="checkdiv" style="float:left"></div>
									${i*2+j+1} #灌溉阀<input class = "value" type="hidden" name="manualControlValues" value="${values[43 + i*2+j ]}" />
								</div>
								</c:forEach>
							</td>
						</tr>
						</c:forEach>
					</table>
				</fieldset>
				<table  cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto">
					<tr>
						<td>侧通风窗:</td>
						<td align="center">${name }:</td>
						<td align="center">
							<div class="radiobox1" onclick="clickradiobox1(this)" style="float:left;">
								<div class="radiodiv" style="float: left" ></div>
								自动|<input class="value" type="hidden" name="manualControlValues" value="${values[47]}" />
							</div>
							
							<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
								<div class = "radiodiv" style="float: left"></div>
								手动<input class="value" type="hidden" name="manualControlValues" value="${values[48]}" />
							</div>
						</td>
						<td>手动位置:<input type="text" class="target" name="manualControlValues" value="${values[49]}" /></td>
					</tr>
				</table>
				<fieldset>
					<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;background-color: #F0F7FD;margin: 0 auto;">
						<tr>
							<td>
								补光灯:
							</td>
							<td>
								<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
									<div class = "radiodiv" style="float: left" ></div>
									自动|<input class="value" type="hidden" name="manualControlValues" value="${values[50]}"  />
								</div>
							
								<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
									<div class = "radiodiv" style="float: left"></div>
									手动开|<input class="value" type="hidden" name="manualControlValues" value="${values[51]}" />
								</div>
								<div class = "radiobox1" onclick="clickradiobox1(this)" style="float:left;">
									<div class = "radiodiv" style="float: left"></div>
									手动关<input class="value" type="hidden" name="manualControlValues" value="${values[52]}" />
								</div>
							</td>
						</tr>
						<c:forEach begin="0" end="1" var="i">
						<tr>
							<td></td>
							<td>
								<c:forEach begin="0" end="2" var="j">
								<div class="checkbox1" onclick="clickcheckbox1(this)" style="float:left;">
									<div class="checkdiv" style="float:left"></div>
									${i*3+j+1} #灯<input class = "value" type="hidden" name="manualControlValues" value="${values[53+ i*3+j ]}" />
								</div>
								</c:forEach>
							</td>
						</tr>
						</c:forEach>
					</table>
				</fieldset>
				</div>
			</fieldset>
		<div style="clear: both"></div>
	</div>	
</div>
</form>
</body>
</html>