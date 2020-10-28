package com.Cinema_Management_System.utils.webSocket;



import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * websocket握手拦截器
 * 拦截握手前，握手后的两个切面
 */
public class MyHandShakeInterceptor implements HandshakeInterceptor {

    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
//        System.out.println("Websocket:用户[ID:" + ((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getSession(false).getAttribute("user") + "]已经建立连接");
        System.out.println("Websocket:用户[ID:" + ((ServletServerHttpRequest) serverHttpRequest).getHeaders().get("sec-websocket-protocol") + "]已经建立连接");
//        System.out.println("ServerHttpRequest---------"+serverHttpRequest);
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
            String token= servletRequest.getHeaders().get("sec-websocket-protocol").get(0);
            String userId=token.substring(0,token.indexOf("-"));
            String identity=token.substring(token.indexOf("-")+1);
            System.out.println(userId);
            System.out.println(identity);
            // 标记用户
//            User user = new User(userId);
            if(!"".equals(userId)){
                map.put("uid", userId);//为服务器创建WebSocketSession做准备
                map.put("type", identity);//为服务器创建WebSocketSession做准备
//                map.put("uid", "754406394");//为服务器创建WebSocketSession做准备
                System.out.println("用户id："+userId+" 被加入");
                return true;
            }else{
                System.out.println("userId为空");
                return false;
            }
        }

        return true;
    }

    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();
        if (StringUtils.isNotEmpty(servletRequest.getHeader("sec-websocket-protocol"))){
            servletResponse.setHeader("sec-websocket-protocol",servletRequest.getHeader("sec-websocket-protocol"));
        }

    }
}
