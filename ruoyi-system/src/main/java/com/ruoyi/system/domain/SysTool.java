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

}
