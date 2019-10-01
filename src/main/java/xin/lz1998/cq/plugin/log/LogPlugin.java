package xin.lz1998.cq.plugin.log;

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
public class LogPlugin extends CQPlugin {

    private Logger logger = LoggerFactory.getLogger(LogPlugin.class);

    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        Log log = Log.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getMessageType())
                .userId(event.getUserId())
                .content(event.getMessage())
                .build();
        logger.info("EVENT self:{} type:{} userId:{} content:{}", log.getSelfId(), log.getType(), log.getUserId(), log.getContent());
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        Log log = Log.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getMessageType())
                .groupId(event.getGroupId())
                .userId(event.getUserId())
                .content(event.getMessage())
                .build();
        logger.info("EVENT self:{} type:{} groupId:{} userId:{} content:{}", log.getSelfId(), log.getType(), log.getGroupId(), log.getUserId(), log.getContent());
        return MESSAGE_IGNORE;
    }

    @Override
    public int onDiscussMessage(CoolQ cq, CQDiscussMessageEvent event) {
        Log log = Log.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getMessageType())
                .groupId(event.getDiscussId())
                .userId(event.getUserId())
                .content(event.getMessage())
                .build();
        logger.info("EVENT self:{} type:{} groupId:{} userId:{} content:{}", log.getSelfId(), log.getType(), log.getGroupId(), log.getUserId(), log.getContent());


        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupUploadNotice(CoolQ cq, CQGroupUploadNoticeEvent event) {
        Log log = Log.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getNoticeType())
                .groupId(event.getGroupId())
                .userId(event.getUserId())
                .content(event.getFile().getName())
                .build();
        logger.info("EVENT self:{} type:{} groupId:{} userId:{} content:{}", log.getSelfId(), log.getType(), log.getGroupId(), log.getUserId(), log.getContent());


        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupAdminNotice(CoolQ cq, CQGroupAdminNoticeEvent event) {
        Log log = Log.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getNoticeType())
                .groupId(event.getGroupId())
                .userId(event.getUserId())
                .build();
        logger.info("EVENT self:{} type:{} groupId:{} userId:{}", log.getSelfId(), log.getType(), log.getGroupId(), log.getUserId());


        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupDecreaseNotice(CoolQ cq, CQGroupDecreaseNoticeEvent event) {
        Log log = Log.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getNoticeType() + "_" + event.getSubType())
                .groupId(event.getGroupId())
                .userId(event.getUserId())
                .operatorId(event.getOperatorId())
                .build();
        logger.info("EVENT self:{} type:{} groupId:{} userId:{} operatorId:{}", log.getSelfId(), log.getType(), log.getGroupId(), log.getUserId(), log.getOperatorId());


        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupIncreaseNotice(CoolQ cq, CQGroupIncreaseNoticeEvent event) {
        Log log = Log.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getNoticeType() + "_" + event.getSubType())
                .groupId(event.getGroupId())
                .userId(event.getUserId())
                .operatorId(event.getOperatorId())
                .build();
        logger.info("EVENT self:{} type:{} groupId:{} userId:{} operatorId:{}", log.getSelfId(), log.getType(), log.getGroupId(), log.getUserId(), log.getOperatorId());

        return MESSAGE_IGNORE;
    }

    @Override
    public int onFriendAddNotice(CoolQ cq, CQFriendAddNoticeEvent event) {
        Log log = Log.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getNoticeType())
                .userId(event.getUserId())
                .build();
        logger.info("EVENT self:{} type:{} userId:{}", log.getSelfId(), log.getType(), log.getUserId());


        return MESSAGE_IGNORE;
    }

    @Override
    public int onFriendRequest(CoolQ cq, CQFriendRequestEvent event) {
        Log log = Log.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getRequestType())
                .userId(event.getUserId())
                .content(event.getComment())
                .build();
        logger.info("EVENT self:{} type:{} userId:{} content:{}", log.getSelfId(), log.getType(), log.getUserId(), log.getContent());

        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupRequest(CoolQ cq, CQGroupRequestEvent event) {
        Log log = Log.builder()
                .selfId(cq.getSelfId())
                .type("event_" + event.getPostType() + "_" + event.getRequestType() + "_" + event.getSubType())
                .groupId(event.getGroupId())
                .userId(event.getUserId())
                .content(event.getComment())
                .build();
        logger.info("EVENT self:{} type:{} groupId:{} userId:{} content:{}", log.getSelfId(), log.getType(), log.getGroupId(), log.getUserId(), log.getContent());

        return MESSAGE_IGNORE;
    }
}
