package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.Echart;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.vo.SysProjectVo;

import java.util.List;
import java.util.Map;

/**
 * 项目 服务层
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public interface ISysProjectService extends BaseService<SysProject>
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
     * 删除项目信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysProjectByIds(String ids);

	/**
	 * 查询项目列表
	 * @param project
	 * @return
	 */
	List<SysProjectVo> selectSysProjectListWithCustomerName(SysProject project);

	/**
	 * 业绩统计
	 */
    List<Echart> earningReport(String type);

	/**
	 * 客户金额占比
	 * @param type
	 * @return
	 */
	List<Echart> customerReport(String type);

	/**
	 * 当月业绩完成度
	 * @return
	 */
	List<Echart> performanceCompletion(SysUser sysUser);

	SysProject selectSysProjectByNumber(String projectNumber);

    List<SysProject> selectSysProjectListOn();

    void submitApply(SysProject sysProject, String applyUserId);
}
