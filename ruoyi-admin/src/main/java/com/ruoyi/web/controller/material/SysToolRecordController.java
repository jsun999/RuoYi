package com.ruoyi.web.controller.material;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysToolRecord;
import com.ruoyi.system.service.ISysToolRecordService;
import com.ruoyi.system.vo.SysToolRecordVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 刀具变更 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-20
 */
@Controller
@RequestMapping("/material/toolRecord")
public class SysToolRecordController extends BaseController
{
    private String prefix = "material/toolRecord";
	
	@Autowired
	private ISysToolRecordService toolRecordService;
	
	@RequiresPermissions("system:toolRecord:view")
	@GetMapping("draw")
	public String toolDrawRecord()
	{
	    return prefix + "/toolDrawRecord";
	}

	@RequiresPermissions("system:toolRecord:view")
	@GetMapping("scrap")
	public String toolScrapRecord()
	{
		return prefix + "/toolScrapRecord";
	}
	
	/**
	 * 查询刀具变更列表
	 */
	@RequiresPermissions("system:toolRecord:list")
	@PostMapping("/listDraw")
	@ResponseBody
	public TableDataInfo listDraw(SysToolRecordVo toolRecordVo)
	{
		startPage();
		toolRecordVo.setChangeType(1);
        List<SysToolRecordVo> list = toolRecordService.selectToolRecordList(toolRecordVo);
		return getDataTable(list);
	}
	/**
	 * 查询刀具变更列表
	 */
	@RequiresPermissions("system:toolRecord:list")
	@PostMapping("/listScrap")
	@ResponseBody
	public TableDataInfo listScrap(SysToolRecordVo toolRecordVo)
	{
		startPage();
		toolRecordVo.setChangeType(2);
		List<SysToolRecordVo> list = toolRecordService.selectToolRecordList(toolRecordVo);
		return getDataTable(list);
	}
	
	/**
	 * 导出刀具变更列表
	 */
	@RequiresPermissions("system:toolRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysToolRecordVo toolRecordVo)
    {
    	List<SysToolRecordVo> list = toolRecordService.selectToolRecordList(toolRecordVo);
        ExcelUtil<SysToolRecordVo> util = new ExcelUtil<SysToolRecordVo>(SysToolRecordVo.class);
        return util.exportExcel(list, "toolRecord");
    }
	
	/**
	 * 新增刀具变更
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存刀具变更
	 */
	@RequiresPermissions("system:toolRecord:add")
	@Log(title = "刀具变更", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysToolRecord toolRecord)
	{		
		return toAjax(toolRecordService.insertToolRecord(toolRecord));
	}

	/**
	 * 修改刀具变更
	 */
	@GetMapping("/edit/{toolRecordId}")
	public String edit(@PathVariable("toolRecordId") Long toolRecordId, ModelMap mmap)
	{
		SysToolRecord toolRecord = toolRecordService.selectToolRecordById(toolRecordId);
		mmap.put("toolRecord", toolRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存刀具变更
	 */
	@RequiresPermissions("system:toolRecord:edit")
	@Log(title = "刀具变更", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysToolRecord toolRecord)
	{		
		return toAjax(toolRecordService.updateToolRecord(toolRecord));
	}
	
	/**
	 * 删除刀具变更
	 */
	@RequiresPermissions("system:toolRecord:remove")
	@Log(title = "刀具变更", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(toolRecordService.deleteToolRecordByIds(ids));
	}
	
}
