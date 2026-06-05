package com.muang.ai.claw.module.system.api.permission.dto;

import com.muang.ai.claw.constant.CommonStatusEnum;
import lombok.Data;

/**
 * 角色 Response DTO
 *
 */
@Data
public class RoleRespDTO {

    /**
     * 角色编号
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 角色状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}
