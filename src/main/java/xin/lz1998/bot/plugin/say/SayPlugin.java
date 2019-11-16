package xin.lz1998.bot.plugin.say;

import xin.lz1998.cq.event.message.CQPrivateMessageEvent;
import xin.lz1998.cq.robot.CQPlugin;
import xin.lz1998.cq.robot.CoolQ;


public class SayPlugin extends CQPlugin {
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        long user_id = event.getSender().getUserId();
        String msg = event.getMessage();
        if (msg.startsWith("say")) {
            cq.sendPrivateMsg(user_id, msg.substring(3), false);
        }
        return MESSAGE_IGNORE; // 继续执行下一个插件
        // return MESSAGE_BLOCK; // 不执行下一个插件
    }
}
