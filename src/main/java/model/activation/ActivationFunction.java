package model.activation;

/**
 * Created by yuvalapidot.
 */
public interface ActivationFunction {

    /**
     * Activate on input
     * @param input to activate on
     * @return result of activation on input
     */
    public double activate(double input);

    /**
     * Derivative of activation on input
     * @param input to calculate derivative of activation
     * @return derivative of activation for input
     */
    public double derivative(double input);
}
