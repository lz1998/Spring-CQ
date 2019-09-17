package xin.lz1998.cq.plugin.say;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xin.lz1998.cq.event.message.CQPrivateMessageEvent;
import xin.lz1998.cq.robot.CQPlugin;
import xin.lz1998.cq.robot.CoolQ;


@Component // 加入容器
@Order(2) // 插件执行顺序，有多个插件，这个插件第几个执行
public class SayPlugin extends CQPlugin {
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        long user_id = event.getSender().getUserId();
        String msg = event.getMessage();
        if (msg.startsWith("/say")) {
            cq.sendPrivateMsg(user_id, msg.substring(4), false);
        }
        return MESSAGE_IGNORE; // 继续执行下一个插件
        // return MESSAGE_BLOCK; // 不执行下一个插件
    }
}
