package com.ruoyi.web.controller.material;

import java.util.List;
import com.ruoyi.system.domain.SysTool;
import com.ruoyi.system.vo.SysToolVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.ISysToolService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 刀具 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/material/tool")
public class SysToolController extends BaseController
{
    private String prefix = "material/tool";
	
	@Autowired
	private ISysToolService toolService;
	
	@RequiresPermissions("material:tool:view")
	@GetMapping()
	public String tool()
	{
	    return prefix + "/tool";
	}
	
	/**
	 * 查询刀具列表
	 */
	@RequiresPermissions("material:tool:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysTool tool)
	{
		startPage();
        List<SysToolVo> list = toolService.selectToolVoList(tool);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出刀具列表
	 */
	@RequiresPermissions("material:tool:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysTool tool)
    {
    	List<SysTool> list = toolService.selectToolList(tool);
        ExcelUtil<SysTool> util = new ExcelUtil<SysTool>(SysTool.class);
        return util.exportExcel(list, "tool");
    }
	
	/**
	 * 新增刀具
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存刀具
	 */
	@RequiresPermissions("material:tool:add")
	@Log(title = "刀具", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysTool tool)
	{		
		return toAjax(toolService.insertTool(tool));
	}

	/**
	 * 修改刀具
	 */
	@GetMapping("/edit/{toolId}")
	public String edit(@PathVariable("toolId") Long toolId, ModelMap mmap)
	{
		SysTool tool = toolService.selectToolById(toolId);
		mmap.put("tool", tool);
	    return prefix + "/edit";
	}

	/**
	 * 领取刀具
	 */
	@GetMapping("/draw/{toolId}")
	public String draw(@PathVariable("toolId") Long toolId, ModelMap mmap)
	{
		SysTool tool = toolService.selectToolById(toolId);
		mmap.put("tool", tool);
		return prefix + "/draw";
	}

	/**
	 * 报废刀具
	 */
	@GetMapping("/scrap/{toolId}")
	public String scrap(@PathVariable("toolId") Long toolId, ModelMap mmap)
	{
		SysTool tool = toolService.selectToolById(toolId);
		mmap.put("tool", tool);
		return prefix + "/scrap";
	}
	
	/**
	 * 修改保存刀具
	 */
	@RequiresPermissions("material:tool:edit")
	@Log(title = "刀具", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysTool tool)
	{		
		return toAjax(toolService.updateTool(tool));
	}

	/**
	 * 领取保存刀具
	 */
	@RequiresPermissions("material:tool:edit")
	@Log(title = "刀具", businessType = BusinessType.UPDATE)
	@PostMapping("/draw")
	@ResponseBody
	public AjaxResult drawSave(SysTool tool,Long userId)
	{
		return toAjax(toolService.drawTool(tool,userId));
	}

	/**
	 * 报废保存刀具
	 */
	@RequiresPermissions("material:tool:edit")
	@Log(title = "刀具", businessType = BusinessType.UPDATE)
	@PostMapping("/scrap")
	@ResponseBody
	public AjaxResult scrapSave(SysTool tool,Long userId)
	{
		return toAjax(toolService.scrapTool(tool,userId));
	}
	/**
	 * 删除刀具
	 */
	@RequiresPermissions("material:tool:remove")
	@Log(title = "刀具", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(toolService.deleteToolByIds(ids));
	}
	
}
