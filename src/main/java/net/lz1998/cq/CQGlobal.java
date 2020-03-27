package net.lz1998.cq;

import net.lz1998.cq.robot.CoolQ;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CQGlobal {
    public static Map<Long, CoolQ> robots = new ConcurrentHashMap<>();
}
