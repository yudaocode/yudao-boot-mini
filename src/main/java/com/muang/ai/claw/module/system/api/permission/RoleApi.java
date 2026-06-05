package com.muang.ai.claw.module.system.api.permission;

import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.module.system.api.permission.dto.RoleRespDTO;
import com.muang.ai.claw.module.system.entity.permission.RoleEntity;
import com.muang.ai.claw.module.system.service.permission.RoleService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;
import com.muang.ai.claw.util.collection.CollectionUtils;
import java.util.Map;

/**
 * 角色 API 实现类
 *
 */
@Service
public class RoleApi {

    @Resource
    private RoleService roleService;

    public void validRoleList(Collection<Long> ids) {
        roleService.validateRoleList(ids);
    }

    public RoleRespDTO getRole(Long id) {
        RoleEntity role = roleService.getRole(id);
        return BeanUtils.toBean(role, RoleRespDTO.class);
    }

    public List<RoleRespDTO> getRoleList(Collection<Long> ids) {
        List<RoleEntity> list = roleService.getRoleList(ids);
        return BeanUtils.toBean(list, RoleRespDTO.class);
    }

    public Map<Long, RoleRespDTO> getRoleMap(Collection<Long> ids) {
        List<RoleRespDTO> list = getRoleList(ids);
        return CollectionUtils.convertMap(list, RoleRespDTO::getId);
    }

}
