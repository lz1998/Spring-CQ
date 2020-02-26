# 事件上报

> 这部分是从  https://cqhttp.cc/docs/4.14/#/Post  抄过来改一改的
>
> 数据类型的 number (int64) 就是 Long
>
> 数据类型的 number (int32) 就是 Integer

## 使用方法

```java
@Component
public class DemoPlugin extends CQPlugin {
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        // 直接在这里event.getXXX();
        // 如果有嵌套可以继续get, 比如 event.getSender().getNickname()
        Long userId = event.getUserId();
        return MESSAGE_IGNORE;
    }
}
```

## 事件列表

### 私聊消息

#### 上报数据

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------- | ------- | ---- |
| `postType` | string | `message` | 上报类型 |
| `messageType` | string | `private` | 消息类型 |
| `subType` | string | `friend`、`group`、`discuss`、`other` | 消息子类型，如果是好友则是 `friend`，如果从群或讨论组来的临时会话则分别是 `group`、`discuss` |
| `messageId` | number (int32) | - | 消息 ID |
| `userId` | number (int64) | - | 发送者 QQ 号 |
| `message` | message | - | 消息内容 |
| `rawMessage` | string | - | 原始消息内容 |
| `font` | number (int32) | - | 字体 |
| `sender` | object | - | 发送人信息 |

其中 `sender` 字段的内容如下：

| 字段名 | 数据类型 | 说明 |
| ----- | ------ | ---- |
| `userId` | number (int64) | 发送者 QQ 号 |
| `nickname` | string | 昵称 |
| `sex` | string | 性别，`male` 或 `female` 或 `unknown` |
| `age` | number (int32) | 年龄 |

需要注意的是，`sender` 中的各字段是尽最大努力提供的，也就是说，不保证每个字段都一定存在，也不保证存在的字段都是完全正确的（缓存可能过期）。

##### 示例

```json
{
    "time": 1515204254,
    "post_type": "message",
    "message_type": "private",
    "sub_type": "friend",
    "message_id": 12,
    "user_id": 12345678,
    "message": "你好～",
    "raw_message": "你好～",
    "font": 456,
    "sender": {
        "nickname": "小不点",
        "sex": "male",
        "age": 18
    }
}
```

下面的其它事件同这个类似，将不再给出。

#### 响应数据

| 字段名 | 数据类型 | 说明 | 默认情况 |
| ----- | ------- | --- | ------- |
| `reply` | message | 要回复的内容 | 不回复 |
| `autoEscape` | boolean | 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 `reply` 字段是字符串时有效 | 不转义 |

### 群消息

#### 上报数据

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------- | ------- | --- |
| `postType` | string | `message` | 上报类型 |
| `messageType` | string | `group` | 消息类型 |
| `subType` | string | `normal`、`anonymous`、`notice` | 消息子类型，正常消息是 `normal`，匿名消息是 `anonymous`，系统提示（如「管理员已禁止群内匿名聊天」）是 `notice` |
| `messageId` | number (int32) | - | 消息 ID |
| `groupId` | number (int64) | - | 群号 |
| `userId` | number (int64) | - | 发送者 QQ 号 |
| `anonymous` | object | - | 匿名信息，如果不是匿名消息则为 null |
| `message` | message | - | 消息内容 |
| `rawMessage` | string | - | 原始消息内容 |
| `font` | number (int32) | - | 字体 |
| `sender` | object | - | 发送人信息 |

其中 `anonymous` 字段的内容如下：

| 字段名 | 数据类型 | 说明 |
| ----- | ------ | ---- |
| `id` | number (int64) | 匿名用户 ID |
| `name` | string | 匿名用户名称 |
| `flag` | string | 匿名用户 flag，在调用禁言 API 时需要传入 |

`sender` 字段的内容如下：

| 字段名 | 数据类型 | 说明 |
| ----- | ------ | ---- |
| `userId` | number (int64) | 发送者 QQ 号 |
| `nickname` | string | 昵称 |
| `card` | string | 群名片／备注 |
| `sex` | string | 性别，`male` 或 `female` 或 `unknown` |
| `age` | number (int32) | 年龄 |
| `area` | string | 地区 |
| `level` | string | 成员等级 |
| `role` | string | 角色，`owner` 或 `admin` 或 `member` |
| `title` | string | 专属头衔 |

