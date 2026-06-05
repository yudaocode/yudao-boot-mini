package com.muang.ai.claw.module.system.service.logger;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.module.system.entity.logger.OperateLogEntity;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.module.system.api.logger.dto.OperateLogCreateReqDTO;
import com.muang.ai.claw.module.system.api.logger.dto.OperateLogPageReqDTO;
import com.muang.ai.claw.module.system.controller.admin.logger.vo.operatelog.OperateLogPageForm;
import com.muang.ai.claw.module.system.mapper.logger.OperateLogMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 操作日志 Service 实现类
 *
 */
@Service
@Validated
@Slf4j
public class OperateLogService {

    @Resource
    private OperateLogMapper operateLogMapper;

    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        OperateLogEntity log = BeanUtils.toBean(createReqDTO, OperateLogEntity.class);
        operateLogMapper.insert(log);
    }

    public OperateLogEntity getOperateLog(Long id) {
        return operateLogMapper.selectById(id);
    }

    public PageResult<OperateLogEntity> getOperateLogPage(OperateLogPageForm pageReqVO) {
        return operateLogMapper.selectPage(pageReqVO);
    }

    public PageResult<OperateLogEntity> getOperateLogPage(OperateLogPageReqDTO pageReqDTO) {
        return operateLogMapper.selectPage(pageReqDTO);
    }

}
