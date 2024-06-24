package com.server.enums;

import com.server.constant.HttpStateConstant;
import com.server.utils.string.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 统一状态码枚举类
 * 这个类定义了各种HTTP状态码及其描述信息，方便在应用程序中使用。
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/1/31 11:37
 */
public enum HttpStateEnum {
    // 数据操作定义
    SUCCESS(HttpStateConstant.SUCCESS, "请求成功"),

    CREATED(HttpStateConstant.CREATED, "创建成功"),

    ACCEPTED(HttpStateConstant.ACCEPTED, "请求已经被接受，但尚未处理完成"),

    NO_CONTENT(HttpStateConstant.NO_CONTENT, "请求成功，但响应不包含内容"),

    MOVED_PERM(HttpStateConstant.MOVED_PERM, "请求的资源已被永久移动到新的位置"),

    SEE_OTHER(HttpStateConstant.SEE_OTHER, "重定向"),

    NOT_MODIFIED(HttpStateConstant.NOT_MODIFIED, " 资源没有被修改"),

    BAD_REQUEST(HttpStateConstant.BAD_REQUEST, "参数列表错误（缺少，格式不匹配）"),

    UNAUTHORIZED(HttpStateConstant.UNAUTHORIZED, "未授权, 请求需要用户身份验证"),

    FORBIDDEN(HttpStateConstant.FORBIDDEN, "访问受限，授权过期"),

    NOT_FOUND(HttpStateConstant.NOT_FOUND, "服务器找不到请求的资源"),

    BAD_METHOD(HttpStateConstant.BAD_METHOD, "不允许的http方法"),

    CONFLICT(HttpStateConstant.CONFLICT, "资源冲突，或者资源被锁"),

    UNSUPPORTED_TYPE(HttpStateConstant.UNSUPPORTED_TYPE, "不支持的数据，媒体类型"),

    ERROR(HttpStateConstant.ERROR, "系统内部错误");

    /**
     * 响应码
     * 每个枚举值都有一个响应码，通常与HTTP状态码相对应。
     */
    private final Integer code;

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
    HttpStateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
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
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.code).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 需要判断的 value 值
     * @return ResultEnum
     */
    public static HttpStateEnum getEnumByValue(Integer value) {
        if (StringUtils.isNull(value)) {
            return null;
        }
        for (HttpStateEnum anEnum : HttpStateEnum.values()) {
            if (anEnum.code.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

}
