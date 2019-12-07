package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysSupplier;

import java.util.List;

/**
 * 供应商 服务层
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
public interface ISysSupplierService
{
	/**
     * 查询供应商信息
     * 
     * @param supplierId 供应商ID
     * @return 供应商信息
     */
	public SysSupplier selectSupplierById(Long supplierId);
	
	/**
     * 查询供应商列表
     * 
     * @param supplier 供应商信息
     * @return 供应商集合
     */
	public List<SysSupplier> selectSupplierList(SysSupplier supplier);
	
	/**
     * 新增供应商
     * 
     * @param supplier 供应商信息
     * @return 结果
     */
	public int insertSupplier(SysSupplier supplier);
	
	/**
     * 修改供应商
     * 
     * @param supplier 供应商信息
     * @return 结果
     */
	public int updateSupplier(SysSupplier supplier);
		
	/**
     * 删除供应商信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSupplierByIds(String ids);
	
}
