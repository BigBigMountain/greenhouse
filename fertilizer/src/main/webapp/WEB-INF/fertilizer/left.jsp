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
<%@ include file="date.jsp" %>
     <ul class="w-lful">
		<li><a href="timeData.do" target="rightFrame">实时数据</a></li>
		<li><a href="historyData.do" target="rightFrame">历史数据</a></li>
		<li><a href="toControl.do" target="rightFrame">操作控制</a></li>
		<li><a href="getValveRecord.do" target="rightFrame">灌溉记录</a></li>
		<li><a href="getIrrigationStatistics.do" target="rightFrame">灌溉统计</a></li>
     </ul>
</div>
</body>
</html>