package com.ruoyi.system.vo;


import com.ruoyi.system.domain.SysPart;

public class SysPartVo extends SysPart {

    private String projectName;
    private String programUserName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProgramUserName() {
        return programUserName;
    }

    public void setProgramUserName(String programUserName) {
        this.programUserName = programUserName;
    }
}
