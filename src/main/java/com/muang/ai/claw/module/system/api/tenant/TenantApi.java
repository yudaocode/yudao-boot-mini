package com.muang.ai.claw.module.system.api.tenant;

import com.muang.ai.claw.module.system.service.tenant.TenantService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 多租户的 API 实现类
 *
 */
@Service
public class TenantApi {

    @Resource
    private TenantService tenantService;
    public List<Long> getTenantIdList() {
        return tenantService.getTenantIdList();
    }
    public void validateTenant(Long id) {
        tenantService.validTenant(id);
    }

}
