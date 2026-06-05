package com.muang.ai.claw.module.infra.convert.file;

import com.muang.ai.claw.module.infra.controller.admin.file.vo.config.FileConfigSaveForm;
import com.muang.ai.claw.module.infra.entity.file.FileConfigEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 文件配置 Convert
 *
 */
@Mapper
public interface FileConfigConvert {

    FileConfigConvert INSTANCE = Mappers.getMapper(FileConfigConvert.class);

    @Mapping(target = "config", ignore = true)
    FileConfigEntity convert(FileConfigSaveForm bean);

}
