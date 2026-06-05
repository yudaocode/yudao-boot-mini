package com.muang.ai.claw.module.system.api.logger;

import com.muang.ai.claw.module.system.api.logger.dto.LoginLogCreateReqDTO;
import com.muang.ai.claw.module.system.service.logger.LoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 登录日志的 API 实现类
 *
 */
@Service
@Validated
public class LoginLogApi {

    @Resource
    private LoginLogService loginLogService;

    public void createLoginLog(LoginLogCreateReqDTO reqDTO) {
        loginLogService.createLoginLog(reqDTO);
    }

}
