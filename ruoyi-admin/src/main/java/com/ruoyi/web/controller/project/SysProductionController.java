package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysProduction;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysProductionService;
import com.ruoyi.system.service.ISysProjectService;
import com.ruoyi.system.vo.SysProductionGantt;
import com.ruoyi.system.vo.SysProductionProcessGantt;
import com.ruoyi.system.vo.SysProductionVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
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
    @Autowired
    private ISysDictDataService sysDictDataService;
    /**
     * 查询排程
     */
    @RequiresPermissions("project:production:view")
    @GetMapping("/production/{projectNumber}")
    public String detail(@PathVariable("projectNumber") String projectNumber, ModelMap mmap) {
        mmap.put("projectNumber", projectNumber);
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
        if(StringUtils.isEmpty(production.getProjectNumber())){
            List<SysProject> sysProjects = projectService.selectSysProjectListOn();
            production.setProjectNumber(sysProjects.get(0).getProjectNumber());
        }
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


    /**
     * 查询排程gantt
     */
    @RequiresPermissions("project:production:view")
    @GetMapping("/gantt/{projectNumber}")
    @ResponseBody
    public SysProductionProcessGantt processGantt(@PathVariable("projectNumber") String projectNumber) {
        SysProductionProcessGantt result = new SysProductionProcessGantt();
        SysProduction sysProduction = new SysProduction();
        sysProduction.setProjectNumber(projectNumber);
        List<SysProductionVo> sysProductionVos = productionService.selectProductionList(sysProduction);
        List<SysProductionGantt> gantts = new LinkedList<>();
        String[] planBegin = new String[sysProductionVos.size()];
        String[] planEnd = new String[sysProductionVos.size()];
        String[] actualBegin = new String[sysProductionVos.size()];
        String[] actualEnd = new String[sysProductionVos.size()];
        String[] processType = new String[sysProductionVos.size()];
        for (int i = 0; i < sysProductionVos.size(); i++) {
            SysProductionVo sysProductionVo =  sysProductionVos.get(i);
            planBegin[i]=sysProductionVo.getPlanBeginTime();
            planEnd[i] = sysProductionVo.getPlanEndTime();
            actualBegin[i]=sysProductionVo.getActualBeginTime();
            actualEnd[i]=sysProductionVo.getActualEndTime();
            processType[i] = sysDictDataService.selectDictLabel("production_process",sysProductionVo.getProcessType().toString());
        }
        SysProductionGantt gantt2 = new SysProductionGantt();
        gantt2.setName("计划时间");
        gantt2.setType(1);
        gantt2.setStack("plan");
        gantt2.setData(planEnd);
        gantts.add(gantt2);

        SysProductionGantt gantt1 = new SysProductionGantt();
        gantt1.setName("计划时间");
        gantt1.setType(0);
        gantt1.setStack("plan");
        gantt1.setData(planBegin);
        gantts.add(gantt1);

        SysProductionGantt gantt4 = new SysProductionGantt();
        gantt4.setName("实际时间");
        gantt4.setType(1);
        gantt4.setStack("actual");
        gantt4.setData(actualEnd);
        gantts.add(gantt4);
        result.setGantts(gantts);
        result.setProcessType(processType);

        SysProductionGantt gantt3 = new SysProductionGantt();
        gantt3.setName("实际时间");
        gantt3.setType(0);
        gantt3.setStack("actual");
        gantt3.setData(actualBegin);
        gantts.add(gantt3);

        result.setProjectName(projectService.selectSysProjectByNumber(projectNumber).getProjectName());
        return result;
    }
}
