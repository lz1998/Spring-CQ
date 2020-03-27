package net.lz1998.cq.robot;

import net.lz1998.cq.boot.CQProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

/**
 * 酷Q 工厂类
 */
@Service
public class CoolQFactory {
    @Autowired
    private ApiHandler apiHandler;
    @Autowired
    private CQProperties cqProperties;


    /**
     * 创建一个CoolQ对象
     * 把spring容器中的apiHandler放入对象
     *
     * @param selfId     机器人自己的QQ号
     * @param botSession ws的session
     * @return CoolQ对象
     */
    public CoolQ createCoolQ(Long selfId, WebSocketSession botSession) {
        CoolQ cq = new CoolQ(selfId, botSession, apiHandler, cqProperties.getPluginList());
        return cq;
    }
}
