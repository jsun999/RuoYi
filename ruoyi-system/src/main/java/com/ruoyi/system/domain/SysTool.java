package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 刀具表 sys_tool
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
@Data
@Table(name="sys_tool")
public class SysTool extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 刀具ID */
	@Id
	private Long toolId;
	/** 料号 */
	private String toolPartNumber;
	/** 刀具类型（字典） */
	private Long toolType;
	/** 规格 */
	private String toolSpecifications;
	/** 数量 */
	private Integer quantity;
	/** 价格 */
	private BigDecimal toolPrice;
	/** 材质（字典） */
	private Long toolMaterial;
	/** 制造商 */
	private String manufacturer;
	/** 1-新刀，2-使用中，3-报废 */
	private Integer status;

	public String getToolPartNumber() {
		return toolPartNumber;
	}

	public void setToolPartNumber(String toolPartNumber) {
		this.toolPartNumber = toolPartNumber;
	}

	public String getToolSpecifications() {
		return toolSpecifications;
	}

	public void setToolSpecifications(String toolSpecifications) {
		this.toolSpecifications = toolSpecifications;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setToolId(Long toolId)
	{
		this.toolId = toolId;
	}

	public Long getToolId() 
	{
		return toolId;
	}
	public void setToolType(Long toolType) 
	{
		this.toolType = toolType;
	}

	public Long getToolType() 
	{
		return toolType;
	}
	public void setToolPrice(BigDecimal toolPrice) 
	{
		this.toolPrice = toolPrice;
	}

	public BigDecimal getToolPrice() 
	{
		return toolPrice;
	}
	public void setToolMaterial(Long toolMaterial) 
	{
		this.toolMaterial = toolMaterial;
	}

	public Long getToolMaterial() 
	{
		return toolMaterial;
	}
	public void setManufacturer(String manufacturer) 
	{
		this.manufacturer = manufacturer;
	}

	public String getManufacturer() 
	{
		return manufacturer;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("toolId", getToolId())
			.append("toolPartNumber",getToolPartNumber())
            .append("toolType", getToolType())
            .append("toolPrice", getToolPrice())
            .append("toolMaterial", getToolMaterial())
            .append("manufacturer", getManufacturer())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
