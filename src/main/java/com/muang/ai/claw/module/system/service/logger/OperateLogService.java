package com.muang.ai.claw.module.system.service.logger;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.common.biz.system.logger.dto.OperateLogCreateReqDTO;
import com.muang.ai.claw.module.system.api.logger.dto.OperateLogPageReqDTO;
import com.muang.ai.claw.module.system.controller.admin.logger.vo.operatelog.OperateLogPageReqVO;
import com.muang.ai.claw.module.system.dal.dataobject.logger.OperateLogDO;
import com.muang.ai.claw.module.system.dal.mysql.logger.OperateLogMapper;
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
        OperateLogDO log = BeanUtils.toBean(createReqDTO, OperateLogDO.class);
        operateLogMapper.insert(log);
    }

    public OperateLogDO getOperateLog(Long id) {
        return operateLogMapper.selectById(id);
    }

    public PageResult<OperateLogDO> getOperateLogPage(OperateLogPageReqVO pageReqVO) {
        return operateLogMapper.selectPage(pageReqVO);
    }

    public PageResult<OperateLogDO> getOperateLogPage(OperateLogPageReqDTO pageReqDTO) {
        return operateLogMapper.selectPage(pageReqDTO);
    }

}
