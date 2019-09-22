<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/portal/head.jsp" %>
<!doctype html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<!-- 每多少秒刷新一次 -->
<meta http-equiv="refresh" content="60">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/highcharts/code/highcharts.js"></script>
<script src="/highcharts/code/modules/exporting.js"></script>
<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>

<!-- <script src="/camera/jquery-1.7.1.min.js"></script> -->
<script src="/camera/webVideoCtrl.js"></script>
<script src="/camera/camera-easy-nvr.js"></script>
<script>
	document.write("<link type='text/css' href='/camera/demo.css?version=" + new Date().getTime() + "' rel='stylesheet' />");
</script>
<title>houseData-list</title>
<style>
.top{overfloat:hidden;font-weight:bold;} 
.col4{width:25%;}
.data{float: left; position: relative;left: 50%;}
.dataLeft{float:left;height:30px;position: relative;left: -50%;}
.dataRight{float:left;height:30px;line-height:30px;text-align:center;font-size: 20px;position: relative;left: -50%;}
.dataClear{clear:both;text-align: center;font-size: 25px;}
.p {
	position:relative;
	margin:5px 5px;
	width:fit-content;
}
.p .c {
	width:140px;
	height:60px;
	background:#B6F157;
	border-radius:15px;
	border-style:ridge;
	border-width:8px;
	border-color:#ee7507;
}
</style>
<script type="text/javascript">


function changeHouse(){
	var houseId = $("#houseId").val();
	window.location.href="/houseData/listAll.do?houseId="+houseId;
}

function download(){
	var houseId = $("#houseId").val();
	$.ajax({
		url:"/houseData/downloadData.do",
		data:{"houseId":houseId}
	})
}
	/* setTimeOut('changeHouse',1000*5); */
 	
$(function () {
	// 检查插件是否已经安装过
    var iRet = WebVideoCtrl.I_CheckPluginInstall();
	if (-2 == iRet) {
		alert("您的Chrome浏览器版本过高，不支持NPAPI插件！");
		return;
	} else if (-1 == iRet) {
        alert("您还未安装过插件，双击开发包目录里的WebComponentsKit.exe安装！");
		return;
    }
	
	// 初始化插件参数及插入插件
	WebVideoCtrl.I_InitPlugin(550, 330, 
		{
			bWndFull: true,//是否支持单窗口双击全屏，默认支持 true:支持 false:不支持
			iWndowType: 1,
		}
	);
	WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");
	
	// 检查插件是否最新
	if (-1 == WebVideoCtrl.I_CheckPluginVersion()) {
		alert("检测到新的插件版本，双击开发包目录里的WebComponentsKit.exe升级！");
		return;
	}

	// 窗口事件绑定
	$(window).bind({
		resize: function () {
			var $Restart = $("#restartDiv");
			if ($Restart.length > 0) {
				var oSize = getWindowSize();
				$Restart.css({
					width: oSize.width + "px",
					height: oSize.height + "px"
				});
			}
		}
	});

	
	var houseName = $("#houseName").val();
	var oSel = $("#channels").empty();

	// TODO 遍历nvrs
	var nvrs = ${nvrsjson};
//	console.log(nvrs); 
 //	var xxx = $(nvrs);
//	console.log(xxx); 
	for(var nvr of nvrs){
 		console.log(nvr); 
	// TODO 登录
		var	iProtocol= 1,	// protocol 1：http, 2:https
			szIP = nvr.nvr_ipv4,
			szPort = nvr.nvr_port,
			szUsername = nvr.nvr_username,
			szPassword = nvr.nvr_password,
		 	szInfo="";
			
		if ("" == szIP || "" == szPort) {
			continue;
		}
		
		console.log(szIP);
		console.log(szPort);
		console.log(szUsername);
		console.log(szPassword);
		
		
		
		var iRet = WebVideoCtrl.I_Login(szIP, iProtocol, szPort, szUsername, szPassword, {
			success: function (xmlDoc) {

				showOPInfo(szIP + " nvr登录成功！");

				// 打开数字通道
				WebVideoCtrl.I_GetDigitalChannelInfo(szIP, {
					async: false,
					success: function (xmlDoc) {
						showOPInfo(szIP + " 获取数字通道成功！");
						//获取通道列表
						var oChannels = $(xmlDoc).find("InputProxyChannelStatus");
						//遍历通道
						$.each(oChannels, function (i) {

							var id = $(this).find("id").eq(0).text(),
								name = $(this).find("name").eq(0).text(),
								online = $(this).find("online").eq(0).text();
							console.log(name);

							if ("false" == online) {// 过滤禁用的数字通道
								return true;
							}

							if(name == houseName){          

								oSel.append("<option value='" + id + "' bZero='false'>" + name + "</option>");
								
								var t=setTimeout(function(){
									
									// TODO 开始预览
									//var oWndInfo = WebVideoCtrl.I_GetWindowStatus();//如果不带参数,则查询的是所有的视频框,若带参数,则查询指定索引的视频框
									var oWndInfo = WebVideoCtrl.I_GetWindowStatus(0);
									
									console.log("oWndInfo");
									console.log(oWndInfo);
									
									if (oWndInfo != null) {
										console.log("已经在播放了，111111停止")
										//WebVideoCtrl.I_Stop();
									}

									if(oWndInfo == null){
										var iRet = WebVideoCtrl.I_StartRealPlay(szIP, {
											iWndIndex: 0,
											iStreamType: 1,// 1:高清  2:普清
											iChannelID: id,
											bZeroChannel: false
										});
									}
								},1000);
								
								szInfo = "";
								if (0 == iRet) { szInfo = "开始预览成功！"; } else { szInfo = "开始预览失败！"; }
								showOPInfo(szIP + " " + szInfo);
								
							}
						});
						
					},
					error: function () {
						showOPInfo(szIP + " 获取数字通道失败！");
					}
				});
				
			},
			error: function () {
				showOPInfo(szIP + " nvr登录失败！");
			}
		});
		if (-1 == iRet) {
			showOPInfo(szIP + " 已登录过！");
		};
			
	}
	
});

	
</script>
</head>
<body >

