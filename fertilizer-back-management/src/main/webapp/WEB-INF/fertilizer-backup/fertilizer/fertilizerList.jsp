<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fertilizer-backup/head.jsp" %>
<!doctype html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>setting - FertilizerList</title>
<style type="text/css">
#bg {
	display: none;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: #000000;
	opacity:0.4; 
	filter: alpha(opacity=40); 
	-moz-opacity: 0.7;
	}
#addDiv {
	display: none;
	position: absolute;
	top:30%;bottom:70%;margin:auto;
	left:0;right:0;
	width: 400px;
	height: 400px;
	background-color: #ffffff;
	}
.input-text {
	width:90%;
	font-size:18px;
	}
</style>
<script type="text/javascript">
$(function(){
	var msg="${msg}";
	if(msg != ""){
		alert(msg)
	}
});

</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 
		<select id="user" name="userId" onchange="changeUser()" style="background-color:#FEFFD0 ">
			<c:forEach items="${userList }" var="user">
				<option value="${user.userId }" <c:if test="${userId == user.userId }">selected="selected"</c:if>>${user.username }</option>
			</c:forEach>
		</select>
	</div>
	<div style="float:right;">
		<input type="button" value="添加施肥机" onclick="addFertilizer()" style="line-height:25px"/>
	</div>
	<div class="clear"></div>
</div>
<div class="body-box">
	<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
		<thead class="pn-lthead">
			<tr>
				
				<th >名称</th>
				<th >地址</th>
				<th >坐标</th>
				<th >描述</th>
				<th >生产日期</th>
				<th >dtu编号</th>
				<th >阀类型</th>
			</tr>
		</thead>
		<tbody id="tbody" class="pn-ltbody"  align="center">
		<c:forEach items="${fertilizerList}" var="fertilizer" >
			<form action="/fertilizer/saveOrUpdateFertilizer.do" method="post">
				<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
					<td align="center">
						<input type="hidden" id="userId" name="userId" value="${userId }"/>
						<input type="hidden" id="fertilizerId"  name="fertilizerId" value="${fertilizer.fertilizerId }" />
						<input type="text" name="fertilizerName" value="${fertilizer.fertilizerName }" style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="addr" value="${fertilizer.addr }" style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="coordinate" value="${fertilizer.coordinate }" style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="desc" value="${fertilizer.desc }" style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="date" name="prodDate" value="${fertilizer.prodDate }" style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="dtuCode" value="${fertilizer.dtuCode }" style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<select id="valveType" name="valveType" style="height: 30px" >
			        		<option value="0" <c:if test="${fertilizer.valveType == 0 }">selected = "selectede" </c:if> >普通阀</option>
			        		<option value="1" <c:if test="${fertilizer.valveType == 1 }">selected = "selectede" </c:if> >天正无线阀</option>
			        	</select>
					</td>
					<td align="center">
						<input type="button" onclick="delFertilizer(${fertilizer.fertilizerId })" value="删除"/>||
						<input type="submit" value="更新"/>
					</td>
				</tr>
			</form>
		</c:forEach>
		</tbody>
	</table>

</div>
<div id="bg"></div>
<div id="addDiv">
	<div style="width: 100%; color: #000000; text-align: center; background-color:#A2D5F3; font-size:18px; height:36px; line-height:36px; vertical-align:middle;">
               添加施肥机
		<div id="btnclose" onclick="cancel();" style="float: right; margin-right:10px; cursor:pointer;" >×</div>
	</div>
	<form action="/fertilizer/saveOrUpdateFertilizer.do" method="post">
		<table style="width: 100%; height:250px;font-size:18px">
			
			<tr>
				
				<td style="text-align: right;">
					<input type="hidden" name="userId" value="${userId }"/>
					名称：
				</td>
				<td ><input type="text" class="input-text" name="fertilizerName" style="height: 30px"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">地址：</td>
				<td ><input type="text" class="input-text" name="addr" style="height: 30px"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">坐标：</td>
				<td ><input type="text" class="input-text" name="coordinate" style="height: 30px"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">描述：</td>
				<td ><input type="text" class="input-text" name="desc" style="height: 30px"/></td>
			</tr>
			
			<tr>
				<td style="text-align: right;">生产日期：</td>
				<td ><input type="date" class="input-text" name="prodDate" style="height: 30px"/></td>
			</tr> 
			<tr>
				<td style="text-align: right;">dtu编号：</td>
				<td ><input type="text" class="input-text" name="dtuCode" style="height: 30px"/></td>
			</tr>
			<tr>
		        <td style="text-align: right;">阀类型:</td>
		        <td >
		        	<select  name="valveType" style="height: 30px" >
		        		<option value="0">有线阀</option>
		        		<option value="1">天正无线阀</option>
		        	</select>
		        </td>
			</tr>
			
			<tr>
		        <td colspan="2" style="text-align: center">
					<input type="button" value="取消" onclick="cancel()"/>
					<input type="submit" value="保存"/>
		        </td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">

function changeUser(){
	var userId = $("#user").val();
	window.location.href="/fertilizer/fertilizerList.do?userId="+userId;
}

function cancel(){
	$("#bg").hide();
	$("#addDiv").hide();
}
function addFertilizer(){
	$("#bg").show();
	$("#addDiv").show();
}
function delFertilizer(id){

	var del = confirm("确定要删除该施肥机吗?");
	if(del){
		var userId = $("#userId").val();
		var fertilizerId = $("#fertilizerId").val();
		$.ajax({
			url:"/fertilizer/delFertilizer.do",
			type:"POST",
			data:{"fertilizerId":id},
			success:function(data){
				if(data != ""){
					alert(data);
				}
				window.location.href ="/fertilizer/fertilizerList.do?fertilizerId="+fertilizerId+"&userId="+userId;
			}
		})
	}
}

function mouseover(i){
	i.bgColor='#eeeeee'
}
function mouseout(i){
	i.bgColor='#ffffff'
} 
function inputOver(i){
	i.style.background="#FEFFD0";
}
function inputOut(i){
	i.style.border="0px";
	i.style.background="none"; 
}

</script>
</body>
</html>