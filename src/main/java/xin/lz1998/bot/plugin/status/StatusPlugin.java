package xin.lz1998.bot.plugin.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.lz1998.cq.event.meta.CQHeartBeatEvent;
import xin.lz1998.cq.robot.CQPlugin;
import xin.lz1998.cq.robot.CoolQ;

public class StatusPlugin extends CQPlugin {
    private Logger logger = LoggerFactory.getLogger(StatusPlugin.class);
    @Override
    public int onHeartBeatMeta(CoolQ cq, CQHeartBeatEvent event) {
        logger.info(cq.getSelfId()+" 在线:"+event.getStatus().isOnline());
        logger.info(cq.getSelfId()+" 正常:"+event.getStatus().isGood());
        return MESSAGE_BLOCK;
    }
}
