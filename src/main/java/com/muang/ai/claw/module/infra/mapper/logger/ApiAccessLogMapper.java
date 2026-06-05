package com.muang.ai.claw.module.infra.mapper.logger;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogPageForm;
import com.muang.ai.claw.module.infra.entity.logger.ApiAccessLogEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * API 访问日志 Mapper
 *
 */
@Mapper
public interface ApiAccessLogMapper extends BaseMapperX<ApiAccessLogEntity> {

    default PageResult<ApiAccessLogEntity> selectPage(ApiAccessLogPageForm reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ApiAccessLogEntity>()
                .eqIfPresent(ApiAccessLogEntity::getUserId, reqVO.getUserId())
                .eqIfPresent(ApiAccessLogEntity::getUserType, reqVO.getUserType())
                .eqIfPresent(ApiAccessLogEntity::getApplicationName, reqVO.getApplicationName())
                .likeIfPresent(ApiAccessLogEntity::getRequestUrl, reqVO.getRequestUrl())
                .betweenIfPresent(ApiAccessLogEntity::getBeginTime, reqVO.getBeginTime())
                .geIfPresent(ApiAccessLogEntity::getDuration, reqVO.getDuration())
                .eqIfPresent(ApiAccessLogEntity::getResultCode, reqVO.getResultCode())
                .orderByDesc(ApiAccessLogEntity::getId)
        );
    }

    /**
     * 物理删除指定时间之前的日志
     *
     * @param createTime 最大时间
     * @param limit      删除条数，防止一次删除太多
     * @return 删除条数
     */
    @Delete("DELETE FROM infra_api_access_log WHERE create_time < #{createTime} LIMIT #{limit}")
    Integer deleteByCreateTimeLt(@Param("createTime") LocalDateTime createTime, @Param("limit") Integer limit);

}
