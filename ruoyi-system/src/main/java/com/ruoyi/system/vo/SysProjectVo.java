package com.ruoyi.system.vo;

import com.ruoyi.system.domain.SysProject;
import lombok.Data;

@Data
public class SysProjectVo extends SysProject {

    private String customerName;
    private String pmUserName;
    private String taskId;
}
