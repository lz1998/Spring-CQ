package xin.lz1998.bot.plugin.status;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.lz1998.cq.event.meta.CQHeartBeatEvent;
import xin.lz1998.cq.robot.CQPlugin;
import xin.lz1998.cq.robot.CoolQ;

@Slf4j
public class StatusPlugin extends CQPlugin {
    @Override
    public int onHeartBeatMeta(CoolQ cq, CQHeartBeatEvent event) {
        log.info(cq.getSelfId()+" 在线:"+event.getStatus().isOnline());
        log.info(cq.getSelfId()+" 正常:"+event.getStatus().isGood());
        return MESSAGE_BLOCK;
    }
}
