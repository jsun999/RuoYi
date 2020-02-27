package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.OrderNumberUtils;
import com.ruoyi.system.domain.SysTool;
import com.ruoyi.system.domain.SysToolRecord;
import com.ruoyi.system.basemapper.BaseMapper;
import com.ruoyi.system.mapper.SysToolMapper;
import com.ruoyi.system.mapper.SysToolRecordMapper;
import com.ruoyi.system.service.ISysToolService;
import com.ruoyi.system.vo.SysToolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 刀具 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
@Service
public class SysToolServiceImpl extends BaseServiceImpl<SysTool>  implements ISysToolService
{
	@Autowired
	private SysToolMapper toolMapper;
	@Autowired
	private SysToolRecordMapper sysToolRecordMapper;

	@Override
	public BaseMapper<SysTool> getBaseMapper() {
		return toolMapper;
	}

	/**
     * 查询刀具信息
     * 
     * @param toolId 刀具ID
     * @return 刀具信息
     */
    @Override
	public SysTool selectToolById(Long toolId)
	{
	    return toolMapper.selectToolById(toolId);
	}
	
	/**
     * 查询刀具列表
     * 
     * @param tool 刀具信息
     * @return 刀具集合
     */
	@Override
	public List<SysTool> selectToolList(SysTool tool)
	{
	    return toolMapper.selectToolList(tool);
	}
	
    /**
     * 新增刀具
     * 
     * @param tool 刀具信息
     * @return 结果
     */
	@Override
	public int insertTool(SysTool tool)
	{
		tool.setToolPartNumber(OrderNumberUtils.generatePartNumber());
	    return toolMapper.insertTool(tool);
	}
	
	/**
     * 修改刀具
     * 
     * @param tool 刀具信息
     * @return 结果
     */
	@Override
	public int updateTool(SysTool tool)
	{
	    return toolMapper.updateTool(tool);
	}

	/**
     * 删除刀具对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteToolByIds(String ids)
	{
		return toolMapper.deleteToolByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询刀具列表
	 * @param tool
	 * @return
	 */
	@Override
	public List<SysToolVo> selectToolVoList(SysTool tool) {
		return toolMapper.selectToolVoList(tool);
	}

	/**
	 * 领取刀具
	 * @param tool
	 * @return
	 */
	@Override
	public int drawTool(SysTool tool,Long userId) {
		Integer quantity = tool.getQuantity();
		tool.setQuantity(null);
		List<SysTool> sysTools = toolMapper.selectToolList(tool);
		int flg=0;
		for (int i = 0; i < sysTools.size(); i++) {
			SysTool sysTool =  sysTools.get(i);
			if(sysTool.getStatus()==1){
				if(sysTool.getQuantity()-quantity<0){
					throw new BusinessException("库存不足。");
				}
				sysTool.setQuantity(sysTool.getQuantity()-quantity);
				toolMapper.updateTool(sysTool);
			}
			if(sysTool.getStatus()==2){
				sysTool.setQuantity(sysTool.getQuantity()+quantity);
				sysTool.setStatus(2);
				toolMapper.updateTool(sysTool);
				flg++;
			}
		}
		SysToolRecord sysToolRecord = new SysToolRecord();
		sysToolRecord.setToolPartNumber(sysTools.get(0).getToolPartNumber());
		sysToolRecord.setChangeBy(userId);
		sysToolRecord.setChangeTime(new Date());
		sysToolRecord.setChangeType(1);
		sysToolRecord.setQuantity(quantity);
		sysToolRecordMapper.insertToolRecord(sysToolRecord);
		if(flg==0){
			SysTool sysTool = sysTools.get(0);
			sysTool.setQuantity(quantity);
			sysTool.setStatus(2);
			return toolMapper.insertTool(sysTool);
		}else{
			return 1;
		}
	}

	/**
	 * 报废刀具
	 * @param tool
	 * @return
	 */
	@Override
	public int scrapTool(SysTool tool,Long userId) {
		Integer quantity = tool.getQuantity();
		tool.setQuantity(null);
		List<SysTool> sysTools = toolMapper.selectToolList(tool);
		int flg=0;
		for (int i = 0; i < sysTools.size(); i++) {
			SysTool sysTool =  sysTools.get(i);
			if(sysTool.getStatus()==2){
				if(sysTool.getQuantity()-quantity<0){
					throw new BusinessException("报废刀具不可超过使用中的数目。");
				}
				sysTool.setQuantity(sysTool.getQuantity()-quantity);
				toolMapper.updateTool(sysTool);
			}
			if(sysTool.getStatus()==3){
				sysTool.setQuantity(sysTool.getQuantity()+quantity);
				sysTool.setStatus(3);
				toolMapper.updateTool(sysTool);
				flg++;
			}
		}
		SysToolRecord sysToolRecord = new SysToolRecord();
		sysToolRecord.setToolPartNumber(sysTools.get(0).getToolPartNumber());
		sysToolRecord.setChangeBy(userId);
		sysToolRecord.setChangeTime(new Date());
		sysToolRecord.setChangeType(2);
		sysToolRecord.setQuantity(quantity);
		sysToolRecordMapper.insertToolRecord(sysToolRecord);
		if(flg==0){
			SysTool sysTool = sysTools.get(0);
			sysTool.setQuantity(quantity);
			sysTool.setStatus(3);
			return toolMapper.insertTool(sysTool);
		}else{
			return 1;
		}
	}


}
