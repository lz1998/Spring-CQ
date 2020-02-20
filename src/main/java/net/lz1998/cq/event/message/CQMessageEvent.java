package net.lz1998.cq.event.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.lz1998.cq.event.CQEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class CQMessageEvent extends CQEvent {
    /**
     * 消息类型
     */
    @JSONField(name = "message_type")
    private String messageType;
    /**
     * 消息 ID
     */
    @JSONField(name = "message_id")
    private int messageId;
    /**
     * 发送者 QQ 号
     */
    @JSONField(name = "user_id")
    private long userId;
    /**
     * 消息内容
     */
    @JSONField(name = "message")
    private String message;
    /**
     * 原始消息内容
     */
    @JSONField(name = "raw_message")
    private String rawMessage;
    /**
     * 字体
     */
    @JSONField(name = "font")
    private int font;
}
