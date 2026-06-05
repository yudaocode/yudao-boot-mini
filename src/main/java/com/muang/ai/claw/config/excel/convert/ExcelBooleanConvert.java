package com.muang.ai.claw.config.excel.convert;

import cn.idev.excel.converters.Converter;
import cn.idev.excel.enums.CellDataTypeEnum;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;

/**
 * Excel Boolean 转换器
 *
 * 将 Boolean 值格式化成「是 / 否」，用于替代原本基于数据字典（infra_boolean_string）的转换。
 */
public class ExcelBooleanConvert implements Converter<Boolean> {

    private static final String TRUE_LABEL = "是";
    private static final String FALSE_LABEL = "否";

    @Override
    public Class<?> supportJavaTypeKey() {
        return Boolean.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Boolean convertToJavaData(ReadCellData readCellData, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) {
        return TRUE_LABEL.equals(readCellData.getStringValue());
    }

    @Override
    public WriteCellData<String> convertToExcelData(Boolean object, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        if (object == null) {
            return new WriteCellData<>("");
        }
        return new WriteCellData<>(object ? TRUE_LABEL : FALSE_LABEL);
    }

}
