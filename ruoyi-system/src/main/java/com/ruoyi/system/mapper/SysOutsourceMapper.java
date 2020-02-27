package com.ruoyi.system.mapper;

import com.ruoyi.system.basemapper.BaseMapper;
import com.ruoyi.system.domain.SysOutsource;
import com.ruoyi.system.vo.SysOutsourceVo;

import java.util.List;	

/**
 * 外发记录 数据层
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
public interface SysOutsourceMapper extends BaseMapper<SysOutsource>
{
	/**
     * 查询外发记录信息
     * 
     * @param outsourceId 外发记录ID
     * @return 外发记录信息
     */
	public SysOutsource selectOutsourceById(Long outsourceId);
	
	/**
     * 查询外发记录列表
     * 
     * @param outsource 外发记录信息
     * @return 外发记录集合
     */
	public List<SysOutsource> selectOutsourceList(SysOutsource outsource);
	
	/**
     * 新增外发记录
     * 
     * @param outsource 外发记录信息
     * @return 结果
     */
	public int insertOutsource(SysOutsource outsource);
	
	/**
     * 修改外发记录
     * 
     * @param outsource 外发记录信息
     * @return 结果
     */
	public int updateOutsource(SysOutsource outsource);
	
	/**
     * 删除外发记录
     * 
     * @param outsourceId 外发记录ID
     * @return 结果
     */
	public int deleteOutsourceById(Long outsourceId);
	
	/**
     * 批量删除外发记录
     * 
     * @param outsourceIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteOutsourceByIds(String[] outsourceIds);

	/**
	 * 查询外发列表
	 * @param outsource
	 * @return
	 */
    List<SysOutsourceVo> selectOutsourceVoList(SysOutsource outsource);
}