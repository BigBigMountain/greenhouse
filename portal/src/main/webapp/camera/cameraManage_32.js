
//TODO 显示操作信息
function showOPInfo(szInfo) {
	szInfo = "<div>" + dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss") + " " + szInfo + "</div>";
	$("#opinfo").html(szInfo + $("#opinfo").html());
}


// TODO 格式化时间
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

// 打开选择框 0：文件夹  1：文件
function clickOpenFileDlg(id, iType) {
	var szDirPath = WebVideoCtrl.I_OpenFileDlg(iType);
	
	if (szDirPath != -1 && szDirPath != "" && szDirPath != null) {
		$("#" + id).val(szDirPath);
	}
}


// 登录 TODO 登录
function clickLogin() {
	var szIP = $("#loginip").val(),
		szPort = $("#port").val(),
		szUsername = $("#username").val(),
		szPassword = $("#password").val();

	if ("" == szIP || "" == szPort) {
		return;
	}

	var iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername, szPassword, {
		success: function (xmlDoc) {
			showOPInfo(szIP + " 登录成功！");

			$("#ip").prepend("<option value='" + szIP + "'>" + szIP + "</option>");
			setTimeout(function () {
				$("#ip").val(szIP);
				
				getChannelInfo();//TODO -->获取通道
				
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

// 退出 TODO 退出
function clickLogout() {
	var szIP = $("#ip").val(),
		szInfo = "";

	if (szIP == "") {
		return;
	}

	var iRet = WebVideoCtrl.I_Logout(szIP);
	if (0 == iRet) {
		szInfo = "退出成功！";

		$("#ip option[value='" + szIP + "']").remove();
		getChannelInfo();
	} else {
		szInfo = "退出失败！";
	}
	showOPInfo(szIP + " " + szInfo);
}
/*  //TODO 以后可能有用
// 获取设备信息
function clickGetDeviceInfo() {
	var szIP = $("#ip").val();

	if ("" == szIP) {
		return;
	}

	WebVideoCtrl.I_GetDeviceInfo(szIP, {
		success: function (xmlDoc) {
			var arrStr = [];
			arrStr.push("设备名称：" + $(xmlDoc).find("deviceName").eq(0).text() + "\r\n");
			arrStr.push("设备ID：" + $(xmlDoc).find("deviceID").eq(0).text() + "\r\n");
			arrStr.push("型号：" + $(xmlDoc).find("model").eq(0).text() + "\r\n");
			arrStr.push("设备序列号：" + $(xmlDoc).find("serialNumber").eq(0).text() + "\r\n");
			arrStr.push("MAC地址：" + $(xmlDoc).find("macAddress").eq(0).text() + "\r\n");
			arrStr.push("主控版本：" + $(xmlDoc).find("firmwareVersion").eq(0).text() + " " + $(xmlDoc).find("firmwareReleasedDate").eq(0).text() + "\r\n");
			arrStr.push("编码版本：" + $(xmlDoc).find("encoderVersion").eq(0).text() + " " + $(xmlDoc).find("encoderReleasedDate").eq(0).text() + "\r\n");
			
			showOPInfo(szIP + " 获取设备信息成功！");
			alert(arrStr.join(""));
		},
		error: function () {
			showOPInfo(szIP + " 获取设备信息失败！");
		}
	});
}
*/
/*// 获取数字通道 //TODO 获取数字通道列表 以后可能有用
function clickGetDigitalChannelInfo() {
	var szIP = $("#ip").val(),
		iAnalogChannelNum = 0;

	$("#digitalchannellist").empty();

	if ("" == szIP) {
		return;
	}

	// 模拟通道
	WebVideoCtrl.I_GetAnalogChannelInfo(szIP, {
		async: false,
		success: function (xmlDoc) {
			iAnalogChannelNum = $(xmlDoc).find("VideoInputChannel").length;
		},
		error: function () {
			
		}
	});

	// 数字通道
	WebVideoCtrl.I_GetDigitalChannelInfo(szIP, {
		async: false,
		success: function (xmlDoc) {
			var oChannels = $(xmlDoc).find("InputProxyChannelStatus");
			
			$.each(oChannels, function () {
				var id = parseInt($(this).find("id").eq(0).text(), 10),
					ipAddress = $(this).find("ipAddress").eq(0).text(),
					srcInputPort = $(this).find("srcInputPort").eq(0).text(),
					managePortNo = $(this).find("managePortNo").eq(0).text(),
					online = $(this).find("online").eq(0).text(),
					proxyProtocol = $(this).find("proxyProtocol").eq(0).text();
				console.log($(this));		// TODO 查看通道结合的内容,
				var objTr = $("#digitalchannellist").get(0).insertRow(-1);
				var objTd = objTr.insertCell(0);
				objTd.innerHTML = (id - iAnalogChannelNum) < 10 ? "D0" + (id - iAnalogChannelNum) : "D" + (id - iAnalogChannelNum);
				objTd = objTr.insertCell(1);
				objTd.width = "25%";
				objTd.innerHTML = ipAddress;
				objTd = objTr.insertCell(2);
				objTd.width = "15%";
				objTd.innerHTML = srcInputPort;
				objTd = objTr.insertCell(3);
				objTd.width = "20%";
				objTd.innerHTML = managePortNo;
				objTd = objTr.insertCell(4);
				objTd.width = "15%";
				objTd.innerHTML = "true" == online ? "在线" : "离线";
				objTd = objTr.insertCell(5);
				objTd.width = "25%";
				objTd.innerHTML = proxyProtocol;
			});
			showOPInfo(szIP + " 获取数字通道成功！");
		},
		error: function () {
			showOPInfo(szIP + " 没有数字通道！");
		}
	});
}
*/
//TODO 开始预览
function clickStartRealPlay() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(),
		szIP = $("#loginip").val(),
		iStreamType = parseInt($("#streamtype").val(), 10),
		iChannelID = parseInt($("#channels").val(), 10),

//		bZeroChannel = $("#channels option").eq($("#channels").get(0).selectedIndex).attr("bZero") == "true" ? true : false,
		szInfo = "";
	console.log("iStreamType:  ");
	console.log(iStreamType);
	console.log("iChannelID :");
	console.log(iChannelID);
	if ("" == szIP) {
		return;
	}
	console.log("oWndInfo : "+oWndInfo);
	if (oWndInfo != null) {// 已经在播放了，先停止
		console.log("已经在播放了，先停止")
		WebVideoCtrl.I_Stop();
	}

	var iRet = WebVideoCtrl.I_StartRealPlay(szIP, {
		iStreamType: iStreamType,
		iChannelID: iChannelID,
		bZeroChannel: false

	});

	if (0 == iRet) {
		szInfo = "开始预览成功！";
	} else {
		szInfo = "开始预览失败！";
	}

	showOPInfo(szIP + " " + szInfo);
}

// 停止预览  TODO 停止预览  
function clickStopRealPlay() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_Stop();
		if (0 == iRet) {
			szInfo = "停止预览成功！";
		} else {
			szInfo = "停止预览失败！";
		}
		showOPInfo(szInfo);
	}
}

// 全屏 TODO  全屏
function clickFullScreen() {
	WebVideoCtrl.I_FullScreen(true);
}

// PTZ控制 9为自动，1,2,3,4,5,6,7,8为方向PTZ  TODO 方向控制
var g_bPTZAuto = false;
function mouseDownPTZControl(iPTZIndex) {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(),
//		bZeroChannel = $("#channels option").eq($("#channels").get(0).selectedIndex).attr("bZero") == "true" ? true : false,
		iPTZSpeed = $("#ptzspeed").val();

/*	if (bZeroChannel) {// 零通道不支持云台
		return;
	}
*/	
	if (oWndInfo != null) {
		if (9 == iPTZIndex && g_bPTZAuto) {
//			iPTZSpeed = 0;// 自动开启后，速度置为0可以关闭自动
			g_bPTZAuto = !g_bPTZAuto;
			mouseUpPTZControl();
			return;
		} else {
			g_bPTZAuto = false;// 点击其他方向，自动肯定会被关闭
		}

		WebVideoCtrl.I_PTZControl(iPTZIndex, false, {
			iPTZSpeed: iPTZSpeed,
			success: function (xmlDoc) {
				if (9 == iPTZIndex) {
					g_bPTZAuto = !g_bPTZAuto;
				}
				showOPInfo(" 开启云台成功！");
			},
			error: function () {
				showOPInfo(" 开启云台失败！");
			}
		});
	}
}

// 方向PTZ停止 // TODO  方向停止
function mouseUpPTZControl() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus();

	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(1, true, {
			success: function (xmlDoc) {
				showOPInfo(" 停止云台成功！");
			},
			error: function () {
				showOPInfo(" 停止云台失败！");
			}
		});
	}
}

