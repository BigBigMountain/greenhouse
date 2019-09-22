package com.lyyh.fertilizer.autoRun;

import java.net.URI;
import java.util.Iterator;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.lyyh.fertilizer.dao.TEquipmentDao;
import com.lyyh.fertilizer.threadTask.SaveEqmsg;
import com.lyyh.fertilizer.threadTask.StartWebSocketConnect;
import com.lyyh.greenhouse.util.ThreadUtils;

public class WebSocketClientImpl extends WebSocketClient {
	
	TEquipmentDao tEquipmentDao;
	
	public WebSocketClientImpl(URI serverUri,TEquipmentDao tEquipmentDao) {
		super(serverUri);
		this.tEquipmentDao = tEquipmentDao;
	}

	@Override
	public void onOpen(ServerHandshake shake) {
//		System.out.println("握手...");
		for(Iterator<String> it=shake.iterateHttpFields();it.hasNext();){
			String key = it.next();
			System.out.println(key+":"+shake.getFieldValue(key));
		}
	}

	@Override
	public void onMessage(String jsonString) {
		// TODO Auto-generated method stub
		System.out.println("接收到的消息: "+jsonString);
		ThreadUtils.execute(new SaveEqmsg(tEquipmentDao,jsonString));
	}

	@Override
	public void onClose(int arg0, String arg1, boolean arg2) {
		this.close();
		System.out.println("websocket连接关闭了");
		try {
			Thread.sleep(60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ThreadUtils.execute(new StartWebSocketConnect(tEquipmentDao));
	}

	@Override
	public void onError(Exception e) {
		System.out.println("异常"+e);
		
		
	}



}
