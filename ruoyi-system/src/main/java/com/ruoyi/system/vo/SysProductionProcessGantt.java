package com.ruoyi.system.vo;

import lombok.Data;

import java.util.List;
@Data
public class SysProductionProcessGantt {
    private String[] processType;
    private List<SysProductionGantt> gantts;
}
