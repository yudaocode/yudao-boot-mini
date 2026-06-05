package com.muang.ai.claw.config.banner.config;

import com.muang.ai.claw.config.banner.core.BannerApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

/**
 * Banner 的自动配置类
 *
 */
@Configuration
public class BannerAutoConfiguration {

    @Bean
    public BannerApplicationRunner bannerApplicationRunner() {
        return new BannerApplicationRunner();
    }

}
