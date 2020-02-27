package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 刀具变更表 sys_tool_record
 * 
 * @author ruoyi
 * @date 2019-08-20
 */
@Data
@Table(name="sys_tool_record")
public class SysToolRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	@Id
	private Long toolRecordId;
	/** tool_part_number */
	private String toolPartNumber;
	/** 数量 */
	@Excel(name = "领用数量")
	private Integer quantity;
	/** 操作人员 */
	private Long changeBy;
	/** 变更时间 */
	@Excel(name = "操作时间")
	private Date changeTime;
	/** 1-领用，2-报废 */
	private Integer changeType;

}
