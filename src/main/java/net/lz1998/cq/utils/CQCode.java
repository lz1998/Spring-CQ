package net.lz1998.cq.utils;


/**
 * 酷Q码工具类
 * https://docs.cqp.im/manual/cqcode/
 */
public class CQCode {
    public static String unescape(String str) {
        return str.replace("&#44;", ",")
                .replace("&#91;", "[")
                .replace("&#93;", "]")
                .replace("&amp;", "&");
    }

    public static String escape(String str) {
        return str.replace(",", "&#44;")
                .replace("[", "&#91;")
                .replace("]", "&#93;")
                .replace("&", "&amp;");
    }

    /**
     * 系统表情
     *
     * @param id 为≥0的数字
     * @return [CQ:face,id=14]（发送一个微笑的系统表情）
     */
    public static String face(int id) {
        return "[CQ:face,id=" + id + "]";
    }

    /**
     * emoji表情
     * id表 https://cqp.cc/t/15827
     *
     * @param id emoji字符的unicode编号
     * @return [CQ:emoji,id=128513]（发送一个大笑的emoji表情）
     */
    public static String emoji(int id) {
        return "[CQ:emoji,id=" + id + "]";
    }

    /**
     * 原创表情
     *
     * @param id 该原创表情的ID，存放在酷Q目录的data\bface\下
     * @return [CQ:bface,id={1}]
     */
    public static String bface(int id) {
        return "[CQ:bface,id=" + id + "]";
    }

    /**
     * 小表情
     *
     * @param id 该小表情的ID
     * @return [CQ:sface,id={1}]
     */
    public static String sface(int id) {
        return "[CQ:sface,id=" + id + "]";
    }

    /**
     * 自定义图片
     *
     * @param file 图片文件名称，图片存放在酷Q目录的data\image\下
     * @return [CQ:image,file={1}]
     */
    public static String image(String file) {
        return "[CQ:image,file=" + escape(file) + "]";
    }


    /**
     * 自定义图片
     *
     * @param file    图片文件名称，图片存放在酷Q目录的data\image\下
     * @param cache   是否缓存
     * @param timeout 下载操作超时(单位秒)
     * @return [CQ:image,file={1},cache={2},timeout={3}]
     */
    public static String image(String file, boolean cache, int timeout) {
        return "[CQ:image,file=" + escape(file) + ",cache=" + cache + ",timeout=" + timeout + "]";
    }


    /**
     * 语音
     *
     * @param file  音频文件名称，音频存放在酷Q目录的data\record\下
     * @param magic 是否为变声，若该参数为true则显示变声标记。该参数可被忽略。
     * @return [CQ:record,file={1},magic={2},cache={2},timeout={3}]
     */
    public static String record(String file, boolean magic) {
        return "[CQ:record,file=" + escape(file) + ",magic=" + magic + "]";
    }

    /**
     * 语音
     *
     * @param file    音频文件名称，音频存放在酷Q目录的data\record\下
     * @param magic   是否为变声，若该参数为true则显示变声标记。该参数可被忽略。
     * @param cache   是否缓存
     * @param timeout 下载操作超时(单位秒)
     * @return
     */
    public static String record(String file, boolean magic, boolean cache, int timeout) {
        return "[CQ:record,file=" + escape(file) + ",magic=" + magic + ",cache=" + cache + ",timeout=" + timeout + "]";
    }

    /**
     * at某人
     *
     * @param qq 被@的群成员帐号
     * @return [CQ:at,qq={1}]
     */
    public static String at(Long qq) {
        return "[CQ:at,qq=" + qq + "]";
    }

    /**
     * at全体成员
     *
     * @return [CQ:at,qq=all]
     */
    public static String atAll() {
        return "[CQ:at,qq=all]";
    }

    /**
     * 猜拳魔法表情
     *
     * @param type 为猜拳结果的类型，暂不支持发送时自定义
     * @return [CQ:rps,type={1}]
     */
    public static String rps(int type) {
        return "[CQ:rps,type=" + type + "]";
    }

