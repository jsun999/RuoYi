package com.ruoyi.system.vo;

import com.ruoyi.system.domain.SysProject;

public class SysProjectVo extends SysProject {

    private String customerName;
    private String pmUserName;

    public String getCustomerName() {
        return customerName;
    }

    public String getPmUserName() {
        return pmUserName;
    }

    public void setPmUserName(String pmUserName) {
        this.pmUserName = pmUserName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
