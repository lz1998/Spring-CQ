package xin.lz1998.cq.retdata;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class LoginInfoData {
    @JSONField(name = "user_id")
    private long user_id;
    @JSONField(name = "nickname")
    private String nickname;
}
