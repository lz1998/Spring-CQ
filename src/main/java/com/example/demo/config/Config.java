package com.example.demo.config;

import com.example.demo.plugin.DemoPlugin;
import net.lz1998.cq.CQGlobal;
import net.lz1998.cq.EnableCQ;

@EnableCQ
public class Config {
    public static void init(){
        CQGlobal.pluginList.add(DemoPlugin.class);
    }
}
