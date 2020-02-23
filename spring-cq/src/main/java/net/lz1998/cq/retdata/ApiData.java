package net.lz1998.cq.retdata;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ApiData<T> {
    /**
     * status 字段如果是 ok 则表示操作成功，同时 retcode （返回码）会等于 0，即 酷Q 函数返回了 0。
     * status 字段如果是 async 则表示请求已提交异步处理，此时 retcode 为 1，具体成功或失败将无法获知。
     * status 字段如果是 failed 则表示操作失败，此时 retcode 有两种情况：当大于 0 时，表示是 HTTP API 插件判断出的失败；小于 0 时，为调用 酷Q 函数的返回码，具体含义直接参考 酷Q 文档的 错误代码 和 酷Q 日志。
     */
    @JSONField(name = "status")
    private String status;
    /**
     * 如果 access token 未提供，状态码为 401；
     * 如果 access token 不符合，状态码为 403；
     * 如果 POST 请求的 Content-Type 不支持，状态码为 406；
     * 如果 POST 请求的正文格式不正确，状态码为 400；
     * 如果 API 不存在，状态码为 404；
     * 剩下的所有情况，无论操作失败还是成功，状态码都是 200。
     */
    @JSONField(name = "retcode")
    private int retcode;

    @JSONField(name = "data")
    private T data;
}
