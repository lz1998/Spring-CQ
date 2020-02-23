package net.lz1998.cq.event.notice;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群禁言
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CQGroupBanNoticeEvent extends CQNoticeEvent {
    /**
     * 事件子类型
     * ban、lift_ban分别表示禁言、解除禁言
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
    /**
     * 禁言时长
     * 单位秒
     */
    @JSONField(name = "duration")
    private long duration;
}
