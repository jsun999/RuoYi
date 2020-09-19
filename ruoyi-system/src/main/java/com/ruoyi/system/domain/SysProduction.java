package com.ruoyi.system.domain;

import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productionId;
	/** 项目编号 */
	private String projectNumber;
	/** 工序 */
	private Integer processType;
	/** 排序 */
	private Integer processSort;
	/** 计划开始时间 */
	private String planBeginTime;
	/** 计划结束时间 */
	private String planEndTime;
	/** 计划天数 */
	private Integer planDays;
	/** 实际开始时间 */
	private String actualBeginTime;
	/** 实际结束时间 */
	private String actualEndTime;
	/** 实际天数 */
	private Integer actualDays;

}
