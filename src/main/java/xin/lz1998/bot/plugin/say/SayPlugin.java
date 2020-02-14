package xin.lz1998.bot.plugin.say;

import lombok.extern.slf4j.Slf4j;
import xin.lz1998.cq.event.message.CQGroupMessageEvent;
import xin.lz1998.cq.event.message.CQPrivateMessageEvent;
import xin.lz1998.cq.robot.CQPlugin;
import xin.lz1998.cq.robot.CoolQ;
import xin.lz1998.cq.utils.CQCode;

@Slf4j
public class SayPlugin extends CQPlugin {
    /**
     * 收到私聊消息后会调用这个方法
     *
     * @param cq    机器人对象，用于调用API，例如发送私聊消息 sendPrivateMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        long userId = event.getUserId();
        String msg = event.getMessage();
        if (msg.startsWith("say")) {
            cq.sendPrivateMsg(userId, msg.substring(3), false);
        }
        return MESSAGE_IGNORE; // 继续执行下一个插件
        // return MESSAGE_BLOCK; // 不执行下一个插件
    }

    /**
     * 收到群消息后会调用这个方法
     *
     * @param cq    机器人对象，用于调用API，例如发送群消息 sendPrivateMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        // 获取消息内容、群号、发送者QQ
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        long userId = event.getUserId();

        if (msg.startsWith("say")) {
            // 回复内容为 at发送者 + say之后的内容
            String result = CQCode.at(userId) + msg.substring("say".length());

            // 调用API发送消息
            cq.sendGroupMsg(groupId, result, false);
        }

        // 在PluginConfig中配置的插件List，不调用下一个Plugin
        return MESSAGE_BLOCK;
    }
}
