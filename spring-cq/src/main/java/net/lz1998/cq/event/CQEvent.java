package net.lz1998.cq.event;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 事件上报
 */
@Data
public class CQEvent {
    /**
     * 上报类型
     */
    @JSONField(name = "post_type")
    private String postType;

    /**
     * 事件发生的时间戳
     */
    @JSONField(name = "time")
    private long time;

    /**
     * 收到消息的机器人 QQ 号
     */
    @JSONField(name = "self_id")
    private long selfId;
}
