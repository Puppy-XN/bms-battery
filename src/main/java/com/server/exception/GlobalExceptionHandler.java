package com.server.exception;

import com.server.base.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 处理自定义异常
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/1/25 17:46
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(value = ServiceException.class)
    public Result<Object> customExceptionHandler(ServiceException e) {
        return Result.ServiceExceptionError(e);
    }
}
