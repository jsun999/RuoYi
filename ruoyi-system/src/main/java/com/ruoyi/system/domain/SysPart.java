package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 零件表 sys_part
 * 
 * @author ruoyi
 * @date 2019-07-09
 */
public class SysPart extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 零件ID */
	private Long partId;
	/** 项目ID */
	private Long projectId;
	/** pm */
	private Long pmUserId;
	/** 零件名称 */
	private String partName;
	/** 规格 */
	private String partNorms;
	/** 数量 */
	private Integer quantity;
	/** 单价 */
	private BigDecimal unitPrice;
	/** 交货时间 */
	private String deliveryTime;
	/** 工艺类型 */
	private Integer craftType;
	/** 编程 */
	private Long programUser;
	/** 删除标记 */
	private Boolean delFlag;

	public Long getPmUserId() {
		return pmUserId;
	}

	public void setPmUserId(Long pmUserId) {
		this.pmUserId = pmUserId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Integer getCraftType() {
		return craftType;
	}

	public void setCraftType(Integer craftType) {
		this.craftType = craftType;
	}

	public Long getProgramUser() {
		return programUser;
	}

	public void setProgramUser(Long programUser) {
		this.programUser = programUser;
	}

	public void setPartId(Long partId)
	{
		this.partId = partId;
	}

	public Long getPartId() 
	{
		return partId;
	}
	public void setPartName(String partName) 
	{
		this.partName = partName;
	}

	public String getPartName() 
	{
		return partName;
	}
	public void setPartNorms(String partNorms)
	{
		this.partNorms = partNorms;
	}

	public String getPartNorms()
	{
		return partNorms;
	}
	public void setQuantity(Integer quantity) 
	{
		this.quantity = quantity;
	}

	public Integer getQuantity() 
	{
		return quantity;
	}
	public void setUnitPrice(BigDecimal unitPrice) 
	{
		this.unitPrice = unitPrice;
	}

	public BigDecimal getUnitPrice() 
	{
		return unitPrice;
	}
	public void setDeliveryTime(String deliveryTime)
	{
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryTime()
	{
		return deliveryTime;
	}
	public void setDelFlag(Boolean delFlag)
	{
		this.delFlag = delFlag;
	}

	public Boolean getDelFlag()
	{
		return delFlag;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("partId", getPartId())
            .append("partName", getPartName())
            .append("partNorms", getPartNorms())
            .append("quantity", getQuantity())
            .append("unitPrice", getUnitPrice())
            .append("deliveryTime", getDeliveryTime())
			.append("craftType", getCraftType())
			.append("programUser", getProgramUser())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
