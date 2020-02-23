package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 刀具变更表 sys_tool_record
 * 
 * @author ruoyi
 * @date 2019-08-20
 */
public class SysToolRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long toolRecordId;
	/** tool_part_number */
	private String toolPartNumber;
	/** 数量 */
	@Excel(name = "领用数量")
	private Integer quantity;
	/** 操作人员 */
	private Long changeBy;
	/** 变更时间 */
	@Excel(name = "操作时间")
	private Date changeTime;
	/** 1-领用，2-报废 */
	private Integer changeType;

	public void setToolRecordId(Long toolRecordId) 
	{
		this.toolRecordId = toolRecordId;
	}

	public Long getToolRecordId() 
	{
		return toolRecordId;
	}
	public void setToolPartNumber(String toolPartNumber)
	{
		this.toolPartNumber = toolPartNumber;
	}

	public String getToolPartNumber()
	{
		return toolPartNumber;
	}
	public void setQuantity(Integer quantity) 
	{
		this.quantity = quantity;
	}

	public Integer getQuantity() 
	{
		return quantity;
	}
	public void setChangeBy(Long changeBy) 
	{
		this.changeBy = changeBy;
	}

	public Long getChangeBy() 
	{
		return changeBy;
	}
	public void setChangeTime(Date changeTime) 
	{
		this.changeTime = changeTime;
	}

	public Date getChangeTime() 
	{
		return changeTime;
	}
	public void setChangeType(Integer changeType) 
	{
		this.changeType = changeType;
	}

	public Integer getChangeType() 
	{
		return changeType;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("toolRecordId", getToolRecordId())
            .append("toolId", getToolPartNumber())
            .append("quantity", getQuantity())
            .append("changeBy", getChangeBy())
            .append("changeTime", getChangeTime())
            .append("changeType", getChangeType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
