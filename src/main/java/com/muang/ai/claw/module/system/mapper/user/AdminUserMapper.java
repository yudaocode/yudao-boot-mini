package com.muang.ai.claw.module.system.mapper.user;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.system.controller.admin.user.vo.user.UserPageForm;
import com.muang.ai.claw.module.system.entity.user.AdminUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface AdminUserMapper extends BaseMapperX<AdminUserEntity> {

    default AdminUserEntity selectByUsername(String username) {
        return selectOne(AdminUserEntity::getUsername, username);
    }

    default AdminUserEntity selectByEmail(String email) {
        return selectOne(AdminUserEntity::getEmail, email);
    }

    default AdminUserEntity selectByMobile(String mobile) {
        return selectOne(AdminUserEntity::getMobile, mobile);
    }

    default PageResult<AdminUserEntity> selectPage(UserPageForm reqVO, Collection<Long> deptIds, Collection<Long> userIds) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AdminUserEntity>()
                .likeIfPresent(AdminUserEntity::getUsername, reqVO.getUsername())
                .likeIfPresent(AdminUserEntity::getMobile, reqVO.getMobile())
                .eqIfPresent(AdminUserEntity::getStatus, reqVO.getStatus())
                .betweenIfPresent(AdminUserEntity::getCreateTime, reqVO.getCreateTime())
                .inIfPresent(AdminUserEntity::getDeptId, deptIds)
                .inIfPresent(AdminUserEntity::getId, userIds)
                .orderByDesc(AdminUserEntity::getId));
    }

    default List<AdminUserEntity> selectListByNickname(String nickname) {
        return selectList(new LambdaQueryWrapperX<AdminUserEntity>().like(AdminUserEntity::getNickname, nickname));
    }

    default List<AdminUserEntity> selectListByStatus(Integer status) {
        return selectList(AdminUserEntity::getStatus, status);
    }

    default List<AdminUserEntity> selectListByDeptIds(Collection<Long> deptIds) {
        return selectList(AdminUserEntity::getDeptId, deptIds);
    }

}
