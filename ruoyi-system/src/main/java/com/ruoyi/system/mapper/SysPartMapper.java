package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysPart;
import com.ruoyi.system.vo.SysPartVo;

import java.util.List;	

/**
 * 零件 数据层
 * 
 * @author ruoyi
 * @date 2019-07-09
 */
public interface SysPartMapper
{
	/**
     * 查询零件信息
     * 
     * @param partId 零件ID
     * @return 零件信息
     */
	public SysPart selectPartById(Long partId);
	
	/**
     * 查询零件列表
     * 
     * @param part 零件信息
     * @return 零件集合
     */
	public List<SysPart> selectPartList(SysPart part);
	
	/**
     * 新增零件
     * 
     * @param part 零件信息
     * @return 结果
     */
	public int insertPart(SysPart part);
	
	/**
     * 修改零件
     * 
     * @param part 零件信息
     * @return 结果
     */
	public int updatePart(SysPart part);
	
	/**
     * 删除零件
     * 
     * @param partId 零件ID
     * @return 结果
     */
	public int deletePartById(Long partId);
	
	/**
     * 批量删除零件
     * 
     * @param partIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePartByIds(String[] partIds);

	/**
	 * 查询零件列表
	 * @param part
	 * @return
	 */
    List<SysPartVo> selectPartVoList(SysPart part);
}