package net.lz1998.cq.boot;

import net.lz1998.cq.websocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@ComponentScan(
        basePackages = {"net.lz1998.cq"}
)
@EnableWebSocket
@EnableConfigurationProperties({CQProperties.class, EventProperties.class})
@Import(CQBean.class)
public class CQAutoConfiguration implements WebSocketConfigurer {


    @Autowired
    CQProperties cqProperties;


    @Autowired
    WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, cqProperties.getUrl()).setAllowedOrigins("*");
    }


}
