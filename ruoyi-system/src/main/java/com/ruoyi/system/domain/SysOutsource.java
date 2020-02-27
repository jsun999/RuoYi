package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 外发记录表 sys_outsource
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
@Data
@Table(name="sys_outsource")
public class SysOutsource extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 外发ID */
	@Id
	private Long outsourceId;
	/** 零件ID */
	private Long partId;
	/** 项目ID */
	private Long projectId;
	/** pm */
	private Long pmUserId;
	/** 规格 */
	private String norms;
	/** 报价 */
	private BigDecimal ourUnitprice;
	/** 总数 */
	private Integer ourQuantity;
	/** 总价 */
	private BigDecimal ourTotalamount;
	/** 交期 */
	private String ourDeliveryTime;
	/** 外发单价 */
	private BigDecimal outUnitprice;
	/** 外发数量 */
	private Integer outQuantity;
	/** 外发总价 */
	private BigDecimal outTotalamount;
	/** 外发状态 */
	private Integer status;
	/** 外发交期 */
	private String outDeliveryTime;
	/** 是否删除 */
	private Integer delFlag;
}
