<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/portal/head.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>历史数据</title>
<link rel="stylesheet"
	href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
<link href="/css/foundation.min.css" rel="stylesheet" type="text/css">
<link href="/css/foundation-datepicker.css" rel="stylesheet"
	type="text/css">
<script src="/js/jquery-1.11.3.min.js"></script>
<script src="/js/foundation-datepicker.js"></script>
<script src="/js/locales/foundation-datepicker.zh-CN.js"></script>
<!-- 	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> -->
<script src="/highcharts/code/highcharts.js"></script>
<script src="/highcharts/code/modules/exporting.js"></script>
<script
	src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
<style>
.container {
	margin: 0 auto;
	max-width: 960px;
	padding: 20px;
	background-color: #AADEF6
}
</style>
</head>
<body>

	<div class="container">
		<div style="float: left;margin:15 40">
			<form id="dataForm11" style="padding-top: 5px;">
				<table class="table">
					<thead>
						<tr>
							<th>起始日期: <input type="text" id="start" name="start"
								class="span2"
								value="<fmt:formatDate type="date" value="${start }" pattern="MM/dd/yyyy"/>">
							</th>
							<th>截止日期: <input type="text" id="end" name="end"
								class="span2"
								value="<fmt:formatDate type="date" value="${end }" pattern="MM/dd/yyyy"/>">
							</th>
						</tr>
					</thead>
				</table>
			</form>
		</div>
		<div style="float: right; margin: 15 40">
			<table>
				<tr>
					<td><a onclick="changeChart('getTemperatureList','室外温度','温度/ ℃')">温度</a></td>
					<td><a onclick="changeChart('getLightingList','室外光照强度','光照强度/ LUX')" >光照</a></td>
					<td><a onclick="changeChart('getWindSpeedList','风速','风速/ m/s')" >风速</a></td>
					<td><a onclick="changeChart('getPHList','PH值','PH值')" >PH</a></td>
				</tr>
				<tr>
					<td><a onclick="changeChart('getHumidityList','室外湿度','湿度/ RH')" >湿度</a></td>
					<td><a onclick="changeChart('getPressureList','大气压','气压/ Pa')" >气压</a></td>
					<td><a onclick="changeChart('getRainFallList','降雨量','降雨量/ mm')" >降雨量</a></td>
					<td><a onclick="changeChart('getPM25List','PM2.5','PM2.5/ ug/m³')" >PM2.5</a></td>
				</tr>
			</table>
		</div>
		<div style="clear: both"></div>
	</div>
	
	<div class="dataChart" id="dataChart" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		
	<%@ include file="historyChart/changeChart.jsp"%> 
	<%@ include file="historyChart/index.jsp"%> 
<script>
	var nowTemp = new Date();
	var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp
			.getDate(), 0, 0, 0, 0);
	var checkin = $('#start').fdatepicker({
		onRender : function(date) {
			return date.valueOf() < now.valueOf() ? '' : '';
		}
	}).on('changeDate', function(ev) {
		if (ev.date.valueOf() > checkout.date.valueOf()) {
			var newDate = new Date(ev.date)
			newDate.setDate(newDate.getDate() + 1);
			checkout.update(newDate);
		}
		checkin.hide();
		$('#end')[0].focus();
	}).data('datepicker');
	var checkout = $('#end')
			.fdatepicker(
					{
						onRender : function(date) {
							return date.valueOf() <= checkin.date.valueOf() ? 'disabled'
									: '';
						}
					}).on('changeDate', function(ev) {
				checkout.hide();
			}).data('datepicker');
</script>

</body>
</html>