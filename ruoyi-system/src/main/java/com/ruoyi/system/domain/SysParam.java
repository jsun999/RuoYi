package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.Table;

/**
 * 参数表 sys_param
 * 
 * @author ruoyi
 * @date 2019-05-27
 */
@Data
@Table(name="sys_param")
public class SysParam extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 参数名 */
	private String paramName;
	/** 参数值 */
	private String paramValue;
	/** 描述 */
	private String descr;
	/** 排序 */
	private Integer sort;
	/** 类型：1:全局 2:个人 */
	private Integer type;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setParamName(String paramName) 
	{
		this.paramName = paramName;
	}

	public String getParamName() 
	{
		return paramName;
	}
	public void setParamValue(String paramValue) 
	{
		this.paramValue = paramValue;
	}

	public String getParamValue() 
	{
		return paramValue;
	}
	public void setDescr(String descr) 
	{
		this.descr = descr;
	}

	public String getDescr() 
	{
		return descr;
	}
	public void setSort(Integer sort) 
	{
		this.sort = sort;
	}

	public Integer getSort() 
	{
		return sort;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("paramName", getParamName())
            .append("paramValue", getParamValue())
            .append("descr", getDescr())
            .append("sort", getSort())
            .append("type", getType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
