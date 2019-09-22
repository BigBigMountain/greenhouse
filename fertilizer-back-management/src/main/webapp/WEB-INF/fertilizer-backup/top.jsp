<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="C"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>lyyh User's Control Panel - powered by lyyh</title>
<link href="/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="/css/theme.css" rel="stylesheet" type="text/css"/>

<script src="/js/jquery.js" type="text/javascript"></script>
<script src="/js/jquery.ext.js" type="text/javascript"></script>
<style type="text/css">        
*{margin:0;padding:0}
a:focus {outline:none;}
html{height:100%;overflow:hidden;}
body{height:100%;}
#top{ background-color:#1d63c6; height:69px; width:100%;}
.logo{width:215px; height:69px;}
.topbg{background:url(/images/admin/top-tbg.png) no-repeat; height:38px;}
.login-welcome{padding-left:20px; color:#fff; font-size:12px;background:url(/images/admin/topbg.gif) no-repeat;}
.login-welcome a:link,.login-welcome a:visited{color:#fff; text-decoration:none;}

#welcome {color: #FFFFFF;padding: 0 30px 0 5px;}
#logout {color: #FFFFFF; padding-left: 5px;}

.nav{height:31px; overflow:hidden;}
.nav-menu{background:url(/images/admin/bg.png) repeat-x; height:31px; list-style:none; padding-left:20px; font-size:14px;}
.nav .current {background: url(/images/admin/navcurrbg.gif) no-repeat 0px 2px; color:#fff; width:72px; text-align:center;} 
.nav .current a{color:#fff;}
.nav-menu li {height:31px;text-align:center; line-height:31px; float:left; }
.nav-menu li a{color:#2b2b2b; font-weight:bold;}
.nav-menu li.sep{background: url(/images/admin/step.png) no-repeat; width:2px; height:31px; margin:0px 5px;}
.nav .normal{width:72px; text-align:center;}
.top-bottom{width:100%; background: url(/images/admin/bg.png) repeat-x 0px -34px; height:3px;}
.undis{display:none;}
.dis{display:block;}
</style>

<script type="text/javascript">
function HoverLi(id){
	$("li[id^='tb_']").each(function(){
		$(this).removeClass("current");
		$(this).addClass("normal");
	});
	$("#tb_"+id).removeClass("normal");
	$("#tb_"+id).addClass("current");
}
</script>
</head>
<body>
<div id="top">
     <div class="top">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="215"><div class="logo"><img src="/images/admin/logo.jpg" width="215" height="69" style="background-color: #4CA4E1"/></div></td>
            <td valign="top">
                <div class="topbg">
                     <div class="login-welcome">
                             <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td width="420" height="38">
                                    <img src="/images/admin/welconlogin-icon.png"/><span id="welcome">您好:${loginUser.username }!</span>
                                    <img src="/images/admin/loginout-icon.png"/><a href="/user/logout.do" target="_top" id="logout" onclick="return confirm('您确定退出吗?');">退出</a> 
                                    <img src="/images/admin/message-unread.png"/>&nbsp;<a href="message/v_list.do" target="rightFrame">你有<span id="countDiv">0</span>条信息未读</a>
                                    </td>
                                  </tr>
                                </table>
                       </div>  
                     <div class="nav">
                     	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td style="background-image:url('/images/admin/nav-left.png')" width="14" height="31"></td>
                                <td>
                                	<ul class="nav-menu">
                                    	<li class="current" id="tb_11" onclick="HoverLi(11);"><a href="main.do" target="mainFrame">首页</a></li>
										<li class="sep"></li><li class="normal" id="tb_18" onclick="HoverLi(18);"><a href="/user/userList.do" target="mainFrame">用户管理</a></li>
										<li class="sep"></li><li class="normal" id="tb_19" onclick="HoverLi(19);"><a href="/fertilizer/fertilizerList.do" target="mainFrame">施肥机管理</a></li>
										<li class="sep"></li><li class="normal" id="tb_15" onclick="HoverLi(15);"><a href="/fertilizer-backup/equipmentMain.do" target="mainFrame">设备管理</a></li>
                                    </ul>
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