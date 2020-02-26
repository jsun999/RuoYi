package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.system.domain.SysOutsource;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.mapper.basemapper.BaseMapper;
import com.ruoyi.system.mapper.SysOutsourceMapper;
import com.ruoyi.system.mapper.SysProjectMapper;
import com.ruoyi.system.vo.SysPartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysPartMapper;
import com.ruoyi.system.domain.SysPart;
import com.ruoyi.system.service.ISysPartService;
import com.ruoyi.common.core.text.Convert;

/**
 * 零件 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-09
 */
@Service
public class SysPartServiceImpl extends BaseServiceImpl<SysPart>  implements ISysPartService
{
	@Autowired
	private SysPartMapper partMapper;
	@Autowired
	private SysProjectMapper sysProjectMapper;
	@Autowired
	private SysOutsourceMapper sysOutsourceMapper;

	@Override
	public BaseMapper<SysPart> getBaseMapper() {
		return partMapper;
	}

	/**
     * 查询零件信息
     * 
     * @param partId 零件ID
     * @return 零件信息
     */
    @Override
	public SysPart selectPartById(Long partId)
	{
	    return partMapper.selectPartById(partId);
	}
	
	/**
     * 查询零件列表
     * 
     * @param part 零件信息
     * @return 零件集合
     */
	@Override
	public List<SysPart> selectPartList(SysPart part)
	{
	    return partMapper.selectPartList(part);
	}
	
    /**
     * 新增零件
     * 
     * @param part 零件信息
     * @return 结果
     */
	@Override
	public int insertPart(SysPart part)
	{
		part = updateProjectAmount(part);
		int i = partMapper.insertPart(part);
		addOutsource(part);
		return i;
	}
	
	/**
     * 修改零件
     * 
     * @param part 零件信息
     * @return 结果
     */
	@Override
	public int updatePart(SysPart part)
	{
		part = updateProjectAmount(part);
		addOutsource(part);
		return partMapper.updatePart(part);
	}

	private void addOutsource(SysPart part) {
		if (part.getCraftType()!=null&&part.getCraftType() == 4) {//外发

			SysOutsource sysOutsource = new SysOutsource();
			sysOutsource.setPartId(part.getPartId());
			sysOutsource.setProjectId(part.getProjectId());
			List<SysOutsource> sysOutsources = sysOutsourceMapper.selectOutsourceList(sysOutsource);
			if(sysOutsources.size()>0){
				return;
			}
			sysOutsource.setPmUserId(part.getPmUserId());
			sysOutsource.setDelFlag(0);
			sysOutsource.setNorms(part.getPartNorms());
			sysOutsource.setOurDeliveryTime(part.getDeliveryTime());
			sysOutsource.setOurQuantity(part.getQuantity());
			sysOutsource.setOurUnitprice(part.getUnitPrice());
			sysOutsource.setOurTotalamount(part.getUnitPrice()==null ?new BigDecimal(0):part.getUnitPrice().multiply(new BigDecimal(part.getQuantity())));
			sysOutsourceMapper.insertOutsource(sysOutsource);
		}
	}

	private SysPart updateProjectAmount(SysPart part) {
		SysProject sysProject = sysProjectMapper.selectByPrimaryKey(part.getProjectId());
		if (part.getUnitPrice() != null) {
			sysProject.setAmount(sysProject.getAmount().add(part.getUnitPrice().multiply(new BigDecimal(part.getQuantity()))));
			sysProjectMapper.updateByPrimaryKey(sysProject);
		}
		part.setPmUserId(sysProject.getPmUserId());
		return part;
	}

	/**
     * 删除零件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePartByIds(String ids)
	{
		return partMapper.deletePartByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询零件列表
	 * @param part
	 * @return
	 */
	@Override
	public List<SysPartVo> selectPartVoList(SysPart part) {
		return partMapper.selectPartVoList(part);
	}

}
