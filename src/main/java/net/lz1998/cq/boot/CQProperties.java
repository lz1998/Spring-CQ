package net.lz1998.cq.boot;

import lombok.Getter;
import lombok.Setter;
import net.lz1998.cq.robot.CQPlugin;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "spring.cq")
public class CQProperties {
    @Getter
    @Setter
    private String url = "/ws/*/";
    @Getter
    @Setter
    private Integer maxTextMessageBufferSize = 512000;
    @Getter
    @Setter
    private Integer maxBinaryMessageBufferSize = 512000;
    @Getter
    @Setter
    private Long maxSessionIdleTimeout = 15 * 60000L;
    @Getter
    @Setter
    private Long apiTimeout = 120000L;
    @Getter
    @Setter
    List<Class<? extends CQPlugin>> pluginList = new ArrayList<>();

}
