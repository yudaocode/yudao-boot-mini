package com.muang.ai.claw.module.system.mapper.dept;

import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.system.controller.admin.dept.vo.dept.DeptListForm;
import com.muang.ai.claw.module.system.entity.dept.DeptEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DeptMapper extends BaseMapperX<DeptEntity> {

    default List<DeptEntity> selectList(DeptListForm reqVO) {
        return selectList(new LambdaQueryWrapperX<DeptEntity>()
                .likeIfPresent(DeptEntity::getName, reqVO.getName())
                .eqIfPresent(DeptEntity::getStatus, reqVO.getStatus()));
    }

    default DeptEntity selectByParentIdAndName(Long parentId, String name) {
        return selectOne(DeptEntity::getParentId, parentId, DeptEntity::getName, name);
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(DeptEntity::getParentId, parentId);
    }

    default List<DeptEntity> selectListByParentId(Collection<Long> parentIds) {
        return selectList(DeptEntity::getParentId, parentIds);
    }

    default List<DeptEntity> selectListByLeaderUserId(Long id) {
        return selectList(DeptEntity::getLeaderUserId, id);
    }

}
