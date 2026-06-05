package com.muang.ai.claw.module.infra.api.logger;

import com.muang.ai.claw.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import com.muang.ai.claw.module.infra.service.logger.ApiErrorLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * API 访问日志的 API 接口
 *
 */
@Service
@Validated
public class ApiErrorLogApi {

    @Resource
    private ApiErrorLogService apiErrorLogService;
    public void createApiErrorLog(ApiErrorLogCreateReqDTO createDTO) {
        apiErrorLogService.createApiErrorLog(createDTO);
    }

    @org.springframework.scheduling.annotation.Async
    public void createApiErrorLogAsync(ApiErrorLogCreateReqDTO createDTO) {
        createApiErrorLog(createDTO);
    }

}
