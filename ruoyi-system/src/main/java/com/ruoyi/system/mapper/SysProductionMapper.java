package com.ruoyi.system.mapper;

import com.ruoyi.system.basemapper.BaseMapper;
import com.ruoyi.system.domain.SysProduction;

import java.util.List;

/**
 * 生产排程 数据层
 * 
 * @author ruoyi
 * @date 2019-08-28
 */
public interface SysProductionMapper extends BaseMapper<SysProduction>
{
	/**
     * 查询生产排程信息
     * 
     * @param productionId 生产排程ID
     * @return 生产排程信息
     */
	public SysProduction selectProductionById(Long productionId);
	
	/**
     * 查询生产排程列表
     * 
     * @param production 生产排程信息
     * @return 生产排程集合
     */
	public List<SysProduction> selectProductionList(SysProduction production);
	
	/**
     * 新增生产排程
     * 
     * @param production 生产排程信息
     * @return 结果
     */
	public int insertProduction(SysProduction production);
	
	/**
     * 修改生产排程
     * 
     * @param production 生产排程信息
     * @return 结果
     */
	public int updateProduction(SysProduction production);
	
	/**
     * 删除生产排程
     * 
     * @param productionId 生产排程ID
     * @return 结果
     */
	public int deleteProductionById(Long productionId);
	
	/**
     * 批量删除生产排程
     * 
     * @param productionIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductionByIds(String[] productionIds);
	
}