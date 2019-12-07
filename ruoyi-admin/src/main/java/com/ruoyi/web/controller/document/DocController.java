package com.ruoyi.web.controller.document;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.IDocService;
import com.ruoyi.system.service.ISysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private IDocService docService;
    @Autowired
    private ISysParamService sysParamService;

    @GetMapping()
    public String document() {
        return prefix + "/document";
    }

    @GetMapping("/getKey")
    @ResponseBody
    public AjaxResult getKey(){
        return AjaxResult.success(sysParamService.getHuaweiParam());
    }

}
