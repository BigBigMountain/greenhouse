<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fertilizer-backup/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>management - user</title>
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
	height:30px;
	font-size:18px;
	}
</style>
<script type="text/javascript">
$(function(){
	var msg="${msg}";
	console.log(msg);
	if(null != msg && msg!=""){
		alert(msg);
	}
});

</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 用户管理 </div>
	<div class="clear"></div>
	<div style="float:right">
		<input type="button" value="添加" onclick="addUser()"/>
	</div>
</div>

<div class="body-box">
	
	<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
		<thead class="pn-lthead">
			<tr>
				<th >用户名</th>
				<th >密码</th>
				<th >姓名</th>
				<th >单位</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody id="tbody" class="pn-ltbody"  align="center">
		<c:forEach items="${userList}" var="user" >
			<form action="/user/saveOrUpdateUser.do" method="post">
				<tr bgcolor="#ffffff" onmouseover="this.bgColor='#C1F2A0'" onmouseout="this.bgColor='#ffffff'">
					<td align="center">
						<input type="hidden" name="userId" value="${user.userId }" />
						<input type="text" name="username" value="${user.username }" 
						style="width:90%;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="password" value="${user.password }" 
						style="width:90%;height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					<td align="center">
						<input type="text" name="realname" value="${user.realname }" 
						style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					
					<td align="center">
						<input type="text" name="company" value="${user.company }" 
						style="height:30px;font-size:18px;border: 0px;background: none;text-align: center; " onmouseover="inputOver(this)" onmouseout="inputOut(this)"/>
					</td>
					
					<td align="center">
						<input type="button" onclick="delUser(${user.userId })" value="删除"/>||<input type="submit" value="保存"/>
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
               添加用户
		<div id="btnclose" onclick="cancel();" style="float: right; margin-right:10px; cursor:pointer;" >×</div>
	</div>
	<form action="/user/saveOrUpdateUser.do" method="post">
		<table style="width: 100%; height:250px;font-size:18px">
		    <tr>
		        <td style="text-align: right;">用户名：</td>
		        <td ><input type="text" class="input-text" name="username"  style="height: 30px"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">密码：</td>
				<td ><input type="text" class="input-text" name="password" style="height: 30px"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">姓名 ：</td>
				<td><input type="text" class="input-text" name="realname" style="height: 30px"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">单位 ：</td>
				<td><input type="text" class="input-text" name="company" style="height: 30px"/></td>
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
function cancel(){
	$("#bg").hide();
	$("#addDiv").hide();
}
function addUser(){
	$("#bg").show();
	$("#addDiv").show();
}
function delUser(id){
	var del = confirm("确定要删除该用户吗?");
	if(del){
		$.ajax({
			url:"/user/delUser.do",
			type:"POST",
			data:{"userId":id},
			success:function(data){
				alert(data);
				window.location.href = "/user/userList.do";
			}
		})
	}
}


function inputOver(i){
	i.style.background="#FEFFD0";
}
function inputOut(i){
	i.style.background="none"; 
}
</script>
</body>
</html>