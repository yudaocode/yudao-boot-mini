package com.muang.ai.claw.config.datapermission.config;

import com.muang.ai.claw.module.system.api.permission.PermissionApi;
import com.muang.ai.claw.config.datapermission.core.rule.dept.DeptDataPermissionRule;
import com.muang.ai.claw.config.datapermission.core.rule.dept.DeptDataPermissionRuleCustomizer;
import com.muang.ai.claw.config.security.core.LoginUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 基于部门的数据权限 AutoConfiguration
 *
 */
@Configuration
@ConditionalOnClass(LoginUser.class)
@ConditionalOnBean(value = {DeptDataPermissionRuleCustomizer.class})
public class DeptDataPermissionAutoConfiguration {

    @Bean
    public DeptDataPermissionRule deptDataPermissionRule(PermissionApi permissionApi,
                                                         List<DeptDataPermissionRuleCustomizer> customizers) {
        // 创建 DeptDataPermissionRule 对象
        DeptDataPermissionRule rule = new DeptDataPermissionRule(permissionApi);
        // 补全表配置
        customizers.forEach(customizer -> customizer.customize(rule));
        return rule;
    }

}
