package com.muang.ai.claw.module.system.api.logger;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.common.biz.system.logger.dto.OperateLogCreateReqDTO;
import com.muang.ai.claw.module.system.api.logger.dto.OperateLogPageReqDTO;
import com.muang.ai.claw.module.system.api.logger.dto.OperateLogRespDTO;
import com.muang.ai.claw.module.system.dal.dataobject.logger.OperateLogDO;
import com.muang.ai.claw.module.system.service.logger.OperateLogService;
import com.fhs.core.trans.anno.TransMethodResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.muang.ai.claw.common.biz.system.logger.OperateLogCommonApi;

/**
 * 操作日志 API 实现类
 *
 */
@Service
@Validated
public class OperateLogApi implements OperateLogCommonApi {

    @Resource
    private OperateLogService operateLogService;

    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        operateLogService.createOperateLog(createReqDTO);
    }

    @TransMethodResult
    public PageResult<OperateLogRespDTO> getOperateLogPage(OperateLogPageReqDTO pageReqDTO) {
        PageResult<OperateLogDO> operateLogPage = operateLogService.getOperateLogPage(pageReqDTO);
        return BeanUtils.toBean(operateLogPage, OperateLogRespDTO.class);
    }

}
