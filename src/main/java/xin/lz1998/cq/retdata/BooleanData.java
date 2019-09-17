package xin.lz1998.cq.retdata;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class BooleanData {
    @JSONField(name = "yes")
    private boolean yes;
}
