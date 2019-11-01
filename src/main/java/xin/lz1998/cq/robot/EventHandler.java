package xin.lz1998.cq.robot;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.lz1998.cq.event.message.CQDiscussMessageEvent;
import xin.lz1998.cq.event.message.CQGroupMessageEvent;
import xin.lz1998.cq.event.message.CQPrivateMessageEvent;
import xin.lz1998.cq.event.meta.CQHeartBeatEvent;
import xin.lz1998.cq.event.notice.*;
import xin.lz1998.cq.event.request.CQFriendRequestEvent;
import xin.lz1998.cq.event.request.CQGroupRequestEvent;
import xin.lz1998.cq.plugin.PluginConfig;

import java.util.List;

@Service
class EventHandler {


    static void handle(CoolQ cq, JSONObject eventJson) {
        System.out.println(eventJson);
        String postType=eventJson.getString("post_type");
        switch (postType){
            case "message":{
                handleMessage(cq, eventJson);
                break;
            }
            case "notice":{
                handleNotice(cq, eventJson);
                break;
            }
            case "request":{
                handleRequest(cq, eventJson);
                break;
            }
            case "meta_event":{
                handleMeta(cq, eventJson);
                break;
            }
        }
    }

    private static void handleMessage(CoolQ cq, JSONObject eventJson) {
        String messageType = eventJson.getString("message_type");
        switch (messageType){
            case "private":{
                CQPrivateMessageEvent event= eventJson.toJavaObject(CQPrivateMessageEvent.class);
                for(CQPlugin plugin:PluginConfig.pluginList){
                    if (plugin.onPrivateMessage(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group":{
                CQGroupMessageEvent event=eventJson.toJavaObject(CQGroupMessageEvent.class);
                for(CQPlugin plugin:PluginConfig.pluginList){
                    if (plugin.onGroupMessage(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "discuss":{
                CQDiscussMessageEvent event=eventJson.toJavaObject(CQDiscussMessageEvent.class);
                for(CQPlugin plugin:PluginConfig.pluginList){
                    if (plugin.onDiscussMessage(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
        }

    }

    private static void handleNotice(CoolQ cq, JSONObject eventJson) {

        String noticeType = eventJson.getString("notice_type");

        switch (noticeType){
            case "group_upload":{
                CQGroupUploadNoticeEvent event= eventJson.toJavaObject(CQGroupUploadNoticeEvent.class);
                for(CQPlugin plugin:PluginConfig.pluginList){
                    if (plugin.onGroupUploadNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_admin":{
                CQGroupAdminNoticeEvent event= eventJson.toJavaObject( CQGroupAdminNoticeEvent.class);
                for(CQPlugin plugin:PluginConfig.pluginList){
                    if (plugin.onGroupAdminNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_decrease":{
                CQGroupDecreaseNoticeEvent event=eventJson.toJavaObject(CQGroupDecreaseNoticeEvent.class);
                for(CQPlugin plugin:PluginConfig.pluginList){
                    if (plugin.onGroupDecreaseNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_increase":{
                CQGroupIncreaseNoticeEvent event=eventJson.toJavaObject( CQGroupIncreaseNoticeEvent.class);
                for(CQPlugin plugin:PluginConfig.pluginList){
                    if (plugin.onGroupIncreaseNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_ban":{
                CQGroupBanNoticeEvent event=eventJson.toJavaObject(CQGroupBanNoticeEvent.class);
                for(CQPlugin plugin:PluginConfig.pluginList){
                    if(plugin.onGroupBanNotice(cq,event)==CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "friend_add":{
                CQFriendAddNoticeEvent event=eventJson.toJavaObject(CQFriendAddNoticeEvent.class);
                for(CQPlugin plugin:PluginConfig.pluginList){
                    if (plugin.onFriendAddNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
        }


    }

    private static void handleRequest(CoolQ cq, JSONObject eventJson) {
        String requestType = eventJson.getString("request_type");
        switch (requestType){
            case "friend":{
                CQFriendRequestEvent event=eventJson.toJavaObject( CQFriendRequestEvent.class);
                for(CQPlugin plugin:PluginConfig.pluginList){
                    if (plugin.onFriendRequest(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group":{
                CQGroupRequestEvent event=eventJson.toJavaObject(CQGroupRequestEvent.class);
                for(CQPlugin plugin: PluginConfig.pluginList){
                    if (plugin.onGroupRequest(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
        }
    }
    private static void handleMeta(CoolQ cq, JSONObject eventJson) {
        String metaType = eventJson.getString("meta_event_type");
        switch (metaType){
            case "heartbeat":{
                CQHeartBeatEvent event=eventJson.toJavaObject( CQHeartBeatEvent.class);
                for(CQPlugin plugin:PluginConfig.pluginList){
                    if (plugin.onHeartBeatMeta(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
        }
    }
}