<div class="box-positon" style="background-color: #F6FAFB">
	<div class="rpos">当前位置: 室内信息</div>
	<div style="margin-left: 80px;float: left">
		<form action="/houseData/downloadData.do">
			温室编号:
			<select id="houseId" name="houseId"  onchange="changeHouse()"> 
				<c:forEach items="${houses }" var="house">
					<option value="${house.houseId }" <c:if test="${houseId == house.houseId }">selected="selected"</c:if>>${house.houseName }</option>
				</c:forEach>
			</select>
			<input type="submit" class="download"  value="导出" />
		</form>
	</div>
	<div style="margin-left: 80px;float: left">
		<input type = "hidden" id="houseName"  value="${houseName }">
		摄像头:
		<select id="channels" name="hh"  style="width:100">
			<option value = "1">连栋温室南侧 </option>
		</select>	
	</div>
	<div class="clear"></div>
</div>
<div style="background-color: #F6FAFB">

<!-- 摄像头开始-->  
	<div id="divPlugin" style="width:550px;height:330px;float: left"></div>

<%--  	<div >
		<input type="hidden" id="ca_ip" value="${camera.ca_ip }"/>
		<input type="hidden" id="ca_port" value="${camera.ca_httpPort }"/>
		<input type="hidden" id="ca_username" value="${camera.ca_username }"/>
		<input type="hidden" id="ca_password" value="${camera.ca_password }"/>
	</div>  --%>
	<div style="float: left;">
		<fieldset style="width:235px;height:120px;padding:10px;margin-left:10px;border:1px solid #7F9DB9;">
			<legend>操作</legend>
			
			<div>
				
				<div style="float:left; height: 40px;width:40px">
					<img alt="开始预览" src="/images/arrow/start.png" style="height: 40px;width:40px" onclick="clickStartRealPlay();" >
				</div>
				<div style="float:right; height: 40px;width:40px;">
					<img alt="停止预览" src="/images/arrow/end.png" style="height: 40px;width:40px" onclick="clickStopRealPlay();">
				</div>
				<div style="clear: both"></div>
			<!-- 	方向控制组合按钮 -->
				<div >
					<div style="height:120px; width:120px;position:relative;top:-52;background-image: url('/images/arrow/bl.png');background-size: cover;">
						<div style="position:relative; left:10px;top:8px;width:86px;">
							<img src="/images/arrow/ul.png" style="height:25px;width:65px" onmousedown="mouseDownPTZControl(1);" onmouseup="mouseUpPTZControl();" >
						</div>
						<div style="position:relative; left:-36px;top:2px;width:33px;">
							<img src="/images/arrow/ll.png" style="height:65px;width:25px" onmousedown="mouseDownPTZControl(3);" onmouseup="mouseUpPTZControl();" >
						</div>
						<div style="position:relative; left:8px;top:-55px;width:65px;">
							<img src="/images/arrow/ml.png" style="height:49px;width:49px" onclick="mouseDownPTZControl(9);">
						</div>
						<div style="position:relative; left:44px;top:-112px;width:33;">
							<img src="/images/arrow/rl.png" style="height:65px;width:25px" onmousedown="mouseDownPTZControl(4);" onmouseup="mouseUpPTZControl();">
						</div>
						<div style="position:relative; left:10px;top:-117px;width:86px;">
							<img src="/images/arrow/dl.png" style="height:25px;width:65px" onmousedown="mouseDownPTZControl(2);" onmouseup="mouseUpPTZControl();" >
						</div>
					</div>
				</div>	
			</div>
		</fieldset>
	</div>
	<div style="float: left;">
		<fieldset style="width:380;height:120px;padding:10px;margin-left:10px;border:1px solid #7F9DB9;">
			<legend>操作信息</legend>
			<div id="opinfo" style="height:100px;border:1px solid #7F9DB9;overflow:auto;"></div>
		</fieldset>
	</div>

	
