package com.muang.ai.claw.module.system.mapper.tenant;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.config.mybatis.core.util.MyBatisUtils;
import com.muang.ai.claw.module.system.controller.admin.tenant.vo.tenant.TenantPageForm;
import com.muang.ai.claw.module.system.entity.tenant.TenantEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TenantMapper extends BaseMapperX<TenantEntity> {

    default PageResult<TenantEntity> selectPage(TenantPageForm reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TenantEntity>()
                .likeIfPresent(TenantEntity::getName, reqVO.getName())
                .likeIfPresent(TenantEntity::getContactName, reqVO.getContactName())
                .likeIfPresent(TenantEntity::getContactMobile, reqVO.getContactMobile())
                .eqIfPresent(TenantEntity::getStatus, reqVO.getStatus())
                .betweenIfPresent(TenantEntity::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TenantEntity::getId));
    }

    default TenantEntity selectByName(String name) {
        return selectOne(TenantEntity::getName, name);
    }

    default List<TenantEntity> selectListByWebsite(String website) {
        return selectList(new LambdaQueryWrapperX<TenantEntity>()
                .apply(MyBatisUtils.findInSet("websites", website)));
    }

    default Long selectCountByPackageId(Long packageId) {
        return selectCount(TenantEntity::getPackageId, packageId);
    }

    default List<TenantEntity> selectListByPackageId(Long packageId) {
        return selectList(TenantEntity::getPackageId, packageId);
    }

    default List<TenantEntity> selectListByStatus(Integer status) {
        return selectList(TenantEntity::getStatus, status);
    }

}
