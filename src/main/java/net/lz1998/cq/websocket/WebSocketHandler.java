package net.lz1998.cq.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.lz1998.cq.CQGlobal;
import net.lz1998.cq.boot.EventProperties;
import net.lz1998.cq.robot.ApiHandler;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.robot.CoolQFactory;
import net.lz1998.cq.robot.EventHandler;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * ws处理类
 * <p>
 * 建立连接
 * 断开连接
 * 收到消息
 */
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private CoolQFactory coolQFactory;
    private ApiHandler apiHandler;
    private EventHandler eventHandler;
    private ExecutorService executor;


    public WebSocketHandler(EventProperties eventProperties, CoolQFactory coolQFactory, ApiHandler apiHandler, EventHandler eventHandler) {
        this.coolQFactory = coolQFactory;
        this.apiHandler = apiHandler;
        this.eventHandler = eventHandler;
        this.executor =
                new ThreadPoolExecutor(eventProperties.getCorePoolSize(),
                        eventProperties.getMaxPoolSize(),
                        eventProperties.getKeepAliveTime(),
                        TimeUnit.MILLISECONDS,
                        new ArrayBlockingQueue<>(eventProperties.getWorkQueueSize()));
    }

    /**
     * ws建立连接，创建CoolQ对象，并放入CQGlobal.robots，是static Map，方便在jar外面获取
     *
     * @param session websocket session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        long xSelfId = Long.parseLong(session.getHandshakeHeaders().get("x-self-id").get(0));
        log.info("{} connected", xSelfId);

        // 新连接上的，创建一个对象
        CoolQ cq = coolQFactory.createCoolQ(xSelfId, session);

        // 存入Map，方便在未收到消息时调用API发送消息(定时、Controller或其他方式触发)
        CQGlobal.robots.put(xSelfId, cq);
    }

    /**
     * ws连接断开，需要清除之前的CoolQ对象
     *
     * @param session websocket session
     * @param status  状态码
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        long xSelfId = Long.parseLong(session.getHandshakeHeaders().get("x-self-id").get(0));
        log.info("{} disconnected", xSelfId);

        CQGlobal.robots.remove(xSelfId);
    }

    /**
     * ws收到消息时的方法
     * 可能是api响应（包含echo字段）
     * 可能是事件上报
     *
     * @param session websocketSession
     * @param message 消息内容
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        long xSelfId = Long.parseLong(session.getHandshakeHeaders().get("x-self-id").get(0));
        CoolQ robot = CQGlobal.robots.get(xSelfId);

        // 防止网络问题，快速重连可能 （连接1，断开1，连接2） 变成 （连接1，连接2，断开1）
        if (robot == null) {
            robot = coolQFactory.createCoolQ(xSelfId, session);
            CQGlobal.robots.put(xSelfId, robot);
        }
        robot.setBotSession(session);

        JSONObject recvJson = JSON.parseObject(message.getPayload());
        if (recvJson.containsKey("echo")) {
            // 带有echo说明是调用api的返回数据
            apiHandler.onReceiveApiMessage(recvJson);
        } else {
            // 不带有echo是事件上报
            CoolQ finalRobot = robot;
            executor.execute(() -> eventHandler.handle(finalRobot, recvJson));
        }
    }
}