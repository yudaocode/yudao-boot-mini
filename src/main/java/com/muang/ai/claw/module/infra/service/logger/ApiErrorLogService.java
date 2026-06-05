package com.muang.ai.claw.module.infra.service.logger;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.tenant.core.context.TenantContextHolder;
import com.muang.ai.claw.config.tenant.core.util.TenantUtils;
import com.muang.ai.claw.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import com.muang.ai.claw.module.infra.constant.logger.ApiErrorLogProcessStatusEnum;
import com.muang.ai.claw.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogPageForm;
import com.muang.ai.claw.module.infra.dal.dataobject.logger.ApiErrorLogDO;
import com.muang.ai.claw.module.infra.dal.mysql.logger.ApiErrorLogMapper;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.util.string.StrUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

import static com.muang.ai.claw.common.exception.util.ServiceExceptionUtil.exception;
import static com.muang.ai.claw.module.infra.constant.ErrorCodeConstants.API_ERROR_LOG_NOT_FOUND;
import static com.muang.ai.claw.module.infra.constant.ErrorCodeConstants.API_ERROR_LOG_PROCESSED;
import static com.muang.ai.claw.module.infra.dal.dataobject.logger.ApiErrorLogDO.REQUEST_PARAMS_MAX_LENGTH;

/**
 * API 错误日志 Service 实现类
 *
 */
@Service
@Validated
@Slf4j
public class ApiErrorLogService {

    @Resource
    private ApiErrorLogMapper apiErrorLogMapper;

    public void createApiErrorLog(ApiErrorLogCreateReqDTO createDTO) {
        ApiErrorLogDO apiErrorLog = BeanUtils.toBean(createDTO, ApiErrorLogDO.class)
                .setProcessStatus(ApiErrorLogProcessStatusEnum.INIT.getStatus());
        apiErrorLog.setRequestParams(StrUtils.maxLength(apiErrorLog.getRequestParams(), REQUEST_PARAMS_MAX_LENGTH));
        try {
            if (TenantContextHolder.getTenantId() != null) {
                apiErrorLogMapper.insert(apiErrorLog);
            } else {
                // 极端情况下，上下文中没有租户时，此时忽略租户上下文，避免插入失败！
                TenantUtils.executeIgnore(() -> apiErrorLogMapper.insert(apiErrorLog));
            }
        } catch (Exception ex) {
            // 兜底处理，目前只有 yudao-cloud 会发生：https://gitee.com/yudaocode/yudao-cloud-mini/issues/IC1O0A
            log.error("[createApiErrorLog][记录时({}) 发生异常]", createDTO, ex);
        }
    }

    public PageResult<ApiErrorLogDO> getApiErrorLogPage(ApiErrorLogPageForm pageForm) {
        return apiErrorLogMapper.selectPage(pageForm);
    }

    public ApiErrorLogDO getApiErrorLog(Long id) {
        return apiErrorLogMapper.selectById(id);
    }

    public void updateApiErrorLogProcess(Long id, Integer processStatus, Long processUserId) {
        ApiErrorLogDO errorLog = apiErrorLogMapper.selectById(id);
        if (errorLog == null) {
            throw exception(API_ERROR_LOG_NOT_FOUND);
        }
        if (!ApiErrorLogProcessStatusEnum.INIT.getStatus().equals(errorLog.getProcessStatus())) {
            throw exception(API_ERROR_LOG_PROCESSED);
        }
        // 标记处理
        apiErrorLogMapper.updateById(ApiErrorLogDO.builder().id(id).processStatus(processStatus)
                .processUserId(processUserId).processTime(LocalDateTime.now()).build());
    }

    @SuppressWarnings("DuplicatedCode")
    public Integer cleanErrorLog(Integer exceedDay, Integer deleteLimit) {
        int count = 0;
        LocalDateTime expireDate = LocalDateTime.now().minusDays(exceedDay);
        // 循环删除，直到没有满足条件的数据
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            int deleteCount = apiErrorLogMapper.deleteByCreateTimeLt(expireDate, deleteLimit);
            count += deleteCount;
            // 达到删除预期条数，说明到底了
            if (deleteCount < deleteLimit) {
                break;
            }
        }
        return count;
    }

}
