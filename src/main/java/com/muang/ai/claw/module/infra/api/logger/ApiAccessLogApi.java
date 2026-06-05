package com.muang.ai.claw.module.infra.api.logger;

import com.muang.ai.claw.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import com.muang.ai.claw.module.infra.service.logger.ApiAccessLogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * API 访问日志的 API 实现类
 *
 */
@Service
@Validated
public class ApiAccessLogApi {

    @Resource
    private ApiAccessLogService apiAccessLogService;
    public void createApiAccessLog(ApiAccessLogCreateReqDTO createDTO) {
        apiAccessLogService.createApiAccessLog(createDTO);
    }

    @org.springframework.scheduling.annotation.Async
    public void createApiAccessLogAsync(ApiAccessLogCreateReqDTO createDTO) {
        createApiAccessLog(createDTO);
    }

}
