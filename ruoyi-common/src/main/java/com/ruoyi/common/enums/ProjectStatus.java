package com.ruoyi.common.enums;

/**
 * 订单状态
 * 
 * @author ruoyi
 */
public enum ProjectStatus
{
    evaluation(1, "评估中");

    private final Integer code;
    private final String info;

    ProjectStatus(Integer code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
