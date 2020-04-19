package com.ruoyi.process.orderReceive.service.impl;


import com.ruoyi.common.enums.ProjectStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.process.orderReceive.domain.ProcessProjectVo;
import com.ruoyi.process.orderReceive.service.IOrderReceiveService;
import com.ruoyi.process.todoitem.domain.BizTodoItem;
import com.ruoyi.process.todoitem.service.IBizTodoItemService;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysProjectMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    TaskService taskService;

    @Autowired
    IBizTodoItemService bizTodoItemService;

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public ProcessInstance submitApply(SysProject sysProject, String applyUserId) {
        sysProject.setInspectionTime(DateUtils.getNowDate());
        String businessKey = sysProject.getProjectId().toString(); // 实体类 ID，作为流程的业务 key

        // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(String.valueOf(applyUserId));

        ProcessInstance processInstance = runtimeService // 启动流程时设置业务 key
                .startProcessInstanceByKey("orderReceive", businessKey);
        String processInstanceId = processInstance.getId();

        sysProject.setProjectStatus(ProjectStatus.evaluation.getCode());
        sysProject.setDealFlag(ProjectStatus.evaluation.getCode().byteValue());
        sysProject.setInstanceId(processInstanceId); // 建立双向关系
        projectMapper.updateByPrimaryKeySelective(sysProject);

        // 下一节点处理人待办事项
        bizTodoItemService.insertProjectTodoItem(processInstanceId, sysProject, "orderReceive");

        return processInstance;
    }


    @Override
    @Transactional(readOnly = true)
    public List<ProcessProjectVo> findProjectTodoTasks(ProcessProjectVo processProjectVo, String userId) {
        List<ProcessProjectVo> results = new ArrayList<>();
        List<Task> tasks = new ArrayList<Task>();
        // 根据当前人的ID查询
        List<Task> todoList = taskService.createTaskQuery().processDefinitionKey("orderReceive").taskAssignee(String.valueOf(userId)).list();
        // 根据当前人未签收的任务
        List<Task> unsignedTasks = taskService.createTaskQuery().processDefinitionKey("orderReceive").taskCandidateUser(userId).list();
        // 合并
        tasks.addAll(todoList);
        tasks.addAll(unsignedTasks);
        // 根据流程的业务ID查询实体并关联
        for (Task task : tasks) {
            String processInstanceId = task.getProcessInstanceId();
            // 条件过滤 1
            if (StringUtils.isNotBlank(processProjectVo.getInstanceId()) && !processProjectVo.getInstanceId().equals(processInstanceId)) {
                continue;
            }
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            String businessKey = processInstance.getBusinessKey();
            SysProject project = projectMapper.selectByPrimaryKey(new Long(businessKey));
            ProcessProjectVo projectVo = new ProcessProjectVo();
            BeanUtils.copyProperties(project, projectVo);
            projectVo.setTaskId(task.getId());
            projectVo.setTaskName(task.getName());
            SysUser sysUser = userMapper.selectUserById(project.getPmUserId());
            projectVo.setPmUserName(sysUser.getUserName());
            results.add(projectVo);
        }
        return results;
    }

    @Override
    public SysProject selectById(Long id) {

        return projectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void complete(SysProject project, String taskId, Map<String, Object> variables) {
        projectMapper.updateByPrimaryKeySelective(project);
        // 只有签收任务，act_hi_taskinst 表的 assignee 字段才不为 null
        taskService.claim(taskId, ShiroUtils.getLoginName());
        taskService.complete(taskId, variables);
        // 更新待办事项状态
        BizTodoItem query = new BizTodoItem();
        query.setTaskId(taskId);
        // 考虑到候选用户组，会有多个 todoitem 办理同个 task
        List<BizTodoItem> updateList = CollectionUtils.isEmpty(bizTodoItemService.selectBizTodoItemList(query)) ? null : bizTodoItemService.selectBizTodoItemList(query);
        for (BizTodoItem update: updateList) {
            // 找到当前登录用户的 todoitem，置为已办
            if (update.getTodoUserId().equals(ShiroUtils.getLoginName())) {
                update.setIsView("1");
                update.setIsHandle("1");
                update.setHandleUserId(ShiroUtils.getLoginName());
                update.setHandleUserName(ShiroUtils.getSysUser().getUserName());
                update.setHandleTime(DateUtils.getNowDate());
                bizTodoItemService.updateBizTodoItem(update);
            } else {
                bizTodoItemService.deleteBizTodoItemById(update.getId()); // 删除候选用户组其他 todoitem
            }
        }

        // 下一节点处理人待办事项
        bizTodoItemService.insertProjectTodoItem(project.getInstanceId(), project, "orderReceive");
    }
}
