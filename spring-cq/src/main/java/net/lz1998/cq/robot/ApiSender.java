package net.lz1998.cq.robot;

import com.alibaba.fastjson.JSONObject;
import net.lz1998.cq.CQGlobal;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

class ApiSender extends Thread {
    private final WebSocketSession apiSession;
    private JSONObject responseJSON;

    ApiSender(WebSocketSession apiSession) {
        this.apiSession = apiSession;
    }

    JSONObject sendApiJson(JSONObject apiJSON) throws IOException, InterruptedException {
        synchronized (apiSession){
            apiSession.sendMessage(new TextMessage(apiJSON.toJSONString()));
        }
        synchronized (this) {
            this.wait(CQGlobal.API_TIME_OUT);
        }
        return responseJSON;
    }


    void onReceiveJson(JSONObject responseJSON) {
        this.responseJSON = responseJSON;
        synchronized (this) {
            this.notify();
        }
    }

}
