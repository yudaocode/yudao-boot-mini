package com.muang.ai.claw.module.infra.dal.mysql.demo.demo03.normal;

import com.muang.ai.claw.framework.common.pojo.PageResult;
import com.muang.ai.claw.framework.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.infra.controller.admin.demo.demo03.normal.vo.Demo03StudentNormalPageReqVO;
import com.muang.ai.claw.module.infra.dal.dataobject.demo.demo03.Demo03StudentDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生 Mapper
 *
 */
@Mapper
public interface Demo03StudentNormalMapper extends BaseMapperX<Demo03StudentDO> {

    default PageResult<Demo03StudentDO> selectPage(Demo03StudentNormalPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<Demo03StudentDO>()
                .likeIfPresent(Demo03StudentDO::getName, reqVO.getName())
                .eqIfPresent(Demo03StudentDO::getSex, reqVO.getSex())
                .eqIfPresent(Demo03StudentDO::getDescription, reqVO.getDescription())
                .betweenIfPresent(Demo03StudentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(Demo03StudentDO::getId));
    }

}