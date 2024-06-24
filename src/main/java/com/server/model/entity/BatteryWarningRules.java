package com.server.model.entity;

import lombok.Data;

/**
 * TODO
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/6/20 下午4:48
 */
@Data
public class BatteryWarningRules {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 规则编号
     */
    private Long ruleNumber;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 电池类型
     */
    private String batteryType;

    /**
     * 规则类型
     */
    private String ruleType;

    /**
     * 通用字段，用于最小差值（电压或电流）
     */
    private Double minDiff;

    /**
     * 通用字段，用于最大差值（电压或电流）
     */
    private Double maxDiff;

    /**
     * 等级
     */
    private String warningLevel;


}
