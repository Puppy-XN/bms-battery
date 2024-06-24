package com.server.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 信号 实体类
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/6/20 下午4:22
 */
@Data
public class SignalVo {

    /**
     * 最高电压
     */
    @JSONField(name = "Mx")
    private Double mx;

    /**
     * 最小电压
     */
    @JSONField(name = "Mi")
    private Double mi;

    /**
     * 最高电流
     */
    @JSONField(name = "Ix")
    private Double ix;

    /**
     * 最小电流
     */
    @JSONField(name = "Ii")
    private Double ii;
}
