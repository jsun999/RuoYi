package com.ruoyi.process.orderReceive.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.process.leave.domain.BizLeaveVo;
import com.ruoyi.process.leave.service.IBizLeaveService;
import com.ruoyi.process.orderReceive.domain.ProcessProjectVo;
import com.ruoyi.process.orderReceive.service.IOrderReceiveService;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysProjectService;
import com.ruoyi.system.vo.SysProjectVo;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/process/orderReceive")
public class OrderReceiveController extends BaseController {
    private String prefix = "process/orderReceive";

    @Autowired
    private IBizLeaveService bizLeaveService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private ISysProjectService projectService;

    @Autowired
    private IOrderReceiveService orderReceiveService;

    @Autowired
    private ISysDictDataService dictDataService;

    @RequiresPermissions("process:orderReceive:view")
    @GetMapping()
    public String leave(ModelMap mmap) {
        mmap.put("currentUser", ShiroUtils.getSysUser());
        return prefix + "/orderReceive";
    }

    /**
     * 查询订单列表
     */
    @RequiresPermissions("process:orderReceive:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysProject project) {
        startPage();
        List<SysProjectVo> list = projectService.selectSysProjectListWithCustomerName(project);
        return getDataTable(list);
    }

    /**
     * 提交评审
     */
    @Log(title = "提交评审", businessType = BusinessType.UPDATE)
    @PostMapping( "/submitApply")
    @ResponseBody
    public AjaxResult submitApply(Long projectId) {
        SysProject sysProject = projectService.selectSysProjectById(projectId);
        if(sysProject.getProjectStatus()!=0){
            return error(dictDataService.selectDictLabel("sys_project_status",sysProject.getProjectStatus().toString())+",不可发起评审。");
        }
        String loginName = ShiroUtils.getLoginName();
        sysProject.setPmUserId(ShiroUtils.getUserId());
        orderReceiveService.submitApply(sysProject, loginName);
        return success();
    }

    @RequiresPermissions("process:orderReceive:todoView")
    @GetMapping("/todo")
    public String todoView() {
        return prefix + "/orderReceiveTodo";
    }

    /**
     * 我的待办列表
     * @param processProjectVo
     * @return
     */
    @RequiresPermissions("process:orderReceive:taskList")
    @PostMapping("/taskList")
    @ResponseBody
    public TableDataInfo taskList(ProcessProjectVo processProjectVo) {
        startPage();
        List<ProcessProjectVo> list = orderReceiveService.findProjectTodoTasks(processProjectVo, ShiroUtils.getLoginName());
        return getDataTable(list);
    }

    /**
     * 加载审批弹窗
     * @param taskId
     * @param mmap
     * @return
     */
    @GetMapping("/showVerifyDialog/{taskId}")
    public String showVerifyDialog(@PathVariable("taskId") String taskId, ModelMap mmap) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        SysProject sysProject = orderReceiveService.selectById(new Long(processInstance.getBusinessKey()));
//        List<Comment> list = taskService.getProcessInstanceComments(processInstanceId);
//        list.forEach(a -> mmap.put(a.getType(),a.getFullMessage()));
        Map<String,Object> map = taskService.getVariables(taskId);
        mmap.addAllAttributes(map);
        mmap.put("project", sysProject);
        mmap.put("taskId", taskId);
        String verifyName = task.getTaskDefinitionKey().substring(0, 1).toUpperCase() + task.getTaskDefinitionKey().substring(1);
        return prefix + "/task" + verifyName;
    }

    /**
     * 完成任务
     *
     * @return
     */
    @RequestMapping(value = "/complete/{taskId}", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult complete(@PathVariable("taskId") String taskId,
                               @ModelAttribute("preloadProject") SysProject project, HttpServletRequest request) {
        Map<String, Object> variables = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        String commentType = null;
        String comment = null;          // 批注
        try {
            while (parameterNames.hasMoreElements()) {
                String parameterName = parameterNames.nextElement();
                if (parameterName.startsWith("p_")) {
                    // 参数结构：p_B_name，p为参数的前缀，B为类型，name为属性名称
                    String[] parameter = parameterName.split("_");
                    if (parameter.length == 3) {
                        String paramValue = request.getParameter(parameterName);
                        Object value = paramValue;
                        if (parameter[1].equals("B")) {
                            value = BooleanUtils.toBoolean(paramValue);
                        } else if (parameter[1].equals("COM")) {
                            commentType = parameter[2];
                            comment = paramValue;
                        }
                        variables.put(parameter[2], value);
                    } else {
                        throw new RuntimeException("invalid parameter for activiti variable: " + parameterName);
                    }
                }else if("suggestQuotation".equals(parameterName)){
                    String suggestQuotation = request.getParameter(parameterName);
                    project.setSuggestQuotation(new BigDecimal(suggestQuotation));
                }else if("lowestQuotation".equals(parameterName)){
                    String lowestQuotation = request.getParameter(parameterName);
                    project.setLowestQuotation(new BigDecimal(lowestQuotation));
                }
            }
            if (StringUtils.isNotEmpty(comment)) {
                identityService.setAuthenticatedUserId(ShiroUtils.getLoginName());
                taskService.addComment(taskId, project.getInstanceId(),commentType, comment);
            }
            orderReceiveService.complete(project, taskId, variables);

            return success("任务已完成");
        } catch (Exception e) {
            logger.error("error on complete task {}, variables={}", new Object[]{taskId, variables, e});
            return error("完成任务失败");
        }
    }

    /**
     * 自动绑定页面字段
     */
    @ModelAttribute("preloadProject")
    public SysProject getProject(@RequestParam(value = "projectId", required = false) Long projectId, HttpSession session) {
        if (projectId != null) {
            return orderReceiveService.selectById(projectId);
        }
        return new SysProject();
    }

    @RequiresPermissions("process:orderReceive:doneView")
    @GetMapping("/done")
    public String doneView() {
        return prefix + "/orderReceiveDone";
    }

    /**
     * 我的已办列表
     * @param processProjectVo
     * @return
     */
    @RequiresPermissions("process:orderReceive:taskDoneList")
    @PostMapping("/taskDoneList")
    @ResponseBody
    public TableDataInfo taskDoneList(ProcessProjectVo processProjectVo) {
        startPage();
        List<ProcessProjectVo> list = orderReceiveService.findProjectDoneTasks(processProjectVo, ShiroUtils.getUserId());
        return getDataTable(list);
    }


}
