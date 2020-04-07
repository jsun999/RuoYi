package com.ruoyi.process.orderReceive.service.impl;


import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.process.orderReceive.service.IOrderReceiveService;
import com.ruoyi.process.todoitem.service.IBizTodoItemService;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.mapper.SysProjectMapper;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderReceiveServiceImpl implements IOrderReceiveService {

    @Autowired
    SysProjectMapper projectMapper;

    @Autowired
    IdentityService identityService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    IBizTodoItemService bizTodoItemService;
    @Override
    public ProcessInstance submitApply(SysProject sysProject, long applyUserId) {
        sysProject.setPmUserId(applyUserId);
        sysProject.setInspectionDate(DateUtils.getNowDate());
        String businessKey = sysProject.getProjectId().toString(); // 实体类 ID，作为流程的业务 key

        // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(String.valueOf(applyUserId));

        ProcessInstance processInstance = runtimeService // 启动流程时设置业务 key
                .startProcessInstanceByKey("orderReceive", businessKey);
        String processInstanceId = processInstance.getId();
        sysProject.setInstanceId(processInstanceId); // 建立双向关系
        projectMapper.updateByPrimaryKeySelective(sysProject);

        // 下一节点处理人待办事项
        bizTodoItemService.insertTodoItem(processInstanceId, sysProject, "orderReceive");

        return processInstance;
    }
}
