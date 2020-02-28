package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysProduction;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.vo.SysProductionVo;

import java.util.List;

/**
 * 生产排程 服务层
 * 
 * @author ruoyi
 * @date 2019-08-28
 */
public interface ISysProductionService extends BaseService<SysProduction>
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
	public List<SysProductionVo> selectProductionList(SysProduction production);
	
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
     * 删除生产排程信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductionByIds(String ids);
	
}
