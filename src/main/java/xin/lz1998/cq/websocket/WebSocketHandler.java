package xin.lz1998.cq.websocket;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import xin.lz1998.cq.Global;
import xin.lz1998.cq.robot.CoolQ;


public class WebSocketHandler extends TextWebSocketHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        long xSelfId = Long.valueOf(session.getHandshakeHeaders().get("x-self-id").get(0));
        String xClientRole = session.getHandshakeHeaders().get("x-client-role").get(0);
        CoolQ robot = Global.robots.get(xSelfId);

        if ("Event".equals(xClientRole)) {
            robot.onReceiveEventMessage(JSON.parseObject(message.getPayload()));
        } else if ("API".equals(xClientRole)) {
            robot.onReceiveApiMessage(JSON.parseObject(message.getPayload()));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        long xSelfId = Long.valueOf(session.getHandshakeHeaders().get("x-self-id").get(0));
        String xClientRole = session.getHandshakeHeaders().get("x-client-role").get(0);
        logger.info("{} {} connected", xSelfId, xClientRole);

        Global.robots.putIfAbsent(xSelfId, new CoolQ(xSelfId));
        CoolQ robot = Global.robots.get(xSelfId);
        robot.setSelfId(xSelfId);

        if ("Event".equals(xClientRole)) {
            robot.setEventSession(session);
        } else if ("API".equals(xClientRole)) {
            robot.setApiSession(session);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        long xSelfId = Long.valueOf(session.getHandshakeHeaders().get("x-self-id").get(0));
        String xClientRole = session.getHandshakeHeaders().get("x-client-role").get(0);
        logger.info("{} {} disconnected", xSelfId, xClientRole);

        Global.robots.putIfAbsent(xSelfId, new CoolQ(xSelfId));
        CoolQ robot = Global.robots.get(xSelfId);

        if ("Event".equals(xClientRole)) {
            robot.setEventSession(null);
        } else if ("API".equals(xClientRole)) {
            robot.setApiSession(null);
        }

        if (robot.getApiSession() == null && robot.getEventSession() == null) {
            Global.robots.remove(xSelfId);
        }
    }
}