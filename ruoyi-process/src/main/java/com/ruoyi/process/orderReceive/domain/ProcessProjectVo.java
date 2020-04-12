package com.ruoyi.process.orderReceive.domain;

import com.ruoyi.system.domain.SysProject;
import lombok.Data;

import java.util.Date;

@Data
public class ProcessProjectVo extends SysProject {

    /** pm姓名 */
    private String pmUserName;

    /** 任务ID */
    private String taskId;

    /** 任务名称 */
    private String taskName;

    /** 办理时间 */
    private Date doneTime;

    /** 创建人 */
    private String createUserName;

}
