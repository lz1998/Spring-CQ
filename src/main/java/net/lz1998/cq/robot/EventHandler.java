package net.lz1998.cq.robot;

import com.alibaba.fastjson.JSONObject;
import net.lz1998.cq.event.message.CQDiscussMessageEvent;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.event.meta.CQHeartBeatMetaEvent;
import net.lz1998.cq.event.meta.CQLifecycleMetaEvent;
import net.lz1998.cq.event.notice.*;
import net.lz1998.cq.event.request.CQFriendRequestEvent;
import net.lz1998.cq.event.request.CQGroupRequestEvent;
import org.springframework.stereotype.Service;


@Service
class EventHandler {

    


    public void handle(CoolQ cq, JSONObject eventJson) {
        System.out.println(eventJson);
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
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onPrivateMessage(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group": {
                CQGroupMessageEvent event = eventJson.toJavaObject(CQGroupMessageEvent.class);
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onGroupMessage(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "discuss": {
                CQDiscussMessageEvent event = eventJson.toJavaObject(CQDiscussMessageEvent.class);
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onDiscussMessage(cq, event) == CQPlugin.MESSAGE_BLOCK)
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
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onGroupUploadNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_admin": {
                CQGroupAdminNoticeEvent event = eventJson.toJavaObject(CQGroupAdminNoticeEvent.class);
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onGroupAdminNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_decrease": {
                CQGroupDecreaseNoticeEvent event = eventJson.toJavaObject(CQGroupDecreaseNoticeEvent.class);
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onGroupDecreaseNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_increase": {
                CQGroupIncreaseNoticeEvent event = eventJson.toJavaObject(CQGroupIncreaseNoticeEvent.class);
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onGroupIncreaseNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_ban": {
                CQGroupBanNoticeEvent event = eventJson.toJavaObject(CQGroupBanNoticeEvent.class);
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onGroupBanNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "friend_add": {
                CQFriendAddNoticeEvent event = eventJson.toJavaObject(CQFriendAddNoticeEvent.class);
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onFriendAddNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
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
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onFriendRequest(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group": {
                CQGroupRequestEvent event = eventJson.toJavaObject(CQGroupRequestEvent.class);
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onGroupRequest(cq, event) == CQPlugin.MESSAGE_BLOCK)
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
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onHeartBeatMeta(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "lifecycle": {
                CQLifecycleMetaEvent event = eventJson.toJavaObject(CQLifecycleMetaEvent.class);
                for (CQPlugin plugin : cq.getPluginList()) {
                    if (plugin.onLifecycleMeta(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
        }
    }
}
