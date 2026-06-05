package com.muang.ai.claw.module.system.constant.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别的枚举值
 *
 */
@Getter
@AllArgsConstructor
public enum SexEnum {

    /** 男 */
    MALE(1, "男"),
    /** 女 */
    FEMALE(2, "女"),
    /* 未知 */
    UNKNOWN(0, "未知");

    /**
     * 性别
     */
    private final Integer sex;
    /**
     * 名字
     */
    private final String name;

}
