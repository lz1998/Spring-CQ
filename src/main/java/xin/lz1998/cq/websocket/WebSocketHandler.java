package xin.lz1998.cq.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import xin.lz1998.Global;
import xin.lz1998.cq.robot.CoolQ;


@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        long xSelfId = Long.valueOf(session.getHandshakeHeaders().get("x-self-id").get(0));
        CoolQ robot = Global.robots.get(xSelfId);
        JSONObject recvJson = JSON.parseObject(message.getPayload());
        if (recvJson.containsKey("echo")) {
            // 带有echo说明是调用api的返回数据
            robot.onReceiveApiMessage(recvJson);
        } else {
            // 不带有echo是事件上报
            robot.onReceiveEventMessage(recvJson);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        long xSelfId = Long.valueOf(session.getHandshakeHeaders().get("x-self-id").get(0));
        log.info("{} connected", xSelfId);
        Global.robots.putIfAbsent(xSelfId, new CoolQ(xSelfId));
        CoolQ robot = Global.robots.get(xSelfId);
        robot.setSelfId(xSelfId);
        robot.setBotSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        long xSelfId = Long.valueOf(session.getHandshakeHeaders().get("x-self-id").get(0));
        log.info("{} disconnected", xSelfId);
        Global.robots.remove(xSelfId);
    }
}