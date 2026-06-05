package com.muang.ai.claw.module.infra.constant.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConfigTypeEnum {

    /**
     * 系统配置
     */
    SYSTEM(1, "系统内置"),
    /**
     * 自定义配置
     */
    CUSTOM(2, "自定义");

    private final Integer type;
    /**
     * 名字
     */
    private final String name;

}
