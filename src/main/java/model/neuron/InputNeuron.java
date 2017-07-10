package model.neuron;

import model.Edge;
import model.loss.CostFunction;
import model.neuron.Neuron;

/**
 * Created by yuvalapidot.
 */
public class InputNeuron extends Neuron {

    private double input;

    /**
     * Input Neuron
     */
    public InputNeuron() {
        super(null);
    }

    @Override
    public void input(double input) {
        this.input = input;
    }

    @Override
    protected void output() {
        output = input;
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
