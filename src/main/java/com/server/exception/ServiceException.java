package com.server.exception;

import com.server.constant.HttpStateConstant;
import com.server.enums.HttpStateEnum;

/**
 * 业务异常
 * 这个类用于封装在业务逻辑处理过程中发生的错误。
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/1/25 17:46
 */
public final class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     * 这个字段用于存储业务异常的错误码，通常用于业务逻辑错误。
     */
    private Integer code;

    /**
     * 错误提示
     * 这个字段用于存储业务异常的提示信息，用于向用户或调用者展示错误信息。
     */
    private String message;

    /**
     * 错误明细，内部调试错误
     * 这个字段用于存储业务异常的详细信息，通常用于内部调试和问题定位。
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     * 这个构造方法是一个空构造方法，用于反序列化时使用，避免反序列化失败的问题。
     */
    public ServiceException() {
        this.code = HttpStateEnum.ERROR.getCode();
        this.message = HttpStateEnum.ERROR.getMessage();
    }

    /**
     * 只包含错误提示的构造方法
     * 这个构造方法接受一个错误提示信息，用于创建一个只包含提示信息的业务异常。
     *
     * @param message 错误提示
     */
    public ServiceException(String message) {
        this.code = HttpStateConstant.ERROR;
        this.message = message;
    }

    /**
     * 包含错误提示和错误码的构造方法
     * 这个构造方法接受一个错误提示信息和错误码，用于创建一个包含提示信息和错误码的业务异常。
     *
     * @param message 错误提示
     * @param code    错误码
     */
    public ServiceException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    /**
     * 获取错误明细
     * 这个方法用于获取业务异常的详细信息。
     *
     * @return 错误明细
     */
    public String getDetailMessage() {
        return detailMessage;
    }

    /**
     * 设置错误明细
     * 这个方法用于设置业务异常的详细信息。
     *
     * @param detailMessage 错误明细
     * @return 当前业务异常对象，方便链式调用
     */
    public ServiceException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 设置错误提示
     * 这个方法用于设置业务异常的提示信息。
     *
     * @param message 错误提示
     * @return 当前业务异常对象，方便链式调用
     */
    public ServiceException setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }
}
