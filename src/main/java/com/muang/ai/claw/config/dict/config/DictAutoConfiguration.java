package com.muang.ai.claw.config.dict.config;

import com.muang.ai.claw.module.system.api.dict.DictDataApi;
import com.muang.ai.claw.config.dict.core.DictFrameworkUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class DictAutoConfiguration {

    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public DictFrameworkUtils dictUtils(DictDataApi dictDataApi) {
        DictFrameworkUtils.init(dictDataApi);
        return new DictFrameworkUtils();
    }

}
