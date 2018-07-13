package model.loss;

/**
 * Created by yuvalapidot.
 */
public class QuadraticCostFunction implements CostFunction {

    @Override
    public double cost(double expected, double actual) {
        return Math.pow(expected - actual, 2) / 2;
    }

    @Override
    public double derivative(double expected, double actual) {
        return actual - expected;
    }
}