需要注意的是，`sender` 中的各字段是尽最大努力提供的，也就是说，不保证每个字段都一定存在，也不保证存在的字段都是完全正确的（缓存可能过期）。尤其对于匿名消息，此字段不具有参考价值。

#### 响应数据

| 字段名 | 数据类型 | 说明 | 默认情况 |
| ----- | ------- | --- | ------- |
| `reply` | message | 要回复的内容 | 不回复 |
| `autoEscape` | boolean | 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 `reply` 字段是字符串时有效 | 不转义 |
| `atSender` | boolean | 是否要在回复开头 at 发送者（自动添加），发送者是匿名用户时无效 | at 发送者 |
| `delete` | boolean | 撤回该条消息 | 不撤回 |
| `kick` | boolean | 把发送者踢出群组（需要登录号权限足够），**不拒绝**此人后续加群请求，发送者是匿名用户时无效 | 不踢 |
| `ban` | boolean | 把发送者禁言 `banDuration` 指定时长，对匿名用户也有效 | 不禁言 |
| `banDuration` | number | 禁言时长 | 30 分钟 |

### 讨论组消息

#### 上报数据

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------- | ------- | --- |
| `postType` | string | `message` | 上报类型 |
| `messageType` | string | `discuss` | 消息类型 |
| `messageId` | number (int32) | - | 消息 ID |
| `discussId` | number (int64) | - | 讨论组 ID |
| `userId` | number (int64) | - | 发送者 QQ 号 |
| `message` | message | - | 消息内容 |
| `rawMessage` | string | - | 原始消息内容 |
| `font` | number (int32) | - | 字体 |
| `sender` | object | - | 发送人信息 |

其中 `sender` 字段的内容如下：

| 字段名 | 数据类型 | 说明 |
| ----- | ------ | ---- |
| `userId` | number (int64) | 发送者 QQ 号 |
| `nickname` | string | 昵称 |
| `sex` | string | 性别，`male` 或 `female` 或 `unknown` |
| `age` | number (int32) | 年龄 |

#### 响应数据

| 字段名 | 数据类型 | 说明 | 默认情况 |
| ----- | ------- | --- | ------- |
| `reply` | message | 要回复的内容 | 不回复 |
| `autoEscape` | boolean | 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 `reply` 字段是字符串时有效 | 不转义 |
| `atSender` | boolean | 是否要在回复开头 at 发送者（自动添加） | at 发送者 |

### 群文件上传

**注意：仅群文件上传表现为事件，好友发送文件在 酷Q 中没有独立的事件，而是直接表现为好友消息，请注意在编写业务逻辑时进行判断。**

#### 上报数据

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------ | ------- | ---- |
| `postType` | string | `notice` | 上报类型 |
| `noticeType` | string | `groupUpload` | 通知类型 |
| `groupId` | number (int64) | - | 群号 |
| `userId` | number (int64) | - | 发送者 QQ 号 |
| `file` | object | - | 文件信息 |

其中 `file` 字段的内容如下：

| 字段名 | 数据类型 | 说明 |
| ----- | ------ | ---- |
| `id` | string | 文件 ID |
| `name` | string | 文件名 |
| `size` | number (int64) | 文件大小（字节数） |
| `busid` | number (int64) | busid（目前不清楚有什么作用） |

### 群管理员变动

#### 上报数据

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------ | -------- | --- |
| `postType` | string | `notice` | 上报类型 |
| `noticeType` | string | `groupAdmin` | 通知类型 |
| `subType` | string | `set`、`unset` | 事件子类型，分别表示设置和取消管理员 |
| `groupId` | number (int64) | - | 群号 |
| `userId` | number (int64) | - | 管理员 QQ 号 |

### 群成员减少

#### 上报数据

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------ | -------- | --- |
| `postType` | string | `notice` | 上报类型 |
| `noticeType` | string | `groupDecrease` | 通知类型 |
| `subType` | string | `leave`、`kick`、`kickMe` | 事件子类型，分别表示主动退群、成员被踢、登录号被踢 |
| `groupId` | number (int64) | - | 群号 |
| `operatorId` | number (int64) | - | 操作者 QQ 号（如果是主动退群，则和 `userId` 相同） |
| `userId` | number (int64) | - | 离开者 QQ 号 |

### 群成员增加