//  TODO 设置预置点
function clickSetPreset() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(),
		iPresetID = parseInt($("#preset").val(), 10);
	if (oWndInfo != null) {
		WebVideoCtrl.I_SetPreset(iPresetID, {
			success: function (xmlDoc) {
				showOPInfo( "设置预置点成功！");
			},
			error: function () {
				showOPInfo("设置预置点失败！");
			}
		});
	}
}

// TODO 调用预置点 
function clickGoPreset() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(),
		iPresetID = parseInt($("#preset").val(), 10);

	if (oWndInfo != null) {
		WebVideoCtrl.I_GoPreset(iPresetID, {
			success: function (xmlDoc) {
				showOPInfo(" 调用预置点成功！");
			},
			error: function () {
				showOPInfo(" 调用预置点失败！");
			}
		});
	}
}


// TODO 调焦+
function PTZZoomIn() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus();

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(10, false, {
 //       	iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(" 调焦+成功！");
            },
            error: function () {
                showOPInfo("  调焦+失败！");
            }
        });
    }
}
//TODO 调焦-
function PTZZoomout() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus();

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(11, false, {
 //       	iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(" 调焦-成功！");
            },
            error: function () {
                showOPInfo("  调焦-失败！");
            }
        });
    }
}
//TODO 停止调焦
function PTZZoomStop() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus();

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(11, true, {
 //           iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(" 调焦停止成功！");
            },
            error: function () {
                showOPInfo("  调焦停止失败！");
            }
        });
    }
}
//TODO 聚焦+
function PTZFocusIn() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus();

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(12, false, {
//            iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(" 聚焦+成功！");
            },
            error: function () {
                showOPInfo("  聚焦+失败！");
            }
        });
    }
}
//TODO 聚焦-
function PTZFoucusOut() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus();

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(13, false, {
 //           iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(" 聚焦-成功！");
            },
            error: function () {
                showOPInfo("  聚焦-失败！");
            }
        });
    }
}
//TODO 停止聚焦
function PTZFoucusStop() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus();

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(12, true, {
 //           iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(" 聚焦停止成功！");
            },
            error: function () {
                showOPInfo("  聚焦停止失败！");
            }
        });
    }
}
//TODO 光圈+
function PTZIrisIn() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus();

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(14, false, {
//            iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(" 光圈+成功！");
            },
            error: function () {
                showOPInfo("  光圈+失败！");
            }
        });
    }
}
//TODO 光圈-
function PTZIrisOut() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus();

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(15, false, {
//            iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(" 光圈-成功！");
            },
            error: function () {
                showOPInfo("  光圈-失败！");
            }
        });
    }
}
//TODO 停止调节光圈
function PTZIrisStop() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus();

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(14, true, {
 //           iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(" 光圈停止成功！");
            },
            error: function () {
                showOPInfo("  光圈停止失败！");
            }
        });
    }
}


// 切换模式   以后可能有用
function changeIPMode(iType) {
	var arrPort = [0, 7071, 80];

	$("#serverport").val(arrPort[iType]);
}

// 获取设备IP  以后可能有用
/*function clickGetDeviceIP() {
	var iDeviceMode = parseInt($("#devicemode").val(), 10),
		szAddress = $("#serveraddress").val(),
		iPort = parseInt($("#serverport").val(), 10) || 0,
		szDeviceID = $("#deviceid").val(),
		szDeviceInfo = "";

	szDeviceInfo = WebVideoCtrl.I_GetIPInfoByMode(iDeviceMode, szAddress, iPort, szDeviceID);

	if ("" == szDeviceInfo) {
		showOPInfo("设备IP和端口解析失败！");
	} else {
		showOPInfo("设备IP和端口解析成功！");

		var arrTemp = szDeviceInfo.split("-");
		$("#loginip").val(arrTemp[0]);
		$("#deviceport").val(arrTemp[1]);
	}
}*/