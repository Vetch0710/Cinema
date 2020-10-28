package com.Cinema_Management_System.utils.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Component注解告诉SpringMVC该类是一个SpringIOC容器下管理的类
 * 其实@Controller, @Service, @Repository是@Component的细化
 */
@Component
@EnableWebSocket
public class MyWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Autowired
    MyWebSocketHandler handler;



    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
//        webSocketHandlerRegistry.addHandler(myHandler(), "/myHandler").setAllowedOrigins("*");

        System.out.println("1213213213");
        //添加websocket处理器，添加握手拦截器
        webSocketHandlerRegistry.addHandler(handler, "/ws").setAllowedOrigins("*").addInterceptors(new MyHandShakeInterceptor());

        //添加websocket处理器，添加握手拦截器
        webSocketHandlerRegistry.addHandler(handler, "/ws/sockjs").setAllowedOrigins("*").addInterceptors(new MyHandShakeInterceptor()).withSockJS();
    }
}
