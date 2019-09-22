<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,innitial-scale=1">  
		<title>温室管理系统  登陆页面</title>
		<script src="/js/jquery.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="css/login.css">
		<script type="text/javascript">
			if(window.self != window.top){
				window.top.location = window.location;
			}
			$(function(){
				//页面加载完成后，将光标定位到账号输入框中
				$("input[name=username]").focus();
			});
		</script>
	</head>
	
	<body>
		<div class="loginbox">
		<div class="loginnav">
			<nav class="navbar navbar-default">
				<div class="container">
					<div class="navbar-header">
						<a class="navbar-brand" href="#"></a>
						<span class="logintitle">温室管理系统</span>
					</div>
				</div>
			</nav>
		</div>
		
		<section class="mainlogin">
			<div class="container">
				<div class="col-md-4 col-md-offset-7 logincontent">
					<h4>用户登录</h4>
					<form class="form-horizontal" id="loginform" name="loginform" method="post" 
							action="userController/login.do">
						<div class="form-group" id="idInputLine">
							<label for="inputPassword3" class="col-sm-3 control-label">账号</label>
							<div class="col-sm-8">
								<input id="loginform:idInput" type="text" name="username" class="form-control" placeholder="请输入手机号/邮箱/用户名">
							</div>
						</div>
						<div class="form-group" id="pwdInputLine">
							<label id="loginform:pwdInput" class="col-sm-3 control-label">密码</label>
							<div class="col-sm-8">
								<input name="password" for="pwdInput" type="password" class="form-control" id="inputaccount" placeholder="请输入您的密码">
							</div>
						</div>
						
						<div class="form-group">
							
							<div class="col-sm-offset-3 col-sm-4">
								<input type="checkbox"><span class="size12">　记住用户名</span>
							</div>
							<div class="col-sm-4">
								<a href="#"><span class="size12 forget">忘记密码</span></a>
							</div>
						</div>
						<div class="col-md-offset-3 col-md-8">
							<a href="javascript:document.getElementById('loginform').submit();"
								 id="loginform:j_id19" name="loginform:j_id19"
								 class="btn btn-danger">立即登录</a>
						</div>
					</form>
				</div>
			</div>
		</section>
		<footer class="clearfix">
			<div class="container">
		<!-- 
			<p class="text-center">北京蓝洋益海科技有限公司  <!--  版权所有 2008-2017  京ICP备12037404号 </p>
			<p class="text-center">地址：北京市昌平区回龙观镇高新四街6号院1号楼4层404、406室      邮编：102206</p>
			<p class="text-center">电话：010-52801446 13911879234       传真：010-52801447</p>
			<p class="text-center">蓝洋益海现代农业物联网信息化系统领航者</p>
			<p class="text-center">农业物联网及水肥一体化专业供应商</p>
		 -->
			</div>
		</footer>
		</div>
	</body>
</html>