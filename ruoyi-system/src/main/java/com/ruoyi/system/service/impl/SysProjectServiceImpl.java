package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.Echart;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysProjectMapper;
import com.ruoyi.system.mapper.SysProjectMapper;
import com.ruoyi.system.service.ISysProjectService;
import com.ruoyi.system.vo.SysProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 项目 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
@Service
public class SysProjectServiceImpl implements ISysProjectService
{
	@Autowired
	private SysProjectMapper projectMapper;

	/**
     * 查询项目信息
     * 
     * @param projectId 项目ID
     * @return 项目信息
     */
    @Override
	public SysProject selectSysProjectById(Long projectId)
	{
	    return projectMapper.selectByPrimaryKey(projectId);
	}
	
	/**
     * 查询项目列表
     * 
     * @param project 项目信息
     * @return 项目集合
     */
	@Override
	public List<SysProject> selectSysProjectList(SysProject project)
	{
	    return projectMapper.select(project);
	}
	
    /**
     * 新增项目
     * 
     * @param project 项目信息
     * @return 结果
     */
	@Override
	public int insertSysProject(SysProject project)
	{
	    return projectMapper.insertSelective(project);
	}
	
	/**
     * 修改项目
     * 
     * @param project 项目信息
     * @return 结果
     */
	@Override
	public int updateSysProject(SysProject project)
	{
	    return projectMapper.updateByPrimaryKeySelective(project);
	}

	/**
     * 删除项目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSysProjectByIds(String ids)
	{
		return projectMapper.deleteSysProjectByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询项目列表
	 * @param project
	 * @return
	 */
	@Override
	public List<SysProjectVo> selectSysProjectListWithCustomerName(SysProject project) {
		return projectMapper.selectSysProjectListWithCustomerName(project);
	}
	/**
	 * 业绩统计
	 */
	@Override
	public List<Echart> earningReport(String type) {
		List<Echart> echarts = null;
		if("1".equals(type)){
			echarts = projectMapper.earningReportMonth();
		}
		if("2".equals(type)){
			echarts = projectMapper.earningReportQuarter();
		}
		if("3".equals(type)){
			echarts = projectMapper.earningReportYear();
		}
		return echarts;
	}

	@Override
	public List<Echart> customerReport(String type) {
		List<Echart> echarts = null;
		if ("1".equals(type)) {
			echarts = projectMapper.customerReportAll();
		}
		if ("2".equals(type)) {
			echarts = projectMapper.customerReportPreviousYear();
		}
		if ("3".equals(type)) {
			echarts = projectMapper.customerReportThisYear();
		}
		return echarts;
	}

	@Override
	public List<Echart> performanceCompletion(SysUser sysUser) {
		List<Echart> echarts = null;
		echarts = projectMapper.selectPerformanceCompletionByUser(sysUser);
		return echarts;
	}
}
