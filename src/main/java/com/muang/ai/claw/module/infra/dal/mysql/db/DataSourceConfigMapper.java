package com.muang.ai.claw.module.infra.dal.mysql.db;

import com.muang.ai.claw.common.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.module.infra.dal.dataobject.db.DataSourceConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 Mapper
 *
 */
@Mapper
public interface DataSourceConfigMapper extends BaseMapperX<DataSourceConfigDO> {
}
