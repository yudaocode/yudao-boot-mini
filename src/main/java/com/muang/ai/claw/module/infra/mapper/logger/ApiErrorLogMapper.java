package com.muang.ai.claw.module.infra.mapper.logger;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogPageForm;
import com.muang.ai.claw.module.infra.entity.logger.ApiErrorLogEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * API 错误日志 Mapper
 *
 */
@Mapper
public interface ApiErrorLogMapper extends BaseMapperX<ApiErrorLogEntity> {

    default PageResult<ApiErrorLogEntity> selectPage(ApiErrorLogPageForm reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ApiErrorLogEntity>()
                .eqIfPresent(ApiErrorLogEntity::getUserId, reqVO.getUserId())
                .eqIfPresent(ApiErrorLogEntity::getUserType, reqVO.getUserType())
                .eqIfPresent(ApiErrorLogEntity::getApplicationName, reqVO.getApplicationName())
                .likeIfPresent(ApiErrorLogEntity::getRequestUrl, reqVO.getRequestUrl())
                .betweenIfPresent(ApiErrorLogEntity::getExceptionTime, reqVO.getExceptionTime())
                .eqIfPresent(ApiErrorLogEntity::getProcessStatus, reqVO.getProcessStatus())
                .orderByDesc(ApiErrorLogEntity::getId)
        );
    }

    /**
     * 物理删除指定时间之前的日志
     *
     * @param createTime 最大时间
     * @param limit      删除条数，防止一次删除太多
     * @return 删除条数
     */
    @Delete("DELETE FROM infra_api_error_log WHERE create_time < #{createTime} LIMIT #{limit}")
    Integer deleteByCreateTimeLt(@Param("createTime") LocalDateTime createTime, @Param("limit") Integer limit);

}
