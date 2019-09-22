package com.lyyh.fertilizer.webSocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.jws.HandlerChain;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import com.lyyh.greenhouse.util.MapList;

@Service
public class SocketHandler implements WebSocketHandler {
	// 用户
	public static final String USER_KEY = "username";

	/**
	 * userMap:存储用户连接webscoket信息
	 */
	private final static int size = 300;// 用户数量
	private final static MapList<String, WebSocketSession> sessionMap = new MapList<>();

	/**
	 * 建立websocket连接时调用该方法
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String username = this.getUsername(session);
		if (!StringUtils.isEmpty(username)) {
			sessionMap.put(username, session);
			System.out.println("用户"+ username + "建立连接成功!");
			session.sendMessage( new TextMessage("恭喜"+ username + "建立连接成功!"));
//			TextMessage("用户"+userId+"建立WebSocket连接成功！")); //给所有用户发送消息
		}

	}

	/**
	 * 关闭websocket时调用该方法
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String username = this.getUsername(session);
		if (!StringUtils.isEmpty(username)) {
			sessionMap.remove(username, session);
			System.err.println(username + "用户已成功关闭会话");
		} else {
			sessionMap.remove(session);
			System.err.println("关闭时，获取用户id为空");
		}

	}

	/**
	 * 客户端调用websocket.send时候，会调用该方法,进行数据通信
	 */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		String msg = message.toString();
		String username = this.getUsername(session);
		System.err.println("该" + username + "用户发送的消息是：" + msg);
		message = new TextMessage("服务端已经接收到消息，msg=" + msg);
		session.sendMessage(message);

	}

	/**
	 * 传输过程出现异常时，调用该方法
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		WebSocketMessage<String> message = new TextMessage("异常信息：" + e.getMessage());
		session.sendMessage(message);
	}

	/**
	 * @see org.springframework.web.socket.WebSocketHandler#supportsPartialMessages()
	 */
	@Override
	public boolean supportsPartialMessages() {

		return false;
	}

	/**
	 * sendMessageToUser:发给指定用户
	 */
	public void sendMessageToUser(String username, String contents) {
		List<WebSocketSession> sessionList = sessionMap.get(username);
		if (!CollectionUtils.isEmpty(sessionList)) {
			for (WebSocketSession session : sessionList) {
				if (session != null && session.isOpen()) {
					try {
						TextMessage message = new TextMessage(contents);
						session.sendMessage(message);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * sendMessageToAllUsers:发给所有的用户
	 */
	public void sendMessageToAllUsers(String contents) {
		List<WebSocketSession> sessionList = sessionMap.values();
		if(!CollectionUtils.isEmpty(sessionList)){
			TextMessage message = new TextMessage(contents);
			for (WebSocketSession session : sessionList) {
				try {
					session.sendMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * getUserId:获取用户id
	 */
	private String getUsername(WebSocketSession session) {
		try {
			String username = (String) session.getAttributes().get(USER_KEY);
			return username;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}