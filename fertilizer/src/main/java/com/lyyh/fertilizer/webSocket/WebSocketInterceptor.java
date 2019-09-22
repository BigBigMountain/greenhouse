package com.lyyh.fertilizer.webSocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor  {  
    /** 
     * TODO 描述该方法的实现功能. 
     * @see org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor#beforeHandshake(org.springframework.http.server.ServerHttpRequest, org.springframework.http.server.ServerHttpResponse, org.springframework.web.socket.WebSocketHandler, java.util.Map) 
     */  
    @Override  
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,  
            Map<String, Object> attributes) throws Exception {  
        if(request instanceof ServletServerHttpRequest){  
             ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest)request;  
             //获取参数  
             String userId = serverHttpRequest .getServletRequest().getParameter("userId");  
             attributes.put(SocketHandler.USER_KEY, userId);  
        }  
          
        return true;  
    }  
}

