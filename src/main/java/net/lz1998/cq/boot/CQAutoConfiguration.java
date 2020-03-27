package net.lz1998.cq.boot;

import net.lz1998.cq.websocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@ComponentScan(
        basePackages = {"net.lz1998.cq.robot"}
)
@Import({WebSocketHandler.class})
@EnableWebSocket
@EnableConfigurationProperties(CQProperties.class)
public class CQAutoConfiguration implements WebSocketConfigurer {

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Autowired
    CQProperties cqProperties;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, cqProperties.getUrl()).setAllowedOrigins("*");
    }

    @Bean
    @ConditionalOnMissingBean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        // ws 传输数据的时候，数据过大有时候会接收不到，所以在此处设置bufferSize
        container.setMaxTextMessageBufferSize(cqProperties.getMaxTextMessageBufferSize());
        container.setMaxBinaryMessageBufferSize(cqProperties.getMaxBinaryMessageBufferSize());
        container.setMaxSessionIdleTimeout(cqProperties.getMaxSessionIdleTimeout());
        return container;
    }

}
