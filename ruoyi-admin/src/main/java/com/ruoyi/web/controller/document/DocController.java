package com.ruoyi.web.controller.document;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.ISysParamService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


/**
 * 文档管理
 * 
 * @author jsun
 */
@Controller
@RequestMapping("/document")
public class DocController extends BaseController
{
    private String prefix = "document";

    @Autowired
    private ISysParamService sysParamService;

    @RequiresPermissions("document:manage")
    @GetMapping()
    public String document() {
        return prefix + "/document";
    }

    @RequiresPermissions("document:manage")
    @GetMapping("/upload/{projectId}")
    public String upload(@PathVariable("projectId") String projectId, ModelMap mmap) {
        mmap.put("projectId", projectId);
        return prefix + "/document";
    }

    @RequiresPermissions("document:manage")
    @GetMapping("/goto")
    public String gotoPath(String path, ModelMap mmap) {
        mmap.put("filePath", path);
        return prefix + "/document";
    }

    @GetMapping("/getKey")
    @ResponseBody
    public AjaxResult getKey(){
        return AjaxResult.success(sysParamService.getHuaweiParam());
    }

}
