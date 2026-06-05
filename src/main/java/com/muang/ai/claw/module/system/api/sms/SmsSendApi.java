package com.muang.ai.claw.module.system.api.sms;

import com.muang.ai.claw.module.system.api.sms.dto.send.SmsSendSingleToUserReqDTO;
import com.muang.ai.claw.module.system.service.sms.SmsSendService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 短信发送 API 接口
 *
 */
@Service
@Validated
public class SmsSendApi {

    @Resource
    private SmsSendService smsSendService;

    public Long sendSingleSmsToAdmin(SmsSendSingleToUserReqDTO reqDTO) {
        return smsSendService.sendSingleSmsToAdmin(reqDTO.getMobile(), reqDTO.getUserId(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

    public Long sendSingleSmsToMember(SmsSendSingleToUserReqDTO reqDTO) {
        return smsSendService.sendSingleSmsToMember(reqDTO.getMobile(), reqDTO.getUserId(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

}
