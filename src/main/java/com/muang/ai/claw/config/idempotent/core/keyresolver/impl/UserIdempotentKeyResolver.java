package com.muang.ai.claw.config.idempotent.core.keyresolver.impl;

import cn.hutool.crypto.SecureUtil;
import com.muang.ai.claw.util.string.StrUtils;
import com.muang.ai.claw.config.idempotent.core.annotation.Idempotent;
import com.muang.ai.claw.config.idempotent.core.keyresolver.IdempotentKeyResolver;
import com.muang.ai.claw.config.web.core.util.WebFrameworkUtils;
import org.aspectj.lang.JoinPoint;

/**
 * 用户级别的幂等 Key 解析器，使用方法名 + 方法参数 + userId + userType，组装成一个 Key
 *
 * 为了避免 Key 过长，使用 MD5 进行“压缩”
 *
 */
public class UserIdempotentKeyResolver implements IdempotentKeyResolver {

    @Override
    public String resolver(JoinPoint joinPoint, Idempotent idempotent) {
        String methodName = joinPoint.getSignature().toString();
        String argsStr = StrUtils.joinMethodArgs(joinPoint);
        Long userId = WebFrameworkUtils.getLoginUserId();
        Integer userType = WebFrameworkUtils.getLoginUserType();
        return SecureUtil.md5(methodName + argsStr + userId + userType);
    }

}
