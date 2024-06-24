package com.server.exception;

/**
 * 工具类异常
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/1/25 17:46
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    /**
     * 使用外部异常创建工具类异常
     * 这个构造方法接受一个外部异常作为参数，用于创建一个工具类异常，并将外部异常的详细信息包含在内。
     *
     * @param e 外部异常
     */
    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    /**
     * 使用字符串创建工具类异常
     * 这个构造方法接受一个字符串作为参数，用于创建一个工具类异常，并设置异常的提示信息。
     *
     * @param message 异常提示信息
     */
    public UtilException(String message) {
        super(message);
    }

    /**
     * 使用字符串和外部异常创建工具类异常
     * 这个构造方法接受一个字符串和外部异常作为参数，用于创建一个工具类异常，并将外部异常的详细信息包含在内。
     *
     * @param message 异常提示信息
     * @param throwable 外部异常
     */
    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
