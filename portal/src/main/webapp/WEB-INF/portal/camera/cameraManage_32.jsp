<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
	<meta http-equiv="Expires" content="0" />
	<link type="text/css" href="/demo1/demo.css" rel='stylesheet'/>
	<script src="/demo1/jquery-1.7.1.min.js"></script>
	<script src="/camera/webVideoCtrl.js"></script>
	<script src="/camera/cameraManage_32.js"></script>
<script type="text/javascript">

//var g_iWndIndex = 2; //可以不用设置这个变量，有窗口参数的接口中，不用传值，开发包会默认使用当前选择窗口
// TODO 初始化
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
	WebVideoCtrl.I_InitPlugin(1000, 700, 
		{
			bWndFull: true,//是否支持单窗口双击全屏，默认支持 true:支持 false:不支持
			iWndowType: ${row},
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

	
	// TODO 遍历nvrs
	var nvrs = ${nvrsjson};
/* 	console.log(nvrs); */
/* 	var xxx = $(nvrs);
	console.log(xxx); */
	for(var nvr of nvrs){
/* 		console.log(nvr); */
	// TODO 登录
		var	iProtocol= 1,	// protocol 1：http, 2:https
			szIP = nvr.nvr_ipv4,
			szPort = nvr.nvr_port,
			szUsername = nvr.nvr_username,
			szPassword = nvr.nvr_password,
		 	szInfo="",
			oSel = $("#channels").empty();
		if ("" == szIP || "" == szPort) {
			return;
		}
		var iRet = WebVideoCtrl.I_Login(szIP, iProtocol, szPort, szUsername, szPassword, {
			success: function (xmlDoc) {

				showOPInfo(szIP + " 登录成功！");
				//获取通道
				// 数字通道
				WebVideoCtrl.I_GetDigitalChannelInfo(szIP, {
					async: false,
					success: function (xmlDoc) {
						//获取通道
						var oChannels = $(xmlDoc).find("InputProxyChannelStatus");
						//遍历通道
						$.each(oChannels, function (i) {
							/* 遍历通道开始 */
							
							console.log(i);
							console.log($(this)); 
							if(i != 10){
								
								
								var id = $(this).find("id").eq(0).text(),
									name = $(this).find("name").eq(0).text(),
									online = $(this).find("online").eq(0).text();
								if ("false" == online) {// 过滤禁用的数字通道
									//return true;
								}
								if ("" == name) {
									name = "IPCamera " + (i < 9 ? "0" + (i + 1) : (i + 1));
								}
								
	//							console.log(name);
								
								oSel.append("<option value='" + id + "' bZero='false'>" + name + "</option>");
						/* 		console.log("oSel:")
								console.log(oSel); */
								
								var t=setTimeout(function(){ 
									
									// TODO 开始预览
									//var oWndInfo = WebVideoCtrl.I_GetWindowStatus();
									var oWndInfo = WebVideoCtrl.I_GetWindowStatus(i);
									
									console.log("oWndInfo : "+oWndInfo);
									if (oWndInfo != null) {
										console.log("已经在播放了，先停止")
										// 已经在播放了，先停止
										WebVideoCtrl.I_Stop();
									}
									
									
									//iChannelID = id,
									//bZeroChannel = false,
									
									console.log("iChannelID :");
									console.log(id);
									
									console.log("szIP  :  "+szIP);
									
									var iRet = WebVideoCtrl.I_StartRealPlay(szIP, {
										iWndIndex: i,
										iStreamType: 1,// 1:高清  2:普清
										iChannelID: id,
										bZeroChannel: false
		
									});
								
								},1000);
								
								szInfo = "";
								if (0 == iRet) {
									szInfo = "开始预览成功！";
								} else {
									szInfo = "开始预览失败！";
								}
	
								showOPInfo(szIP + " " + szInfo);
								
							}
							
							/* 遍历结束 */
						});
						showOPInfo(szIP + " 获取数字通道成功！");
					},
					error: function () {
						showOPInfo(szIP + " 获取数字通道失败！");
					}
				});
				
			},
			error: function () {
				showOPInfo(szIP + " 登录失败！");
			}
		});
		if (-1 == iRet) {
			showOPInfo(szIP + " 已登录过！");
		};
			
	}
	
});

</script>
</head>
<body>
<div class="left">
	<div id="divPlugin"></div>
</div>
	
