package xin.lz1998.bot.plugin;

import xin.lz1998.bot.plugin.log.LogPlugin;
import xin.lz1998.bot.plugin.prefix.PrefixPlugin;
import xin.lz1998.bot.plugin.say.SayPlugin;
import xin.lz1998.bot.plugin.status.StatusPlugin;
import xin.lz1998.bot.plugin.test.TestPlugin;
import xin.lz1998.cq.robot.CQPlugin;

import java.util.ArrayList;
import java.util.List;

public class PluginConfig {
    public static List<CQPlugin> pluginList = new ArrayList<>();

    static {
        pluginList.add(new TestPlugin());// 测试插件 TODO get group member list有问题
        pluginList.add(new StatusPlugin()); // 状态监控插件
        pluginList.add(new LogPlugin()); // 日志插件
        pluginList.add(new PrefixPlugin()); // 前缀处理插件
        pluginList.add(new SayPlugin()); // 说话插件
    }

}
