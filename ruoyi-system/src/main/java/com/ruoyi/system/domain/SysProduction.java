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
	private String projectNumber;
	/** 工序 */
	private Integer productionSort;
	/** 计划开始时间 */
	private String planBeginTime;
	/** 计划结束时间 */
	private String planEndTime;
	/** 计划天数 */
	private Integer planDays;
	/** 实际开始时间 */
	private String actualBeginTime;
	/** 计划开始时间 */
	private String actualEndTime;
	/** 实际天数 */
	private Integer actualDays;

}
