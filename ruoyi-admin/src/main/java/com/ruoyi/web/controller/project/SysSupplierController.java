package com.ruoyi.web.controller.project;

import java.util.List;
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
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.service.ISysSupplierService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 供应商 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
@Controller
@RequestMapping("/project/supplier")
public class SysSupplierController extends BaseController
{
    private String prefix = "project/supplier";
	
	@Autowired
	private ISysSupplierService supplierService;
	
	@RequiresPermissions("project:supplier:view")
	@GetMapping()
	public String supplier()
	{
	    return prefix + "/supplier";
	}
	
	/**
	 * 查询供应商列表
	 */
	@RequiresPermissions("project:supplier:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysSupplier supplier)
	{
		startPage();
        List<SysSupplier> list = supplierService.selectSupplierList(supplier);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出供应商列表
	 */
	@RequiresPermissions("project:supplier:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysSupplier supplier)
    {
    	List<SysSupplier> list = supplierService.selectSupplierList(supplier);
        ExcelUtil<SysSupplier> util = new ExcelUtil<SysSupplier>(SysSupplier.class);
        return util.exportExcel(list, "supplier");
    }
	
	/**
	 * 新增供应商
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存供应商
	 */
	@RequiresPermissions("project:supplier:add")
	@Log(title = "供应商", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysSupplier supplier)
	{		
		return toAjax(supplierService.insertSupplier(supplier));
	}

	/**
	 * 修改供应商
	 */
	@GetMapping("/edit/{supplierId}")
	public String edit(@PathVariable("supplierId") Long supplierId, ModelMap mmap)
	{
		SysSupplier supplier = supplierService.selectSupplierById(supplierId);
		mmap.put("supplier", supplier);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存供应商
	 */
	@RequiresPermissions("project:supplier:edit")
	@Log(title = "供应商", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysSupplier supplier)
	{		
		return toAjax(supplierService.updateSupplier(supplier));
	}
	
	/**
	 * 删除供应商
	 */
	@RequiresPermissions("project:supplier:remove")
	@Log(title = "供应商", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(supplierService.deleteSupplierByIds(ids));
	}
	
}
