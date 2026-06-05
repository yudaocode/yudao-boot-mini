package com.muang.ai.claw.module.system.constant.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录日志的类型枚举
 */
@Getter
@AllArgsConstructor
public enum LoginLogTypeEnum {

    LOGIN_USERNAME(100, "账号登录"), // 使用账号登录
    LOGIN_SOCIAL(101, "社交登录"), // 使用社交登录
    LOGIN_MOBILE(103, "手机登录"), // 使用手机登陆
    LOGIN_SMS(104, "短信登录"), // 使用短信登陆

    LOGOUT_SELF(200, "主动登出"),  // 自己主动登出
    LOGOUT_DELETE(202, "强制登出"), // 强制退出
    ;

    /**
     * 日志类型
     */
    private final Integer type;
    /**
     * 名字
     */
    private final String name;

}
