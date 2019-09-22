<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/portal/head.jsp"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/highcharts/code/highcharts.js"></script>
<script src="/highcharts/code/modules/exporting.js"></script>
<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>

	<link href="/css/foundation.min.css" rel="stylesheet" type="text/css">
	<link href="/css/foundation-datepicker.css" rel="stylesheet" type="text/css">
	<script src="/js/foundation-datepicker.js"></script>
	<script src="/js/locales/foundation-datepicker.zh-CN.js"></script>	
<title>houseData-list</title>
<style>
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
<script type="text/javascript">
	function changeHouse() {
		var houseId = $("#houseId").val();
		window.location.href = "/houseData/historyList.do?houseId=" + houseId;
	}

	function download() {
		var houseId = $("#houseId").val();
		$.ajax({
			url : "/houseData/downloadData.do",
			data : {
				"houseId" : houseId
			}
		})
	}
	
</script>
</head>
<body>
	<div style="background-color: #F6FAFB">
		<div style="width:auto">
			<div style="width:auto; display:inline-block !important; display:inline;margin:0px auto">
			<form id="dataForm11" action="/houseData/downloadSensorData.do" method="post" >
				<table >
					<thead>
						<tr>
							<th>温室编号:
								<select id="houseId" name="houseId" onchange="changeHouse()"> 
									<c:forEach items="${houses }" var="house">
										<option value="${house.id }" <c:if test="${houseId == house.id }">selected="selected"</c:if>>${house.name }</option>
									</c:forEach>
								</select>
							</th>
							<th>起始日期:
								<input type="text" class="span2" value="<fmt:formatDate type="date" value="${start }" pattern="MM/dd/yyyy"/>" id="start" name="start" >
							</th>
							<th>截止日期:
								<input type="text" class="span2" value="<fmt:formatDate type="date" value="${end }" pattern="MM/dd/yyyy"/>" id="end" name="end">
							</th>
							<th>
								<input type="submit" class="download"  value="导出" />
							</th>
						</tr>
					</thead>
				</table>
			</form>
			</div>
		</div>
	
		<div >
			<table>
				<tr>
					<c:forEach items="${sensorList}" var="sensor" varStatus="status">
						<td class="col4" onclick="getSensorChartsData(${sensor.id},'${sensor.name }','${sensor.type.unit }')">
							<div class="p" >
								<div class="c">
									<div class="data">
										<div class="dataLeft">
											<img src="/images/weather/${sensor.type.image }.png"
												width="30px" height="30px" />
										</div>
										<div class="dataRight">${sensor.name }</div>
										<div style="clear: both;"></div>
									</div>
									
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
</body>
<script>
	$(function(){
		getSensorChartsData(${sensorList[0].id},'${sensorList[0].name}','${sensorList[0].type.unit}')
	})

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
</html>