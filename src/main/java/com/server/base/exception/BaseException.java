package com.server.base.exception;

import com.server.utils.message.MessageUtils;
import com.server.utils.string.StringUtils;

/**
 * 基础异常类
 *
 * 这个类是所有自定义异常的基类，它提供了错误模块、错误码、错误参数和默认错误消息的存储。
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/1/25 17:46
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     * 这个字段用于标识异常发生的模块，有助于在处理异常时定位问题。
     */
    private String module;

    /**
     * 错误码
     * 这个字段用于存储异常的错误码，通常与国际化消息相关联。
     */
    private String code;

    /**
     * 错误码对应的参数
     * 这个字段用于存储异常消息的参数，用于替换错误码中的占位符。
     */
    private Object[] args;

    /**
     * 错误消息
     * 这个字段用于存储默认的错误消息，当错误码没有找到对应的国际化消息时使用。
     */
    private String defaultMessage;

    /**
     * 完整构造函数
     * 使用模块名、错误码、错误参数和默认错误消息创建异常。
     *
     * @param module         所属模块
     * @param code           错误码
     * @param args           错误码对应的参数
     * @param defaultMessage 默认的错误消息
     */
    public BaseException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    /**
     * 省略模块的构造函数
     * 使用错误码、错误参数创建异常，模块名为空。
     *
     * @param code 错误码
     * @param args 错误码对应的参数
     */
    public BaseException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }

    /**
     * 只包含模块和默认错误消息的构造函数
     * 使用模块名和默认错误消息创建异常，错误码为空。
     *
     * @param module         所属模块
     * @param defaultMessage 默认的错误消息
     */
    public BaseException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }

    /**
     * 只包含错误码和错误参数的构造函数
     * 使用错误码和错误参数创建异常，模块名为空，默认错误消息为空。
     *
     * @param code 错误码
     * @param args 错误码对应的参数
     */
    public BaseException(String code, Object[] args) {
        this(null, code, args, null);
    }

    /**
     * 只包含默认错误消息的构造函数
     * 使用默认错误消息创建异常，模块名和错误码为空。
     *
     * @param defaultMessage 默认的错误消息
     */
    public BaseException(String defaultMessage) {
        this(null, null, null, defaultMessage);
    }

    /**
     * 获取异常消息
     * 这个方法重写了父类的getMessage()方法，用于返回最终的错误消息。
     * 如果错误码不为空，则尝试从国际化消息中获取错误消息，否则使用默认错误消息。
     *
     * @return 最终的错误消息
     */
    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args);
        }
        if (message == null) {
            message = defaultMessage;
        }
        return message;
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
