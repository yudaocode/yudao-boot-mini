package com.muang.ai.claw.config.captcha.config;

import com.muang.ai.claw.config.captcha.core.PictureWordCaptchaServiceImpl;
import com.muang.ai.claw.config.captcha.core.RedisCaptchaServiceImpl;
import com.anji.captcha.config.AjCaptchaAutoConfiguration;
import com.anji.captcha.service.CaptchaCacheService;
import com.anji.captcha.service.impl.CaptchaServiceFactory;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 验证码的配置类
 */
@Configuration(proxyBeanMethods = false)
@ImportAutoConfiguration(AjCaptchaAutoConfiguration.class)
public class CaptchaConfiguration {

    @Bean(name = "AjCaptchaCacheService")
    @Primary
    public CaptchaCacheService captchaCacheService(StringRedisTemplate stringRedisTemplate) {
        RedisCaptchaServiceImpl service = new RedisCaptchaServiceImpl();
        service.setStringRedisTemplate(stringRedisTemplate);
        // Populate factory maps to replace META-INF/services SPI registration
        CaptchaServiceFactory.cacheService.put(service.type(), service);
        PictureWordCaptchaServiceImpl pictureWordService = new PictureWordCaptchaServiceImpl();
        CaptchaServiceFactory.instances.put(pictureWordService.captchaType(), pictureWordService);
        return service;
    }

}
