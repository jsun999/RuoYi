package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.Echart;
import com.ruoyi.system.domain.SysToolRecord;
import com.ruoyi.system.vo.SysToolRecordVo;

import java.util.List;

/**
 * 刀具变更 数据层
 * 
 * @author ruoyi
 * @date 2019-08-20
 */
public interface SysToolRecordMapper
{
	/**
     * 查询刀具变更信息
     * 
     * @param toolRecordId 刀具变更ID
     * @return 刀具变更信息
     */
	public SysToolRecord selectToolRecordById(Long toolRecordId);
	
	/**
     * 查询刀具变更列表
     * 
     * @param toolRecordVo 刀具变更信息
     * @return 刀具变更集合
     */
	public List<SysToolRecordVo> selectToolRecordList(SysToolRecordVo toolRecordVo);
	
	/**
     * 新增刀具变更
     * 
     * @param toolRecord 刀具变更信息
     * @return 结果
     */
	public int insertToolRecord(SysToolRecord toolRecord);
	
	/**
     * 修改刀具变更
     * 
     * @param toolRecord 刀具变更信息
     * @return 结果
     */
	public int updateToolRecord(SysToolRecord toolRecord);
	
	/**
     * 删除刀具变更
     * 
     * @param toolRecordId 刀具变更ID
     * @return 结果
     */
	public int deleteToolRecordById(Long toolRecordId);
	
	/**
     * 批量删除刀具变更
     * 
     * @param toolRecordIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteToolRecordByIds(String[] toolRecordIds);

    List<Echart> toolCostReportMonth();

	List<Echart> toolCostReportQuarter();

	List<Echart> toolCostReportYear();
}