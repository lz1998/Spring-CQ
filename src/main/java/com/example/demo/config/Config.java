package com.example.demo.config;

import com.example.demo.plugin.DemoPlugin;
import net.lz1998.cq.CQGlobal;
import net.lz1998.cq.EnableCQ;

@EnableCQ
public class Config {
    public static void init(){
        CQGlobal.pluginList.add(DemoPlugin.class); // 示例插件
        // CQGlobal.pluginList.add(XXXXXXXX); // 如果还有别的功能，在这里add，模仿上面一行
    }
}