#### 上报数据

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------ | -------- | --- |
| `postType` | string | `notice` | 上报类型 |
| `noticeType` | string | `groupIncrease` | 通知类型 |
| `subType` | string | `approve`、`invite` | 事件子类型，分别表示管理员已同意入群、管理员邀请入群 |
| `groupId` | number (int64) | - | 群号 |
| `operatorId` | number (int64) | - | 操作者 QQ 号 |
| `userId` | number (int64) | - | 加入者 QQ 号 |

### 群禁言

#### 上报数据

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------ | -------- | --- |
| `postType` | string | `notice` | 上报类型 |
| `noticeType` | string | `groupBan` | 通知类型 |
| `subType` | string | `ban`、`liftBan` | 事件子类型，分别表示禁言、解除禁言 |
| `groupId` | number (int64) | - | 群号 |
| `operatorId` | number (int64) | - | 操作者 QQ 号 |
| `userId` | number (int64) | - | 被禁言 QQ 号 |
| `duration` | number (int64) | - | 禁言时长，单位秒 |

### 好友添加

#### 上报数据

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------ | -------- | --- |
| `postType` | string | `notice` | 上报类型 |
| `noticeType` | string | `friendAdd` | 通知类型 |
| `userId` | number (int64) | - | 新添加好友 QQ 号 |

### 加好友请求

#### 上报数据

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------ | -------- | --- |
| `postType` | string | `request` | 上报类型 |
| `requestType` | string | `friend` | 请求类型 |
| `userId` | number (int64) | - | 发送请求的 QQ 号 |
| `comment` | string | - | 验证信息（可能包含 CQ 码，特殊字符被转义） |
| `flag` | string | - | 请求 flag，在调用处理请求的 API 时需要传入 |

#### 响应数据

| 字段名 | 数据类型 | 说明 | 默认情况 |
| ----- | ------- | --- | ------- |
| `approve` | boolean | 是否同意请求 | 不处理 |
| `remark` | string  | 添加后的好友备注（仅在同意时有效） | 无备注 |

### 加群请求／邀请

#### 上报数据

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------ | -------- | --- |
| `postType` | string | `request` | 上报类型 |
| `requestType` | string | `group` | 请求类型 |
| `subType` | string | `add`、`invite` | 请求子类型，分别表示加群请求、邀请登录号入群 |
| `groupId` | number (int64) | - | 群号 |
| `userId` | number (int64) | - | 发送请求的 QQ 号 |
| `comment` | string | - | 验证信息（可能包含 CQ 码，特殊字符被转义） |
| `flag` | string | - | 请求 flag，在调用处理请求的 API 时需要传入 |

#### 响应数据

| 字段名 | 数据类型 | 说明 | 默认情况 |
| ----- | ------- | --- | ------- |
| `approve` | boolean | 是否同意请求／邀请 | 不处理 |
| `reason` | string | 拒绝理由（仅在拒绝时有效） | 无理由 |

## 元事件

上面的事件列表是 酷Q 直接支持的、QQ 机器人真实接收到的事件，除了这些，插件自己还会产生一类事件，这里称之为「元事件」，例如生命周期事件、心跳事件等，这类事件与插件（以及 酷Q）本身的运行状态有关，而与 QQ 无关。

元事件的上报方式和普通事件完全一样，`post_type` 字段为 `meta_event`。

## 元事件列表

### 生命周期

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------ | -------- | --- |
| `postType` | string | `metaEvent` | 上报类型 |
| `metaEventType` | string | `lifecycle` | 元事件类型 |
| `subType` | string | `enable`、`disable`、`connect` | 事件子类型，分别表示插件启用、插件停用、WebSocket 连接成功 |

**注意，目前生命周期元事件中，只有 HTTP 上报（配置了 `post_url` ）的情况下可以收到 `enable` 和 `disable`，只有 WebSocket 和反向 WebSocket 可以收到 `connect`。**

### 心跳

心跳类型的元事件需要通过设置配置项 `enable_heartbeat` 为 `true` 开启，并可通过 `heartbeat_interval` 配置心跳间隔（单位毫秒）。

| 字段名 | 数据类型 | 可能的值 | 说明 |
| ----- | ------ | -------- | --- |
| `postType` | string | `metaEvent` | 上报类型 |
| `metaEventType` | string | `heartbeat` | 元事件类型 |
| `status` | object | - | 状态信息 |
| `interval` | number (int64) | - | 到下次心跳的间隔，单位毫秒 |

其中 `status` 字段的内容和 [`get_status` 接口的响应数据](/API#响应数据27) 相同。
