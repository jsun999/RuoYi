package com.ruoyi.framework.web.service;

import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author jsun999
 */
@Service("user")
public class UserService
{
    @Autowired
    private ISysUserService userService;

    /**
     * 根据部门查询员工数据信息
     *
     * @param deptId 字典类型
     * @return 参数键值
     */
    public List<SysUser> getUser(Long deptId)
    {
        SysUser sysUser = new SysUser();
        sysUser.setDeptId(deptId);
        return userService.selectUserList(sysUser);
    }

}
