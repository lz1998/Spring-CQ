package net.lz1998.cq.event.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 加群请求／邀请
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CQGroupRequestEvent extends CQRequestEvent {
    /**
     * 请求子类型，分别表示加群请求、邀请机器人入群
     */
    @JSONField(name = "sub_type")
    private String subType;
    /**
     * 群号
     */
    @JSONField(name = "group_id")
    private long groupId;
}
