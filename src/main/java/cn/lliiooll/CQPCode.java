package cn.lliiooll;

import java.io.File;

public class CQPCode {

    /**
     * 在群组/讨论组里@一个人
     * 用法: 将此方法返回的String直接包含在消息当中
     *
     * @return CQ码
     */
    public static String atQQ(long sender) {
        return "[CQ:at,qq=" + sender + "]";
    }

    /**
     * 返回at的qq号
     * 是数值返回Long型的QQ号，不是返回null
     *
     * @param CQCode
     * @return
     */
    public static long getAtQQ(String CQCode) {
        return Until.isInteger(CQCode.replace("[CQ:at,qq=", "").replace("]", "")) ? Long.parseLong(CQCode) : null;// 是数值返回Long型的QQ号，不是返回null
    }

    /**
     * 发送一个原版表情
     * 用法: 将此方法返回的String直接包含在消息当中
     * 全部原版表情: https://imgc.cqp.me/forum/201804/12/101625nld1zafkij1dafgd.png
     *
     * @param number
     * @return CQ码
     */
    public static String face(long number) {
        return "[CQ:face,id=" + number + "]";
    }

    /**
     * 发送一个原版表情
     * 用法: 将此方法返回的String直接包含在消息当中
     * 全部emoji表情: https://imgc.cqp.me/forum/201804/12/101622btx9yxpa4z91v3yx.png
     *
     * @param number
     * @return CQ码
     */
    public static String emoji(long number) {
        return "[CQ:emoji,id=" + number + "]";
    }

    /**
     * ****仅限酷Q Pro！****
     * 发送一个图片
     * 将图片放在酷Q目录下的 data\image 下，并填写相对路径。如 data\image\1.jpg 则填写 1.jpg
     * 用法: 将此方法返回的String直接包含在消息当中
     *
     * @param filename
     * @return CQ码
     */
    public static String image(String filename) {
        return "[CQ:image,file=" + filename + "]";
    }

    /**
     * ****仅限酷Q Pro！****
     * 发送一个语音
     * 将语音文件放在酷Q目录下的 data\record 下，并填写相对路径。如 data\record\1.wav 则填写 1.wav
     * 用法: 将此方法返回的String直接包含在消息当中
     *
     * @param filename
     * @return CQ码
     */
    public static String record(String filename) {
        return "[CQ:record,file=" + filename + "]";
    }


}
