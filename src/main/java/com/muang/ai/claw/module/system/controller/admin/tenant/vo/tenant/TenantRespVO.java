package com.muang.ai.claw.module.system.controller.admin.tenant.vo.tenant;

import com.muang.ai.claw.config.excel.convert.ExcelEnumConvert;
import com.muang.ai.claw.config.excel.annotations.ExcelEnumFormat;
import com.muang.ai.claw.constant.CommonStatusEnum;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 租户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TenantRespVO {

    @Schema(description = "租户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @ExcelProperty("租户编号")
    private Long id;

    @Schema(description = "租户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道")
    @ExcelProperty("租户名")
    private String name;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("联系人")
    private String contactName;

    @Schema(description = "联系手机", example = "15601691300")
    @ExcelProperty("联系手机")
    private String contactMobile;

    @Schema(description = "租户状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty(value = "状态", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(CommonStatusEnum.class)
    private Integer status;

    @Schema(description = "绑定域名数组", example = "https://www.iocoder.cn")
    private List<String> websites;

    @Schema(description = "租户套餐编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long packageId;

    @Schema(description = "过期时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime expireTime;

    @Schema(description = "账号数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Integer accountCount;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
