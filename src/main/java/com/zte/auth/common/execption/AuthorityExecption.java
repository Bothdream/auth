package com.zte.auth.common.execption;

/**
 * 权限异常
 */
public class AuthorityExecption extends RuntimeException{
    public AuthorityExecption(String message) {
        super(message);
    }

    public AuthorityExecption() { }

    public AuthorityExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorityExecption(Throwable cause) {
        super(cause);
    }
}
