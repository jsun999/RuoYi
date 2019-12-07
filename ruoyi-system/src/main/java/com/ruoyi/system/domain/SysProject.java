package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目表 sys_project
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public class SysProject extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 项目ID */
	private Long projectId;
	/** 项目名称 */
	private String projectName;
	/** 客户ID */
	private Long customerId;
	/** 项目状态 */
	private Integer status;
	/** 金额 */
	private BigDecimal amount;
	/** 负责人（user_id） */
	private Long pmUserId;
	/** 交期 */
	private String deliveryDate;
	/** 是否接单 */
	private Byte dealFlag;
	/** 是否删除 */
	private Boolean delFlag;

	public Byte getDealFlag() {
		return dealFlag;
	}

	public void setDealFlag(Byte dealFlag) {
		this.dealFlag = dealFlag;
	}


	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
	}

	public Long getProjectId() 
	{
		return projectId;
	}
	public void setProjectName(String projectName) 
	{
		this.projectName = projectName;
	}

	public String getProjectName() 
	{
		return projectName;
	}
	public void setCustomerId(Long customerId) 
	{
		this.customerId = customerId;
	}

	public Long getCustomerId() 
	{
		return customerId;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}

	public BigDecimal getAmount() 
	{
		return amount;
	}
	public void setPmUserId(Long pmUserId) 
	{
		this.pmUserId = pmUserId;
	}

	public Long getPmUserId()
	{
		return pmUserId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("customerId", getCustomerId())
            .append("status", getStatus())
            .append("amount", getAmount())
            .append("pmUserId", getPmUserId())
			.append("deliveryDate",getDeliveryDate())
			.append("dealFlag",getDealFlag())
			.append("delFlag",getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
