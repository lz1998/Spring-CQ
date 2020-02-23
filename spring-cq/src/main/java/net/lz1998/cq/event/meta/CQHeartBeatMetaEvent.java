package net.lz1998.cq.event.meta;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.lz1998.cq.entity.CQStatus;

/**
 * 心跳
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CQHeartBeatMetaEvent extends CQMetaEvent {
    /**
     * 状态信息
     */
    @JSONField(name = "status")
    private CQStatus status;

    /**
     * 到下次心跳的间隔，单位毫秒
     */
    @JSONField(name = "interval")
    private Long interval;
}
