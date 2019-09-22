<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fertilizer/head.jsp" %>
<!doctype html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<!-- <meta http-equiv="refresh" content="60"> -->
<script src="/js/jquery-1.11.3.min.js"></script>

<title>fertilizerData-list</title>
<style>
.open{
	background:url('/images/button/open2.png');
	background-size:100% 100%;
	background-position:center center;
	width:140px;
	height:80px;
	line-height:80px;
	font-size:18px;
	text-align:center;
}
.close{
	background:url(/images/button/close2.png);
	background-size:100% 100%;
	background-position:center center;
	width:140px;
	height:80px;
	line-height:80px;
	font-size:18px;
	text-align:center;
}
</style>
<script type="text/javascript">
$(function(){
	
	var allInput = $(".data");
	allInput.each(function(index,element){
		/* console.log(element.parentElement); */
		var value = element.value;
		if(value == 1){
			element.parentElement.className="open"
		}
		if(value ==0){
			element.parentElement.className="close"
		}
	})
}) 
function changeFertilizer(){
	var fertilizerId = $("#fertilizerId").val();
	//alert(fertilizerId);
	window.location.href="/fertilizer/toControl.do?fertilizerId="+fertilizerId;
}

function download(){
	var fertilizerId = $("#fertilizerId").val();
	$.ajax({
		url:"/fertilizerData/downloadData.do",
		data:{"fertilizerId":fertilizerId}
	})
}

function changestate(name){

	var value = $("#"+name).val();

	if(value==1){
		$("#"+name).parent().attr("class","close");
		$("#"+name).val(0);
	}else{
		$("#"+name).parent().attr("class","open");
		$("#"+name).val(1)
	}
}

function submitValve(thisDiv){
	var fertilizerId = ${fertilizerId };
	var valveDatas = $(".valveData");

	var temp = thisDiv.children[1].value;
	valveDatas.each(function(index,element){
		element.value=0;
		//element.parentElement.className="close";
	})
	if(temp == 1){
		thisDiv.children[1].value = 0;
	}else if(temp == 0){
		thisDiv.children[1].value = 1;
		thisDiv.className="open";
	}
	
	$.ajax({
		url:'/fertilizer/ManualControl.do',
		data:{"fertilizerId":fertilizerId,"valveNum":thisDiv.children[1].name,"value":thisDiv.children[1].value},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.msg != null){
				alert(data.msg)
			}
			window.location.href="/fertilizer/toControl.do?fertilizerId=${fertilizerId}";
		}
	})
}

function submitProgram(thisDiv){
	var fertilizerId = ${fertilizerId };
	var programDatas = $(".programData");

	var temp = thisDiv.children[1].value;
	programDatas.each(function(index,element){
		element.value=0;
		element.parentElement.className="close";
	})
	if(temp == 1){
		thisDiv.children[1].value = 0;
	}else if(temp == 0){
		thisDiv.children[1].value = 1;
		thisDiv.className="open";
	}
	
	$.ajax({
		url:'/fertilizer/programControl.do',
		data:{"fertilizerId":fertilizerId,"programNum":thisDiv.children[1].name,"value":thisDiv.children[1].value},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.msg != null){
				alert(data.msg);
			}
			window.location.href="/fertilizer/toControl.do?fertilizerId=${fertilizerId}";
		}
	})
}
</script>
</head>
<body >

<div class="box-positon" style="background-color: #F6FAFB">
	<div class="rpos">当前位置: 实时数据</div>
	<div class="clear"></div>
</div>
<div style="background-color: #F0F0F0">
<div class="">
		当前施肥机:
		<select id="fertilizerId" name="fertilizerId"  onchange="changeFertilizer()"> 
			<c:forEach items="${fertilizers }" var="fertilizer">
				<option value="${fertilizer.fertilizerId }" <c:if test="${fertilizerId == fertilizer.fertilizerId }">selected="selected"</c:if>>${fertilizer.fertilizerName }</option>
			</c:forEach>
		</select>
</div>
	
	<div class="top" style="float: left;margin-top:20px;margin-left:20px;border: 1px solid #7F9DB9">
	<fieldset style="border:1px solid red">
		<legend><h1> 按程序控制</h1></legend>
		<form id="programForm">
			<input type="hidden" name="fertilizerId" value="${fertilizerId }">
			<c:forEach items="${programs }" var="program" begin="0" end="7" varStatus="status">
				<div style="float:left;width:200;margin-left: 100px;" onclick="submitProgram(this)">
					<b>程序${status.count }</b><input id="program${status.count }" class="programData data" type="hidden" name="${status.count }" value="${program}">
				</div>
				<c:if test="${status.count == 4 }">
					<div style="clear: left"></div>
				</c:if>
			</c:forEach>
		</form>
	</fieldset>
	<fieldset style="border:1px solid red;">
		<legend><h1>手动控制</h1></legend>
		<input type="hidden" name="fertilizerId" value="${fertilizerId }">
		<%-- <div >	
			<fieldset style="border:3px solid #7F9DB9">
				<legend><h1>水泵</h1></legend>
				<div style="float:left;width:200;" onclick="changestate('irrigWaterPump')">
					<b>灌溉水泵</b><input class="data" id="irrigWaterPump" type="hidden" name="irrigWaterPump" value="${manualControlData.irrigWaterPump }">
				</div>
				<div style="float:right;width:200;"  onclick="changestate('inWaterPump')">
					<b>进水水泵</b><input class="data" id="inWaterPump" type="hidden" name="inWaterPump" value="${manualControlData.inWaterPump }">
				</div>
			</fieldset>
		</div> --%>
		
		
		<fieldset  style="border:3px solid #7F9DB9;">
			<legend><h1>灌溉阀</h1></legend>
			<c:forEach items="${irrigValves }" begin="0" end="59" var="valve" varStatus="status">
			<div style="float: left;"  onclick="submitValve(this)">
				<b>${status.count }#阀</b><input class="valveData data" id="irrigValve${status.count }" type="hidden" name="${status.count }" value="${valve}">
			</div>
			<c:if test="${status.count % 10 == 0 }">
				<div style="clear:left"></div>
			</c:if>
			</c:forEach>
		</fieldset>
		
		
	</fieldset>
		
		
	</div>
	<div style="clear: both"></div>
</div>
<div >
	<img src="/images/logo/logo1.png" width="100%">
</div>
</body>
</html>