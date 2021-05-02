package com.zte.auth.common.execption;

/**
 * 业务异常
 */
public class BusinessExecption extends RuntimeException{
    public BusinessExecption(String message) {
        super(message);
    }

    public BusinessExecption() { }

    public BusinessExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessExecption(Throwable cause) {
        super(cause);
    }
}
