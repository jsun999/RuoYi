package com.ruoyi.system.service;

import com.ruoyi.common.exception.base.BaseException;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface BaseService<T> {


    void save(T model) throws BaseException;

    void update(T model) throws BaseException;

    void delete(Integer id) throws BaseException;

    T getById(Integer id) throws BaseException;

    List<T> getList() throws BaseException;

    List<T> getPyPage(Integer pageNum, Integer pageSize) throws BaseException;

    List<T> getPyExamplePage(Example example, Integer pageNum, Integer pageSize) throws BaseException;
}
