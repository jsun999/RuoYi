package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysOutsource;
import com.ruoyi.system.service.ISysOutsourceService;
import com.ruoyi.system.vo.SysOutsourceVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 外发记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
@Controller
@RequestMapping("/project/outsource")
public class SysOutsourceController extends BaseController
{
    private String prefix = "project/outsource";
	
	@Autowired
	private ISysOutsourceService outsourceService;
	
	@RequiresPermissions("project:outsource:view")
	@GetMapping()
	public String outsource()
	{
	    return prefix + "/outsource";
	}
	
	/**
	 * 查询外发记录列表
	 */
	@RequiresPermissions("project:outsource:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysOutsource outsource)
	{
		startPage();
        List<SysOutsourceVo> list = outsourceService.selectOutsourceVoList(outsource);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出外发记录列表
	 */
	@RequiresPermissions("project:outsource:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysOutsource outsource)
    {
    	List<SysOutsource> list = outsourceService.selectOutsourceList(outsource);
        ExcelUtil<SysOutsource> util = new ExcelUtil<SysOutsource>(SysOutsource.class);
        return util.exportExcel(list, "outsource");
    }
	
	/**
	 * 新增外发记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存外发记录
	 */
	@RequiresPermissions("project:outsource:add")
	@Log(title = "外发记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysOutsource outsource)
	{		
		return toAjax(outsourceService.insertOutsource(outsource));
	}

	/**
	 * 修改外发记录
	 */
	@GetMapping("/edit/{outsourceId}")
	public String edit(@PathVariable("outsourceId") Long outsourceId, ModelMap mmap)
	{
		SysOutsource outsource = outsourceService.selectOutsourceById(outsourceId);
		mmap.put("outsource", outsource);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存外发记录
	 */
	@RequiresPermissions("project:outsource:edit")
	@Log(title = "外发记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysOutsource outsource)
	{		
		return toAjax(outsourceService.updateOutsource(outsource));
	}
	
	/**
	 * 删除外发记录
	 */
	@RequiresPermissions("project:outsource:remove")
	@Log(title = "外发记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(outsourceService.deleteOutsourceByIds(ids));
	}
	
}
