package com.ruoyi.common.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * echarts实体
 */
@Data
public class Echart implements Serializable {
    private static final long serialVersionUID = 1L;

    private String xaxis;

    private Long yaxis;

    private String changeType;

}
