package xin.lz1998.bot.plugin;

import xin.lz1998.bot.plugin.log.LogPlugin;
import xin.lz1998.bot.plugin.example.ExamplePlugin;
import xin.lz1998.bot.plugin.status.StatusPlugin;
import xin.lz1998.cq.robot.CQPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * 插件配置类
 * 在pluginList中配置需要执行插件的顺序
 * 收到消息后会按顺序调用插件
 * <p>
 * 提示：
 * 如果前一个插件返回MESSAGE_BLOCK，那么之后的插件不会继续处理
 * 如果前一个插件返回MESSAGE_IGNORE，那么之后的插件会继续处理
 */
public class PluginConfig {
    public static List<CQPlugin> pluginList = new ArrayList<>();

    static {
        pluginList.add(new StatusPlugin()); // 状态监控插件
        pluginList.add(new LogPlugin()); // 日志插件
        // pluginList.add(new PrefixPlugin()); //前缀处理插件 如果需要给所有指令加上前缀，比如“.”、“/”，可以使用这个插件在此统一处理
        pluginList.add(new ExamplePlugin()); // 示例插件
    }
}
