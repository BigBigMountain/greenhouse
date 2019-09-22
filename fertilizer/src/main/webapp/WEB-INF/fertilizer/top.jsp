<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="C"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>lyyh User's Control Panel - powered by lyyh</title>
<link href="/css/admin.css" rel="stylesheet" type="text/css" />
<link href="/css/theme.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery.validate.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery.treeview.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery.ui.css" rel="stylesheet" type="text/css" />

<script src="/js/jquery.js" type="text/javascript"></script>
<script src="/js/jquery.ext.js" type="text/javascript"></script>
<script src="/js/jquery.form.js" type="text/javascript"></script>
<script src="/js/admin.js" type="text/javascript"></script>
<style type="text/css">
* { margin: 0; padding: 0 }
a:focus { outline: none; }
html { height: 100%; overflow: hidden; }
body {height: 100%;}
#top {background-color: #1d63c6;height: 69px;width: 100%;}
.logo {width: 215px;height: 69px;}
.topbg {background: url(/images/admin/top-tbg.png) no-repeat;height: 69px;}
.login-welcome {padding-left: 20px;color: #fff;font-size: 12px;height: 69px;background: url(/images/admin/topbg.gif) no-repeat;background-size:100% 100%;}
.login-welcome a:link, .login-welcome a:visited {color: #fff;text-decoration: none;}
#welcome {color: #FFFFFF;padding: 0 30px 0 5px;}
#logout {color: #FFFFFF;padding-left: 5px;}
.top-bottom {width: 100%;background: url(/images/admin/bg.png) repeat-x 0px -34px;height: 3px;}
.undis {display: none;}
.dis {display: block;}
</style>

</head>
<body>
	<div id="top">
		<div class="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="215">
						<div class="logo">
							<img src="/images/admin/logo.jpg" width="215" height="69"
								style="background-color: #4CA4E1" />
						</div>
					</td>
					<td valign="top">
						<div class="topbg">
							<div class="login-welcome">
								<table width="100%" hight="69" border="0" cellspacing="0" cellpadding="0">
									<tr>
										
										<td width="420" height="69">
											<img src="/images/admin/welconlogin-icon.png" />
											<span id="welcome">您好:${loginUser.username }!</span> 
											<img src="/images/admin/loginout-icon.png" />
											<a href="/userController/logout.do" target="_top" id="logout" 
												onclick="return confirm('您确定退出吗?');">退出</a>  
											<img src="/images/admin/message-unread.png" />&nbsp;
											<a href="message/v_list.do" target="rightFrame">你有
												<span id="countDiv">0</span>条信息未读</a>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="top-bottom"></div>
</body>
</html>