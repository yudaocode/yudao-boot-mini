package com.muang.ai.claw.config.excel.convert;

import cn.hutool.core.convert.Convert;
import com.muang.ai.claw.config.excel.annotations.ExcelEnumFormat;
import cn.idev.excel.converters.Converter;
import cn.idev.excel.enums.CellDataTypeEnum;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Excel 枚举转换器
 *
 * 基于 {@link ExcelEnumFormat} 指定的枚举类，将枚举的值与中文标签（getName）互相转换。
 * 用于替代原本基于数据字典的 DictConvert。
 */
@Slf4j
public class ExcelEnumConvert implements Converter<Object> {

    /**
     * 候选的"值" getter 名称（不同枚举的值字段命名不一致）
     */
    private static final String[] VALUE_GETTERS = {
            "getValue", "getStatus", "getType", "getResult", "getScope", "getSex", "getCode"};

    /**
     * 缓存：枚举类 -> （值 -> 标签）映射
     */
    private static final Map<Class<?>, Map<Object, String>> CACHE = new ConcurrentHashMap<>();

    @Override
    public Class<?> supportJavaTypeKey() {
        throw new UnsupportedOperationException("暂不支持，也不需要");
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        throw new UnsupportedOperationException("暂不支持，也不需要");
    }

    @Override
    public Object convertToJavaData(ReadCellData readCellData, ExcelContentProperty contentProperty,
                                    GlobalConfiguration globalConfiguration) {
        Class<?> enumClass = getEnumClass(contentProperty);
        String label = readCellData.getStringValue();
        for (Map.Entry<Object, String> entry : getValueLabelMap(enumClass).entrySet()) {
            if (entry.getValue().equals(label)) {
                return Convert.convert(contentProperty.getField().getType(), entry.getKey());
            }
        }
        log.error("[convertToJavaData][enum({}) 解析不掉 label({})]", enumClass.getName(), label);
        return null;
    }

    @Override
    public WriteCellData<String> convertToExcelData(Object object, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        if (object == null) {
            return new WriteCellData<>("");
        }
        Class<?> enumClass = getEnumClass(contentProperty);
        Map<Object, String> valueLabelMap = getValueLabelMap(enumClass);
        String label = valueLabelMap.get(object);
        if (label == null) {
            // 兼容值类型差异（例如 Long 与 Integer），退化为字符串比较
            for (Map.Entry<Object, String> entry : valueLabelMap.entrySet()) {
                if (String.valueOf(entry.getKey()).equals(String.valueOf(object))) {
                    label = entry.getValue();
                    break;
                }
            }
        }
        if (label == null) {
            log.error("[convertToExcelData][enum({}) 转换不了 value({})]", enumClass.getName(), object);
            return new WriteCellData<>("");
        }
        return new WriteCellData<>(label);
    }

    private static Class<?> getEnumClass(ExcelContentProperty contentProperty) {
        return contentProperty.getField().getAnnotation(ExcelEnumFormat.class).value();
    }

    private static Map<Object, String> getValueLabelMap(Class<?> enumClass) {
        return CACHE.computeIfAbsent(enumClass, ExcelEnumConvert::buildValueLabelMap);
    }

    private static Map<Object, String> buildValueLabelMap(Class<?> enumClass) {
        Map<Object, String> map = new LinkedHashMap<>();
        try {
            Method valueGetter = findValueGetter(enumClass);
            Method nameGetter = enumClass.getMethod("getName");
            for (Object constant : enumClass.getEnumConstants()) {
                Object value = valueGetter.invoke(constant);
                String label = (String) nameGetter.invoke(constant);
                map.put(value, label);
            }
        } catch (Exception e) {
            throw new IllegalStateException("解析枚举(" + enumClass.getName() + ")失败", e);
        }
        return map;
    }

    private static Method findValueGetter(Class<?> enumClass) throws NoSuchMethodException {
        for (String name : VALUE_GETTERS) {
            try {
                return enumClass.getMethod(name);
            } catch (NoSuchMethodException ignored) {
                // 尝试下一个候选 getter
            }
        }
        throw new NoSuchMethodException("枚举(" + enumClass.getName() + ")找不到值的 getter");
    }

}
