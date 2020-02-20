package net.lz1998.cq;

import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CQGlobal {
    public static Map<Long, CoolQ> robots = new ConcurrentHashMap<>();
    public static List<CQPlugin> pluginList=new ArrayList<>();
    public static Integer API_TIME_OUT=120000;
    public static String WEB_SOCKET_URL="/ws/*/";
}
