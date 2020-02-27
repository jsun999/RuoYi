package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 生产排程表 sys_production
 * 
 * @author ruoyi
 * @date 2019-08-28
 */
@Data
@Table(name="sys_production")
public class SysProduction extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	@Id
	private Long productionId;
	/** 项目编号 */
	private Long projectId;
	/** dict_code */
	private Long projectStatus;
	/** 工序 */
	private Integer productionSort;
	/** 计划开始时间 */
	private Date planBeginTime;
	/** 计划结束时间 */
	private Date planEndTime;
	/** 计划天数 */
	private Integer planDays;
	/** 实际开始时间 */
	private Date actualBeginTime;
	/** 计划开始时间 */
	private Date actualEndTime;
	/** 实际天数 */
	private Integer actualDays;

	public Integer getProductionSort() {
		return productionSort;
	}

	public void setProductionSort(Integer productionSort) {
		this.productionSort = productionSort;
	}

	public void setProductionId(Long productionId)
	{
		this.productionId = productionId;
	}

	public Long getProductionId() 
	{
		return productionId;
	}
	public void setProjectId(Long projectId) 
	{
		this.projectId = projectId;
	}

	public Long getProjectId() 
	{
		return projectId;
	}
	public void setProjectStatus(Long projectStatus) 
	{
		this.projectStatus = projectStatus;
	}

	public Long getProjectStatus() 
	{
		return projectStatus;
	}
	public void setPlanBeginTime(Date planBeginTime) 
	{
		this.planBeginTime = planBeginTime;
	}

	public Date getPlanBeginTime() 
	{
		return planBeginTime;
	}
	public void setPlanEndTime(Date planEndTime) 
	{
		this.planEndTime = planEndTime;
	}

	public Date getPlanEndTime() 
	{
		return planEndTime;
	}
	public void setPlanDays(Integer planDays) 
	{
		this.planDays = planDays;
	}

	public Integer getPlanDays() 
	{
		return planDays;
	}
	public void setActualBeginTime(Date actualBeginTime) 
	{
		this.actualBeginTime = actualBeginTime;
	}

	public Date getActualBeginTime() 
	{
		return actualBeginTime;
	}
	public void setActualEndTime(Date actualEndTime) 
	{
		this.actualEndTime = actualEndTime;
	}

	public Date getActualEndTime() 
	{
		return actualEndTime;
	}
	public void setActualDays(Integer actualDays) 
	{
		this.actualDays = actualDays;
	}

	public Integer getActualDays() 
	{
		return actualDays;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productionId", getProductionId())
            .append("projectId", getProjectId())
            .append("projectStatus", getProjectStatus())
			.append("productionSort",getProductionSort())
            .append("planBeginTime", getPlanBeginTime())
            .append("planEndTime", getPlanEndTime())
            .append("planDays", getPlanDays())
            .append("actualBeginTime", getActualBeginTime())
            .append("actualEndTime", getActualEndTime())
            .append("actualDays", getActualDays())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
