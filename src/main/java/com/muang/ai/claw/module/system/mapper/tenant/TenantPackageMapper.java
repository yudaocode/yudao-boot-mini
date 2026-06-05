package com.muang.ai.claw.module.system.mapper.tenant;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.system.controller.admin.tenant.vo.packages.TenantPackagePageForm;
import com.muang.ai.claw.module.system.entity.tenant.TenantPackageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TenantPackageMapper extends BaseMapperX<TenantPackageEntity> {

    default PageResult<TenantPackageEntity> selectPage(TenantPackagePageForm reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TenantPackageEntity>()
                .likeIfPresent(TenantPackageEntity::getName, reqVO.getName())
                .eqIfPresent(TenantPackageEntity::getStatus, reqVO.getStatus())
                .likeIfPresent(TenantPackageEntity::getRemark, reqVO.getRemark())
                .betweenIfPresent(TenantPackageEntity::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TenantPackageEntity::getId));
    }

    default List<TenantPackageEntity> selectListByStatus(Integer status) {
        return selectList(TenantPackageEntity::getStatus, status);
    }

    default TenantPackageEntity selectByName(String name) {
        return selectOne(TenantPackageEntity::getName, name);
    }
}
