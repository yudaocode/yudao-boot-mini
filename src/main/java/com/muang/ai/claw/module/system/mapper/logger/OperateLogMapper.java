package com.muang.ai.claw.module.system.mapper.logger;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.system.api.logger.dto.OperateLogPageReqDTO;
import com.muang.ai.claw.module.system.controller.admin.logger.vo.operatelog.OperateLogPageForm;
import com.muang.ai.claw.module.system.entity.logger.OperateLogEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper extends BaseMapperX<OperateLogEntity> {

    default PageResult<OperateLogEntity> selectPage(OperateLogPageForm pageReqDTO) {
        return selectPage(pageReqDTO, new LambdaQueryWrapperX<OperateLogEntity>()
                .eqIfPresent(OperateLogEntity::getUserId, pageReqDTO.getUserId())
                .eqIfPresent(OperateLogEntity::getBizId, pageReqDTO.getBizId())
                .likeIfPresent(OperateLogEntity::getType, pageReqDTO.getType())
                .likeIfPresent(OperateLogEntity::getSubType, pageReqDTO.getSubType())
                .likeIfPresent(OperateLogEntity::getAction, pageReqDTO.getAction())
                .betweenIfPresent(OperateLogEntity::getCreateTime, pageReqDTO.getCreateTime())
                .orderByDesc(OperateLogEntity::getId));
    }

    default PageResult<OperateLogEntity> selectPage(OperateLogPageReqDTO pageReqDTO) {
        return selectPage(pageReqDTO, new LambdaQueryWrapperX<OperateLogEntity>()
                .eqIfPresent(OperateLogEntity::getType, pageReqDTO.getType())
                .eqIfPresent(OperateLogEntity::getBizId, pageReqDTO.getBizId())
                .eqIfPresent(OperateLogEntity::getUserId, pageReqDTO.getUserId())
                .orderByDesc(OperateLogEntity::getId));
    }

}
