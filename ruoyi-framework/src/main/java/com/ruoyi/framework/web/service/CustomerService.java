package com.ruoyi.framework.web.service;

import com.ruoyi.system.domain.SysCustomer;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysCustomerService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author jsun999
 */
@Service("customer")
public class CustomerService
{
    @Autowired
    private ISysCustomerService sysCustomerService;

    /**
     * 根据客户列表
     *
     * @param delflag 字典类型
     * @return 参数键值
     */
    public List<SysCustomer> getCustomer(byte delflag)
    {
        SysCustomer sysCustomer = new SysCustomer();
        sysCustomer.setDelFlag(delflag);
        return sysCustomerService.selectCustomerList(sysCustomer);
    }
//
//    /**
//     * 根据字典类型和字典键值查询字典数据信息
//     *
//     * @param dictType 字典类型
//     * @param dictValue 字典键值
//     * @return 字典标签
//     */
//    public String getLabel(String dictType, String dictValue)
//    {
//        return userService.selectDictLabel(dictType, dictValue);
//    }
}
