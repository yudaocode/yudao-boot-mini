package com.muang.ai.claw.module.system.service.logger;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.module.system.entity.logger.LoginLogEntity;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.module.system.api.logger.dto.LoginLogCreateReqDTO;
import com.muang.ai.claw.module.system.controller.admin.logger.vo.loginlog.LoginLogPageForm;
import com.muang.ai.claw.module.system.mapper.logger.LoginLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 登录日志 Service 实现
 */
@Service
@Validated
public class LoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    public LoginLogEntity getLoginLog(Long id) {
        return loginLogMapper.selectById(id);
    }

    public PageResult<LoginLogEntity> getLoginLogPage(LoginLogPageForm pageReqVO) {
        return loginLogMapper.selectPage(pageReqVO);
    }

    public void createLoginLog(LoginLogCreateReqDTO reqDTO) {
        LoginLogEntity loginLog = BeanUtils.toBean(reqDTO, LoginLogEntity.class);
        loginLogMapper.insert(loginLog);
    }

}
