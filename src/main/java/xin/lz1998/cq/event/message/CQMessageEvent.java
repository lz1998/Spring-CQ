package xin.lz1998.cq.event.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xin.lz1998.cq.event.CQEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class CQMessageEvent extends CQEvent {
    @JSONField(name="message_type")
    private String messageType;
    @JSONField(name="message_id")
    private int messageId;
    @JSONField(name="user_id")
    private long userId;
    @JSONField(name="message")
    private String message;
    @JSONField(name = "raw_message")
    private String rawMessage;
    @JSONField(name = "font")
    private int font;
}
