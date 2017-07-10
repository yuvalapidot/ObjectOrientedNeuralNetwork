package model.neuron;

import model.Edge;
import model.loss.CostFunction;
import model.neuron.Neuron;

/**
 * Created by yuvalapidot.
 */
public class BiasNeuron extends Neuron {

    public static int BIAS = 1;

    /**
     * Biased Network
     */
    public BiasNeuron() {
        super(null);
    }

    @Override
    public void input(double input) {
    }

    @Override
    protected void output() {
        output = BIAS;
    }

    @Override
    public void accumulateError(double partialError) {
    }

    @Override
    public void accumulateError(double expected, CostFunction cost) {
    }

    @Override
    public void backPropagate() {
    }

    @Override
    public void fixBackwardsWeights(double learningRate) {
    }
}
