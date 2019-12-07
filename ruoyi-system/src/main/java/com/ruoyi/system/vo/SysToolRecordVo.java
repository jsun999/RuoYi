package com.ruoyi.system.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.SysToolRecord;

import java.math.BigDecimal;

public class SysToolRecordVo extends SysToolRecord {
    /** 刀具类型（字典） */
    @Excel(name = "刀具类型")
    private Long toolType;
    /** 规格 */
    @Excel(name = "刀具规格")
    private String toolSpecifications;
    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal toolPrice;
    /** 材质（字典） */
    @Excel(name = "材质")
    private Long toolMaterial;
    /** 制造商 */
    @Excel(name = "制造商")
    private String manufacturer;
    /** 操作人 */
    @Excel(name = "操作人")
    private String userName;
    /** 操作人 */
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getToolType() {
        return toolType;
    }

    public void setToolType(Long toolType) {
        this.toolType = toolType;
    }

    public String getToolSpecifications() {
        return toolSpecifications;
    }

    public void setToolSpecifications(String toolSpecifications) {
        this.toolSpecifications = toolSpecifications;
    }

    public BigDecimal getToolPrice() {
        return toolPrice;
    }

    public void setToolPrice(BigDecimal toolPrice) {
        this.toolPrice = toolPrice;
    }

    public Long getToolMaterial() {
        return toolMaterial;
    }

    public void setToolMaterial(Long toolMaterial) {
        this.toolMaterial = toolMaterial;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
