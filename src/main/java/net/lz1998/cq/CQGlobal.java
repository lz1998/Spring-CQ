package net.lz1998.cq;

import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class CQGlobal {
    public static Map<Long, CoolQ> robots = new ConcurrentHashMap<>();
    public static List<Class<? extends CQPlugin>> pluginList = new ArrayList<>();
}
