package com.muang.ai.claw.config.mybatis.config;

import cn.hutool.core.collection.CollUtil;
import com.muang.ai.claw.util.json.JsonUtils;
import com.muang.ai.claw.config.mybatis.core.handler.DefaultDBFieldHandler;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.baomidou.mybatisplus.extension.parser.JsqlParserGlobal;
import com.baomidou.mybatisplus.extension.parser.cache.JdkSerialCaffeineJsqlParseCache;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * MyBaits 配置类
 *
 */
@Configuration // 目的：先于 MyBatis Plus 自动配置，避免 @MapperScan 可能扫描不到 Mapper 打印 warn 日志
@MapperScan(value = "${yudao.info.base-package}", annotationClass = Mapper.class,
        lazyInitialization = "${mybatis.lazy-initialization:false}") // Mapper 懒加载，目前仅用于单元测试
public class MybatisAutoConfiguration {

    static {
        // 动态 SQL 智能优化支持本地缓存加速解析，更完善的租户复杂 XML 动态 SQL 支持，静态注入缓存
        JsqlParserGlobal.setJsqlParseCache(new JdkSerialCaffeineJsqlParseCache(
                (cache) -> cache.maximumSize(1024)
                        .expireAfterWrite(5, TimeUnit.SECONDS))
        );
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor()); // 分页插件
        return mybatisPlusInterceptor;
    }

    @Bean
    public MetaObjectHandler defaultMetaObjectHandler() {
        return new DefaultDBFieldHandler(); // 自动填充参数类
    }

    @Bean // 特殊：返回结果使用 Object 而不用 JacksonTypeHandler 的原因，避免因为 JacksonTypeHandler 被 mybatis 全局使用！
    public Object jacksonTypeHandler(List<ObjectMapper> objectMappers) {
        // 特殊：设置 JacksonTypeHandler 的 ObjectMapper！
        ObjectMapper objectMapper = CollUtil.getFirst(objectMappers);
        if (objectMapper == null) {
            objectMapper = JsonUtils.getObjectMapper();
        }
        JacksonTypeHandler.setObjectMapper(objectMapper);
        return new JacksonTypeHandler(Object.class);
    }

}
