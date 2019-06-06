package com.ruoyi.system.service.impl;

import com.ruoyi.system.mapper.SysParamMapper;
import com.ruoyi.system.service.ISysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class SysParamServiceImpl implements ISysParamService {
    @Autowired
    private SysParamMapper sysParamMapper;
    @Override
    public List<Map<String, String>> getHuaweiParam() {
        return sysParamMapper.getHuaweiParam();
    }
}
