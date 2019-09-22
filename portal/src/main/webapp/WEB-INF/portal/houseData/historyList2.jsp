<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/portal/head.jsp" %>
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
<!-- 	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> -->
	<script src="/highcharts/code/highcharts.js"></script>
	<script src="/highcharts/code/modules/exporting.js"></script>
	<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
<style>
.container { margin:0 auto;  max-width:960px;padding:20px;}
.top {
	overfloat: hidden;
	font-weight: bold;
}

.col4 {
	width: 25%;
}

.data {
	float: left;
	position: relative;
	left: 50%;
}

.dataLeft {
	float: left;
	height: 30px;
	position: relative;
	left: -50%;
}

.dataRight {
	float: left;
	height: 30px;
	line-height: 30px;
	text-align: center;
	font-size: 20px;
	position: relative;
	left: -50%;
}

.dataClear {
	clear: both;
	text-align: center;
	font-size: 25px;
}

.p {
	position: relative;
	margin: 5px 9px;
	width: fit-content;
}

.p .c {
	width: 140px;
	height: 60px;
	background: #B6F157;
	border-radius: 15px;
	border-style: ridge;
	border-width: 8px;
	border-color: #ee7507;
}
</style>
	
</head>
<body>

<div class="container">
	<form id="dataForm11" action="/houseData/downloadData.do" method="post" style="padding-top:5px;">
		<table class="table">
			<thead>
				<tr>
					<th>温室编号:
						<select name="houseId"> 
							<c:forEach items="${houses }" var="house">
								<option value="${house.id }" <c:if test="${houseId == house.id }">selected="selected"</c:if>>${house.name }</option>
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
						<input type="submit" class="download"  value="导出" />
					</th>
				</tr>
			</thead>
		</table>
	</form>
	
	<div style="background-color: #F6FAFB">
		<div class="top"
			style="float: left; margin-top: 5px; margin-left: 5px; border: 1px solid #7F9DB9">
			<table>
				<tr>
					<c:forEach items="${sensorDataList}" var="sensorData" varStatus="status">
						<td class="col4" onclick="getSensorChartsData(${sensorData.sensorId},'${sensorData.sensorName }','${sensorData.unit }')">
							<div class="p" >
								<div class="c">
									<div class="data">
										<div class="dataLeft">
											<img src="/images/weather/${sensorData.image }.png"
												width="30px" height="30px" />
										</div>
										<div class="dataRight">${sensorData.sensorName } (${sensorData.unit })</div>
										<div style="clear: both;"></div>
									</div>
									<div class="dataClear">${sensorData.value }</div>
								</div>
							</div>
						</td>
						<c:if test="${status.count % 9 == 0}">
							</tr>
							<tr>
						</c:if>
					</c:forEach>
				</tr>
			</table>
		</div>
		<div style="clear: both"></div>
		<%@ include file="changeCharts.jsp" %>
	</div>
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