package cloud.liupeng.api.exception;

/**
 * 异常场景，例如服务调用异常
 *
 * @author liupeng
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -2375722139644724409L;

    public ServiceException() {
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}