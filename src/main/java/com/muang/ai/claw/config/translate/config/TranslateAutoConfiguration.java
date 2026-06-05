package com.muang.ai.claw.config.translate.config;

import com.muang.ai.claw.config.translate.core.TranslateUtils;
import com.fhs.trans.service.impl.TransService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class TranslateAutoConfiguration {

    @Bean
    @SuppressWarnings({"InstantiationOfUtilityClass", "SpringJavaInjectionPointsAutowiringInspection"})
    public TranslateUtils translateUtils(TransService transService) {
        TranslateUtils.init(transService);
        return new TranslateUtils();
    }

}
