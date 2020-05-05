package net.lz1998.cq.robot;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.lz1998.cq.event.message.CQDiscussMessageEvent;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.event.meta.CQHeartBeatMetaEvent;
import net.lz1998.cq.event.meta.CQLifecycleMetaEvent;
import net.lz1998.cq.event.notice.*;
import net.lz1998.cq.event.request.CQFriendRequestEvent;
import net.lz1998.cq.event.request.CQGroupRequestEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


/**
 * 事件处理器
 * 先根据 post_type 分类，消息/通知/请求/元事件
 * 然后交给对应的继续分类
 * 职责链模式调用插件，返回MESSAGE_BLOCK停止
 */
@Slf4j
public class EventHandler {

    private ApplicationContext applicationContext;

    private CQPlugin defaultPlugin = new CQPlugin();

    public EventHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void handle(CoolQ cq, JSONObject eventJson) {
        String postType = eventJson.getString("post_type");
        switch (postType) {
            case "message": {
                handleMessage(cq, eventJson);
                break;
            }
            case "notice": {
                handleNotice(cq, eventJson);
                break;
            }
            case "request": {
                handleRequest(cq, eventJson);
                break;
            }
            case "meta_event": {
                handleMeta(cq, eventJson);
                break;
            }
        }
    }

    private void handleMessage(CoolQ cq, JSONObject eventJson) {
        String messageType = eventJson.getString("message_type");
        switch (messageType) {
            case "private": {
                CQPrivateMessageEvent event = eventJson.toJavaObject(CQPrivateMessageEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onPrivateMessage(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group": {
                CQGroupMessageEvent event = eventJson.toJavaObject(CQGroupMessageEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupMessage(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "discuss": {
                CQDiscussMessageEvent event = eventJson.toJavaObject(CQDiscussMessageEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onDiscussMessage(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
        }

    }

    private void handleNotice(CoolQ cq, JSONObject eventJson) {

        String noticeType = eventJson.getString("notice_type");

        switch (noticeType) {
            case "group_upload": {
                CQGroupUploadNoticeEvent event = eventJson.toJavaObject(CQGroupUploadNoticeEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupUploadNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_admin": {
                CQGroupAdminNoticeEvent event = eventJson.toJavaObject(CQGroupAdminNoticeEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupAdminNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_decrease": {
                CQGroupDecreaseNoticeEvent event = eventJson.toJavaObject(CQGroupDecreaseNoticeEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupDecreaseNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_increase": {
                CQGroupIncreaseNoticeEvent event = eventJson.toJavaObject(CQGroupIncreaseNoticeEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupIncreaseNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_ban": {
                CQGroupBanNoticeEvent event = eventJson.toJavaObject(CQGroupBanNoticeEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupBanNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "friend_add": {
                CQFriendAddNoticeEvent event = eventJson.toJavaObject(CQFriendAddNoticeEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onFriendAddNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
        }


    }

    private void handleRequest(CoolQ cq, JSONObject eventJson) {
        String requestType = eventJson.getString("request_type");
        switch (requestType) {
            case "friend": {
                CQFriendRequestEvent event = eventJson.toJavaObject(CQFriendRequestEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onFriendRequest(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group": {
                CQGroupRequestEvent event = eventJson.toJavaObject(CQGroupRequestEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupRequest(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
        }
    }

    private void handleMeta(CoolQ cq, JSONObject eventJson) {
        String metaType = eventJson.getString("meta_event_type");
        switch (metaType) {
            case "heartbeat": {
                CQHeartBeatMetaEvent event = eventJson.toJavaObject(CQHeartBeatMetaEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onHeartBeatMeta(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "lifecycle": {
                CQLifecycleMetaEvent event = eventJson.toJavaObject(CQLifecycleMetaEvent.class);
                for (Class<? extends CQPlugin> pluginClass : cq.getPluginList()) {
                    if (getPlugin(pluginClass).onLifecycleMeta(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
        }
    }

    private CQPlugin getPlugin(Class<? extends CQPlugin> pluginClass) {
        try {
            return applicationContext.getBean(pluginClass);
        } catch (Exception e) {
            log.error("已跳过 {} ，请检查 @Component", pluginClass.getSimpleName());
            return defaultPlugin;
        }
    }
}
