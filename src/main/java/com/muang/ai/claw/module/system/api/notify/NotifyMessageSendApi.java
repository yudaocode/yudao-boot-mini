package com.muang.ai.claw.module.system.api.notify;

import com.muang.ai.claw.module.system.api.notify.dto.NotifySendSingleToUserReqDTO;
import com.muang.ai.claw.module.system.service.notify.NotifySendService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 站内信发送 API 实现类
 *
 * @author xrcoder
 */
@Service
public class NotifyMessageSendApi {

    @Resource
    private NotifySendService notifySendService;

    public Long sendSingleMessageToAdmin(NotifySendSingleToUserReqDTO reqDTO) {
        return notifySendService.sendSingleNotifyToAdmin(reqDTO.getUserId(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

    public Long sendSingleMessageToMember(NotifySendSingleToUserReqDTO reqDTO) {
        return notifySendService.sendSingleNotifyToMember(reqDTO.getUserId(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

}
