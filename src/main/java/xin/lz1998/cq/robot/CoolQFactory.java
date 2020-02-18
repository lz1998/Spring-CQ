package xin.lz1998.cq.robot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class CoolQFactory {
    private EventHandler eventHandler;
    private RobotConfigInterface robotConfig;

    @Autowired
    public CoolQFactory(EventHandler eventHandler, RobotConfigInterface robotConfig) {
        this.eventHandler = eventHandler;
        this.robotConfig = robotConfig;
    }

    public CoolQ createCoolQ(Long selfId, WebSocketSession botSession){
        CoolQ cq=new CoolQ(selfId,botSession,eventHandler,robotConfig);
        return cq;
    }
}
