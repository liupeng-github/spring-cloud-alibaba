package cloud.liupeng.api.exception;

/**
 * 异常场景，算数异常
 *
 * @author liupeng
 */
public class IllegalStateException extends RuntimeException {

    private static final long serialVersionUID = -2375722139644724409L;

    public IllegalStateException() {
    }

    public IllegalStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalStateException(String message) {
        super(message);
    }

    public IllegalStateException(Throwable cause) {
        super(cause);
    }
}
