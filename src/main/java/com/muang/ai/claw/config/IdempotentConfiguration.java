package com.muang.ai.claw.config;

import com.muang.ai.claw.common.idempotent.core.aop.IdempotentAspect;
import com.muang.ai.claw.common.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import com.muang.ai.claw.common.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import com.muang.ai.claw.common.idempotent.core.keyresolver.IdempotentKeyResolver;
import com.muang.ai.claw.common.idempotent.core.keyresolver.impl.UserIdempotentKeyResolver;
import com.muang.ai.claw.common.idempotent.core.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = RedisAutoConfiguration.class)
public class IdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public UserIdempotentKeyResolver userIdempotentKeyResolver() {
        return new UserIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
