package cloud.liupeng.api.exception;

/**
 * 异常场景，例如业务异常、转换异常等
 *
 * @author liupeng
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -2375722139644724409L;

    public BusinessException() {
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
