package net.lz1998.cq.event.notice;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.lz1998.cq.entity.CQFile;

/**
 * 群文件上传
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CQGroupUploadNoticeEvent extends CQNoticeEvent {
    /**
     * 群号
     */
    @JSONField(name = "group_id")
    private long groupId;
    /**
     * 文件信息
     */
    @JSONField(name = "file")
    private CQFile file;
}
