package xin.lz1998.cq.event.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xin.lz1998.cq.entity.CQGroupAnonymous;
import xin.lz1998.cq.entity.CQGroupUser;

@EqualsAndHashCode(callSuper = true)
@Data
public class CQGroupMessageEvent extends CQMessageEvent {
    @JSONField(name = "sub_type")
    private String subType;
    @JSONField(name = "group_id")
    private long groupId;
    @JSONField(name = "anonymous")
    private CQGroupAnonymous anonymous;
    @JSONField(name = "sender")
    private CQGroupUser sender;
}
