package com.ruoyi.framework.web.service;

import com.ruoyi.system.domain.SysPart;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysDictTypeService;
import com.ruoyi.system.service.ISysPartService;
import com.ruoyi.system.service.ISysPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author jsun999
 */
@Service("part")
public class PartService
{
    @Autowired
    private ISysPartService partService;
    /**
     * 查询所有part
     */
    public List<SysPart> getParts(Boolean delFlag,String type)
    {
        SysPart sysPart = new SysPart();
        sysPart.setDelFlag(delFlag);
        if("外发".equals(type)){
            sysPart.setCraftType(4);
        }
        return partService.selectPartList(sysPart);
    }

}
