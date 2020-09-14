package com.lyp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@ServerEndpoint("/ws/user")
@Controller
public class WebSocketController {
    private static final AtomicLong userCounter = new AtomicLong(0);
    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<>();
    private Session session;

    //发送消息
    public static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)", message, session.getId()));
        } catch (IOException e) {
            log.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        sessionSet.add(session);
        long count = userCounter.incrementAndGet();// 在线数加1
        log.info("有连接加入，当前连接数为：{}", count);
    }

    //收到消息
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口{}的信息:{}", session.getId(), message);
    }

    @OnClose
    public void onClose() {
        sessionSet.remove(this.session);  //从set中删除
        long count = userCounter.decrementAndGet();//在线数减1
        log.info("有一连接关闭！当前在线人数为{}", count);
    }

    //出现错误
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
        error.printStackTrace();
    }
}
