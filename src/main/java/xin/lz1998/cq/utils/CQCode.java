package xin.lz1998.cq.utils;


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

    public static String face(int id) {
        return "[CQ:face,id=" + id + "]";
    }

    // id表 https://cqp.cc/t/15827
    public static String emoji(int id) {
        return "[CQ:emoji,id=" + id + "]";
    }

    public static String bface(int id) {
        return "[CQ:bface,id=" + id + "]";
    }

    public static String sface(int id) {
        return "[CQ:sface,id=" + id + "]";
    }

    public static String image(String file) {
        return "[CQ:image,file=" + escape(file) + "]";
    }

    public static String record(String file) {
        return "[CQ:record,file=" + escape(file) + "]";
    }

    public static String at(Long qq) {
        return "[CQ:at,qq=" + qq + "]";
    }

    public static String rps(int type) {
        return "[CQ:rps,type=" + type + "]";
    }

    public static String dice(int type) {
        return "[CQ:dice,type=" + type + "]";
    }

    public static String shake() {
        return "[CQ:shake]";
    }

    public static String anonymous(boolean ignore) {
        return "[CQ:anonymous,ignore=" + ignore + "]";
    }

    public static String location(double lat, double lon, String title, String content) {
        return "[CQ:location,lat=" + lat + ",lon=" + lon + ",title=" + escape(title) + ",content=" + escape(content) + "]";
    }

    public static String sign(String location, String title, String image) {
        return "[CQ:sign,location=" + escape(location) + ",title=" + escape(title) + ",image=" + escape(image) + "]";
    }

    public static String music(String type, int id, String style) {
        return "[CQ:music,type=" + escape(type) + ",id=" + id + ",style=" + escape(style) + "]";
    }

    public static String customMusic(String url, String audio, String title, String content, String image) {
        return "[CQ:music,type=custom,url=" + escape(url) + ",audio=" + escape(audio) + ",title=" + escape(title) + ",content=" + escape(content) + ",image=" + escape(image) + "]";
    }

    public static String share(String url, String title, String content, String image) {
        return "[CQ:share,url=" + escape(url) + ",title=" + escape(title) + ",content=" + escape(content) + ",image=" + escape(image) + "]";
    }

    public static String contact(Long id,String type){
        return "[CQ:contact,type=" + type + ",id=" + id +"]";
    }

}
