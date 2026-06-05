package com.muang.ai.claw.module.system.api.dict;

import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.common.biz.system.dict.dto.DictDataRespDTO;
import com.muang.ai.claw.module.system.dal.dataobject.dict.DictDataDO;
import com.muang.ai.claw.module.system.service.dict.DictDataService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import com.muang.ai.claw.common.biz.system.dict.DictDataCommonApi;

/**
 * 字典数据 API 实现类
 *
 */
@Service
public class DictDataApi {

    @Resource
    private DictDataService dictDataService;

    public void validateDictDataList(String dictType, Collection<String> values) {
        dictDataService.validateDictDataList(dictType, values);
    }

    public List<DictDataRespDTO> getDictDataList(String dictType) {
        List<DictDataDO> list = dictDataService.getDictDataListByDictType(dictType);
        return BeanUtils.toBean(list, DictDataRespDTO.class);
    }

}
