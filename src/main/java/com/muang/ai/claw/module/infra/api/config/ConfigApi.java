package com.muang.ai.claw.module.infra.api.config;

import com.muang.ai.claw.module.infra.entity.config.ConfigEntity;
import com.muang.ai.claw.module.infra.service.config.ConfigService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 参数配置 API 实现类
 *
 */
@Service
@Validated
public class ConfigApi {

    @Resource
    private ConfigService configService;

    public String getConfigValueByKey(String key) {
        ConfigEntity config = configService.getConfigByKey(key);
        return config != null ? config.getValue() : null;
    }

}
