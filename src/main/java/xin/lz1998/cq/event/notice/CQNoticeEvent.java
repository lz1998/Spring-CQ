package xin.lz1998.cq.event.notice;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xin.lz1998.cq.event.CQEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class CQNoticeEvent extends CQEvent {
    @JSONField(name = "notice_type")
    private String noticeType;
    @JSONField(name = "user_id")
    private long userId;
}
