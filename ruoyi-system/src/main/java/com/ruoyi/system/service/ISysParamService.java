package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysParam;
import com.ruoyi.system.domain.SysUser;

import java.util.List;
import java.util.Map;

public interface ISysParamService extends BaseService<SysParam> {
    /**
     * 查询华为参数
     */
    public List<Map<String,String>> getHuaweiParam();
}
