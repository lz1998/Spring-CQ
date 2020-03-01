package net.lz1998.cq.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.lz1998.cq.CQGlobal;
import net.lz1998.cq.robot.ApiHandler;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.robot.CoolQFactory;
import net.lz1998.cq.robot.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Slf4j
@Service
public class WebSocketHandler extends TextWebSocketHandler {

    private CoolQFactory coolQFactory;
    private ApiHandler apiHandler;
    private EventHandler eventHandler;

    @Autowired
    public WebSocketHandler(CoolQFactory coolQFactory, ApiHandler apiHandler, EventHandler eventHandler) {
        this.coolQFactory = coolQFactory;
        this.apiHandler = apiHandler;
        this.eventHandler = eventHandler;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        long xSelfId = Long.parseLong(session.getHandshakeHeaders().get("x-self-id").get(0));
        CoolQ robot = CQGlobal.robots.get(xSelfId);
        JSONObject recvJson = JSON.parseObject(message.getPayload());
        if (recvJson.containsKey("echo")) {
            // 带有echo说明是调用api的返回数据
            apiHandler.onReceiveApiMessage(recvJson);
        } else {
            // 不带有echo是事件上报
            CQGlobal.executor.execute(() -> eventHandler.handle(robot, recvJson));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        long xSelfId = Long.parseLong(session.getHandshakeHeaders().get("x-self-id").get(0));
        log.info("{} connected", xSelfId);

        CoolQ cq = coolQFactory.createCoolQ(xSelfId, session);

        // 存入Map，方便在未收到消息时调用API发送消息(定时、Controller或其他方式触发)
        CQGlobal.robots.put(xSelfId, cq);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        long xSelfId = Long.parseLong(session.getHandshakeHeaders().get("x-self-id").get(0));
        log.info("{} disconnected", xSelfId);
        CQGlobal.robots.remove(xSelfId);
    }
}