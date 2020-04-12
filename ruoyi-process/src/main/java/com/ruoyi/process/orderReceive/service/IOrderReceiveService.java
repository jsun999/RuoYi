package com.ruoyi.process.orderReceive.service;

import com.ruoyi.process.leave.domain.BizLeaveVo;
import com.ruoyi.process.orderReceive.domain.ProcessProjectVo;
import com.ruoyi.system.domain.SysProject;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.List;
import java.util.Map;


public interface IOrderReceiveService {

    ProcessInstance submitApply(SysProject sysProject, long applyUserId);

    List<ProcessProjectVo> findProjectTodoTasks(ProcessProjectVo processProjectVo, String loginName);

    SysProject selectById(Long id);

    void complete(SysProject project, String taskId, Map<String, Object> variables);
}
