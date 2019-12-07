package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 供应商表 sys_supplier
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
public class SysSupplier extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 供应商ID */
	private Long supplierId;
	/** 供应商名称 */
	private String supplierName;
	/** 供应商公司名称 */
	private String supplierCompanyName;
	/** 地址 */
	private String supplierAddress;
	/** 联系方式 */
	private String supplierTelephone;
	/** 邮箱 */
	private String supplierEmail;
	/** 删除标记 */
	private byte delFlag;

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public byte getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(byte delFlag) {
		this.delFlag = delFlag;
	}

	public void setSupplierId(Long supplierId)
	{
		this.supplierId = supplierId;
	}

	public Long getSupplierId() 
	{
		return supplierId;
	}
	public void setSupplierName(String supplierName) 
	{
		this.supplierName = supplierName;
	}

	public String getSupplierName() 
	{
		return supplierName;
	}
	public void setSupplierAddress(String supplierAddress) 
	{
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierAddress() 
	{
		return supplierAddress;
	}
	public void setSupplierTelephone(String supplierTelephone) 
	{
		this.supplierTelephone = supplierTelephone;
	}

	public String getSupplierTelephone() 
	{
		return supplierTelephone;
	}

	public String getSupplierCompanyName() {
		return supplierCompanyName;
	}

	public void setSupplierCompanyName(String supplierCompanyName) {
		this.supplierCompanyName = supplierCompanyName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("supplierId", getSupplierId())
            .append("supplierName", getSupplierName())
			.append("supplierCompanyName",getSupplierCompanyName())
            .append("supplierAddress", getSupplierAddress())
            .append("supplierTelephone", getSupplierTelephone())
			.append("supplierEmail", getSupplierEmail())
			.append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
