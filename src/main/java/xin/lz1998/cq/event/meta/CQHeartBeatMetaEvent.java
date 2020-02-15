package xin.lz1998.cq.event.meta;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xin.lz1998.cq.entity.CQStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class CQHeartBeatMetaEvent extends CQMetaEvent {
    @JSONField(name = "status")
    private CQStatus status;

    @JSONField(name = "interval")
    private Long interval;
}
