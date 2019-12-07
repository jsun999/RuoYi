package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.Echart;
import com.ruoyi.system.domain.SysTool;
import com.ruoyi.system.vo.SysToolVo;

import java.util.List;

/**
 * 刀具 服务层
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public interface ISysToolService {
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
	 * 删除刀具信息
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteToolByIds(String ids);

	/**
	 * 查询刀具列表
	 *
	 * @param tool
	 * @return
	 */
	List<SysToolVo> selectToolVoList(SysTool tool);

	/**
	 * 领取刀具
	 *
	 * @param tool
	 * @return
	 */
	int drawTool(SysTool tool, Long userId);

	/**
	 * 报废刀具
	 *
	 * @param tool
	 * @return
	 */
	int scrapTool(SysTool tool, Long userId);

}
