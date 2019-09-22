<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>climatic-left</title>
<link href="/css/admin.css" rel="stylesheet" type="text/css"/>

</head>
<body class="lbody">
<div class="left">
<%@ include file="../date.jsp" %>
     <ul class="w-lful">
		<li><a href="/gateway/listAll.do" target="rightFrame">网关管理</a></li>
		<li><a href="/sensor/typeList.do" target="rightFrame">传感器类别管理</a></li>
		<li><a href="/sensor/list.do" target="rightFrame">传感器管理</a></li>
		<li><a href="/sensor/climaticSettingList.do" target="rightFrame">气象站数据采集设置</a></li>
     </ul>
</div>
</body>
</html>