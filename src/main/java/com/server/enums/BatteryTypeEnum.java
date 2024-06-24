package com.server.enums;

import com.server.constant.HttpStateConstant;
import com.server.utils.string.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 电池类型
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/1/31 11:37
 */
public enum BatteryTypeEnum {
    TERNARY_BATTERY("三元电池", "三元电池"),

    LITHUUM_IRON_BATTERY("铁锂电池", "铁锂电池");

    /**
     * 响应码
     * 每个枚举值都有一个响应码，通常与HTTP状态码相对应。
     */
    private final String code;

    /**
     * 响应信息
     * 每个枚举值都有一个响应信息，用于描述状态码的含义。
     */
    private final String message;

    /**
     * 有参构造
     * 这个构造函数用于创建枚举值，并设置响应码和响应信息。
     *
     * @param code    响应码
     * @param message 响应信息
     */
    BatteryTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 获取值列表
     * 这个方法返回所有枚举值的列表，便于遍历或使用。
     *
     * @return 值列表
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.code).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 需要判断的 value 值
     * @return ResultEnum
     */
    public static BatteryTypeEnum getEnumByValue(String value) {
        if (StringUtils.isNull(value)) {
            return null;
        }
        for (BatteryTypeEnum anEnum : BatteryTypeEnum.values()) {
            if (anEnum.code.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

}
