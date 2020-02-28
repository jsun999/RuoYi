package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysProduction;
import com.ruoyi.system.service.ISysProductionService;
import com.ruoyi.system.service.ISysProjectService;
import com.ruoyi.system.vo.SysProductionVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 生产排程 信息操作处理
 *
 * @author ruoyi
 * @date 2019-08-28
 */
@Controller
@RequestMapping("/project/production")
public class SysProductionController extends BaseController {
    private String prefix = "project/production";

    @Autowired
    private ISysProductionService productionService;
    @Autowired
    private ISysProjectService projectService;

    /**
     * 查询排程
     */
    @RequiresPermissions("project:production:view")
    @GetMapping("/production/{projectNumber}")
    public String detail(@PathVariable("projectNumber") String projectNumber, ModelMap mmap) {
        SysProduction sysProduction = new SysProduction();
        sysProduction.setProjectNumber(projectNumber);
        mmap.put("productionList", productionService.selectProductionList(sysProduction));
        mmap.put("project", projectService.selectSysProjectByNumber(projectNumber));
        return prefix + "/production";
    }

    @RequiresPermissions("project:production:view")
    @GetMapping()
    public String project() {
        return prefix + "/production";
    }

    /**
     * 查询生产排程列表
     */
    @RequiresPermissions("project:production:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysProduction production) {
        startPage();
        List<SysProductionVo> list = productionService.selectProductionList(production);
        return getDataTable(list);
    }


    /**
     * 导出生产排程列表
     */
    @RequiresPermissions("project:production:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysProduction production) {
        List<SysProductionVo> list = productionService.selectProductionList(production);
        ExcelUtil<SysProductionVo> util = new ExcelUtil<SysProductionVo>(SysProductionVo.class);
        return util.exportExcel(list, "production");
    }

    /**
     * 新增生产排程
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存生产排程
     */
    @RequiresPermissions("project:production:add")
    @Log(title = "生产排程", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysProduction production) {
        return toAjax(productionService.insertProduction(production));
    }

    /**
     * 修改生产排程
     */
    @GetMapping("/edit/{productionId}")
    public String edit(@PathVariable("productionId") Long productionId, ModelMap mmap) {
        SysProduction production = productionService.selectProductionById(productionId);
        mmap.put("production", production);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产排程
     */
    @RequiresPermissions("project:production:edit")
    @Log(title = "生产排程", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysProduction production) {
        return toAjax(productionService.updateProduction(production));
    }

    /**
     * 删除生产排程
     */
    @RequiresPermissions("project:production:remove")
    @Log(title = "生产排程", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(productionService.deleteProductionByIds(ids));
    }

}
