//package com.Cinema_Management_System.utils.webSocket;
//
//import com.CinemaData.aaa.entity.Message;
//import com.CinemaData.aaa.service.XXXService;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.*;
//
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.*;
//
//
//@Component
//public class MyWebSocketHandler implements WebSocketHandler {
//    @Qualifier("XXXService")
//    @Autowired
//    private XXXService infoService;
//
//
//    //当MyWebSocketHandler类被加载时就会创建该Map，随类而生
//    public static final Map<String, WebSocketSession> userSocketSessionMap;
//
//    static {
//        userSocketSessionMap = new HashMap<String, WebSocketSession>();
//    }
//
//    //握手实现连接后
//    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
//        System.out.println("握手实现连接后------------id----------" + webSocketSession.getAttributes().get("uid"));
//        String uid = webSocketSession.getAttributes().get("uid").toString();
//        String identity = webSocketSession.getAttributes().get("type").toString();
////        if (userSocketSessionMap.get(uid) == null) {
//        System.out.println("webSocketSession+++++++++++++++++++");
//        if ("customer".equals(identity)) {
//            userSocketSessionMap.put(uid, webSocketSession);
//            System.out.println("查询聊天记录");
//            List<Message> messages = infoService.selectAllInfo(Integer.parseInt(uid));
//            sendHistoryMessage(uid, new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(messages)));
//        } else {
//            userSocketSessionMap.put(identity, webSocketSession);
//            System.out.println("查询未读信息");
//            List<Message> messages = infoService.selectInfoByType();
//            sendHistoryMessage(identity, new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(messages)));
//
//        }
////        if ("customer".equals(identity)) {
////            System.out.println("查询聊天记录");
////            List<Message> messages = infoService.selectAllInfo(Integer.parseInt(uid));
////            sendMessageToUser(uid, new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(messages)));
////        }else {
////            System.out.println("查询未读信息");
////            List<Message> messages = infoService.selectInfoByType();
////            sendMessageToUser(identity, new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(messages)));
////
////        }
////        }
//    }
//
//    //发送信息前的处理
//    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
//        System.out.println("发送信息前的处理--userSocketSessionMap" + userSocketSessionMap);
//        String uid = webSocketSession.getAttributes().get("uid").toString();
//        String identity = webSocketSession.getAttributes().get("type").toString();
//        System.out.println(webSocketMessage.getPayloadLength() + "++++++" + webSocketMessage.getPayload());
//        if (webSocketMessage.getPayloadLength() == 0) {
//            return;
//        }
//        ;
//
//        //得到Socket通道中的数据并转化为Message对象
//        Message msg = new Gson().fromJson(webSocketMessage.getPayload().toString(), Message.class);
//        Timestamp now = new Timestamp(System.currentTimeMillis());
//        System.out.println(now+"sssssssssssssssssssssssss");
//        msg.setInfoTime(now);
//        if ("customer".equals(identity)) {
//            msg.setInfoStatus("未读");
//            msg.setInfoType("customer");
//            msg.setCustomerId(Integer.parseInt(uid));
//            int id = infoService.addInfo(msg);
//            String name = infoService.selectCustomerName(Integer.parseInt(uid));
//            msg.setCustomerName(name);
////            msg.setInfoId(id);
//            System.out.println("发送的消息是"+msg);
//            sendMessageToUser(String.valueOf(msg.getCustomerId()),"manager", new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
//        }else {
//            msg.setInfoStatus("未读");
//            msg.setInfoType("manager");
//            int id = infoService.addInfo(msg);
//            String name = infoService.selectCustomerName(msg.getCustomerId());
//            msg.setCustomerName(name);
////            msg.setInfoId(id);
//            System.out.println("发送的消息是"+msg);
//            sendMessageToUser("manager",String.valueOf(msg.getCustomerId()), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
//        }
//
//        //将信息保存至数据库
////        youandmeService.addMessage(msg.getFromId(),msg.getFromName(),msg.getToId(),msg.getMessageText(),msg.getMessageDate());
//
//        //发送Socket信息
////        sendMessageToUser(msg.getToId(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
////        sendMessageToUser(msg.getCustomerId(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
//    }
//
//    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
//        System.out.println("handleTransportError");
//    }
//
//*
//     * 在此刷新页面就相当于断开WebSocket连接,原本在静态变量userSocketSessionMap中的
//     * WebSocketSession会变成关闭状态(close)，但是刷新后的第二次连接服务器创建的
//     * 新WebSocketSession(open状态)又不会加入到userSocketSessionMap中,所以这样就无法发送消息
//     * 因此应当在关闭连接这个切面增加去除userSocketSessionMap中当前处于close状态的WebSocketSession，
//     * 让新创建的WebSocketSession(open状态)可以加入到userSocketSessionMap中
//     *
//     * @param webSocketSession
//     * @param closeStatus
//     * @throws Exception
//
//
//    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
//        System.out.println("afterConnectionClosed");
//        System.out.println("WebSocket:" + webSocketSession.getAttributes().get("uid") + "close connection");
//        Iterator<Map.Entry<String, WebSocketSession>> iterator = userSocketSessionMap.entrySet().iterator();
//        String uid = webSocketSession.getAttributes().get("uid").toString();
//        String identity = webSocketSession.getAttributes().get("type").toString();
//        while (iterator.hasNext()) {
//            Map.Entry<String, WebSocketSession> entry = iterator.next();
//            if ("customer".equals(identity)) {
//                if (entry.getValue().getAttributes().get("uid").equals(webSocketSession.getAttributes().get("uid"))) {
//                    userSocketSessionMap.remove(uid);
//                    System.out.println("WebSocket in staticMap:" + webSocketSession.getAttributes().get("uid") + "removed");
//                }
//            } else {
//                userSocketSessionMap.remove(identity);
//                System.out.println("WebSocket in staticMap:" + identity + "removed");
//            }
//        }
//        System.out.println("***********" + userSocketSessionMap + "***************");
//    }
//
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//
//    //发送信息的实现
//    public void sendMessageToUser(String from,String to, TextMessage message)
//            throws IOException {
//        System.out.println("检查是否在线");
//        WebSocketSession session = userSocketSessionMap.get(to);
//        WebSocketSession session2 = userSocketSessionMap.get(from);
////        System.out.println(session);
////        System.out.println(session.isOpen());
//        if (session != null && session.isOpen()) {
//            System.out.println("在线--发送信息---"+message);
//            session.sendMessage(message);
//        }else {
//            System.out.println("不在线---并返回数据");
//        }
//        session2.sendMessage(message);
//    }
//    //发送历史消息的实现
//    public void sendHistoryMessage(String from ,TextMessage message)
//            throws IOException {
//        WebSocketSession session = userSocketSessionMap.get(from);
//        System.out.println(session);
//        System.out.println(session.isOpen());
//        if (session != null && session.isOpen()) {
//            System.out.println("发送历史信息---"+message);
//            session.sendMessage(message);
//        }
//    }
//}
