package net.lz1998.cq.boot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cq")
public class CQProperties {
    @Getter
    @Setter
    private String url="/ws/*/";
    @Getter
    @Setter
    private Integer maxTextMessageBufferSize=512000;
    @Getter
    @Setter
    private Integer maxBinaryMessageBufferSize=512000;
    @Getter
    @Setter
    private Long maxSessionIdleTimeout=15*60000L;
    @Getter
    @Setter
    private Long apiTimeout=120000L;
}
