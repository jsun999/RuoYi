package com.ruoyi.system.mapper;

import com.ruoyi.system.basemapper.BaseMapper;
import com.ruoyi.system.domain.SysTool;
import com.ruoyi.system.vo.SysToolVo;

import java.util.List;

/**
 * 刀具 数据层
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public interface SysToolMapper extends BaseMapper<SysTool>
{
	/**
     * 查询刀具信息
     * 
     * @param toolId 刀具ID
     * @return 刀具信息
     */
	public SysTool selectToolById(Long toolId);
	
	/**
     * 查询刀具列表
     * 
     * @param tool 刀具信息
     * @return 刀具集合
     */
	public List<SysTool> selectToolList(SysTool tool);
	
	/**
     * 新增刀具
     * 
     * @param tool 刀具信息
     * @return 结果
     */
	public int insertTool(SysTool tool);
	
	/**
     * 修改刀具
     * 
     * @param tool 刀具信息
     * @return 结果
     */
	public int updateTool(SysTool tool);
	
	/**
     * 删除刀具
     * 
     * @param toolId 刀具ID
     * @return 结果
     */
	public int deleteToolById(Long toolId);
	
	/**
     * 批量删除刀具
     * 
     * @param toolIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteToolByIds(String[] toolIds);

	/**
	 * 查询刀具列表
	 * @param tool
	 * @return
	 */
	List<SysToolVo> selectToolVoList(SysTool tool);

}