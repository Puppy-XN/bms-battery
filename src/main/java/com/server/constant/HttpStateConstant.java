package com.server.constant;


/**
 * 返回状态码常量类
 * 这个类包含了一系列标准的HTTP状态码，用于在服务器响应中标识不同的结果状态。
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/1/25 17:46
 */
public class HttpStateConstant {

    /**
     * 成功状态码。
     * 表示请求已成功处理。
     */
    public static final int SUCCESS = 200;

    /**
     * 创建状态码。
     * 通常用于POST请求，表示新的资源已经创建成功。
     */
    public static final int CREATED = 201;

    /**
     * 已接受状态码。
     * 表示请求已经被接受，但尚未处理完成。
     */
    public static final int ACCEPTED = 202;

    /**
     * 无内容状态码。
     * 表示请求成功，但响应不包含内容。
     */
    public static final int NO_CONTENT = 204;

    /**
     * 永久移动状态码。
     * 表示请求的资源已被永久移动到新的位置。
     */
    public static final int MOVED_PERM = 301;

    /**
     * 查看其它位置状态码。
     * 表示可以在另一个URI中找到对请求的响应。
     */
    public static final int SEE_OTHER = 303;

    /**
     * 未修改状态码。
     * 表示客户端的缓存资源是最新的，无需重新传输。
     */
    public static final int NOT_MODIFIED = 304;

    /**
     * 错误请求状态码。
     * 表示请求中有语法错误或无法被服务器理解。
     */
    public static final int BAD_REQUEST = 400;

    /**
     * 未授权状态码。
     * 表示请求需要用户身份验证。
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 禁止状态码。
     * 表示服务器理解请求但拒绝执行。
     */
    public static final int FORBIDDEN = 403;

    /**
     * 未找到状态码。
     * 表示服务器找不到请求的资源。
     */
    public static final int NOT_FOUND = 404;

    /**
     * 方法不允许状态码。
     * 表示请求的方法不被允许。
     */
    public static final int BAD_METHOD = 405;

    /**
     * 冲突状态码。
     * 表示请求与服务器当前状态冲突。
     */
    public static final int CONFLICT = 409;

    /**
     * 不支持的媒体类型状态码。
     * 表示请求的格式不受请求的资源支持。
     */
    public static final int UNSUPPORTED_TYPE = 415;

    /**
     * 服务器错误状态码。
     * 表示服务器遇到错误，无法完成请求。
     */
    public static final int ERROR = 500;

}
