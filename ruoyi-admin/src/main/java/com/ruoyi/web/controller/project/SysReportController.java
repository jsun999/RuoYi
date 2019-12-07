package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Echart;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysProjectService;
import com.ruoyi.system.service.ISysToolRecordService;
import com.ruoyi.system.service.ISysToolService;
import com.ruoyi.system.vo.SysProjectVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 报表展示处理
 * 
 * @author ruoyi
 * @date 2019-07-29
 */
@Controller
@RequestMapping("/report/report")
public class SysReportController extends BaseController
{
    private String prefix = "project/report";
	
	@Autowired
	private ISysProjectService projectService;
	@Autowired
	private ISysToolService toolService;
	@Autowired
	private ISysToolRecordService toolRecordService;

	@RequiresPermissions("report:report:view")
	@GetMapping()
	public String report()
	{
		return prefix + "/report";
	}
    /**
	 * 业绩统计
	 */
	@RequiresPermissions("report:report:view")
	@PostMapping( "/earningReport")
	@ResponseBody
	public List<Echart> earningReport(String type)
	{
		List<Echart> echarts = projectService.earningReport(type);
		return echarts;
	}

	/**
	 * 客户金额比例
	 */
	@RequiresPermissions("report:report:view")
	@PostMapping( "/customerReport")
	@ResponseBody
	public List<Echart> customerReport(String type)
	{
		List<Echart> echarts = projectService.customerReport(type);
		return echarts;
	}

	/**
	 * 个人业绩报表
	 */
	@RequiresPermissions("report:report:view")
	@PostMapping( "/performanceCompletion")
	@ResponseBody
	public List<Echart> performanceCompletion()
	{
		SysUser sysUser = ShiroUtils.getSysUser();
		List<Echart> echarts = projectService.performanceCompletion(sysUser);
		return echarts;
	}

	/**
	 * 刀具损耗
	 */
	@RequiresPermissions("report:report:view")
	@PostMapping( "/toolCostReport")
	@ResponseBody
	public List<Echart> toolCostReport(String type)
	{
		List<Echart> echarts = toolRecordService.toolCostReport(type);
		return echarts;
	}
}
