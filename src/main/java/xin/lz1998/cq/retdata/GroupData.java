package xin.lz1998.cq.retdata;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class GroupData {
    @JSONField(name = "group_id")
    private long groupId;
    @JSONField(name = "group_name")
    private String groupName;
}
