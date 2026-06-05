package com.muang.ai.claw.module.system.api.sms;

import com.muang.ai.claw.module.system.api.sms.dto.code.SmsCodeValidateReqDTO;
import com.muang.ai.claw.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import com.muang.ai.claw.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import com.muang.ai.claw.module.system.service.sms.SmsCodeService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.annotation.Resource;
import com.muang.ai.claw.common.exception.ServiceException;
import jakarta.validation.Valid;

/**
 * 短信验证码 API 实现类
 *
 */
@Service
@Validated
public class SmsCodeApi {

    @Resource
    private SmsCodeService smsCodeService;

    public void sendSmsCode(SmsCodeSendReqDTO reqDTO) {
        smsCodeService.sendSmsCode(reqDTO);
    }

    public void useSmsCode(SmsCodeUseReqDTO reqDTO) {
        smsCodeService.useSmsCode(reqDTO);
    }

    public void validateSmsCode(SmsCodeValidateReqDTO reqDTO) {
        smsCodeService.validateSmsCode(reqDTO);
    }

}
