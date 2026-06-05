package com.muang.ai.claw.config.ratelimiter.core.keyresolver.impl;

import cn.hutool.crypto.SecureUtil;
import com.muang.ai.claw.util.string.StrUtils;
import com.muang.ai.claw.config.ratelimiter.core.annotation.RateLimiter;
import com.muang.ai.claw.config.ratelimiter.core.keyresolver.RateLimiterKeyResolver;
import com.muang.ai.claw.config.web.core.util.WebFrameworkUtils;
import org.aspectj.lang.JoinPoint;

/**
 * 用户级别的限流 Key 解析器，使用方法名 + 方法参数 + userId + userType，组装成一个 Key
 *
 * 为了避免 Key 过长，使用 MD5 进行“压缩”
 *
 */
public class UserRateLimiterKeyResolver implements RateLimiterKeyResolver {

    @Override
    public String resolver(JoinPoint joinPoint, RateLimiter rateLimiter) {
        String methodName = joinPoint.getSignature().toString();
        String argsStr = StrUtils.joinMethodArgs(joinPoint);
        Long userId = WebFrameworkUtils.getLoginUserId();
        Integer userType = WebFrameworkUtils.getLoginUserType();
        return SecureUtil.md5(methodName + argsStr + userId + userType);
    }

}
