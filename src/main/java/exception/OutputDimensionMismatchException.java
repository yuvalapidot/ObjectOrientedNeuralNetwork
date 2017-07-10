package exception;

/**
 * Created by yuvalapidot.
 */
public class OutputDimensionMismatchException extends RuntimeException {

    public OutputDimensionMismatchException() {
    }

    public OutputDimensionMismatchException(String message) {
        super(message);
    }

    public OutputDimensionMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutputDimensionMismatchException(Throwable cause) {
        super(cause);
    }
}
