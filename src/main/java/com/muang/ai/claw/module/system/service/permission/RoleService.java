package com.muang.ai.claw.module.system.service.permission;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.muang.ai.claw.constant.CommonStatusEnum;
import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.module.system.entity.permission.RoleEntity;
import com.muang.ai.claw.util.collection.CollectionUtils;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.module.system.controller.admin.permission.vo.role.RolePageForm;
import com.muang.ai.claw.module.system.controller.admin.permission.vo.role.RoleSaveForm;
import com.muang.ai.claw.module.system.mapper.permission.RoleMapper;
import com.muang.ai.claw.module.system.constant.RedisKeyConstants;
import com.muang.ai.claw.module.system.constant.permission.DataScopeEnum;
import com.muang.ai.claw.module.system.constant.permission.RoleCodeEnum;
import com.muang.ai.claw.module.system.constant.permission.RoleTypeEnum;
import com.google.common.annotations.VisibleForTesting;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

import static com.muang.ai.claw.common.exception.util.ServiceExceptionUtil.exception;
import static com.muang.ai.claw.util.collection.CollectionUtils.convertMap;
import static com.muang.ai.claw.module.system.constant.ErrorCodeConstants.*;
import static com.muang.ai.claw.module.system.constant.LogRecordConstants.*;

/**
 * 角色 Service 实现类
 *
 */
@Service
@Slf4j
public class RoleService {

    @Resource
    private PermissionService permissionService;

