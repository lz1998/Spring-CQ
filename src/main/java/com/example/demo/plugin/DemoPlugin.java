package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;


/**
 * 示例插件
 * 插件必须继承CQPlugin，上面要 @Component
 * <p>
 * 添加事件：光标移动到类中，按 Ctrl+O 添加事件(讨论组消息、加群请求、加好友请求等)
 * 查看API参数类型：光标移动到方法括号中按Ctrl+P
 * 查看API说明：光标移动到方法括号中按Ctrl+Q
 */
@Component
public class DemoPlugin extends CQPlugin {
    /**
     * 收到私聊消息时会调用这个方法
     *
     * @param cq    机器人对象，用于调用API，例如发送私聊消息 sendPrivateMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        // 获取 发送者QQ 和 消息内容
        long userId = event.getUserId();
        String msg = event.getMessage();

        if (msg.equals("hi")) {
            // 调用API发送hello
            cq.sendPrivateMsg(userId, "hello", false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }
        // 继续执行下一个插件
        return MESSAGE_IGNORE;
    }


    /**
     * 收到群消息时会调用这个方法
     *
     * @param cq    机器人对象，用于调用API，例如发送群消息 sendGroupMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        // 获取 消息内容 群号 发送者QQ
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        long userId = event.getUserId();

        if (msg.equals("hello")) {
            // 回复内容为 at发送者 + hi
            String result = CQCode.at(userId) + "hi";

            // 调用API发送消息
            cq.sendGroupMsg(groupId, result, false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }

        // 继续执行下一个插件
        return MESSAGE_IGNORE;
    }
}
