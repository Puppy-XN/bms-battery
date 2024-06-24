package com.server.base.result;


import com.server.enums.HttpStateEnum;
import com.server.exception.ServiceException;

/**
 * 统一封装返回消息工具类
 *
 * @author Like Old Dream
 * @since 2023-07-04 18:50
 */
public class Result<T> {
    private Integer code;//状态码
    private String message;//提示消息
    private T data;//数据

    public Result() {
    }

    /**
     * @param code    响应码
     * @param message 响应信息
     */
    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @param code    响应码
     * @param message 响应信息
     * @param data    数据
     */
    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * @param resultEnum 自定义枚举类，包含 code 和 message
     */
    public Result(HttpStateEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    /**
     * @param resultEnum 自定义枚举类，包含 code 和 message
     * @param data       数据
     */
    public Result(HttpStateEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = data;
    }

    /**
     * 自定义异常返回的结果
     *
     * @param serviceException 自定义异常处理类
     * @return 返回自定义异常
     */
    public static Result<Object> ServiceExceptionError(ServiceException serviceException) {
        return new Result<>(serviceException.getCode(), serviceException.getMessage());
    }

    /**
     * 其他异常处理方法返回的结果
     *
     * @param resultEnum 自定义枚举类，包含 code 和 message
     * @return 返回其他异常
     */
    public static Result<Object> otherError(HttpStateEnum resultEnum) {
        return new Result<>(resultEnum);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
