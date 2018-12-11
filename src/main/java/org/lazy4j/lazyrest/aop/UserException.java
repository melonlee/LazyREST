package org.lazy4j.lazyrest.aop;

/**
 * Created by Melon on 17/2/17.
 */
public class UserException extends RuntimeException {

    public UserException() {
        super("非法用户!");
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

}
