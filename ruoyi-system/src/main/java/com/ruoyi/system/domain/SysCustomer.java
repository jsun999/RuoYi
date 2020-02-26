package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Table;

/**
 * 客户表 sys_customer
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
@Data
@Table(name="sys_customer")
public class SysCustomer extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 客户ID */
	private Long customerId;
	/** 客户姓名 */
	private String customerName;
	/** 客户公司名称 */
	private String customerCompanyName;
	/** 地址 */
	private String customerAddress;
	/** 联系电话 */
	private String customerTelephone;
	/** 邮箱 */
	private String customerEmail;
	/** 删除标记 */
	private byte delFlag;

	public byte getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(byte delFlag) {
		this.delFlag = delFlag;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public void setCustomerId(Long customerId)
	{
		this.customerId = customerId;
	}

	public Long getCustomerId() 
	{
		return customerId;
	}
	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}

	public String getCustomerName() 
	{
		return customerName;
	}
	public void setCustomerAddress(String customerAddress) 
	{
		this.customerAddress = customerAddress;
	}

	public String getCustomerAddress() 
	{
		return customerAddress;
	}
	public void setCustomerTelephone(String customerTelephone) 
	{
		this.customerTelephone = customerTelephone;
	}

	public String getCustomerTelephone() 
	{
		return customerTelephone;
	}

	public String getCustomerCompanyName() {
		return customerCompanyName;
	}

	public void setCustomerCompanyName(String customerCompanyName) {
		this.customerCompanyName = customerCompanyName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
			.append("customerCompanyName",getCustomerCompanyName())
            .append("customerAddress", getCustomerAddress())
            .append("customerTelephone", getCustomerTelephone())
			.append("customerEmail", getCustomerEmail())
			.append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
