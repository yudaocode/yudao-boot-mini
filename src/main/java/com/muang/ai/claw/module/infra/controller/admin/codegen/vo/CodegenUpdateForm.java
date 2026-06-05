package com.muang.ai.claw.module.infra.controller.admin.codegen.vo;

import com.muang.ai.claw.module.infra.controller.admin.codegen.vo.column.CodegenColumnSaveForm;
import com.muang.ai.claw.module.infra.controller.admin.codegen.vo.table.CodegenTableSaveForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 代码生成表和字段的修改 Request VO")
@Data
public class CodegenUpdateForm {

    @Valid // 校验内嵌的字段
    @NotNull(message = "表定义不能为空")
    private CodegenTableSaveForm table;

    @Valid // 校验内嵌的字段
    @NotNull(message = "字段定义不能为空")
    private List<CodegenColumnSaveForm> columns;

}
