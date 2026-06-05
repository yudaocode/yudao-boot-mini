package com.muang.ai.claw.module.infra.convert.config;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.module.infra.controller.admin.config.vo.ConfigRespVO;
import com.muang.ai.claw.module.infra.controller.admin.config.vo.ConfigSaveForm;
import com.muang.ai.claw.module.infra.entity.config.ConfigEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ConfigConvert {

    ConfigConvert INSTANCE = Mappers.getMapper(ConfigConvert.class);

    PageResult<ConfigRespVO> convertPage(PageResult<ConfigEntity> page);

    List<ConfigRespVO> convertList(List<ConfigEntity> list);

    @Mapping(source = "configKey", target = "key")
    ConfigRespVO convert(ConfigEntity bean);

    @Mapping(source = "key", target = "configKey")
    ConfigEntity convert(ConfigSaveForm bean);

}
