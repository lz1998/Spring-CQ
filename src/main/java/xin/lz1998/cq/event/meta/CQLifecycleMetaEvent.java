package xin.lz1998.cq.event.meta;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CQLifecycleMetaEvent extends CQMetaEvent {
    @JSONField(name = "sub_type")
    private String subType;
}
