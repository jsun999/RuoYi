package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.basemapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysProductionMapper;
import com.ruoyi.system.domain.SysProduction;
import com.ruoyi.system.service.ISysProductionService;
import com.ruoyi.common.core.text.Convert;

/**
 * 生产排程 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-28
 */
@Service
public class ProductionServiceImpl extends BaseServiceImpl<SysProduction>  implements ISysProductionService
{
	@Autowired
	private SysProductionMapper productionMapper;

	@Override
	public BaseMapper<SysProduction> getBaseMapper() {
		return productionMapper;
	}

	/**
     * 查询生产排程信息
     * 
     * @param productionId 生产排程ID
     * @return 生产排程信息
     */
    @Override
	public SysProduction selectProductionById(Long productionId)
	{
	    return productionMapper.selectProductionById(productionId);
	}
	
	/**
     * 查询生产排程列表
     * 
     * @param production 生产排程信息
     * @return 生产排程集合
     */
	@Override
	public List<SysProduction> selectProductionList(SysProduction production)
	{
	    return productionMapper.selectProductionList(production);
	}
	
    /**
     * 新增生产排程
     * 
     * @param production 生产排程信息
     * @return 结果
     */
	@Override
	public int insertProduction(SysProduction production)
	{
	    return productionMapper.insertProduction(production);
	}
	
	/**
     * 修改生产排程
     * 
     * @param production 生产排程信息
     * @return 结果
     */
	@Override
	public int updateProduction(SysProduction production)
	{
	    return productionMapper.updateProduction(production);
	}

	/**
     * 删除生产排程对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProductionByIds(String ids)
	{
		return productionMapper.deleteProductionByIds(Convert.toStrArray(ids));
	}
	
}
