package net.lz1998.cq.robot;

import net.lz1998.cq.event.message.CQDiscussMessageEvent;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.event.meta.CQHeartBeatMetaEvent;
import net.lz1998.cq.event.meta.CQLifecycleMetaEvent;
import net.lz1998.cq.event.notice.*;
import net.lz1998.cq.event.request.CQFriendRequestEvent;
import net.lz1998.cq.event.request.CQGroupRequestEvent;

public class CQPlugin {
    public static final int MESSAGE_BLOCK = 1;
    public static final int MESSAGE_IGNORE = 0;

    /**
     * 收到私聊消息时调用此方法
     *
     * @param cq    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 收到群消息时调用此方法
     *
     * @param cq    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 收到讨论组消息时调用此方法
     *
     * @param cq    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onDiscussMessage(CoolQ cq, CQDiscussMessageEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群内有文件上传时调用此方法
     * 仅群文件上传表现为事件，好友发送文件在 酷Q 中没有独立的事件，而是直接表现为好友消息，请注意在编写业务逻辑时进行判断。
     *
     * @param cq    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupUploadNotice(CoolQ cq, CQGroupUploadNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群管理员变动时调用此方法
     *
     * @param cq    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupAdminNotice(CoolQ cq, CQGroupAdminNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群成员减少时调用此方法
     *
     * @param cq    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupDecreaseNotice(CoolQ cq, CQGroupDecreaseNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群成员增加时调用此方法
     *
     * @param cq    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupIncreaseNotice(CoolQ cq, CQGroupIncreaseNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群禁言时调用此方法
     *
     * @param cq    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupBanNotice(CoolQ cq, CQGroupBanNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 好友添加时调用此方法
     *
     * @param cq    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onFriendAddNotice(CoolQ cq, CQFriendAddNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 加好友请求时调用此方法
     *
     * @param cq    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onFriendRequest(CoolQ cq, CQFriendRequestEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 加群请求/邀请时调用此方法
     *
     * @param cq    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupRequest(CoolQ cq, CQGroupRequestEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 收到心跳包时调用此方法
     * 心跳类型的元事件需要通过设置配置项 enable_heartbeat 为 true 开启，并可通过 heartbeat_interval 配置心跳间隔（单位毫秒）。
     *
     * @param cq    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onHeartBeatMeta(CoolQ cq, CQHeartBeatMetaEvent event) {
        return MESSAGE_IGNORE;
    }

    public int onLifecycleMeta(CoolQ cq, CQLifecycleMetaEvent event) {
        return MESSAGE_IGNORE;
    }
}
