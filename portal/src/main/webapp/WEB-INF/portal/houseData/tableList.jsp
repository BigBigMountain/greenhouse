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

	<!-- <link href="/css/foundation.min.css" rel="stylesheet" type="text/css"> -->
	<link href="/css/foundation-datepicker.css" rel="stylesheet" type="text/css">
	<script src="/js/foundation-datepicker.js"></script>
	<script src="/js/locales/foundation-datepicker.zh-CN.js"></script>	
<title>houseData-list</title>

<script type="text/javascript">
	

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
	<div class="box-positon" style="background-color: #F6FAFB">
		<div class="rpos">当前位置: 室内信息</div>
		<div style="margin-left: 80px; float: left ">
			<form id="dataForm2" action="/houseData/tableList.do" method="post">
				温室编号: 
				<select id="houseId" name="houseId"  style="width:100">
					<c:forEach items="${houses }" var="house">
						<option value="${house.id }"
							<c:if test="${houseId == house.id }">selected="selected"</c:if>>${house.name }</option>
					</c:forEach>
				</select> 
				
				起始日期:
				<input type="text"  value="<fmt:formatDate type="date" value="${start }" pattern="MM/dd/yyyy"/>" id="start" name="start" style="width:100">
				截止日期:
				<input type="text"  value="<fmt:formatDate type="date" value="${end }" pattern="MM/dd/yyyy"/>" id="end" name="end" style="width:100">
				<input type="submit" class="submit" value="确定" />
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<table cellspacing="1" cellpadding="0"  border="0" class="pn-ltable" style="width:${colSize * 100}">
			<thead class="pn-lthead">
				<tr>
					<th width="150">时间</th>
				<c:forEach items="${sensorList}" var="sensor">
					<th width="100">${ sensor.name} /${sensor.type.unit } </th>
				</c:forEach>
				</tr>
			</thead>
			<tbody class="pn-ltbody">
			<c:forEach items="${tableData }" var="row">
				<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
					<c:forEach items="${row }" var="data">
						<td align="center">${data }</td>
					</c:forEach>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
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
</html>