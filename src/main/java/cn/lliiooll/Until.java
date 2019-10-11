package cn.lliiooll;

import java.util.regex.Pattern;

public class Until {

    /**
     * 替换无用字符
     *
     * @param s
     * @return
     */
    public static String replaceOtherChar(String s) {
        return s.replace("\n", "").replace("\r", "").replace("\t", "");
    }

    /**
     * 判断传入字符是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
