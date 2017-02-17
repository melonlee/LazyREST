package lazyrest.web.exception;

/**
 * Created by Melon on 17/2/16.
 */
public class TokenException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TokenException() {
        super("客户端Token失效!");
    }

    public TokenException(String message, Throwable cause,
                          boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }
}
