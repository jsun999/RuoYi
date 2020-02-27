package com.ruoyi.system.mapper;

import com.ruoyi.system.basemapper.BaseMapper;
import com.ruoyi.system.domain.SysCalendar;

/**
 * 日历 数据层
 * 
 * @author ruoyi
 * @date 2019-08-07
 */
public interface SysCalendarMapper extends BaseMapper<SysCalendar>
{

	/**
     * 批量删除日历
     * 
     * @param calendarIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteCalendarByIds(String[] calendarIds);
	
}