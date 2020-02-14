package xin.lz1998.bot.plugin.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.lz1998.cq.event.message.CQDiscussMessageEvent;
import xin.lz1998.cq.event.message.CQGroupMessageEvent;
import xin.lz1998.cq.event.message.CQPrivateMessageEvent;
import xin.lz1998.cq.event.notice.*;
import xin.lz1998.cq.event.request.CQFriendRequestEvent;
import xin.lz1998.cq.event.request.CQGroupRequestEvent;
import xin.lz1998.cq.robot.CQPlugin;
import xin.lz1998.cq.robot.CoolQ;

// 这个插件用于记录日志
@Slf4j
public class LogPlugin extends CQPlugin {


    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        MyLog myLog = MyLog.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getMessageType())
                .userId(event.getUserId())
                .content(event.getMessage())
                .build();
        log.info("EVENT self:{} type:{} userId:{} content:{}", myLog.getSelfId(), myLog.getType(), myLog.getUserId(), myLog.getContent());
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        MyLog myLog = MyLog.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getMessageType())
                .groupId(event.getGroupId())
                .userId(event.getUserId())
                .content(event.getMessage())
                .build();
        log.info("EVENT self:{} type:{} groupId:{} userId:{} content:{}", myLog.getSelfId(), myLog.getType(), myLog.getGroupId(), myLog.getUserId(), myLog.getContent());
        return MESSAGE_IGNORE;
    }

    @Override
    public int onDiscussMessage(CoolQ cq, CQDiscussMessageEvent event) {
        MyLog myLog = MyLog.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getMessageType())
                .groupId(event.getDiscussId())
                .userId(event.getUserId())
                .content(event.getMessage())
                .build();
        log.info("EVENT self:{} type:{} groupId:{} userId:{} content:{}", myLog.getSelfId(), myLog.getType(), myLog.getGroupId(), myLog.getUserId(), myLog.getContent());


        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupUploadNotice(CoolQ cq, CQGroupUploadNoticeEvent event) {
        MyLog myLog = MyLog.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getNoticeType())
                .groupId(event.getGroupId())
                .userId(event.getUserId())
                .content(event.getFile().getName())
                .build();
        log.info("EVENT self:{} type:{} groupId:{} userId:{} content:{}", myLog.getSelfId(), myLog.getType(), myLog.getGroupId(), myLog.getUserId(), myLog.getContent());


        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupAdminNotice(CoolQ cq, CQGroupAdminNoticeEvent event) {
        MyLog myLog = MyLog.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getNoticeType())
                .groupId(event.getGroupId())
                .userId(event.getUserId())
                .build();
        log.info("EVENT self:{} type:{} groupId:{} userId:{}", myLog.getSelfId(), myLog.getType(), myLog.getGroupId(), myLog.getUserId());


        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupDecreaseNotice(CoolQ cq, CQGroupDecreaseNoticeEvent event) {
        MyLog myLog = MyLog.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getNoticeType() + "_" + event.getSubType())
                .groupId(event.getGroupId())
                .userId(event.getUserId())
                .operatorId(event.getOperatorId())
                .build();
        log.info("EVENT self:{} type:{} groupId:{} userId:{} operatorId:{}", myLog.getSelfId(), myLog.getType(), myLog.getGroupId(), myLog.getUserId(), myLog.getOperatorId());


        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupIncreaseNotice(CoolQ cq, CQGroupIncreaseNoticeEvent event) {
        MyLog myLog = MyLog.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getNoticeType() + "_" + event.getSubType())
                .groupId(event.getGroupId())
                .userId(event.getUserId())
                .operatorId(event.getOperatorId())
                .build();
        log.info("EVENT self:{} type:{} groupId:{} userId:{} operatorId:{}", myLog.getSelfId(), myLog.getType(), myLog.getGroupId(), myLog.getUserId(), myLog.getOperatorId());

        return MESSAGE_IGNORE;
    }

    @Override
    public int onFriendAddNotice(CoolQ cq, CQFriendAddNoticeEvent event) {
        MyLog myLog = MyLog.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getNoticeType())
                .userId(event.getUserId())
                .build();
        log.info("EVENT self:{} type:{} userId:{}", myLog.getSelfId(), myLog.getType(), myLog.getUserId());


        return MESSAGE_IGNORE;
    }

    @Override
    public int onFriendRequest(CoolQ cq, CQFriendRequestEvent event) {
        MyLog myLog = MyLog.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getRequestType())
                .userId(event.getUserId())
                .content(event.getComment())
                .build();
        log.info("EVENT self:{} type:{} userId:{} content:{}", myLog.getSelfId(), myLog.getType(), myLog.getUserId(), myLog.getContent());

        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupRequest(CoolQ cq, CQGroupRequestEvent event) {
        MyLog myLog = MyLog.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getRequestType() + "_" + event.getSubType())
                .groupId(event.getGroupId())
                .userId(event.getUserId())
                .content(event.getComment())
                .build();
        log.info("EVENT self:{} type:{} groupId:{} userId:{} content:{}", myLog.getSelfId(), myLog.getType(), myLog.getGroupId(), myLog.getUserId(), myLog.getContent());

        return MESSAGE_IGNORE;
    }
}
