package com.lyyh.fertilizer.threadTask;

import java.net.URI;
import java.net.URISyntaxException;

import com.lyyh.fertilizer.autoRun.WebSocketClientImpl;
import com.lyyh.fertilizer.dao.TEquipmentDao;
import com.lyyh.greenhouse.util.tzgk.LoginToken;
import com.lyyh.greenhouse.util.tzgk.TzgkUtils;

public class StartWebSocketConnect implements Runnable {
	
	TEquipmentDao tEquipmentDao;

	public StartWebSocketConnect() {
		super();
	}

	public StartWebSocketConnect(TEquipmentDao tEquipmentDao) {
		super();
		this.tEquipmentDao = tEquipmentDao;
	}

	@Override
	public void run() {
		TzgkUtils.loginTzgk();
		int uid = LoginToken.getUid();
		String tokey = LoginToken.getTokey();
		//拼接uri
		String uri="ws://www.tscsmart.com/API/echo/"+uid+"/"+tokey;
//		System.out.println(uri);
		try {
			WebSocketClientImpl client = new WebSocketClientImpl(new URI(uri),tEquipmentDao);
//			System.out.println("WebSocketClient: "+client);
			if(null != client){
				client.connect();
				System.out.println("已重新登录天正websocket服务");
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
