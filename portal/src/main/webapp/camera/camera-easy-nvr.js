// 初始化插件

// 全局保存当前选中窗口
var g_iWndIndex = 0; //可以不用设置这个变量，有窗口参数的接口中，不用传值，开发包会默认使用当前选择窗口
/*$(function () {
	
	var szIP = $("#ca_ip").val(),
	szPort = $("#ca_port").val(),
	szUsername = $("#ca_username").val(),
	szPassword = $("#ca_password").val();
	
	if(szIP == null || szIP =="" ||typeof szIP ==="undefined"){
		return;
	}
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
	WebVideoCtrl.I_InitPlugin(550, 330, {
        bWndFull: true,//是否支持单窗口双击全屏，默认支持 true:支持 false:不支持
        iWndowType: 1,
		
	});
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
	
	//登录
	
	/*
	
	// TODO 遍历nvrs
	var nvrs = ${nvrsjson};
 	console.log(nvrs); 
 	var xxx = $(nvrs);
	console.log(xxx); 
	for(var nvr of nvrs){
 		console.log(nvr); 
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
							 遍历通道开始 
							
							console.log(i);
							console.log($(this)); 
							
								
								
								var id = $(this).find("id").eq(0).text(),
									name = $(this).find("name").eq(0).text(),
									online = $(this).find("online").eq(0).text();
								if ("false" == online) {// 过滤禁用的数字通道
									//return true;
								}
								if ("" == name) {
									name = "IPCamera " + (i < 9 ? "0" + (i + 1) : (i + 1));
								}
								
								console.log(name);
								if(name == '培训室2'){
								
								
														var t=setTimeout(function(){ 
															
															
															oSel.append("<option value='" + id + "' bZero='false'>" + name + "</option>");
															 		console.log("oSel:")
																	console.log(oSel); 
															
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
							
							 遍历结束 
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
	
	
	
	
		
	
	
	
	
	
	
	
	
	

	if ("" == szIP || "" == szPort) {
		return;
	}
	var iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername, szPassword, {
		success: function (xmlDoc) {
			showOPInfo(szIP + " 登录成功！");
			//获取视频流,开始预览
			var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
//			szIP = '124.133.239.78',
			iStreamType = 1,
			iChannelID = 1,
			bZeroChannel = false,
			szInfo = "";
		if ("" == szIP) {
			return;
		}
		if (oWndInfo != null) {// 已经在播放了，先停止
			WebVideoCtrl.I_Stop();
		}
		var iRet = WebVideoCtrl.I_StartRealPlay(szIP, {
			iStreamType: iStreamType,
			iChannelID: iChannelID,
			bZeroChannel: bZeroChannel
		});
		if (0 == iRet) {
			szInfo = "开始预览成功！";
		} else {
			szInfo = "开始预览失败！";
		}
		
		showOPInfo(szIP + " " + szInfo);
		},
		error: function () {
			showOPInfo(szIP + " 登录失败！");
		}
	});
	if (-1 == iRet) {
		showOPInfo(szIP + " 已登录过！");
	};

});
*/
// 显示操作信息
function showOPInfo(szInfo) {
	szInfo = "<div>" + dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss") + " " + szInfo + "</div>";
	$("#opinfo").html(szInfo + $("#opinfo").html());
}

// 格式化时间
function dateFormat(oDate, fmt) {
	var o = {
		"M+": oDate.getMonth() + 1, //月份
		"d+": oDate.getDate(), //日
		"h+": oDate.getHours(), //小时
		"m+": oDate.getMinutes(), //分
		"s+": oDate.getSeconds(), //秒
		"q+": Math.floor((oDate.getMonth() + 3) / 3), //季度
		"S": oDate.getMilliseconds()//毫秒
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (oDate.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}

// 获取窗口尺寸
function getWindowSize() {
	var nWidth = $(this).width() + $(this).scrollLeft(),
		nHeight = $(this).height() + $(this).scrollTop();

	return {width: nWidth, height: nHeight};
}

// 登录
function clickLogin() {
	var szIP = '124.133.239.78',
		szPort = '805',
		szUsername = 'admin',
		szPassword = 'stlc12345';

	if ("" == szIP || "" == szPort) {
		return;
	}

	var iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername, szPassword, {
		success: function (xmlDoc) {
			showOPInfo(szIP + " 登录成功！");

			$("#ip").prepend("<option value='" + szIP + "'>" + szIP + "</option>");
			setTimeout(function () {
				$("#ip").val(szIP);
				getChannelInfo();
			}, 10);
		},
		error: function () {
			showOPInfo(szIP + " 登录失败！");
		}
	});

	if (-1 == iRet) {
		showOPInfo(szIP + " 已登录过！");
	}
}

// 开始预览
function clickStartRealPlay() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szIP = '124.133.239.78',
		iStreamType = 1,
		iChannelID = 1,
		bZeroChannel = false,
		szInfo = "";

	if ("" == szIP) {
		return;
	}

	if (oWndInfo != null) {// 已经在播放了，先停止
		WebVideoCtrl.I_Stop();
	}

	var iRet = WebVideoCtrl.I_StartRealPlay(szIP, {
		iStreamType: iStreamType,
		iChannelID: iChannelID,
		bZeroChannel: bZeroChannel
	});

	if (0 == iRet) {
		szInfo = "开始预览成功！";
	} else {
		szInfo = "开始预览失败！";
	}

	showOPInfo(szIP + " " + szInfo);
}

// 停止预览
function clickStopRealPlay() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_Stop();
		if (0 == iRet) {
			szInfo = "停止预览成功！";
		} else {
			szInfo = "停止预览失败！";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// PTZ控制 9为自动，1,2,3,4,5,6,7,8为方向PTZ
var g_bPTZAuto = false;
function mouseDownPTZControl(iPTZIndex) {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		bZeroChannel = false,
		iPTZSpeed = 3;

	if (bZeroChannel) {// 零通道不支持云台
		return;
	}
	
	if (oWndInfo != null) {
		console.log(g_bPTZAuto);
		if (9 == iPTZIndex && g_bPTZAuto) {
			g_bPTZAuto = !g_bPTZAuto;
			mouseUpPTZControl();
			return;
		} else {
			g_bPTZAuto = false;// 点击其他方向，自动肯定会被关闭
		}
		console.log(g_bPTZAuto);
		console.log(iPTZSpeed);

		WebVideoCtrl.I_PTZControl(iPTZIndex, false, {
			iPTZSpeed: iPTZSpeed,
			success: function (xmlDoc) {
				if (9 == iPTZIndex) {
					g_bPTZAuto = !g_bPTZAuto;
				}
				showOPInfo(oWndInfo.szIP + " 开启云台成功！");
			},
			error: function () {
				showOPInfo(oWndInfo.szIP + " 开启云台失败！");
			}
		});
	}
}

// 方向PTZ停止
function mouseUpPTZControl() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(1, true, {
			success: function (xmlDoc) {
				showOPInfo(oWndInfo.szIP + " 停止云台成功！");
			},
			error: function () {
				showOPInfo(oWndInfo.szIP + " 停止云台失败！");
			}
		});
	}
}

/*dateFormat = function (oDate, fmt) {
    var o = {
        "M+": oDate.getMonth() + 1, //月份
        "d+": oDate.getDate(), //日
        "h+": oDate.getHours(), //小时
        "m+": oDate.getMinutes(), //分
        "s+": oDate.getSeconds(), //秒
        "q+": Math.floor((oDate.getMonth() + 3) / 3), //季度
        "S": oDate.getMilliseconds()//毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (oDate.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if(new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};
*/