package xin.lz1998;

import xin.lz1998.cq.robot.CoolQ;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Global {
    public static Map<Long, CoolQ> robots = new ConcurrentHashMap<>();


}
