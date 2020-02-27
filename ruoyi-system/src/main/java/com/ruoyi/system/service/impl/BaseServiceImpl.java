package com.ruoyi.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.system.basemapper.BaseMapper;
import com.ruoyi.system.service.BaseService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    public abstract BaseMapper<T> getBaseMapper();

    @Override
    public void save(T model)  throws BaseException {
        this.getBaseMapper().insertSelective(model);
    }

    @Override
    public void update(T model) throws BaseException  {
        this.getBaseMapper().updateByPrimaryKeySelective(model);
    }

    @Override
    public void delete(Integer id)  throws BaseException  {
        this.getBaseMapper().deleteByPrimaryKey(id);
    }

    @Override
    public T getById(Integer id)  throws BaseException  {
        return this.getBaseMapper().selectByPrimaryKey(id);
    }

    @Override
    public List<T> getList()  throws BaseException  {
        return this.getBaseMapper().selectAll();
    }

    @Override
    public List<T> getPyPage(Integer pageNum, Integer pageSize)   throws BaseException {
        PageHelper.startPage(pageNum,pageSize);
        return this.getList();
    }

    @Override
    public List<T> getPyExamplePage(Example example, Integer pageNum, Integer pageSize)  throws BaseException {
        PageHelper.startPage(pageNum,pageSize);
        return this.getBaseMapper().selectByExample(example);
    }
}
