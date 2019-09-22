<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>微雾</title>
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
	window.location.href="/controllerSetting/toFog.do?controllerId="+controllerId;
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
<form action="/controllerSetting/updateFog.do" method="post">
<div class="box-positon">
	<div class="rpos">当前位置:微雾</div>
	<select name="controllerId" id="controllerId" onchange="changeController()" > 
		<c:forEach items="${controllers }" var="controller">
			<option value="${controller.controllerId }" <c:if test="${controllerId == controller.controllerId }">selected = "selected"</c:if> style="" >${controller.houseName }</option>
		</c:forEach>
	</select>
	<div style="float: right;margin-right: 50px;">
		<input type="submit" value="确定"/>
	</div>
</div>

<div class="body-box" >
	<div style="background-color: #F0F7FD;margin:auto;width:820;">
		<div style="float: left;font-size: 20px;background-color: #AAD7F3;margin: 20px;border: 1px solid blue;">
			<table cellspacing="5" cellpadding="1" border="0"  style="font-size: 20px;">
				<tr>
					<td>
						<div class="checkbox1" onclick="clickcheckbox1(this)" style="float:left;">
							<div class="checkdiv" style="float:left;" ></div>
							有效性:<input class = "value" type="hidden" name="fogValues" value="${values[0]}"/>
						</div>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						输出通道:<input type="text" name="fogValues" value="${values[1] }" class="target"/>
					</td>
				</tr>
				<tr><td>高压微雾加湿间歇工作：开<input type="text" name="fogValues" value="${values[2] }" class="target"/>秒,
						关<input type="text" name="fogValues" value="${values[3] }" class="target"/>秒.</td></tr>
				<tr><td>当室内湿度&nbsp;&lt;目标湿度-偏置湿度<input type="text" name="fogValues" value="${values[4] }" class="target"/>%,高压微雾加湿条件成立,
						确认时间:<input type="text" name="fogValues" value="${values[5] }" class="target"/>秒.</td></tr>
				<tr><td>当室内湿度&nbsp;&gt;=目标湿度-偏置湿度+滞回湿度<input type="text" name="fogValues" value="${values[6] }" class="target"/>%,微雾加湿完毕,
						确认时间:<input type="text" name="fogValues" value="${values[7] }" class="target"/>秒.</td></tr>
				<tr><td>高压微雾加湿时,室内温度降低到&nbsp;&lt;=目标温度-<input type="text" name="fogValues" value="${values[8] }" class="target"/>℃,微雾自动终止加湿.</td></tr>
				<tr><td>高压微雾加湿时,将自动关闭通风窗,终止加湿后继续保持通风窗关闭状态<input type="text" name="fogValues" value="${values[9] }" class="target"/>秒.</td></tr>
			</table>
		</div>
		<div style="clear: both;"></div>
	</div>	
</div>
</form>
</body>
</html>