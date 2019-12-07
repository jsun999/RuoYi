package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.service.ISysProjectService;
import com.ruoyi.system.vo.SysProjectVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目 信息操作处理
 *
 * @author ruoyi
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/project/project")
public class SysProjectController extends BaseController
{
	private String prefix = "project/project";

	@Autowired
	private ISysProjectService projectService;

	@RequiresPermissions("project:project:view")
	@GetMapping()
	public String project()
	{
		return prefix + "/project";
	}

	/**
	 * 查询项目列表
	 */
	@RequiresPermissions("project:project:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysProject project)
	{
		startPage();
		List<SysProjectVo> list = projectService.selectSysProjectListWithCustomerName(project);
		return getDataTable(list);
	}


	/**
	 * 导出项目列表
	 */
	@RequiresPermissions("project:project:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(SysProject project)
	{
		List<SysProject> list = projectService.selectSysProjectList(project);
		ExcelUtil<SysProject> util = new ExcelUtil<SysProject>(SysProject.class);
		return util.exportExcel(list, "project");
	}

	/**
	 * 新增项目
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存项目
	 */
	@RequiresPermissions("project:project:add")
	@Log(title = "项目", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysProject project)
	{
		return toAjax(projectService.insertSysProject(project));
	}

	/**
	 * 修改项目
	 */
	@GetMapping("/edit/{projectId}")
	public String edit(@PathVariable("projectId") Long projectId, ModelMap mmap)
	{
		SysProject project = projectService.selectSysProjectById(projectId);
		mmap.put("project", project);
		return prefix + "/edit";
	}

	/**
	 * 修改保存项目
	 */
	@RequiresPermissions("project:project:edit")
	@Log(title = "项目", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysProject project)
	{
		return toAjax(projectService.updateSysProject(project));
	}

	/**
	 * 删除项目
	 */
	@RequiresPermissions("project:project:remove")
	@Log(title = "项目", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(projectService.deleteSysProjectByIds(ids));
	}

}
