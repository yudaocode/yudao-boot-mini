package com.muang.ai.claw.module.system.mapper.permission;

import com.muang.ai.claw.common.core.BaseEntity;
import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.system.controller.admin.permission.vo.role.RolePageForm;
import com.muang.ai.claw.module.system.entity.permission.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapperX<RoleEntity> {

    default PageResult<RoleEntity> selectPage(RolePageForm reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoleEntity>()
                .likeIfPresent(RoleEntity::getName, reqVO.getName())
                .likeIfPresent(RoleEntity::getCode, reqVO.getCode())
                .eqIfPresent(RoleEntity::getStatus, reqVO.getStatus())
                .betweenIfPresent(BaseEntity::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(RoleEntity::getSort));
    }

    default RoleEntity selectByName(String name) {
        return selectOne(RoleEntity::getName, name);
    }

    default RoleEntity selectByCode(String code) {
        return selectOne(RoleEntity::getCode, code);
    }

    default List<RoleEntity> selectListByStatus(@Nullable Collection<Integer> statuses) {
        return selectList(RoleEntity::getStatus, statuses);
    }

}
