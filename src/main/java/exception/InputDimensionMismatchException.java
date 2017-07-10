package exception;

/**
 * Created by yuvalapidot.
 */
public class InputDimensionMismatchException extends RuntimeException {

    public InputDimensionMismatchException() {
    }

    public InputDimensionMismatchException(String message) {
        super(message);
    }

    public InputDimensionMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputDimensionMismatchException(Throwable cause) {
        super(cause);
    }
}
