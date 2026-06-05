package com.muang.ai.claw.module.system.api.permission;

import com.muang.ai.claw.module.system.api.permission.dto.DeptDataPermissionRespDTO;
import com.muang.ai.claw.module.system.service.permission.PermissionService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * 权限 API 实现类
 *
 */
@Service
public class PermissionApi {

    @Resource
    private PermissionService permissionService;

    public Set<Long> getUserRoleIdListByRoleIds(Collection<Long> roleIds) {
        return permissionService.getUserRoleIdListByRoleId(roleIds);
    }

    public boolean hasAnyPermissions(Long userId, String... permissions) {
        return permissionService.hasAnyPermissions(userId, permissions);
    }

    public boolean hasAnyRoles(Long userId, String... roles) {
        return permissionService.hasAnyRoles(userId, roles);
    }

    public DeptDataPermissionRespDTO getDeptDataPermission(Long userId) {
        return permissionService.getDeptDataPermission(userId);
    }

}
