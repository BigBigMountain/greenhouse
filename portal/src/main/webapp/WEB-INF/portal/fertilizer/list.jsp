<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/portal/head.jsp" %>
<!doctype html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<!-- <meta http-equiv="refresh" content="600"> -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/highcharts/code/highcharts.js"></script>
<script src="/highcharts/code/modules/exporting.js"></script>
<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
<script>
	document.write("<link type='text/css' href='/camera/demo.css?version=" + new Date().getTime() + "' rel='stylesheet' />");
</script>
<title>houseData-list</title>
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
	#fertilizerId {
		height: 33px;
		font-size: 20px;
		margin-left: 30px;
		}
</style>
<script type="text/javascript">
function changeFertilizer(){
	var fertilizerId = $("#fertilizerId").val();
	window.location.href="/fertilizer/listAll.do?fertilizerId="+fertilizerId;
}

function download(){
	var houseId = $("#houseId").val();
	$.ajax({
		url:"/houseData/downloadData.do",
		data:{"houseId":houseId}
	})
}
</script>
</head>
<body >

<div class="box-positon" style="background-color: #F6FAFB">
	<div class="rpos">当前位置: 施肥机控制</div>
	
	<div >
		<select id="fertilizerId" name="fertilizerId"  onchange="changeFertilizer()"> 
				<option value="1" >1#施肥机</option>
				<option value="2" >2#施肥机</option>
				<option value="3" >3#施肥机</option>
			<c:forEach items="${fertilizers }" var="fertilizer">
				<option value="${fertilizer.fertilizerId }" <c:if test="${fertilizerId == fertilizer.fertilizerId }">selected="selected"</c:if>>${fertilizer.fertilizerName }</option>
			</c:forEach>
		</select>
	</div>
	<div class="clear"></div>
</div>
<div style="background-color: #F6FAFB">

	<div class="top" style="float: left;margin-top:20px;margin-left:20px;border: 1px solid #7F9DB9">
		<table>
			<tr>
				<td class="col4" onclick = "temperature1()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/tt.png" width="40px" height="40px"/></div>
								<div class="dataRight" >EC</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">100单位</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "humidity1()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/humidity.png" width="40px" height="40px"/></div>
								<div class="dataRight" >PH</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">6.9</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "lighting()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/lighting.png" width="40px" height="40px"/></div>
								<div class="dataRight" >实时流量</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">8m³/h</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "soilTemperature()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/soilTemperature.png" width="40px" height="40px"/></div>
								<div class="dataRight" >液位</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">20mm</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "temperature2()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/tt.png" width="40px" height="40px"/></div>
								<div class="dataRight" >土壤湿度</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">80%</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
<div style="clear: both"></div>
</div>
<div class="dataChart" id="fertilizerData" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<div>
	<img src="/images/logo/logo1.png" width="100%">
</div>
<%@ include file="chart/index.jsp" %>
</body>
</html>