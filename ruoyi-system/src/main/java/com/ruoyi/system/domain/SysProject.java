package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

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
	private Long projectId;
	/** 项目编号 */
	private String projectNumber;
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

}