<div class="left">
	<div>
		<table >
			 <tr>
				<td><input id="loginip" type="hidden" class="txt" value="${nvrs[0].nvr_ipv4 }" /></td>
				<td><input id="port" type="hidden" class="txt" value="${nvrs[0].nvr_port }" /></td>
			</tr>
			<tr>
				<td><input id="username" type="hidden" class="txt" value="${nvrs[0].nvr_username }" /></td>
				<td><input id="password" type="hidden" class="txt" value="${nvrs[0].nvr_password }" /></td>
			</tr>
			<tr>
				<td><input id="deviceport" type="hidden" class="txt" value="8000" /><!-- （可选参数） --></td>
			</tr>
		</table>
	</div>
	<fieldset class="preview">
		<legend>预览</legend>
		<table cellspacing="5">
			<tr>
				<td>
					<table>
						<tr>
							<td class="tt">摄像头</td>
							<td>
								<select id="channels" class="sel"></select>
							</td>
						</tr>
						<tr>
							<td class="tt">清晰度</td>
							<td>
								<select id="streamtype" class="sel">
									<option value="1">高清</option>
									<option value="2" selected = "selected">普通</option>
								</select>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<img alt="开始预览" src="/images/arrow/start.png" title="开始预览" style="height: 40px;width:40px;cursor:pointer" onclick="clickStartRealPlay();" >
				</td>
				<td>
					<img alt="停止预览" src="/images/arrow/end.png" title="停止预览" style="height: 40px;width:40px;cursor:pointer" onclick="clickStopRealPlay();">
				</td>
				<td>
					<img alt="" src="/images/camera/fullScreen.png" onclick="clickFullScreen();" style="cursor:pointer">
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset class="ptz">
		<legend>云台控制</legend>
		<div style="float: left;margin-left: 20px">
			<table>
				<tr>
					<td><img src="/images/camera/plus.png" title="远" onmousedown="PTZZoomIn()" onmouseup="PTZZoomStop()" style="cursor:pointer"></td>
					<td><div style="margin: 0px auto"><img alt="远/近" src="/images/camera/zoom.png" title="远/近" height="20" width="20"></div></td>
					<td><img src="/images/camera/minus.png" title="近" onmousedown="PTZZoomout()" onmouseup="PTZZoomStop()" style="cursor:pointer"></td>
				</tr>
				<tr>
					<td colspan="3">
						<hr style="height: 2px;width: 80%;margin: auto;">
						<hr style="height: 2px;width: 80%;margin: auto;">
					</td>
				</tr>
				<tr>
					<td><img src="/images/camera/plus.png" title="远焦" onmousedown="PTZFocusIn()" onmouseup="PTZFoucusStop()" style="cursor:pointer"></td>
					<td><img alt="对焦" src="/images/camera/focus.png" title="对焦" height="20" width="20"></td>
					<td><img src="/images/camera/minus.png" title="近焦" onmousedown="PTZFoucusOut()" onmouseup="PTZFoucusStop()" style="cursor:pointer"></td>
				</tr>
				<tr>
					<td colspan="3">
						<hr style="height: 2px;width: 80%;margin: auto;">
						<hr style="height: 2px;width: 80%;margin: auto;">
					</td>
				</tr>
				<tr>
					<td><img src="/images/camera/plus.png" title="补光" onmousedown="PTZIrisIn()" onmouseup="PTZIrisStop()" style="cursor:pointer"></td>
					<td><img alt="光圈" src="/images/camera/Iris.png" title="光圈" height="20" width="20"></td>
					<td><img src="/images/camera/minus.png" title="减光" onmousedown="PTZIrisOut()" onmouseup="PTZIrisStop()" style="cursor:pointer"></td>
				</tr>
			</table>
       <!--  </div>
       
		<div style="float: left;margin-left: 20px"> -->
			<table class="left">
				<tr>
					<td class="tt">云台速度</td>
					<td>
						<select id="ptzspeed" style="width: 100%" >
							<option>1</option>
							<option>2</option>
							<option selected="selected">3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tt">位置记忆</td>
					<td><input id="preset" type="text" class="txt" value="1"  /></td>
				</tr>
				<tr>
					<td >
						<img src="/images/camera/set.png" title="存储位置" onclick="clickSetPreset();" style="cursor:pointer">
					</td>
					<td>	
						<img src="/images/camera/home.png" title="回到指定位置" onclick="clickGoPreset();" style="cursor:pointer">
					</td>
				</tr>
			</table>
		</div>
		
		 <div style="float: left;margin-left: 20px;border: 1px solid red">
		<!-- 	方向控制组合按钮 -->
			<div style="height:160px; width:160px;position:relative;top:-40;background-image: url('/images/arrow/bl.png');background-size: cover;">
				<div style="position:relative; left:37px;top:10px;width:88px;">
					<img src="/images/arrow/ul.png" style="height:33px;width:86px;cursor:pointer;" onmousedown="mouseDownPTZControl(1);" onmouseup="mouseUpPTZControl();" >
				</div>
				<div style="position:relative; left:10px;top:0px">
					<img src="/images/arrow/ll.png" style="height:86px;width:33px;cursor:pointer;" onmousedown="mouseDownPTZControl(3);" onmouseup="mouseUpPTZControl();">
				</div>
				<div style="position:relative; left:47px;top:-78px">
					<img src="/images/arrow/ml.png" style="height:65px;width:65px;cursor:pointer;" onclick="mouseDownPTZControl(9);">
				</div>
				<div style="position:relative; left:116px;top:-155px">
					<img src="/images/arrow/rl.png" style="height:85px;width:33px;cursor:pointer;" onmousedown="mouseDownPTZControl(4);" onmouseup="mouseUpPTZControl();">
				</div>
				<div style="position:relative; left:37px;top:-164px">
					<img src="/images/arrow/dl.png" style="height:33px;width:86px;cursor:pointer;" onmousedown="mouseDownPTZControl(2);" onmouseup="mouseUpPTZControl();" >
				</div>
			</div>
		</div>
	</fieldset>
	
	<fieldset class="operate">
		<legend>操作信息</legend>
		<div id="opinfo" class="opinfo"></div>
	</fieldset>
	<!-- <fieldset class="callback">
		<legend>事件回调信息</legend>
		<div id="cbinfo" class="cbinfo"></div>
	</fieldset> -->
</div>
</body>

</html>