package xin.lz1998.bot.plugin.status;

import lombok.extern.slf4j.Slf4j;
import xin.lz1998.cq.event.meta.CQHeartBeatMetaEvent;
import xin.lz1998.cq.robot.CQPlugin;
import xin.lz1998.cq.robot.CoolQ;

@Slf4j
public class StatusPlugin extends CQPlugin {
    @Override
    public int onHeartBeatMeta(CoolQ cq, CQHeartBeatMetaEvent event) {
        log.info(cq.getSelfId()+" 是否在线:"+event.getStatus().isOnline());
        log.info(cq.getSelfId()+" 是否正常:"+event.getStatus().isGood());
        return MESSAGE_BLOCK;
    }
}
