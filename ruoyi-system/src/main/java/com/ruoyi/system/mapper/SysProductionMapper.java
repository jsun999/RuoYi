package com.ruoyi.system.mapper;

import com.ruoyi.system.basemapper.BaseMapper;
import com.ruoyi.system.domain.SysProduction;
import com.ruoyi.system.vo.SysProductionVo;

import java.util.List;

/**
 * 生产排程 数据层
 * 
 * @author ruoyi
 * @date 2019-08-28
 */
public interface SysProductionMapper extends BaseMapper<SysProduction>
{
	/**
     * 批量删除生产排程
     * 
     * @param productionIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductionByIds(String[] productionIds);

    List<SysProductionVo> selectProductionVo(SysProduction production);
}