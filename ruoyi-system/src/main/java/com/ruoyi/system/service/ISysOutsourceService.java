package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysOutsource;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.vo.SysOutsourceVo;

import java.util.List;

/**
 * 外发记录 服务层
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
public interface ISysOutsourceService extends BaseService<SysOutsource>
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
     * 删除外发记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOutsourceByIds(String ids);

	/**
	 * 查询外发列表
	 * @param outsource
	 * @return
	 */
    List<SysOutsourceVo> selectOutsourceVoList(SysOutsource outsource);
}
