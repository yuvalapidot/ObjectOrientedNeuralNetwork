package model.loss;

/**
 * Created by yuvalapidot.
 */
public interface CostFunction {

    /**
     * Cost of the the error calculated from the expected and actual results
     * @param expected results to calculate error
     * @param actual results to calculate error
     * @return the calculated cost
     */
    public double cost(double expected, double actual);

    /**
     * The derivative of the cost calculated from the expected and actual results
     * @param expected results to calculate derivative
     * @param actual results to calculate derivative
     * @return the calculated cost derivative
     */
    public double derivative(double expected, double actual);
}
