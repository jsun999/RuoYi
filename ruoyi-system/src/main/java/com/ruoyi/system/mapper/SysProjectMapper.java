package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.Echart;
import com.ruoyi.system.basemapper.BaseMapper;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.vo.SysProjectVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目 数据层
 * 
 * @date 2019-07-01
 */

@Repository
public interface SysProjectMapper extends BaseMapper<SysProject>
{


	
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

	SysProjectVo selectSysProjectWithNameById(Long id);
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

    SysProject selectSysProjectByNumber(String projectNumber);

    List<SysProject> selectSysProjectListOn();


}