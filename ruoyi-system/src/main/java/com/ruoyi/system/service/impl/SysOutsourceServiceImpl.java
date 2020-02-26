package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.system.mapper.basemapper.BaseMapper;
import com.ruoyi.system.vo.SysOutsourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysOutsourceMapper;
import com.ruoyi.system.domain.SysOutsource;
import com.ruoyi.system.service.ISysOutsourceService;
import com.ruoyi.common.core.text.Convert;

/**
 * 外发记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
@Service
public class SysOutsourceServiceImpl extends BaseServiceImpl<SysOutsource>  implements ISysOutsourceService
{
	@Autowired
	private SysOutsourceMapper outsourceMapper;

	@Override
	public BaseMapper<SysOutsource> getBaseMapper() {
		return outsourceMapper;
	}

	/**
     * 查询外发记录信息
     * 
     * @param outsourceId 外发记录ID
     * @return 外发记录信息
     */
    @Override
	public SysOutsource selectOutsourceById(Long outsourceId)
	{
	    return outsourceMapper.selectOutsourceById(outsourceId);
	}
	
	/**
     * 查询外发记录列表
     * 
     * @param outsource 外发记录信息
     * @return 外发记录集合
     */
	@Override
	public List<SysOutsource> selectOutsourceList(SysOutsource outsource)
	{
	    return outsourceMapper.selectOutsourceList(outsource);
	}
	
    /**
     * 新增外发记录
     * 
     * @param outsource 外发记录信息
     * @return 结果
     */
	@Override
	public int insertOutsource(SysOutsource outsource)
	{
	    return outsourceMapper.insertOutsource(outsource);
	}
	
	/**
     * 修改外发记录
     * 
     * @param outsource 外发记录信息
     * @return 结果
     */
	@Override
	public int updateOutsource(SysOutsource outsource)
	{
		outsource.setOutTotalamount(outsource.getOutUnitprice()==null?new BigDecimal(0):outsource.getOutUnitprice().multiply(outsource.getOutQuantity()==null?new BigDecimal(0):new BigDecimal(outsource.getOutQuantity())));
	    return outsourceMapper.updateOutsource(outsource);
	}

	/**
     * 删除外发记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOutsourceByIds(String ids)
	{
		return outsourceMapper.deleteOutsourceByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询外发列表
	 * @param outsource
	 * @return
	 */
	@Override
	public List<SysOutsourceVo> selectOutsourceVoList(SysOutsource outsource) {
		return outsourceMapper.selectOutsourceVoList(outsource);
	}

}
