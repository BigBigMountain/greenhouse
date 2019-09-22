<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>controllerSetting-left</title>
<link href="/css/admin.css" rel="stylesheet" type="text/css"/>
</head>
<body class="lbody">
<div class="left">
<%@ include file="../date.jsp" %>
     <ul class="w-lful">
		<li><a href="/controllerSetting/controllerList.do" target="rightFrame">控制器设置</a></li>
		<li><a href="/controllerSetting/toTargetValue.do" target="rightFrame">目标值</a></li>
		<!-- <li><a href="/controllerSetting/toTime.do" target="rightFrame">时间</a></li> -->
		<li><a href="/controllerSetting/toWarning.do" target="rightFrame">警告</a></li>
		<li><a href="/controllerSetting/toWindow1.do" target="rightFrame">通风窗</a></li>
		<li><a href="/controllerSetting/toExSunshade.do" target="rightFrame">外遮阳</a></li>
		<li><a href="/controllerSetting/toInSunshade.do" target="rightFrame">内遮阳</a></li>
		<li><a href="/controllerSetting/toFanAndPad.do" target="rightFrame">风机湿帘</a></li>
		<li><a href="/controllerSetting/toLight.do" target="rightFrame">补光灯</a></li>
		<li><a href="/controllerSetting/toCO2.do" target="rightFrame">CO2补气</a></li>
		<li><a href="/controllerSetting/toSirculatingFan.do" target="rightFrame">环流风扇</a></li>
		<li><a href="/controllerSetting/toIrrigation1.do" target="rightFrame">灌溉控制</a></li>
		<li><a href="/controllerSetting/toUpSpray.do" target="rightFrame">上喷喷淋</a></li>
		<li><a href="/controllerSetting/toFog.do" target="rightFrame">微雾</a></li>
		<li><a href="/controllerSetting/toWarming.do" target="rightFrame">加热设备</a></li>
		<li><a href="/controllerSetting/toManualControl.do" target="rightFrame">手动操作</a></li>
		<li><a href="/controllerSetting/warning.do" target="rightFrame">IP设置</a></li>
		<li><a href="/controllerSetting/warning.do" target="rightFrame">传感器校准</a></li>
     </ul>
</div>
</body>
</html>