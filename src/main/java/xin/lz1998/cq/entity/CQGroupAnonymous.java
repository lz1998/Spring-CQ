package xin.lz1998.cq.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class CQGroupAnonymous {
    @JSONField(name = "id")
    private long id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "flag")
    private String flag;
}