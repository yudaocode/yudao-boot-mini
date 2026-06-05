package com.muang.ai.claw.config.file.config;

import com.muang.ai.claw.config.file.core.client.FileClientFactory;
import com.muang.ai.claw.config.file.core.client.FileClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件配置类
 *
 */
@Configuration(proxyBeanMethods = false)
public class FileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
