<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fertilizer/head.jsp" %>
<!doctype html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="refresh" content="60">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/js/highcharts/highcharts.js"></script>
<script src="/js/highcharts/exporting.js"></script>
<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>

<title>fertilizerData-list</title>
<style>
.top{overfloat:hidden;font-weight:bold;} 
.col4{width:25%;}
.data{float: left; position: relative;left: 50%;}
.dataLeft{float:left;height:40px;position: relative;left: -50%;}
.dataRight{float:left;height:40px;line-height:40px;text-align:center;font-size: 20px;position: relative;left: -50%;}
.dataClear{clear:both;text-align: center;font-size: 30px;}
.p {
	position:relative;
	margin:5px 5px;
	width:fit-content;
}
.p .c {
	width:140px;
	height:90px;
	background:#B6F157;
	border-radius:15px;
	border-style:ridge;
	border-width:10px;
	border-color:#ee7507;
}
</style>
<script type="text/javascript">
$(function(){
	var msg = "${msg}";
	if(msg != null && msg != ''){
		alert(msg);
	}
})
function changeFertilizer(){
	var fertilizerId = $("#fertilizerId").val();
//	alert(fertilizerId);
	window.location.href="/fertilizer/timeData.do?fertilizerId="+fertilizerId;
}

/* function download(){
	var fertilizerId = $("#fertilizerId").val();
	$.ajax({
		url:"/fertilizerData/downloadData.do",
		data:{"fertilizerId":fertilizerId}
	})
} */
</script>
</head>
<body >

<div class="box-positon" style="background-color: #F6FAFB">
	<div class="rpos">当前位置: 实时数据</div>
	<div class="clear"></div>
</div>
<div style="background-color: #F6FAFB">
<div>
		当前施肥机:
	<form action="/fertilizer/downloadData.do">
		<select id="fertilizerId" name="fertilizerId"  onchange="changeFertilizer()"> 
			<c:forEach items="${fertilizers }" var="fertilizer">
				<option value="${fertilizer.fertilizerId }" <c:if test="${fertilizerId == fertilizer.fertilizerId }">selected="selected"</c:if>>${fertilizer.fertilizerName }</option>
			</c:forEach>
		</select>
		<!-- <input type="submit" class="download"  value="导出" /> -->
	</form>
</div>
	<div class="top" style="float: left;margin-top:20px;margin-left:20px;border: 1px solid #7F9DB9">
		<table>
			<tr>
				<td  class="col4" onclick="changeCharts(${fertilizerId},'ec')">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/tt.png" width="40px" height="40px"/></div>
								<div class="dataRight" >EC &nbsp;  us/cm</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${timeData.ec }</div>
						</div>
					</div>
				</td>
				
				<td  class="col4"  onclick="changeCharts(${fertilizerId},'ph')">
<%-- 					<div class="p" onclick="changeCharts(${fertilizerId},ph)"> --%>
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/humidity.png" width="40px" height="40px"/></div>
								<div class="dataRight" >PH</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${timeData.ph }</div>
						</div>
					</div>
				</td>
				<td  class="col4"  onclick="changeCharts(${fertilizerId},'rateFlow')" >
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/lighting.png" width="40px" height="40px"/></div>
								<div class="dataRight" >流量  &nbsp; m³/h</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${timeData.rateFlow }</div>
						</div>
					</div>
				</td>
				<td  class="col4"  onclick="changeCharts(${fertilizerId},'soilHumidity1')" >
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/soilTemperature.png" width="40px" height="40px"/></div>
								<div class="dataRight" >土湿  &nbsp; %</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${timeData.soilHumidity1 } </div>
						</div>
					</div>
				</td>
				<td class="col4"  onclick="changeCharts(${fertilizerId},'liquidLevel')" >
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/tt.png" width="40px" height="40px"/></div>
								<div class="dataRight" >液位 &nbsp; cm</div>
								<div style="clear:both;" ></div>
							</div>
								<div class="dataClear">${timeData.liquidLevel } </div>
						</div>
					</div>
				</td>
				<td class="col4"  onclick="changeCharts(${fertilizerId},'totalIrrigation')" >
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/tt.png" width="40px" height="40px"/></div>
								<div class="dataRight" >总流量 m³</div>
								<div style="clear:both;" ></div>
							</div>
								<div class="dataClear">${timeData.totalIrrigation } </div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div style="clear: both"></div>
	<div id="highcharts" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		<%@ include file="indexCharts.jsp" %>
		<%@ include file="changeCharts.jsp" %>
</div>
<div>
	<img src="/images/logo/logo1.png" width="100%">
</div>
</body>
</html>