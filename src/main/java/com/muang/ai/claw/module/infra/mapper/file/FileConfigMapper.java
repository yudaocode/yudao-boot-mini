package com.muang.ai.claw.module.infra.mapper.file;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.infra.controller.admin.file.vo.config.FileConfigPageForm;
import com.muang.ai.claw.module.infra.entity.file.FileConfigEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileConfigMapper extends BaseMapperX<FileConfigEntity> {

    default PageResult<FileConfigEntity> selectPage(FileConfigPageForm reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FileConfigEntity>()
                .likeIfPresent(FileConfigEntity::getName, reqVO.getName())
                .eqIfPresent(FileConfigEntity::getStorage, reqVO.getStorage())
                .betweenIfPresent(FileConfigEntity::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FileConfigEntity::getId));
    }

    default FileConfigEntity selectByMaster() {
        return selectOne(FileConfigEntity::getMaster, true);
    }

}
