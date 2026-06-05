package com.muang.ai.claw.module.system.mapper.permission;

import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.module.system.entity.permission.RoleMenuEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapperX<RoleMenuEntity> {

    default List<RoleMenuEntity> selectListByRoleId(Long roleId) {
        return selectList(RoleMenuEntity::getRoleId, roleId);
    }

    default List<RoleMenuEntity> selectListByRoleId(Collection<Long> roleIds) {
        return selectList(RoleMenuEntity::getRoleId, roleIds);
    }

    default List<RoleMenuEntity> selectListByMenuId(Long menuId) {
        return selectList(RoleMenuEntity::getMenuId, menuId);
    }

    default void deleteListByRoleIdAndMenuIds(Long roleId, Collection<Long> menuIds) {
        delete(new LambdaQueryWrapper<RoleMenuEntity>()
                .eq(RoleMenuEntity::getRoleId, roleId)
                .in(RoleMenuEntity::getMenuId, menuIds));
    }

    default void deleteListByMenuId(Long menuId) {
        delete(new LambdaQueryWrapper<RoleMenuEntity>().eq(RoleMenuEntity::getMenuId, menuId));
    }

    default void deleteListByRoleId(Long roleId) {
        delete(new LambdaQueryWrapper<RoleMenuEntity>().eq(RoleMenuEntity::getRoleId, roleId));
    }

}
