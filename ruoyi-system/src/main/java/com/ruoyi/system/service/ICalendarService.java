package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysCalendar;

import java.util.List;

/**
 * 日历 服务层
 * 
 * @author ruoyi
 * @date 2019-08-07
 */
public interface ICalendarService extends BaseService<SysCalendar>
{
	/**
     * 查询日历信息
     * 
     * @param calendarId 日历ID
     * @return 日历信息
     */
	public SysCalendar selectCalendarById(Long calendarId);
	
	/**
     * 查询日历列表
     * 
     * @param calendar 日历信息
     * @return 日历集合
     */
	public List<SysCalendar> selectCalendarList(SysCalendar calendar);
	
	/**
     * 新增日历
     * 
     * @param calendar 日历信息
     * @return 结果
     */
	public int insertCalendar(SysCalendar calendar);
	
	/**
     * 修改日历
     * 
     * @param calendar 日历信息
     * @return 结果
     */
	public int updateCalendar(SysCalendar calendar);
		
	/**
     * 删除日历信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCalendarByIds(String ids);
	
}
