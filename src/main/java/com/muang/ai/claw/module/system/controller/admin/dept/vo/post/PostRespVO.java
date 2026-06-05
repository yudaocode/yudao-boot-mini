package com.muang.ai.claw.module.system.controller.admin.dept.vo.post;

import com.muang.ai.claw.config.excel.convert.ExcelEnumConvert;
import com.muang.ai.claw.config.excel.annotations.ExcelEnumFormat;
import com.muang.ai.claw.constant.CommonStatusEnum;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 岗位信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PostRespVO {

    @Schema(description = "岗位序号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @ExcelProperty("岗位序号")
    private Long id;

    @Schema(description = "岗位名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "小土豆")
    @ExcelProperty("岗位名称")
    private String name;

    @Schema(description = "岗位编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    @ExcelProperty("岗位编码")
    private String code;

    @Schema(description = "显示顺序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @ExcelProperty("岗位排序")
    private Integer sort;

    @Schema(description = "状态，参见 CommonStatusEnum 枚举类", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty(value = "状态", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(CommonStatusEnum.class)
    private Integer status;

    @Schema(description = "备注", example = "快乐的备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
