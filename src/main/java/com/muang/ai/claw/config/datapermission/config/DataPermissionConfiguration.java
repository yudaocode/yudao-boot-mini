package com.muang.ai.claw.config.datapermission.config;

import com.muang.ai.claw.module.system.entity.dept.DeptEntity;
import com.muang.ai.claw.module.system.entity.user.AdminUserEntity;
import com.muang.ai.claw.config.datapermission.core.rule.dept.DeptDataPermissionRuleCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * system 模块的数据权限 Configuration
 *
 */
@Configuration(proxyBeanMethods = false)
public class DataPermissionConfiguration {

    @Bean
    public DeptDataPermissionRuleCustomizer sysDeptDataPermissionRuleCustomizer() {
        return rule -> {
            // dept
            rule.addDeptColumn(AdminUserEntity.class);
            rule.addDeptColumn(DeptEntity.class, "id");
            // user
            rule.addUserColumn(AdminUserEntity.class, "id");
        };
    }

}
