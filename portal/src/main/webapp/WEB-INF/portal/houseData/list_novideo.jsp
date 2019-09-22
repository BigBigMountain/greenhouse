<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/portal/head.jsp"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- 每多少秒刷新一次 -->
<meta http-equiv="refresh" content="60">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/highcharts/code/highcharts.js"></script>
<script src="/highcharts/code/modules/exporting.js"></script>
<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
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
		window.location.href = "/houseData/listAll.do?houseId=" + houseId;
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

	<div class="box-positon" style="background-color: #F6FAFB">
		<div class="rpos">当前位置: 室内信息</div>
		<div style="margin-left: 80px; float: left">
			<form action="/houseData/downloadSensorData.do">
				温室编号: 
				<select id="houseId" name="houseId" onchange="changeHouse()">
					<c:forEach items="${houses }" var="house">
						<option value="${house.id }" <c:if test="${houseId == house.id }">selected="selected"</c:if>>
							${house.name }
						</option>
					</c:forEach>
				</select> 
				<input type="submit" class="download" value="导出" />
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<div style="background-color: #F6FAFB;display:table">
		<div class="top" style="float: left;display:table-cell; margin: auto auto;width:auto; border: 1px solid #7F9DB9">
			<table>
				<tr>
					<c:forEach items="${sensorList }" var="sensor" varStatus="status">
						
								<td  onclick="getSensorChartsData(${sensor.id},'${sensor.name }','${sensor.type.unit }')">
									<div class="p" >
										<div class="c">
											<div class="data">
												<div class="dataLeft">
													<img src="/images/weather/${sensor.type.image }.png"
														width="30px" height="30px" />
												</div>
												<div class="dataRight">${sensor.name } (${sensor.type.unit })</div>
												<div style="clear: both;"></div>
											</div>
											<div class="dataClear">
												<c:forEach items="${sensorDataList}" var="sensorData" >
													<c:if test="${sensor.id == sensorData.sensorId}">
														${sensorData.value }
													</c:if>
												</c:forEach>
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
		<c:if test="${fn:contains(osName,'Windows')}">
			<div>
				<form action="/houseData/resetWater.do">
					请选择需要重置的水表: 
					<select id="waterForm" name="sensorId">
						<c:forEach items="${sensorList }" var="sensor">
							<c:if test="${sensor.typeId == 7}">
								<option value="${sensor.id }" >
									${sensor.name }
								</option>
							</c:if>
						</c:forEach>
					</select> 
					<input type="button" class="submit" value="重置" onclick="resetWater()"/>
				</form>
			
			</div>
		</c:if>
	</div>
</body>
<script>
$(function(){
	getSensorChartsData(${sensorList[0].id},'${sensorList[0].name}','${sensorList[0].type.unit}')
})
function resetWater(){
	$.ajax({
		url:"/houseData/resetWater.do",
		type: "POST",//方法类型
		data:$("#waterForm").serialize(),
		success: function (result) {
			alert(result);//打印服务端返回的数据(调试用)
	    },
	    error : function() {
	        alert("异常！");
	    }
	})
}
</script>
</html>