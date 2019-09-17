package xin.lz1998.cq.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class CQFile {
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "size")
    private long size;
    @JSONField(name = "busid")
    private long busid;
}
