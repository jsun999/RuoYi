package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.Echart;
import com.ruoyi.system.basemapper.BaseMapper;
import com.ruoyi.system.vo.SysToolRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysToolRecordMapper;
import com.ruoyi.system.domain.SysToolRecord;
import com.ruoyi.system.service.ISysToolRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 刀具变更 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-20
 */
@Service
public class SysToolRecordServiceImpl extends BaseServiceImpl<SysToolRecord>  implements ISysToolRecordService
{
	@Autowired
	private SysToolRecordMapper toolRecordMapper;

	@Override
	public BaseMapper<SysToolRecord> getBaseMapper() {
		return toolRecordMapper;
	}

	/**
     * 查询刀具变更信息
     * 
     * @param toolRecordId 刀具变更ID
     * @return 刀具变更信息
     */
    @Override
	public SysToolRecord selectToolRecordById(Long toolRecordId)
	{
	    return toolRecordMapper.selectToolRecordById(toolRecordId);
	}
	
	/**
     * 查询刀具变更列表
     * 
     * @param toolRecordVo 刀具变更信息
     * @return 刀具变更集合
     */
	@Override
	public List<SysToolRecordVo> selectToolRecordList(SysToolRecordVo toolRecordVo)
	{
	    return toolRecordMapper.selectToolRecordList(toolRecordVo);
	}
	
    /**
     * 新增刀具变更
     * 
     * @param toolRecord 刀具变更信息
     * @return 结果
     */
	@Override
	public int insertToolRecord(SysToolRecord toolRecord)
	{
	    return toolRecordMapper.insertToolRecord(toolRecord);
	}
	
	/**
     * 修改刀具变更
     * 
     * @param toolRecord 刀具变更信息
     * @return 结果
     */
	@Override
	public int updateToolRecord(SysToolRecord toolRecord)
	{
	    return toolRecordMapper.updateToolRecord(toolRecord);
	}

	/**
     * 删除刀具变更对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteToolRecordByIds(String ids)
	{
		return toolRecordMapper.deleteToolRecordByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<Echart> toolCostReport(String type) {
		List<Echart> echarts = null;
		if("1".equals(type)){
			echarts = toolRecordMapper.toolCostReportMonth();
		}
		if("2".equals(type)){
			echarts = toolRecordMapper.toolCostReportQuarter();
		}
		if("3".equals(type)){
			echarts = toolRecordMapper.toolCostReportYear();
		}
		return echarts;
	}
}
