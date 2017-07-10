package exception;

/**
 * Created by yuvalapidot.
 */
public class TrainSetDimensionMismatchException extends RuntimeException {

    public TrainSetDimensionMismatchException() {
    }

    public TrainSetDimensionMismatchException(String message) {
        super(message);
    }

    public TrainSetDimensionMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrainSetDimensionMismatchException(Throwable cause) {
        super(cause);
    }
}
