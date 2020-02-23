package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 日历表 sys_calendar
 * 
 * @author ruoyi
 * @date 2019-08-07
 */
public class Calendar extends BaseEntity
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

	public void setCalendarId(Long calendarId) 
	{
		this.calendarId = calendarId;
	}

	public Long getCalendarId() 
	{
		return calendarId;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getStartTime()
	{
		return startTime;
	}
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("calendarId", getCalendarId())
            .append("content", getContent())
            .append("userId", getUserId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
