package xin.lz1998.cq.event.meta;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xin.lz1998.cq.event.CQEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class CQMetaEvent extends CQEvent {
    @JSONField(name = "meta_event_type")
    private String metaEventType;
}
