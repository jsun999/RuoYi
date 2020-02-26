package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.mapper.basemapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CalendarMapper;
import com.ruoyi.system.domain.SysCalendar;
import com.ruoyi.system.service.ICalendarService;
import com.ruoyi.common.core.text.Convert;

/**
 * 日历 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-07
 */
@Service
public class CalendarServiceImpl extends BaseServiceImpl<SysCalendar>  implements ICalendarService
{
	@Autowired
	private CalendarMapper calendarMapper;

	@Override
	public BaseMapper<SysCalendar> getBaseMapper() {
		return calendarMapper;
	}

	/**
     * 查询日历信息
     * 
     * @param calendarId 日历ID
     * @return 日历信息
     */
    @Override
	public SysCalendar selectCalendarById(Long calendarId)
	{
	    return calendarMapper.selectCalendarById(calendarId);
	}
	
	/**
     * 查询日历列表
     * 
     * @param calendar 日历信息
     * @return 日历集合
     */
	@Override
	public List<SysCalendar> selectCalendarList(SysCalendar calendar)
	{
	    return calendarMapper.selectCalendarList(calendar);
	}
	
    /**
     * 新增日历
     * 
     * @param calendar 日历信息
     * @return 结果
     */
	@Override
	public int insertCalendar(SysCalendar calendar)
	{
	    return calendarMapper.insertCalendar(calendar);
	}
	
	/**
     * 修改日历
     * 
     * @param calendar 日历信息
     * @return 结果
     */
	@Override
	public int updateCalendar(SysCalendar calendar)
	{
	    return calendarMapper.updateCalendar(calendar);
	}

	/**
     * 删除日历对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCalendarByIds(String ids)
	{
		return calendarMapper.deleteCalendarByIds(Convert.toStrArray(ids));
	}
	
}
