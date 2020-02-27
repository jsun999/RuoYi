package com.ruoyi.system.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="sys_sequence")
public class SysSequence {
    @Id
    private String name;

    private Integer currentValue;

    private Integer increment;
}
