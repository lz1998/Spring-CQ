package xin.lz1998.bot;

import org.springframework.stereotype.Component;
import xin.lz1998.bot.plugin.example.ExamplePlugin;
import xin.lz1998.bot.plugin.log.LogPlugin;
import xin.lz1998.bot.plugin.status.StatusPlugin;
import xin.lz1998.cq.robot.CQPlugin;
import xin.lz1998.cq.robot.RobotConfigInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * 机器人配置类
 */
@Component
public class RobotConfig implements RobotConfigInterface {

    /**
     * @return 插件List，配置插件执行顺序
     */
    @Override
    public List<CQPlugin> getPluginList() {
        List<CQPlugin> pluginList=new ArrayList<>();
        pluginList.add(new StatusPlugin());
        pluginList.add(new LogPlugin());

        pluginList.add(new ExamplePlugin());
        return pluginList;
    }

    /**
     * @return API超时事件（毫秒）
     */
    @Override
    public Integer getApiTimeout() {
        return 120000;
    }

    /**
     * @return WebSocketUrl
     */
    @Override
    public String getWebSocketUrl() {
        return "/ws/*/";
    }
}
