package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.Echart;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.vo.SysProjectVo;

import java.util.List;
import java.util.Map;

/**
 * 项目 数据层
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public interface SysProjectMapper
{
	/**
     * 查询项目信息
     * 
     * @param projectId 项目ID
     * @return 项目信息
     */
	public SysProject selectSysProjectById(Long projectId);
	
	/**
     * 查询项目列表
     * 
     * @param project 项目信息
     * @return 项目集合
     */
	public List<SysProject> selectSysProjectList(SysProject project);
	
	/**
     * 新增项目
     * 
     * @param project 项目信息
     * @return 结果
     */
	public int insertSysProject(SysProject project);
	
	/**
     * 修改项目
     * 
     * @param project 项目信息
     * @return 结果
     */
	public int updateSysProject(SysProject project);
	
	/**
     * 删除项目
     * 
     * @param projectId 项目ID
     * @return 结果
     */
	public int deleteSysProjectById(Long projectId);
	
	/**
     * 批量删除项目
     * 
     * @param projectIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysProjectByIds(String[] projectIds);

	/**
	 * 查询项目列表
	 * @param project
	 * @return
	 */
	List<SysProjectVo> selectSysProjectListWithCustomerName(SysProject project);

	/**
	 * 业绩统计
	 */
    List<Echart> earningReportMonth();

	List<Echart> earningReportQuarter();

	List<Echart> earningReportYear();

	/**
	 * 客户金额占比
	 * @return
	 */
	List<Echart> customerReportThisYear();

	List<Echart> customerReportPreviousYear();

	List<Echart> customerReportAll();

    List<Echart> selectPerformanceCompletionByUser(SysUser sysUser);
}