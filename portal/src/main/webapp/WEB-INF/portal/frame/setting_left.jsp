<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>equipment-left</title>
<link href="/css/admin.css" rel="stylesheet" type="text/css"/>
</head>
<body class="lbody">
<div class="left">
<%@ include file="../date.jsp" %>
     <ul class="w-lful">
		<li><a href="/setting/houseSettingList.do" target="rightFrame">温室数据采集设置</a></li>
		<li><a href="/setting/climaticSettingList.do" target="rightFrame">气象站数据采集设置</a></li>
		<li><a href="/led/listAll.do" target="rightFrame">LED显示设置</a></li>
		<li><a href="/screem8/listAll.do" target="rightFrame">8字屏设置</a></li>
     </ul>
</div>
</body>
</html>