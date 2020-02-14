package xin.lz1998.bot.plugin.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import xin.lz1998.cq.event.message.CQPrivateMessageEvent;
import xin.lz1998.cq.retdata.GroupMemberInfoData;
import xin.lz1998.cq.robot.CQPlugin;
import xin.lz1998.cq.robot.CoolQ;

import java.util.List;

@Slf4j
public class TestPlugin extends CQPlugin {
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        long userId=event.getUserId();
        if(userId!=875543533L){
            return MESSAGE_IGNORE;
        }
        // TODO get group member list有问题
        List<GroupMemberInfoData> data = cq.getGroupMemberList(374735267L).getData();
        log.info(JSON.toJSONString(data));
        return super.onPrivateMessage(cq, event);
    }
}
