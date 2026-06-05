package com.muang.ai.claw.module.infra.service.logger;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.tenant.core.context.TenantContextHolder;
import com.muang.ai.claw.config.tenant.core.util.TenantUtils;
import com.muang.ai.claw.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import com.muang.ai.claw.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogPageForm;
import com.muang.ai.claw.module.infra.entity.logger.ApiAccessLogEntity;
import com.muang.ai.claw.module.infra.mapper.logger.ApiAccessLogMapper;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.util.string.StrUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

import static com.muang.ai.claw.module.infra.entity.logger.ApiAccessLogEntity.REQUEST_PARAMS_MAX_LENGTH;
import static com.muang.ai.claw.module.infra.entity.logger.ApiAccessLogEntity.RESULT_MSG_MAX_LENGTH;

/**
 * API 访问日志 Service 实现类
 *
 */
@Slf4j
@Service
@Validated
public class ApiAccessLogService {

    @Resource
    private ApiAccessLogMapper apiAccessLogMapper;

    public void createApiAccessLog(ApiAccessLogCreateReqDTO createDTO) {
        ApiAccessLogEntity apiAccessLog = BeanUtils.toBean(createDTO, ApiAccessLogEntity.class);
        apiAccessLog.setRequestParams(StrUtils.maxLength(apiAccessLog.getRequestParams(), REQUEST_PARAMS_MAX_LENGTH));
        apiAccessLog.setResultMsg(StrUtils.maxLength(apiAccessLog.getResultMsg(), RESULT_MSG_MAX_LENGTH));
        if (TenantContextHolder.getTenantId() != null) {
            apiAccessLogMapper.insert(apiAccessLog);
        } else {
            // 极端情况下，上下文中没有租户时，此时忽略租户上下文，避免插入失败！
            TenantUtils.executeIgnore(() -> apiAccessLogMapper.insert(apiAccessLog));
        }
    }

    public ApiAccessLogEntity getApiAccessLog(Long id) {
        return apiAccessLogMapper.selectById(id);
    }

    public PageResult<ApiAccessLogEntity> getApiAccessLogPage(ApiAccessLogPageForm pageReqVO) {
        return apiAccessLogMapper.selectPage(pageReqVO);
    }

    @SuppressWarnings("DuplicatedCode")
    public Integer cleanAccessLog(Integer exceedDay, Integer deleteLimit) {
        int count = 0;
        LocalDateTime expireDate = LocalDateTime.now().minusDays(exceedDay);
        // 循环删除，直到没有满足条件的数据
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            int deleteCount = apiAccessLogMapper.deleteByCreateTimeLt(expireDate, deleteLimit);
            count += deleteCount;
            // 达到删除预期条数，说明到底了
            if (deleteCount < deleteLimit) {
                break;
            }
        }
        return count;
    }

}