    /**
     * 掷骰子魔法表情
     *
     * @param type 对应掷出的点数，暂不支持发送时自定义
     * @return [CQ:dice,type={1}]
     */
    public static String dice(int type) {
        return "[CQ:dice,type=" + type + "]";
    }

    /**
     * 戳一戳
     * 仅支持好友消息使用
     *
     * @return [CQ:shake]
     */
    public static String shake() {
        return "[CQ:shake]";
    }

    /**
     * 匿名发消息
     * 本CQ码需加在消息的开头。仅支持群消息使用。
     *
     * @param ignore 为 true 时，代表不强制使用匿名，如果匿名失败将转为普通消息发送。为 false 时，代表强制使用匿名，如果匿名失败将取消该消息的发送。
     * @return [CQ:anonymous,ignore=true]
     */
    public static String anonymous(boolean ignore) {
        return "[CQ:anonymous,ignore=" + ignore + "]";
    }

    /**
     * 地点
     *
     * @param lat     纬度
     * @param lon     经度
     * @param title   分享地点的名称
     * @param content 分享地点的具体地址
     * @return [CQ:location,lat=39.918056,lon=116.397027,title=故宫博物院,content=北京市东城区景山前街4号]
     */
    public static String location(double lat, double lon, String title, String content) {
        return "[CQ:location,lat=" + lat + ",lon=" + lon + ",title=" + escape(title) + ",content=" + escape(content) + "]";
    }

    /**
     * 签到
     *
     * @param location 用户签到的地点，为中文字串
     * @param title    用户签到时发表的心情文字
     * @param image    签到卡片所使用的背景图片链接
     * @return [CQ:sign,location={1},title={2},image={3}]
     */
    public static String sign(String location, String title, String image) {
        return "[CQ:sign,location=" + escape(location) + ",title=" + escape(title) + ",image=" + escape(image) + "]";
    }

    /**
     * 音乐
     *
     * @param type  音乐平台类型，目前支持qq、163
     * @param id    对应音乐平台的数字音乐id
     * @param style 音乐卡片的风格。仅 Pro 支持该参数，该参数可被忽略。
     * @return [CQ:music,type={1},id={2},style={3}]
     */
    public static String music(String type, int id, String style) {
        return "[CQ:music,type=" + escape(type) + ",id=" + id + ",style=" + escape(style) + "]";
    }

    /**
     * 音乐自定义分享
     *
     * @param url     分享链接，即点击分享后进入的音乐页面（如歌曲介绍页）
     * @param audio   音频链接（如mp3链接）
     * @param title   音乐的标题，建议12字以内
     * @param content 音乐的简介，建议30字以内。该参数可被忽略
     * @param image   音乐的封面图片链接。若参数为空或被忽略，则显示默认图片
     * @return [CQ:music,type=custom,url={1},audio={2},title={3},content={4},image={5}]
     */
    public static String customMusic(String url, String audio, String title, String content, String image) {
        return "[CQ:music,type=custom,url=" + escape(url) + ",audio=" + escape(audio) + ",title=" + escape(title) + ",content=" + escape(content) + ",image=" + escape(image) + "]";
    }

    /**
     * 链接分享
     *
     * @param url     分享链接
     * @param title   分享的标题，建议12字以内
     * @param content 分享的简介，建议30字以内。该参数可被忽略。
     * @param image   分享的图片链接。若参数为空或被忽略，则显示默认图片
     * @return [CQ:share,url={1},title={2},content={3},image={4}]
     */
    public static String share(String url, String title, String content, String image) {
        return "[CQ:share,url=" + escape(url) + ",title=" + escape(title) + ",content=" + escape(content) + ",image=" + escape(image) + "]";
    }

    /**
     * 分享联系人/群
     *
     * @param id   QQ/群号
     * @param type 类型
     * @return [CQ:contact,type={1},id={2}]
     */
    public static String contact(String type, Long id) {
        return "[CQ:contact,type=" + type + ",id=" + id + "]";
    }

}
