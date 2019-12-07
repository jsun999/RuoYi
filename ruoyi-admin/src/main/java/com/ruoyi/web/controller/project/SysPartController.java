package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysPart;
import com.ruoyi.system.service.ISysPartService;
import com.ruoyi.system.vo.SysPartVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 零件 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-09
 */
@Controller
@RequestMapping("/project/part")
public class SysPartController extends BaseController
{
    private String prefix = "project/part";
	
	@Autowired
	private ISysPartService partService;
	
	@RequiresPermissions("project:part:view")
	@GetMapping()
	public String part()
	{
	    return prefix + "/part";
	}
	
	/**
	 * 查询零件列表
	 */
	@RequiresPermissions("project:part:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysPart part)
	{
		startPage();
        List<SysPartVo> list = partService.selectPartVoList(part);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出零件列表
	 */
	@RequiresPermissions("project:part:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysPart part)
    {
    	List<SysPartVo> list = partService.selectPartVoList(part);
        ExcelUtil<SysPartVo> util = new ExcelUtil<SysPartVo>(SysPartVo.class);
        return util.exportExcel(list, "part");
    }
	
	/**
	 * 新增零件
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存零件
	 */
	@RequiresPermissions("project:part:add")
	@Log(title = "零件", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysPart part)
	{		
		return toAjax(partService.insertPart(part));
	}

	/**
	 * 修改零件
	 */
	@GetMapping("/edit/{partId}")
	public String edit(@PathVariable("partId") Long partId, ModelMap mmap)
	{
		SysPart part = partService.selectPartById(partId);
		mmap.put("part", part);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存零件
	 */
	@RequiresPermissions("project:part:edit")
	@Log(title = "零件", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysPart part)
	{		
		return toAjax(partService.updatePart(part));
	}
	
	/**
	 * 删除零件
	 */
	@RequiresPermissions("project:part:remove")
	@Log(title = "零件", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(partService.deletePartByIds(ids));
	}
	
}
