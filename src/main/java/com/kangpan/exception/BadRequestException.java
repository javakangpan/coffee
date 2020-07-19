package com.kangpan.exception;

/**
 * @author 康盼Java开发工程师
 * 统一异常处理
 * @date 2020/07/19
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super( message );
    }

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message, Throwable cause) {
        super( message, cause );
    }

    public BadRequestException(Throwable cause) {
        super( cause );
    }
}
