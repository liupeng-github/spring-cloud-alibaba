package cloud.liupeng.api.exception;

/**
 * 异常场景，算数异常
 *
 * @author liupeng
 */
public class ArithmeticException extends RuntimeException {

    private static final long serialVersionUID = -2375722139644724409L;

    public ArithmeticException() {
    }

    public ArithmeticException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArithmeticException(String message) {
        super(message);
    }

    public ArithmeticException(Throwable cause) {
        super(cause);
    }
}
