package com.ruoyi.system.mapper;

import com.ruoyi.system.basemapper.BaseMapper;
import com.ruoyi.system.domain.SysCustomer;

import java.util.List;

/**
 * 客户 数据层
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
public interface SysCustomerMapper extends BaseMapper<SysCustomer>
{
	/**
     * 查询客户信息
     * 
     * @param customerId 客户ID
     * @return 客户信息
     */
	public SysCustomer selectCustomerById(Long customerId);
	
	/**
     * 查询客户列表
     * 
     * @param customer 客户信息
     * @return 客户集合
     */
	public List<SysCustomer> selectCustomerList(SysCustomer customer);
	
	/**
     * 新增客户
     * 
     * @param customer 客户信息
     * @return 结果
     */
	public int insertCustomer(SysCustomer customer);
	
	/**
     * 修改客户
     * 
     * @param customer 客户信息
     * @return 结果
     */
	public int updateCustomer(SysCustomer customer);
	
	/**
     * 删除客户
     * 
     * @param customerId 客户ID
     * @return 结果
     */
	public int deleteCustomerById(Long customerId);
	
	/**
     * 批量删除客户
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteCustomerByIds(String[] customerIds);
	
}