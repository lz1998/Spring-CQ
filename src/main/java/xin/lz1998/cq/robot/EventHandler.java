package xin.lz1998.cq.robot;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.lz1998.cq.event.message.CQDiscussMessageEvent;
import xin.lz1998.cq.event.message.CQGroupMessageEvent;
import xin.lz1998.cq.event.message.CQPrivateMessageEvent;
import xin.lz1998.cq.event.notice.*;
import xin.lz1998.cq.event.request.CQFriendRequestEvent;
import xin.lz1998.cq.event.request.CQGroupRequestEvent;

import java.util.List;

@Service
class EventHandler {
    @Autowired
    private static List<CQPlugin> pluginArrayList;

    public EventHandler(List<CQPlugin> cqPlugins) {
        this.pluginArrayList=cqPlugins;
    }

    static void handle(CoolQ cq, JSONObject eventJson) {
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
        }
    }

    private static void handleMessage(CoolQ cq, JSONObject eventJson) {
        String messageType = eventJson.getString("message_type");
        switch (messageType){
            case "private":{
                CQPrivateMessageEvent event= eventJson.toJavaObject(CQPrivateMessageEvent.class);
                for(CQPlugin plugin:pluginArrayList){
                    if (plugin.onPrivateMessage(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group":{
                CQGroupMessageEvent event=eventJson.toJavaObject(CQGroupMessageEvent.class);
                for(CQPlugin plugin:pluginArrayList){
                    if (plugin.onGroupMessage(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "discuss":{
                CQDiscussMessageEvent event=eventJson.toJavaObject(CQDiscussMessageEvent.class);
                for(CQPlugin plugin:pluginArrayList){
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
                for(CQPlugin plugin:pluginArrayList){
                    if (plugin.onGroupUploadNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_admin":{
                CQGroupAdminNoticeEvent event= eventJson.toJavaObject( CQGroupAdminNoticeEvent.class);
                for(CQPlugin plugin:pluginArrayList){
                    if (plugin.onGroupAdminNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_decrease":{
                CQGroupDecreaseNoticeEvent event=eventJson.toJavaObject(CQGroupDecreaseNoticeEvent.class);
                for(CQPlugin plugin:pluginArrayList){
                    if (plugin.onGroupDecreaseNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group_increase":{
                CQGroupIncreaseNoticeEvent event=eventJson.toJavaObject( CQGroupIncreaseNoticeEvent.class);
                for(CQPlugin plugin:pluginArrayList){
                    if (plugin.onGroupIncreaseNotice(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "friend_add":{
                CQFriendAddNoticeEvent event=eventJson.toJavaObject(CQFriendAddNoticeEvent.class);
                for(CQPlugin plugin:pluginArrayList){
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
                for(CQPlugin plugin:pluginArrayList){
                    if (plugin.onFriendRequest(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
            case "group":{
                CQGroupRequestEvent event=eventJson.toJavaObject(CQGroupRequestEvent.class);
                for(CQPlugin plugin:pluginArrayList){
                    if (plugin.onGroupRequest(cq, event) == CQPlugin.MESSAGE_BLOCK)
                        break;
                }
                break;
            }
        }
    }

}
