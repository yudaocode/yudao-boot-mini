package com.muang.ai.claw.config.tenant.core.db;

import com.muang.ai.claw.common.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 拓展多租户的 BaseDO 基类
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class TenantBaseEntity extends BaseEntity {

    /**
     * 多租户编号
     */
    private Long tenantId;

}
