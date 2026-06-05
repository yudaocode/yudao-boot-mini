package com.muang.ai.claw.module.infra.dal.mysql.job;

import com.muang.ai.claw.framework.common.pojo.PageResult;
import com.muang.ai.claw.framework.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.infra.controller.admin.job.vo.job.JobPageReqVO;
import com.muang.ai.claw.module.infra.dal.dataobject.job.JobDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务 Mapper
 *
 */
@Mapper
public interface JobMapper extends BaseMapperX<JobDO> {

    default JobDO selectByHandlerName(String handlerName) {
        return selectOne(JobDO::getHandlerName, handlerName);
    }

    default PageResult<JobDO> selectPage(JobPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JobDO>()
                .likeIfPresent(JobDO::getName, reqVO.getName())
                .eqIfPresent(JobDO::getStatus, reqVO.getStatus())
                .likeIfPresent(JobDO::getHandlerName, reqVO.getHandlerName())
                .orderByDesc(JobDO::getId));
    }

}
