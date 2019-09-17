package xin.lz1998.cq.event.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xin.lz1998.cq.entity.CQUser;

@EqualsAndHashCode(callSuper = true)
@Data
public class CQDiscussMessageEvent extends CQMessageEvent {
    @JSONField(name = "discuss_id")
    private long discussId;
    @JSONField(name = "sender")
    private CQUser sender;
}
