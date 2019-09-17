package xin.lz1998.cq.event.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xin.lz1998.cq.event.CQEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class CQRequestEvent extends CQEvent {
    @JSONField(name = "request_type")
    private String requestType;
    @JSONField(name = "user_id")
    private long userId;
    @JSONField(name = "comment")
    private String comment;
    @JSONField(name = "flag")
    private String flag;
}
