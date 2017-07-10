package model.activation;

/**
 * Created by yuvalapidot.
 */
public class SigmoidActivationFunction implements ActivationFunction {

    @Override
    public double activate(double input) {
        return 1 / (1 + Math.exp(-input));
    }

    @Override
    public double derivative(double input) {
        return Math.exp(-input) / Math.pow(1 + Math.exp(-input), 2);
    }
}