    @Resource
    private RoleMapper roleMapper;

    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = SYSTEM_ROLE_TYPE, subType = SYSTEM_ROLE_CREATE_SUB_TYPE, bizNo = "{{#role.id}}",
            success = SYSTEM_ROLE_CREATE_SUCCESS)
    public Long createRole(RoleSaveForm createReqVO, Integer type) {
        // 1. 校验角色
        validateRoleDuplicate(createReqVO.getName(), createReqVO.getCode(), null);

        // 2. 插入到数据库
        RoleEntity role = BeanUtils.toBean(createReqVO, RoleEntity.class)
                .setType(ObjectUtil.defaultIfNull(type, RoleTypeEnum.CUSTOM.getType()))
                .setStatus(ObjUtil.defaultIfNull(createReqVO.getStatus(), CommonStatusEnum.ENABLE.getStatus()))
                .setDataScope(DataScopeEnum.ALL.getScope()); // 默认可查看所有数据。原因是，可能一些项目不需要项目权限
        roleMapper.insert(role);

        // 3. 记录操作日志上下文
        LogRecordContext.putVariable("role", role);
        return role.getId();
    }

    @CacheEvict(value = RedisKeyConstants.ROLE, key = "#updateReqVO.id")
    @LogRecord(type = SYSTEM_ROLE_TYPE, subType = SYSTEM_ROLE_UPDATE_SUB_TYPE, bizNo = "{{#updateReqVO.id}}",
            success = SYSTEM_ROLE_UPDATE_SUCCESS)
    public void updateRole(RoleSaveForm updateReqVO) {
        // 1.1 校验是否可以更新
        RoleEntity role = validateRoleForUpdate(updateReqVO.getId());
        // 1.2 校验角色的唯一字段是否重复
        validateRoleDuplicate(updateReqVO.getName(), updateReqVO.getCode(), updateReqVO.getId());

        // 2. 更新到数据库
        RoleEntity updateObj = BeanUtils.toBean(updateReqVO, RoleEntity.class);
        roleMapper.updateById(updateObj);

        // 3. 记录操作日志上下文
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(role, RoleSaveForm.class));
        LogRecordContext.putVariable("role", role);
    }

    @CacheEvict(value = RedisKeyConstants.ROLE, key = "#id")
    public void updateRoleDataScope(Long id, Integer dataScope, Set<Long> dataScopeDeptIds) {
        // 校验是否可以更新
        validateRoleForUpdate(id);

        // 更新数据范围
        RoleEntity updateObject = new RoleEntity();
        updateObject.setId(id);
        updateObject.setDataScope(dataScope);
        updateObject.setDataScopeDeptIds(dataScopeDeptIds);
        roleMapper.updateById(updateObject);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisKeyConstants.ROLE, key = "#id")
    @LogRecord(type = SYSTEM_ROLE_TYPE, subType = SYSTEM_ROLE_DELETE_SUB_TYPE, bizNo = "{{#id}}",
            success = SYSTEM_ROLE_DELETE_SUCCESS)
    public void deleteRole(Long id) {
        // 1. 校验是否可以更新
        RoleEntity role = validateRoleForUpdate(id);

        // 2.1 标记删除
        roleMapper.deleteById(id);
        // 2.2 删除相关数据
        permissionService.processRoleDeleted(id);

        // 3. 记录操作日志上下文
        LogRecordContext.putVariable("role", role);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleList(List<Long> ids) {
        // 1. 校验是否可以删除
        ids.forEach(this::validateRoleForUpdate);

        // 2.1 标记删除
        roleMapper.deleteByIds(ids);
        // 2.2 删除相关数据
        ids.forEach(id -> permissionService.processRoleDeleted(id));
    }

    /**
     * 校验角色的唯一字段是否重复
     *
     * 1. 是否存在相同名字的角色
     * 2. 是否存在相同编码的角色
     *
     * @param name 角色名字
     * @param code 角色额编码
     * @param id 角色编号
     */
    @VisibleForTesting
    void validateRoleDuplicate(String name, String code, Long id) {
        // 0. 超级管理员，不允许创建
        if (RoleCodeEnum.isSuperAdmin(code)) {
            throw exception(ROLE_ADMIN_CODE_ERROR, code);
        }
        // 1. 该 name 名字被其它角色所使用
        RoleEntity role = roleMapper.selectByName(name);
        if (role != null && !role.getId().equals(id)) {
            throw exception(ROLE_NAME_DUPLICATE, name);
        }
        // 2. 是否存在相同编码的角色
        if (!StringUtils.hasText(code)) {
            return;
        }
        // 该 code 编码被其它角色所使用
        role = roleMapper.selectByCode(code);
        if (role != null && !role.getId().equals(id)) {
            throw exception(ROLE_CODE_DUPLICATE, code);
        }
    }

    /**
     * 校验角色是否可以被更新
     *
     * @param id 角色编号
     */
    @VisibleForTesting
    RoleEntity validateRoleForUpdate(Long id) {
        RoleEntity role = roleMapper.selectById(id);
        if (role == null) {
            throw exception(ROLE_NOT_EXISTS);
        }
        // 内置角色，不允许删除
        if (RoleTypeEnum.SYSTEM.getType().equals(role.getType())) {
            throw exception(ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE);
        }
        return role;
    }

    public RoleEntity getRole(Long id) {
        return roleMapper.selectById(id);
    }

    @Cacheable(value = RedisKeyConstants.ROLE, key = "#id",
            unless = "#result == null")
    public RoleEntity getRoleFromCache(Long id) {
        return roleMapper.selectById(id);
    }


    public List<RoleEntity> getRoleListByStatus(Collection<Integer> statuses) {
        return roleMapper.selectListByStatus(statuses);
    }

    public List<RoleEntity> getRoleList() {
        return roleMapper.selectList();
    }

    public List<RoleEntity> getRoleList(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return roleMapper.selectByIds(ids);
    }

    public List<RoleEntity> getRoleListFromCache(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        // 这里采用 for 循环从缓存中获取，主要考虑 Spring CacheManager 无法批量操作的问题
        RoleService self = getSelf();
        return CollectionUtils.convertList(ids, self::getRoleFromCache);
    }

    public PageResult<RoleEntity> getRolePage(RolePageForm reqVO) {
        return roleMapper.selectPage(reqVO);
    }

    public boolean hasAnySuperAdmin(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return false;
        }
        RoleService self = getSelf();
        return ids.stream().anyMatch(id -> {
            RoleEntity role = self.getRoleFromCache(id);
            return role != null && RoleCodeEnum.isSuperAdmin(role.getCode());
        });
    }

    public void validateRoleList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        // 获得角色信息
        List<RoleEntity> roles = roleMapper.selectByIds(ids);
        Map<Long, RoleEntity> roleMap = convertMap(roles, RoleEntity::getId);
        // 校验
        ids.forEach(id -> {
            RoleEntity role = roleMap.get(id);
            if (role == null) {
                throw exception(ROLE_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(role.getStatus())) {
                throw exception(ROLE_IS_DISABLE, role.getName());
            }
        });
    }

    /**
     * 获得自身的代理对象，解决 AOP 生效问题
     *
     * @return 自己
     */
    private RoleService getSelf() {
        return SpringUtil.getBean(getClass());
    }

}
