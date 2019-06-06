package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysParam;

import java.util.List;
import java.util.Map;

public interface SysParamMapper extends BaseMapper<SysParam>{

    List<Map<String, String>> getHuaweiParam();
}
