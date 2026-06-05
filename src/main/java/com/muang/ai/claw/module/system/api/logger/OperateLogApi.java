package com.muang.ai.claw.module.system.api.logger;

import com.muang.ai.claw.framework.common.biz.system.logger.OperateLogCommonApi;
import com.muang.ai.claw.framework.common.pojo.PageResult;
import com.muang.ai.claw.module.system.api.logger.dto.OperateLogPageReqDTO;
import com.muang.ai.claw.module.system.api.logger.dto.OperateLogRespDTO;

/**
 * 操作日志 API 接口
 *
 */
public interface OperateLogApi extends OperateLogCommonApi {

    /**
     * 获取指定模块的指定数据的操作日志分页
     *
     * @param pageReqDTO 请求
     * @return 操作日志分页
     */
    PageResult<OperateLogRespDTO> getOperateLogPage(OperateLogPageReqDTO pageReqDTO);

}
