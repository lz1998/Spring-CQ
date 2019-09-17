package xin.lz1998.cq.event.notice;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CQGroupDecreaseNoticeEvent extends CQNoticeEvent {
    @JSONField(name = "sub_type")
    private String subType;
    @JSONField(name = "group_id")
    private long groupId;
    @JSONField(name = "operator_id")
    private long operatorId;
}
