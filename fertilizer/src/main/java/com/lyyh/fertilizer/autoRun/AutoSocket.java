package com.lyyh.fertilizer.autoRun;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.lyyh.fertilizer.dao.TEquipmentDao;
import com.lyyh.greenhouse.util.tzgk.LoginToken;
import com.lyyh.greenhouse.util.tzgk.TzgkConstant;
import com.lyyh.greenhouse.util.tzgk.TzgkUtils;

@Component
public class AutoSocket implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	TEquipmentDao tEquipmentDao;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			//登录
			TzgkUtils.loginTzgk();
			int uid = LoginToken.getUid();
			String tokey = LoginToken.getTokey();
			//拼接uri
			String uri="ws://"+TzgkConstant.host+"/API/echo/"+uid+"/"+tokey;
//			System.out.println(uri);
			try {
				WebSocketClientImpl client = new WebSocketClientImpl(new URI(uri),tEquipmentDao);
//				System.out.println("WebSocketClient: "+client);
				if(null != client){
					client.connect();
					System.out.println("已登录天正websocket服务");
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
