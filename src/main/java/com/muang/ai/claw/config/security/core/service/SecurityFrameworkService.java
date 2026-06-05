package com.muang.ai.claw.config.security.core.service;

import cn.hutool.core.collection.CollUtil;
import com.muang.ai.claw.module.system.api.permission.PermissionApi;
import com.muang.ai.claw.config.security.core.LoginUser;
import com.muang.ai.claw.config.security.core.util.SecurityFrameworkUtils;
import lombok.AllArgsConstructor;

import java.util.Arrays;

import static com.muang.ai.claw.config.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.muang.ai.claw.config.security.core.util.SecurityFrameworkUtils.skipPermissionCheck;

/**
 * 默认的 {@link SecurityFrameworkService} 实现类
 *
 */
@AllArgsConstructor
public class SecurityFrameworkService {

    private final PermissionApi permissionApi;

    public boolean hasPermission(String permission) {
        return hasAnyPermissions(permission);
    }

    public boolean hasAnyPermissions(String... permissions) {
        // 特殊：跨租户访问
        if (skipPermissionCheck()) {
            return true;
        }

        // 权限校验
        Long userId = getLoginUserId();
        if (userId == null) {
            return false;
        }
        return permissionApi.hasAnyPermissions(userId, permissions);
    }

    public boolean hasRole(String role) {
        return hasAnyRoles(role);
    }

    public boolean hasAnyRoles(String... roles) {
        // 特殊：跨租户访问
        if (skipPermissionCheck()) {
            return true;
        }

        // 权限校验
        Long userId = getLoginUserId();
        if (userId == null) {
            return false;
        }
        return permissionApi.hasAnyRoles(userId, roles);
    }

    public boolean hasScope(String scope) {
        return hasAnyScopes(scope);
    }

    public boolean hasAnyScopes(String... scope) {
        // 特殊：跨租户访问
        if (skipPermissionCheck()) {
            return true;
        }

        // 权限校验
        LoginUser user = SecurityFrameworkUtils.getLoginUser();
        if (user == null) {
            return false;
        }
        return CollUtil.containsAny(user.getScopes(), Arrays.asList(scope));
    }

}
