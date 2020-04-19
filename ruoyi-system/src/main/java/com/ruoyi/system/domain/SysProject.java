package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目表 sys_project
 * 
 * @author ruoyi
 * @date 2019-07-01
 *
 */
@Data
@Accessors(chain = true)
@Table(name="sys_project")
public class SysProject extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 项目ID */
	@Id
	//主键则由数据库自动维护
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectId;
	/** 项目编号 */
	private String projectNumber;
	/** 项目名称 */
	private String projectName;
	/** 客户ID */
	private Long customerId;
	/** 图档位置 */
	private String filePath;
	/** 项目状态 */
	private Integer projectStatus;
	/** 建议报价 */
	private BigDecimal suggestQuotation;
	/** 最低报价 */
	private BigDecimal lowestQuotation;
	/** 实际报价 */
	private BigDecimal actualQuotation;
	/** 金额 */
	private BigDecimal amount;
	/** 负责人（user_id） */
	private Long pmUserId;
	/** 交期 */
	private String deliveryDate;
	/** 接单状态 */
	private Byte dealFlag;
	/** 是否删除 */
	private Boolean delFlag;
	/** 实际交货时间 */
	private Date shippingTime;
	/**
	 * activiti实例Id
	 */
	private String instanceId;
	/** 评审时间 */
	private Date inspectionTime;
}
