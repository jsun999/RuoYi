package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.Table;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 外发记录表 sys_outsource
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
@Data
@Table(name="sys_outsource")
public class SysOutsource extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 外发ID */
	private Long outsourceId;
	/** 零件ID */
	private Long partId;
	/** 项目ID */
	private Long projectId;
	/** pm */
	private Long pmUserId;
	/** 规格 */
	private String norms;
	/** 报价 */
	private BigDecimal ourUnitprice;
	/** 总数 */
	private Integer ourQuantity;
	/** 总价 */
	private BigDecimal ourTotalamount;
	/** 交期 */
	private String ourDeliveryTime;
	/** 外发单价 */
	private BigDecimal outUnitprice;
	/** 外发数量 */
	private Integer outQuantity;
	/** 外发总价 */
	private BigDecimal outTotalamount;
	/** 外发状态 */
	private Integer status;
	/** 外发交期 */
	private String outDeliveryTime;
	/** 是否删除 */
	private Integer delFlag;

	public Long getPmUserId() {
		return pmUserId;
	}

	public void setPmUserId(Long pmUserId) {
		this.pmUserId = pmUserId;
	}

	public void setOutsourceId(Long outsourceId)
	{
		this.outsourceId = outsourceId;
	}

	public Long getOutsourceId() 
	{
		return outsourceId;
	}
	public void setPartId(Long partId) 
	{
		this.partId = partId;
	}

	public Long getPartId() 
	{
		return partId;
	}
	public void setProjectId(Long projectId) 
	{
		this.projectId = projectId;
	}

	public Long getProjectId() 
	{
		return projectId;
	}
	public void setNorms(String norms) 
	{
		this.norms = norms;
	}

	public String getNorms() 
	{
		return norms;
	}
	public void setOurUnitprice(BigDecimal ourUnitprice) 
	{
		this.ourUnitprice = ourUnitprice;
	}

	public BigDecimal getOurUnitprice() 
	{
		return ourUnitprice;
	}
	public void setOurQuantity(Integer ourQuantity) 
	{
		this.ourQuantity = ourQuantity;
	}

	public Integer getOurQuantity() 
	{
		return ourQuantity;
	}
	public void setOurTotalamount(BigDecimal ourTotalamount) 
	{
		this.ourTotalamount = ourTotalamount;
	}

	public BigDecimal getOurTotalamount() 
	{
		return ourTotalamount;
	}
	public void setOurDeliveryTime(String ourDeliveryTime)
	{
		this.ourDeliveryTime = ourDeliveryTime;
	}

	public String getOurDeliveryTime()
	{
		return ourDeliveryTime;
	}
	public void setOutUnitprice(BigDecimal outUnitprice) 
	{
		this.outUnitprice = outUnitprice;
	}

	public BigDecimal getOutUnitprice() 
	{
		return outUnitprice;
	}
	public void setOutQuantity(Integer outQuantity) 
	{
		this.outQuantity = outQuantity;
	}

	public Integer getOutQuantity() 
	{
		return outQuantity;
	}
	public void setOutTotalamount(BigDecimal outTotalamount) 
	{
		this.outTotalamount = outTotalamount;
	}

	public BigDecimal getOutTotalamount() 
	{
		return outTotalamount;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setOutDeliveryTime(String outDeliveryTime)
	{
		this.outDeliveryTime = outDeliveryTime;
	}

	public String getOutDeliveryTime()
	{
		return outDeliveryTime;
	}
	public void setDelFlag(Integer delFlag) 
	{
		this.delFlag = delFlag;
	}

	public Integer getDelFlag() 
	{
		return delFlag;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("outsourceId", getOutsourceId())
            .append("partId", getPartId())
            .append("projectId", getProjectId())
            .append("norms", getNorms())
            .append("ourUnitprice", getOurUnitprice())
            .append("ourQuantity", getOurQuantity())
            .append("ourTotalamount", getOurTotalamount())
            .append("ourDeliveryTime", getOurDeliveryTime())
            .append("outUnitprice", getOutUnitprice())
            .append("outQuantity", getOutQuantity())
            .append("outTotalamount", getOutTotalamount())
            .append("status", getStatus())
            .append("outDeliveryTime", getOutDeliveryTime())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
