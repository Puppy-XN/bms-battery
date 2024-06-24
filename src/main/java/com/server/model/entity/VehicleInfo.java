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
public class VehicleInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 车辆识别码
     */
    private String vid;

    /**
     * 车架编号
     */
    private Long frameNumber;

    /**
     * 电池类型
     */
    private String batteryType;

    /**
     * 总里程
     */
    private String totalMileage;

    /**
     * 电池健康状态
     */
    private Long state;

}
