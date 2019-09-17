package xin.lz1998.cq.event;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class CQEvent {
    @JSONField(name="post_type")
    private String postType;

    @JSONField(name="time")
    private long time;

    @JSONField(name="self_id")
    private long selfId;

}
