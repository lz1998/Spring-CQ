package net.lz1998.cq.robot;

import com.alibaba.fastjson.JSONObject;

/**
 * 自定义API可以实现这个接口
 * 使用cq.callCustomApi(IApiRequest apiRequest)
 */
public interface IApiRequest {
    String getUrl();

    JSONObject getParams();
}
