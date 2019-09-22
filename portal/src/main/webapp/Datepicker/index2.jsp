<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ include file="/WEB-INF/portal/head.jsp" %> --%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>历史数据</title>
	<link rel="stylesheet" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	<link href="/css/foundation.min.css" rel="stylesheet" type="text/css">
	<link href="/css/foundation-datepicker.css" rel="stylesheet" type="text/css">
	<script src="/js/jquery-1.11.3.min.js"></script>
	<script src="/js/foundation-datepicker.js"></script>
	<script src="/js/locales/foundation-datepicker.zh-CN.js"></script>	
	<!-- <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> -->
	<script src="/highcharts/code/highcharts.js"></script>
	<script src="/highcharts/code/modules/exporting.js"></script>
	<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
	<style>
	.container { margin:0 auto;  max-width:960px;padding:20px;}
	</style>
</head>
<body>
<div class="container">
	<form action="/houseData/historyList.do" method="post" style="padding-top:5px;">
		<table class="table">
			<thead>
				<tr>
					<th>温室编号:
						<select name="houseId"> 
							<option id="houseId" >请选择温室</option>
							<c:forEach items="${houses }" var="house">
								<option value="${house.id }" <c:if test="${houseId == house.id }">selected="selected"</c:if>>${house.houseName }</option>
							</c:forEach>
						</select>
					</th>
					<th>起始日期:
						<input type="text" class="span2" value="" id="dpd1" name="start">
					</th>
					<th>截止日期:
						<input type="text" class="span2" value="" id="dpd2" name="end">
					</th>
				</tr>
			</thead>
		</table>
		<input type="submit" class="query" value="查询"/>
	</form>
	<table>
			<tr>
				<td><div><a onclick = "temperature1()">温度1：${houseData.temperature }</a></div></td>
				<td><a onclick = "humidity1()">湿度1：${houseData.humidity }</a></td>
				<td><a onclick = "lighting()">光照：${houseData.lighting }</a></td>
				<td><a onclick = "soilTemperature()">土壤温度：${houseData.soilTemperature }</a></td>
			</tr>
			<tr>
				<td><a onclick = "temperature2()">温度2：${houseData.temperature2 }</a></td>
				<td><a onclick = "humidity2()">湿度2：${houseData.humidity2 }</a></td>
				<td><a onclick = "co2()">CO2：${houseData.co2 }</a></td>
				<td><a onclick = "soilHumidity()">土壤湿度：${houseData.soilHumidity }</a></td>
			</tr>
		</table>
</div>
	
<%-- 	<%@ include file="chart/index.jsp" %>
	<%@ include file="chart/temperature1.jsp" %>
	<%@ include file="chart/temperature2.jsp" %>
	<%@ include file="chart/humidity1.jsp" %>
	<%@ include file="chart/humidity2.jsp" %>
	<%@ include file="chart/lighting.jsp" %>
	<%@ include file="chart/co2.jsp" %>
	<%@ include file="chart/soilTemperature.jsp" %>
	<%@ include file="chart/soilHumidity.jsp" %> --%>
	
<script>
	var nowTemp = new Date();
	var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
	var checkin = $('#dpd1').fdatepicker({
			onRender: function (date) {
				return date.valueOf() < now.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function (ev) {
			if (ev.date.valueOf() > checkout.date.valueOf()) {
				var newDate = new Date(ev.date)
				newDate.setDate(newDate.getDate() + 1);
				checkout.update(newDate);
			}
			checkin.hide();
			$('#dpd2')[0].focus();
		}).data('datepicker');
	var checkout = $('#dpd2').fdatepicker({
			onRender: function (date) {
				return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function (ev) {
			checkout.hide();
		}).data('datepicker');
</script>
</body>
</html>