package com.jwis.demo.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.jwis.demo.dao.BopDao;
import com.jwis.demo.util.ServerEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint(value = "/web", encoders = { ServerEncoder.class })
@Slf4j
public class WsgCtrl {

    @Autowired
    RabbitTemplate rabbitTemplate;

    private Session session;

    public static CopyOnWriteArraySet<WsgCtrl> webSocketSet = new CopyOnWriteArraySet<WsgCtrl>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket消息】有新的连接, 总数:{}", webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("【websocket消息】连接断开, 总数:{}", webSocketSet.size());
    }

//    @RabbitListener(queues = "messagechange")
    @OnMessage
    public void onMessage(String message) throws Exception {
        log.info("【websocket消息】收到客户端发来的消息:{}", message);
        System.out.println(message);
        sendMessage(message);

    }

    public void sendMessage(String message) throws Exception {
//        for (WsgCtrl webSocket: webSocketSet) {
//            log.info("【websocket消息】广播消息, message={}", message);
//            try {
//                webSocket.session.getBasicRemote().sendText(message+"======");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        JSONObject jsonObject = JSONObject.parseObject(message);
        BopDao bopDao = JSONObject.toJavaObject(jsonObject, BopDao.class);
        this.session.getBasicRemote().sendObject(bopDao);

//         this.session.getBasicRemote().sendText(message);
    }

}

