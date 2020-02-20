package net.lz1998.cq.event.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.lz1998.cq.event.CQEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class CQRequestEvent extends CQEvent {
    /**
     * 请求类型
     * group/friend
     */
    @JSONField(name = "request_type")
    private String requestType;
    /**
     * 发送请求的 QQ 号
     */
    @JSONField(name = "user_id")
    private long userId;
    /**
     * 验证信息（可能包含 CQ 码，特殊字符被转义）
     */
    @JSONField(name = "comment")
    private String comment;
    /**
     * 请求 flag，在调用处理请求的 API 时需要传入
     */
    @JSONField(name = "flag")
    private String flag;
}
