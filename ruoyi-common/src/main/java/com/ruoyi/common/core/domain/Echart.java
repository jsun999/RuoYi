package com.ruoyi.common.core.domain;

import java.io.Serializable;

/**
 * echarts实体
 */
public class Echart implements Serializable {
    private static final long serialVersionUID = 1L;

    private String xaxis;

    private Long yaxis;

    private String change_type;

    public String getChange_type() {
        return change_type;
    }

    public void setChange_type(String change_type) {
        this.change_type = change_type;
    }

    public String getXaxis() {
        return xaxis;
    }

    public void setXaxis(String xaxis) {
        this.xaxis = xaxis;
    }

    public Long getYaxis() {
        return yaxis;
    }

    public void setYaxis(Long yaxis) {
        this.yaxis = yaxis;
    }

    @Override
    public String toString() {
        return "Echart{" +
                "xaxis='" + xaxis + '\'' +
                ", change_type=" + change_type + '\'' +
                ", yaxis=" + yaxis +
                '}';
    }
}
