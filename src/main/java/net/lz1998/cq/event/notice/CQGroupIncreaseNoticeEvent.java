package net.lz1998.cq.event.notice;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群成员增加
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CQGroupIncreaseNoticeEvent extends CQNoticeEvent {
    /**
     * 事件子类型
     * approve、invite分别表示管理员已同意入群、管理员邀请入群
     */
    @JSONField(name = "sub_type")
    private String subType;
    /**
     * 群号
     */
    @JSONField(name = "group_id")
    private long groupId;
    /**
     * 操作者 QQ 号
     */
    @JSONField(name = "operator_id")
    private long operatorId;
}
