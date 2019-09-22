<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fertilizer/head.jsp" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>历史数据</title>
	<link rel="stylesheet" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	<link href="/css/datepicker/foundation.min.css" rel="stylesheet" type="text/css">
	<link href="/css/datepicker/foundation-datepicker.css" rel="stylesheet" type="text/css">
	<script src="/js/jquery-1.11.3.min.js"></script>
	<script src="/js/datepicker/foundation-datepicker.js"></script>
	<script src="/js/datepicker/foundation-datepicker.zh-CN.js"></script>	
<!-- 	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> -->
	<script src="/js/highcharts/highcharts.js"></script>
	<script src="/js/highcharts/exporting.js"></script>
	<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
	<style>
	.container { margin:0 auto;  max-width:960px;padding:20px;}
	</style>
</head>
<body>

<div class="container">
	<form id="dataForm11" action="/fertilizer/getHistoryData.do" method="post" style="padding-top:5px;">
		<table class="table">
			<thead>
				<tr>
					<th>当前施肥机:
						<select name="fertilizerId" id="fertilizerId"> 
							<c:forEach items="${fertilizers }" var="fertilizer">
								<option value="${fertilizer.fertilizerId }" <c:if test="${fertilizerId == fertilizer.fertilizerId }">selected="selected"</c:if>>${fertilizer.fertilizerName }</option>
							</c:forEach>
						</select>
					</th>
					<th>起始日期:
						<input type="text" class="span2" value="" id="start" name="start">
					</th>
					<th>截止日期:
						<input type="text" class="span2" value="" id="end" name="end">
					</th>
					<th>
						<!-- <input type="submit" class="download"  value="导出" /> -->
					</th>
				</tr>
			</thead>
		</table>
		
	</form>
	<table>
		<tr>
			<td><a onclick = "changeCharts('ec','us/cm')">EC：${historyData.ec }</a></td>
			<td><a onclick = "changeCharts('ph','')">PH：${historyData.ph }</a></td>
			<td><a onclick = "changeCharts('rateFlow','m³/h')">流量：${historyData.rateFlow }</a></td>
			<td><a onclick = "changeCharts('soilHumidity1','℃')">土湿：${historyData.soilHumidity1 }</a></td>
			<td><a onclick = "changeCharts('liquidLevel','cm')">液位：${historyData.liquidLevel }</a></td>
			<td><a onclick = "changeCharts('totalIrrigation','m³/h')">总流量：${historyData.totalIrrigation }</a></td>
		</tr>
	</table>
</div>
	<div id="highcharts" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

	<%@ include file="changeCharts.jsp" %>
  	
<script>
	var nowTemp = new Date();
	var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
	var checkin = $('#start').fdatepicker({
			onRender: function (date) {
				return date.valueOf() < now.valueOf() ? '' : '';
			}
		}).on('changeDate', function (ev) {
			if (ev.date.valueOf() > checkout.date.valueOf()) {
				var newDate = new Date(ev.date)
				newDate.setDate(newDate.getDate() + 1);
				checkout.update(newDate);
			}
			checkin.hide();
			$('#end')[0].focus();
		}).data('datepicker');
	var checkout = $('#end').fdatepicker({
			onRender: function (date) {
				return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function (ev) {
			checkout.hide();
		}).data('datepicker');
</script>

</body>
</html>