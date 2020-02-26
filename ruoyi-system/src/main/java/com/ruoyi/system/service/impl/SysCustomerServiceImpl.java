package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.SysCustomer;
import com.ruoyi.system.mapper.basemapper.BaseMapper;
import com.ruoyi.system.mapper.SysCustomerMapper;
import com.ruoyi.system.service.ISysCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
@Service
public class SysCustomerServiceImpl extends BaseServiceImpl<SysCustomer>  implements ISysCustomerService
{
	@Autowired
	private SysCustomerMapper customerMapper;

	@Override
	public BaseMapper<SysCustomer> getBaseMapper() {
		return customerMapper;
	}

	/**
     * 查询客户信息
     * 
     * @param customerId 客户ID
     * @return 客户信息
     */
    @Override
	public SysCustomer selectCustomerById(Long customerId)
	{
	    return customerMapper.selectCustomerById(customerId);
	}
	
	/**
     * 查询客户列表
     * 
     * @param customer 客户信息
     * @return 客户集合
     */
	@Override
	public List<SysCustomer> selectCustomerList(SysCustomer customer)
	{
	    return customerMapper.selectCustomerList(customer);
	}
	
    /**
     * 新增客户
     * 
     * @param customer 客户信息
     * @return 结果
     */
	@Override
	public int insertCustomer(SysCustomer customer)
	{
	    return customerMapper.insertCustomer(customer);
	}
	
	/**
     * 修改客户
     * 
     * @param customer 客户信息
     * @return 结果
     */
	@Override
	public int updateCustomer(SysCustomer customer)
	{
	    return customerMapper.updateCustomer(customer);
	}

	/**
     * 删除客户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCustomerByIds(String ids)
	{
		return customerMapper.deleteCustomerByIds(Convert.toStrArray(ids));
	}
	
}
