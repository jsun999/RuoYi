package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysCalendar;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ICalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * 日历 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-07
 */
@Controller
@RequestMapping("/system/calendar")
public class CalendarController extends BaseController
{
    private String prefix = "system/calendar";
	
	@Autowired
	private ICalendarService calendarService;
	
	/**
	 * 查询日历列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysCalendar calendar)
	{
		startPage();
		SysUser sysUser = ShiroUtils.getSysUser();
		calendar.setUserId(sysUser.getUserId());
        List<SysCalendar> list = calendarService.selectCalendarList(calendar);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出日历列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCalendar calendar)
    {
    	List<SysCalendar> list = calendarService.selectCalendarList(calendar);
        ExcelUtil<SysCalendar> util = new ExcelUtil<SysCalendar>(SysCalendar.class);
        return util.exportExcel(list, "calendar");
    }
	

	/**
	 * 新增保存日历
	 */
	@Log(title = "日历", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysCalendar calendar)
	{
		SysUser sysUser = ShiroUtils.getSysUser();
		calendar.setUserId(sysUser.getUserId());
		calendarService.insertCalendar(calendar);
		return AjaxResult.success(calendar.getCalendarId().toString());
	}


	/**
	 * 修改保存日历
	 */
	@Log(title = "日历", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysCalendar calendar)
	{
		try {
			calendar.setStartTime(Convert.toDateTime(calendar.getStartTime()));
			if(calendar.getEndTime()!=null){
				calendar.setEndTime(Convert.toDateTime(calendar.getEndTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return toAjax(calendarService.updateCalendar(calendar));
	}
	
	/**
	 * 删除日历
	 */
	@Log(title = "日历", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String id)
	{		
		return toAjax(calendarService.deleteCalendarByIds(id));
	}
	
}
