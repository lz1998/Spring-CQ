package xin.lz1998.cq.event.notice;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xin.lz1998.cq.entity.CQFile;

@EqualsAndHashCode(callSuper = true)
@Data
public class CQGroupUploadNoticeEvent extends CQNoticeEvent {
    @JSONField(name = "group_id")
    private long groupId;
    @JSONField(name = "file")
    private CQFile file;
}
