package exception;

/**
 * Created by yuvalapidot.
 */
public class EmptyNetworkException extends RuntimeException {

    public EmptyNetworkException() {
    }

    public EmptyNetworkException(String message) {
        super(message);
    }

    public EmptyNetworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyNetworkException(Throwable cause) {
        super(cause);
    }
}