<!--摄像头结束  -->	

	<div class="top" style="float: left;margin-top:5px;margin-left:5px;border: 1px solid #7F9DB9">
		<table>
			<tr>
				<td  class="col4" onclick = "temperature1()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/tt.png" width="30px" height="30px"/></div>
								<div class="dataRight" >温度1</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${houseData.temperature }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "humidity1()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/humidity.png" width="30px" height="30px"/></div>
								<div class="dataRight" >湿度1</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${houseData.humidity }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "lighting()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/lighting.png" width="30px" height="30px"/></div>
								<div class="dataRight" >光照</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${houseData.lighting }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "soilTemperature()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/soilTemperature.png" width="30px" height="30px"/></div>
								<div class="dataRight" >土壤温度</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${houseData.soilTemperature }</div>
						</div>
					</div>
				</td>
				
			</tr>
			<tr>
				<td  class="col4" onclick = "temperature2()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/tt.png" width="30px" height="30px"/></div>
								<div class="dataRight" >温度2</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${houseData.temperature2 }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "humidity2()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/humidity.png" width="30px" height="30px"/></div>
								<div class="dataRight" >湿度2</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${houseData.humidity2 }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "co2()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/co2.png" width="30px" height="30px"/></div>
								<div class="dataRight" >CO2</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${houseData.co2 }</div>
						</div>
					</div>
				</td>
				<td  class="col4" onclick = "soilHumidity()">
					<div class="p">
						<div class="c">
							<div class="data" >
								<div class="dataLeft" ><img src="/images/weather/soilHumidity.png" width="30px" height="30px"/></div>
								<div class="dataRight" >土壤湿度</div>
								<div style="clear:both;" ></div>
							</div>	
								<div class="dataClear">${houseData.soilHumidity }</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div style="clear: both"></div>
	<div>
	 
		<%@ include file="chart/index.jsp" %>
		<%@ include file="chart/temperature1.jsp" %>
		<%@ include file="chart/temperature2.jsp" %>
		<%@ include file="chart/humidity1.jsp" %>
		<%@ include file="chart/humidity2.jsp" %>
		<%@ include file="chart/lighting.jsp" %>
		<%@ include file="chart/co2.jsp" %>
		<%@ include file="chart/soilTemperature.jsp" %>
		<%@ include file="chart/soilHumidity.jsp" %>
	
	</div>
</div>
<!-- <div>
	<img src="/images/logo/logo1.png" width="100%">
</div> -->
</body>
</html>