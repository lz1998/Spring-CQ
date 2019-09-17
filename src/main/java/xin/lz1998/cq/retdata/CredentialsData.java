package xin.lz1998.cq.retdata;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class CredentialsData {
    @JSONField(name = "cookies")
    private String cookies;
    @JSONField(name = "csrf_token")
    private int csrfToken;
}
