package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 * 
 * @author ruoyi
 */
@Data
@Table(name="sys_menu")
public class SysMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    
    /** 菜单ID */
    @Id
    private Long menuId;
    
    /** 菜单名称 */
    private String menuName;
    
    /** 父菜单名称 */
    private String parentName;
    
    /** 父菜单ID */
    private Long parentId;
    
    /** 显示顺序 */
    private String orderNum;
    
    /** 菜单URL */
    private String url;
    
    /** 类型:0目录,1菜单,2按钮 */
    private String menuType;
    
    /** 菜单状态:0显示,1隐藏 */
    private String visible;
    
    /** 权限字符串 */
    private String perms;
    
    /** 菜单图标 */
    private String icon;
    
    /** 子菜单 */
    private List<SysMenu> children = new ArrayList<SysMenu>();


}
