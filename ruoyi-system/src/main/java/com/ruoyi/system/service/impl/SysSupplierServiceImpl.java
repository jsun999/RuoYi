package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.mapper.basemapper.BaseMapper;
import com.ruoyi.system.mapper.SysSupplierMapper;
import com.ruoyi.system.service.ISysSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
@Service
public class SysSupplierServiceImpl extends BaseServiceImpl<SysSupplier>  implements ISysSupplierService
{
	@Autowired
	private SysSupplierMapper supplierMapper;

	@Override
	public BaseMapper<SysSupplier> getBaseMapper() {
		return supplierMapper;
	}

	/**
     * 查询供应商信息
     * 
     * @param supplierId 供应商ID
     * @return 供应商信息
     */
    @Override
	public SysSupplier selectSupplierById(Long supplierId)
	{
	    return supplierMapper.selectSupplierById(supplierId);
	}
	
	/**
     * 查询供应商列表
     * 
     * @param supplier 供应商信息
     * @return 供应商集合
     */
	@Override
	public List<SysSupplier> selectSupplierList(SysSupplier supplier)
	{
	    return supplierMapper.selectSupplierList(supplier);
	}
	
    /**
     * 新增供应商
     * 
     * @param supplier 供应商信息
     * @return 结果
     */
	@Override
	public int insertSupplier(SysSupplier supplier)
	{
	    return supplierMapper.insertSupplier(supplier);
	}
	
	/**
     * 修改供应商
     * 
     * @param supplier 供应商信息
     * @return 结果
     */
	@Override
	public int updateSupplier(SysSupplier supplier)
	{
	    return supplierMapper.updateSupplier(supplier);
	}

	/**
     * 删除供应商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSupplierByIds(String ids)
	{
		return supplierMapper.deleteSupplierByIds(Convert.toStrArray(ids));
	}
	
}
