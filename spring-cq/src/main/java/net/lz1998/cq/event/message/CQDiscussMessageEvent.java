package net.lz1998.cq.event.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.lz1998.cq.entity.CQUser;

/**
 * 讨论组消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CQDiscussMessageEvent extends CQMessageEvent {
    /**
     * 讨论组 ID
     */
    @JSONField(name = "discuss_id")
    private long discussId;
    /**
     * 发送人信息
     */
    @JSONField(name = "sender")
    private CQUser sender;
}
