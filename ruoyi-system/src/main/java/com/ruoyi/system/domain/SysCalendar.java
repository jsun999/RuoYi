package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.Table;
import java.util.Date;

/**
 * 日历表 sys_calendar
 * 
 * @author ruoyi
 * @date 2019-08-07
 */
@Data
@Table(name="sys_calendar")
public class SysCalendar extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long calendarId;
	/** 内容 */
	private String content;
	/** 谁的 */
	private Long userId;
	/** 开始时间 */
	private String startTime;
	/** 结束时间 */
	private String endTime;

}
