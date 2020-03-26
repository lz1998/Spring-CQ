package net.lz1998.cq;

import net.lz1998.cq.websocket.WebSocketConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(
        basePackages = {"net.lz1998.cq.robot","net.lz1998.cq.websocket"}
)
@Import({WebSocketConfig.class})
public class CQConfiguration {
    public CQConfiguration() {
    }
}
