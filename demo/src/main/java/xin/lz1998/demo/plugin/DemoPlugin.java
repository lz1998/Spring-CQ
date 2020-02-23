package xin.lz1998.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.stereotype.Component;

@Component
public class DemoPlugin extends CQPlugin {
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        Long userId=event.getUserId();
        cq.sendPrivateMsg(userId,"hello world",false);
        return MESSAGE_BLOCK;
    }

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        Long groupId=event.getGroupId();
        if(!groupId.equals(335783090L)){
            return MESSAGE_BLOCK; // 如果群号不是335783090忽略，否则测试时会影响其他群
        }
        String msg=event.getMessage();
        String result="你发送了"+msg;
        cq.sendGroupMsg(groupId,result,false);
        return MESSAGE_BLOCK;
    }

}
