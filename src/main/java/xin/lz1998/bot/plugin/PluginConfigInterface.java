package xin.lz1998.bot.plugin;

import org.springframework.stereotype.Component;
import xin.lz1998.cq.robot.CQPlugin;

import java.util.List;

@Component
public interface PluginConfigInterface {
    List<CQPlugin> getList();
}
