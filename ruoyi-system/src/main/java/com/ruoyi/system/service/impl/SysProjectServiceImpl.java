package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.Echart;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysSequence;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.basemapper.BaseMapper;
import com.ruoyi.system.mapper.SysProjectMapper;
import com.ruoyi.system.mapper.SysSequenceMapper;
import com.ruoyi.system.service.ISysProjectService;
import com.ruoyi.system.vo.SysProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 项目 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
@Service
public class SysProjectServiceImpl extends BaseServiceImpl<SysProject> implements ISysProjectService
{
	@Autowired
	private SysProjectMapper projectMapper;

	@Autowired
	private SysSequenceMapper sequenceMapper;

	@Override
	public BaseMapper<SysProject> getBaseMapper() {
		return projectMapper;
	}

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
		String str = getSeqBuilder("OrderSeq");
		project.setProjectNumber(str);
		return projectMapper.insertSelective(project);
	}

	private String getSeqBuilder(String type) {
		Integer orderSeq = sequenceMapper.nextVal(type);
		StringBuilder builder = new StringBuilder();
		builder.append("PNO");
		builder.append(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		for (int i = 0; i < 8-orderSeq.toString().length(); i++) {
			builder.append("0");
		}
		builder.append(orderSeq);
		return builder.toString();
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

	@Override
	public SysProject selectSysProjectByNumber(String projectNumber) {
		return projectMapper.selectSysProjectByNumber(projectNumber);
	}

	@Override
	public List<SysProject> selectSysProjectListOn() {
		return projectMapper.selectSysProjectListOn();
	}

	@Override
	public void submitApply(SysProject sysProject, String applyUserId) {

	}
}
