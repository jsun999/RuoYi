package com.ruoyi.process.leave.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BizLeaveVo extends BizLeave {

    /** 申请人姓名 */
    private String applyUserName;

    /** 任务ID */
    private String taskId;

    /** 任务名称 */
    private String taskName;

    /** 办理时间 */
    private Date doneTime;

    /** 创建人 */
    private String createUserName;

}
