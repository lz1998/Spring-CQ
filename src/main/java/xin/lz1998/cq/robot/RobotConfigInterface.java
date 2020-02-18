package xin.lz1998.cq.robot;

import java.util.List;

/**
 * 机器人配置接口
 */
public interface RobotConfigInterface {
    /**
     * @return 插件List，配置插件执行顺序
     */
    List<CQPlugin> getPluginList();

    /**
     * @return API超时时间(毫秒)
     */
    default Integer getApiTimeout() {
        return 120000;
    }

    /**
     * @return webSocket 的 url
     */
    default String getWebSocketUrl() {
        return "/ws/*/";
    }
}
