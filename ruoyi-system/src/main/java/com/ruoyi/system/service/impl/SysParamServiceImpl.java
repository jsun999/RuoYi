package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysParam;
import com.ruoyi.system.mapper.basemapper.BaseMapper;
import com.ruoyi.system.mapper.SysParamMapper;
import com.ruoyi.system.service.ISysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class SysParamServiceImpl extends BaseServiceImpl<SysParam> implements ISysParamService {
    @Autowired
    private SysParamMapper sysParamMapper;

    @Override
    public BaseMapper<SysParam> getBaseMapper() {
        return sysParamMapper;
    }

    @Override
    public List<Map<String, String>> getHuaweiParam() {
        return sysParamMapper.getHuaweiParam();
    }
}
