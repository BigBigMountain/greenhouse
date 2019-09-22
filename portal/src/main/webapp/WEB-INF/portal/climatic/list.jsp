<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/portal/head.jsp" %>
<!doctype html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/highcharts/code/highcharts.js"></script>
<script src="/highcharts/code/modules/exporting.js"></script>
<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
<title>climatic-list</title>
<style>

.top{overfloat:hidden;font-weight:bold;} 
.col4{width:20%;}
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
</head>
<body>

<div class="box-positon">
	<div class="rpos">当前位置: 气象信息</div>
	<div style="margin-left: 80px; float: left">
		<form action="/climatic/downloadClimaticData.do">
			<input type="hidden" name="zoneId" value="${zoneId }" />
			<input type="submit" class="download" value="导出" />
		</form>
	</div>
	<div class="clear"></div>
</div>
<div class="body-box">
	<div class="top">
		<table style="margin: 0 auto">
			<tr>
				<td  class="col4" onclick = "temperature()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/tt.png" width="40px" height="40px"/></div>
								<div class="dataRight" >温度</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${climatic.temperature }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "lighting()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/lighting.png" width="40px" height="40px"/></div>
								<div class="dataRight" >光照</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${climatic.lighting }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "windSpeed()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/windspeed.png" width="40px" height="40px"/></div>
								<div class="dataRight" >风速</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${climatic.windSpeed }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "rainFall()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/rain.png" width="40px" height="40px"/></div>
								<div class="dataRight" >雨/雪</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${climatic.rainAndSnow }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "ph()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/ph.png" width="40px" height="40px"/></div>
								<div class="dataRight" >PH</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${climatic.ph }</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td  class="col4" onclick = "humidity()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/humidity.png" width="40px" height="40px"/></div>
								<div class="dataRight" >湿度</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${climatic.humidity }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "pressure()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/pressure.png" width="40px" height="40px"/></div>
								<div class="dataRight" >气压</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${climatic.pressure }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "windSpeed()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/winddirection.png" width="40px" height="40px"/></div>
								<div class="dataRight" >风向</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${climatic.windDirection }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "rainFall()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/rainfall.png" width="40px" height="40px"/></div>
								<div class="dataRight" >降雨量</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${climatic.rainFall }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "pm25()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/pm25.png" width="40px" height="40px"/></div>
								<div class="dataRight" >PM2.5</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${climatic.pm25 }</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	
 	<%@ include file="chart/index.jsp" %>
	<%@ include file="chart/temperature.jsp" %>
	<%@ include file="chart/humidity.jsp" %>
	<%@ include file="chart/lighting.jsp" %>
	<%@ include file="chart/pressure.jsp" %>
	<%@ include file="chart/windSpeed.jsp" %>
	<%@ include file="chart/rainFall.jsp" %> 
	<%@ include file="chart/ph.jsp" %> 
	<%@ include file="chart/pm25.jsp" %> 
	
</div>
</body>
</html>	
	
