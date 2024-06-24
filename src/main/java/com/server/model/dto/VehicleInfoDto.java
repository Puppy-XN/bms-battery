package com.server.model.dto;

import lombok.Data;

/**
 * TODO
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/6/20 下午6:41
 */
@Data
public class VehicleInfoDto {
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
