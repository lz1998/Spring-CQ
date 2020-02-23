package net.lz1998.cq.retdata;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class GroupInfoData {
    @JSONField(name = "group_id")
    private long groupId;
    @JSONField(name = "group_name")
    private String groupName;
    @JSONField(name = "member_count")
    private Integer memberCount;
    @JSONField(name = "max_member_count")
    private Integer maxMemberCount;
}
