package xin.lz1998.cq.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import xin.lz1998.Global;
import xin.lz1998.cq.robot.CoolQ;
import xin.lz1998.cq.robot.CoolQFactory;


@Slf4j
@Service
public class WebSocketHandler extends TextWebSocketHandler {

    private CoolQFactory coolQFactory;

    @Autowired
    public WebSocketHandler(CoolQFactory coolQFactory) {
        this.coolQFactory = coolQFactory;
    }

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

        // 如果在session连接时，已经有重复的QQ号在Map中，说明之前的是错误的，删除之前的
        if(Global.robots.containsKey(xSelfId)){
            Global.robots.remove(xSelfId);
        }

        CoolQ cq=coolQFactory.createCoolQ(xSelfId,session);

        // 存入Map，方便在未收到消息时调用API发送消息(定时、Controller或其他方式触发)
        Global.robots.put(xSelfId,cq);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        long xSelfId = Long.valueOf(session.getHandshakeHeaders().get("x-self-id").get(0));
        log.info("{} disconnected", xSelfId);
        Global.robots.remove(xSelfId);
    }
}