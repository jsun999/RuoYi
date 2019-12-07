package com.ruoyi.system.vo;


import com.ruoyi.system.domain.SysOutsource;

public class SysOutsourceVo extends SysOutsource {

    private String projectName;
    private String partName;
    private String pmUserName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPmUserName() {
        return pmUserName;
    }

    public void setPmUserName(String pmUserName) {
        this.pmUserName = pmUserName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}
