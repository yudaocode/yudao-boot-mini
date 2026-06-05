package com.muang.ai.claw.module.system.controller.admin.user.vo.user;

import com.muang.ai.claw.config.excel.convert.ExcelEnumConvert;
import com.muang.ai.claw.config.excel.annotations.ExcelEnumFormat;
import com.muang.ai.claw.constant.CommonStatusEnum;
import com.muang.ai.claw.module.system.constant.common.SexEnum;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户 Excel 导入 VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserImportExcelVO {

    @ExcelProperty("登录名称")
    private String username;

    @ExcelProperty("用户名称")
    private String nickname;

    @ExcelProperty("部门编号")
    private Long deptId;

    @ExcelProperty("用户邮箱")
    private String email;

    @ExcelProperty("手机号码")
    private String mobile;

    @ExcelProperty(value = "用户性别", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(SexEnum.class)
    private Integer sex;

    @ExcelProperty(value = "账号状态", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(CommonStatusEnum.class)
    private Integer status;

}
