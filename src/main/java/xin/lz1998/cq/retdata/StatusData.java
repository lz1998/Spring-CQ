package xin.lz1998.cq.retdata;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class StatusData {
    @JSONField(name = "app_initialized")
    private boolean appInitialized;
    @JSONField(name = "app_enabled")
    private boolean appEnabled;
    @JSONField(name = "plugins_good")
    private JSONObject pluginsGood;
    @JSONField(name = "app_good")
    private boolean appGood;
    @JSONField(name = "online")
    private boolean online;
    @JSONField(name = "good")
    private boolean good;
}
