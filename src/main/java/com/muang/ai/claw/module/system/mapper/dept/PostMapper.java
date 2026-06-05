package com.muang.ai.claw.module.system.mapper.dept;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.system.controller.admin.dept.vo.post.PostPageForm;
import com.muang.ai.claw.module.system.entity.dept.PostEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface PostMapper extends BaseMapperX<PostEntity> {

    default List<PostEntity> selectList(Collection<Long> ids, Collection<Integer> statuses) {
        return selectList(new LambdaQueryWrapperX<PostEntity>()
                .inIfPresent(PostEntity::getId, ids)
                .inIfPresent(PostEntity::getStatus, statuses));
    }

    default PageResult<PostEntity> selectPage(PostPageForm reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PostEntity>()
                .likeIfPresent(PostEntity::getCode, reqVO.getCode())
                .likeIfPresent(PostEntity::getName, reqVO.getName())
                .eqIfPresent(PostEntity::getStatus, reqVO.getStatus())
                .orderByDesc(PostEntity::getId));
    }

    default PostEntity selectByName(String name) {
        return selectOne(PostEntity::getName, name);
    }

    default PostEntity selectByCode(String code) {
        return selectOne(PostEntity::getCode, code);
    }

}
