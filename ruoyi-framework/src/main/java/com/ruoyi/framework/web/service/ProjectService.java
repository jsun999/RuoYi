package com.ruoyi.framework.web.service;

import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysProjectService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author jsun999
 */
@Service("project")
public class ProjectService
{
    @Autowired
    private ISysProjectService projectService;

    /**
     * 查询所有project
     */
    public List<SysProject> getProjects(Boolean delFlag)
    {
        SysProject sysProject = new SysProject();
        sysProject.setDelFlag(delFlag);
        return projectService.selectSysProjectList(sysProject);
    }

}
