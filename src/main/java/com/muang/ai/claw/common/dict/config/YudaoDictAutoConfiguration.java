package com.muang.ai.claw.common.dict.config;

import com.muang.ai.claw.common.biz.system.dict.DictDataCommonApi;
import com.muang.ai.claw.common.dict.core.DictFrameworkUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class YudaoDictAutoConfiguration {

    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public DictFrameworkUtils dictUtils(DictDataCommonApi dictDataApi) {
        DictFrameworkUtils.init(dictDataApi);
        return new DictFrameworkUtils();
    }

}
