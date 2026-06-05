package com.muang.ai.claw.module.infra.mapper.file;

import com.muang.ai.claw.module.infra.entity.file.FileContentEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileContentMapper extends BaseMapper<FileContentEntity> {

    default void deleteByConfigIdAndPath(Long configId, String path) {
        this.delete(new LambdaQueryWrapper<FileContentEntity>()
                .eq(FileContentEntity::getConfigId, configId)
                .eq(FileContentEntity::getPath, path));
    }

    default List<FileContentEntity> selectListByConfigIdAndPath(Long configId, String path) {
        return selectList(new LambdaQueryWrapper<FileContentEntity>()
                .eq(FileContentEntity::getConfigId, configId)
                .eq(FileContentEntity::getPath, path));
    }

}
