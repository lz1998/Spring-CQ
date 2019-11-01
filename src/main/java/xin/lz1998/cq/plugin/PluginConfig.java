package xin.lz1998.cq.plugin;

import xin.lz1998.cq.plugin.log.LogPlugin;
import xin.lz1998.cq.plugin.prefix.PrefixPlugin;
import xin.lz1998.cq.plugin.say.SayPlugin;
import xin.lz1998.cq.plugin.status.StatusPlugin;
import xin.lz1998.cq.robot.CQPlugin;

import java.util.ArrayList;
import java.util.List;

public class PluginConfig {
    public static List<CQPlugin> pluginList = new ArrayList<>();

    static {
        pluginList.add(new StatusPlugin()); // 状态监控插件
        pluginList.add(new LogPlugin()); // 日志插件
        pluginList.add(new PrefixPlugin()); // 前缀处理插件
        pluginList.add(new SayPlugin()); // 说话插件
    }

}
