package model;

import exception.OutputDimensionMismatchException;
import model.loss.CostFunction;
import model.neuron.BiasNeuron;
import model.neuron.Neuron;

import java.util.*;

/**
 * Created by yuvalapidot.
 */
public class NeuralLayer {

    private Set<Neuron> neurons;
    private Set<BiasNeuron> biases;

    // region construction

    /**
     * Constructs a new empty neural layer
     */
    public NeuralLayer() {
        neurons = new LinkedHashSet<>();
        biases = new LinkedHashSet<>();
    }

    /**
     * Add neuron to layer
     * @param neuron to add
     */
    public void addNeuron(Neuron neuron) {
        neurons.add(neuron);
    }

    /**
     * Add bias neuron to layer
     * @param neuron to add
     */
    public void addNeuron(BiasNeuron neuron) {
        biases.add(neuron);
    }

    // endregion
    // region getters
    /**
     * Get all neron including bias
     * @return collections of all neurons
     */
    public Collection<Neuron> getNeuronsAndBias() {
        Collection<Neuron> allNeurons = new LinkedHashSet<>(neurons);
        allNeurons.addAll(biases);
        return allNeurons;
    }

    /**
     * Get only non bias neurons
     * @return collection of non bias neurons
     */
    public Collection<Neuron> getNeurons() {
        return neurons;
    }

    // endregion
    // region forward
    /**
     * Input data into layer
     * @param inputs data to layer
     */
    public void input(double[] inputs) {
        if (neurons.size() != inputs.length) {
            throw new InputMismatchException("Number of given inputs (" + inputs.length
                    + ") does not match size of layer (" + neurons.size() + ")");
        }
        int inputIndex = 0;
        for (Neuron neuron : neurons) {
            neuron.input(inputs[inputIndex++]);
        }
    }

    /**
     * Feed forward the neurons results to next layer
     */
    public void feedForward() {
        for (Neuron neuron : neurons) {
            neuron.feedForward();
        }
        for (Neuron bias : biases) {
            bias.feedForward();
        }
    }

    // endregion
    // region output
    /**
     * Get the output of the last performed feed forward
     * @return output of the layer
     */
    public double[] getOutput() {
        double[] outputs = new double[neurons.size()];
        int index = 0;
        for (Neuron neuron : neurons) {
            outputs[index++] = neuron.getOutput();
        }
        return outputs;
    }

    // endregion
    // region backwards
    /**
     * Calculate the error of the layer from the expected results and the cost function
     * @param expected results of the layer
     * @param cost cost function to calculate error
     */
    public void calculateError(double[] expected, CostFunction cost) {
        if (neurons.size() != expected.length) {
            throw new OutputDimensionMismatchException("Number of given expected outputs (" + expected.length
                    + ") does not match size of layer (" + neurons.size() + ")");
        }
        int expectedIndex = 0;
        for (Neuron neuron : neurons) {
            neuron.accumulateError(expected[expectedIndex++], cost);
        }
    }

    /**
     * Back propagate the error to former layers
     */
    public void backPropagate() {
        for (Neuron neuron : neurons) {
            neuron.backPropagate();
        }
    }

    /**
     * Fix the weights entering this layers
     * @param learningRate learning rate for fixing the weights
     */
    public void fixWeights(double learningRate) {
        for (Neuron neuron : neurons) {
            neuron.fixBackwardsWeights(learningRate);
        }
    }

    // endregion
}
