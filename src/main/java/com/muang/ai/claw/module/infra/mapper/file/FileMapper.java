package com.muang.ai.claw.module.infra.mapper.file;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.infra.controller.admin.file.vo.file.FilePageForm;
import com.muang.ai.claw.module.infra.entity.file.FileEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件操作 Mapper
 *
 */
@Mapper
public interface FileMapper extends BaseMapperX<FileEntity> {

    default PageResult<FileEntity> selectPage(FilePageForm reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FileEntity>()
                .likeIfPresent(FileEntity::getPath, reqVO.getPath())
                .likeIfPresent(FileEntity::getType, reqVO.getType())
                .betweenIfPresent(FileEntity::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FileEntity::getId));
    }

}
