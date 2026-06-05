package com.muang.ai.claw.module.system.mapper.dept;

import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.system.entity.dept.UserPostEntity;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface UserPostMapper extends BaseMapperX<UserPostEntity> {

    default List<UserPostEntity> selectListByUserId(Long userId) {
        return selectList(UserPostEntity::getUserId, userId);
    }

    default void deleteByUserIdAndPostId(Long userId, Collection<Long> postIds) {
        delete(new LambdaQueryWrapperX<UserPostEntity>()
                .eq(UserPostEntity::getUserId, userId)
                .in(UserPostEntity::getPostId, postIds));
    }

    default List<UserPostEntity> selectListByPostIds(Collection<Long> postIds) {
        return selectList(UserPostEntity::getPostId, postIds);
    }

    default void deleteByUserId(Long userId) {
        delete(Wrappers.lambdaUpdate(UserPostEntity.class).eq(UserPostEntity::getUserId, userId));
    }
}
