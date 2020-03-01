package net.lz1998.cq.robot;

import net.lz1998.cq.CQGlobal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class CoolQFactory {
    private ApiHandler apiHandler;

    @Autowired
    public CoolQFactory(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
    }

    public CoolQ createCoolQ(Long selfId, WebSocketSession botSession){
        CoolQ cq=new CoolQ(selfId,botSession,apiHandler, CQGlobal.pluginList);
        return cq;
    }
}
