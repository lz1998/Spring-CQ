package net.lz1998.cq.boot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

@Order(LoggingApplicationListener.DEFAULT_ORDER)
public class CQBannerApplicationListener
        implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    private static final Log logger = LogFactory.getLog(CQBannerApplicationListener.class);

    private static Banner.Mode BANNER_MODE = Banner.Mode.CONSOLE;

    public static void setBANNER_MODE(Banner.Mode bANNER_MODE) {
        BANNER_MODE = bANNER_MODE;
    }

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        if (BANNER_MODE == Banner.Mode.OFF) {
            return;
        }
        String bannerText = this.buildBannerText();
        if (BANNER_MODE == Banner.Mode.CONSOLE) {
            System.out.print(bannerText);
        } else if (BANNER_MODE == Banner.Mode.LOG) {
            logger.info(bannerText);
        }
    }

    private String buildBannerText() {
        StringBuilder bannerTextBuilder = new StringBuilder();
        bannerTextBuilder.append(System.getProperty("line.separator"))
                .append(CQLogo.logo)
                .append(" :: SpringCQ :: ")
                .append(System.getProperty("line.separator"));
        return bannerTextBuilder.toString();
    }
}