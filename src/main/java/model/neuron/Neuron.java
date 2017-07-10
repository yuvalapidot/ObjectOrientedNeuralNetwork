package model.neuron;

import model.activation.ActivationFunction;
import model.Edge;
import model.loss.CostFunction;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by yuvalapidot.
 */
public class Neuron {

    private ActivationFunction activation;
    private Set<Edge> backwardEdges;
    private Set<Edge> forwardEdges;

    private double accumulatedInput;
    private double intermediateValue;

    protected double output;

    private double accumulatedDerivedError;
    private double error;

    /**
     * Constructs a neuron with activation function
     * @param activation for neuron
     */
    public Neuron(ActivationFunction activation) {
        this.activation = activation;
        backwardEdges = new LinkedHashSet<Edge>();
        forwardEdges = new LinkedHashSet<Edge>();
        accumulatedInput = 0;
    }

    /**
     * Get output of last performed feed forward
     * @return output of the neuron
     */
    public double getOutput() {
        return output;
    }

    /**
     * Get error of the last back propagation performed
     * @return
     */
    public double getError() {
        return error;
    }

    /**
     * Add an edge from neuron
     * @param edge from neuron
     */
    public void addForwardEdge(Edge edge) {
        forwardEdges.add(edge);
    }

    /**
     * Add an edge to neuron
     * @param edge to neuron
     */
    public void addBackwardEdge(Edge edge) {
        backwardEdges.add(edge);
    }

    /**
     * Input into neuron
     * @param input to the neuron
     */
    public void input(double input) {
        accumulatedInput += input;
    }

    /**
     * Feed forward the input to edges
     */
    public void feedForward() {
        output();
        for (Edge edge : forwardEdges) {
            edge.feedForward();
        }
    }

    /**
     * Accumulate the error of this neuron
     * @param partialError to accumulate
     */
    public void accumulateError(double partialError) {
        accumulatedDerivedError += partialError;
    }

    /**
     * Accumulate the error of this neuron from cost function and expected value
     * @param expected expected result
     * @param cost cost function to use
     */
    public void accumulateError(double expected, CostFunction cost) {
        accumulatedDerivedError += cost.derivative(expected, output);
    }

    /**
     * Back propagate the error to former neurons
     */
    public void backPropagate() {
        calculateError();
        for (Edge edge : backwardEdges) {
            edge.backPropagate();
        }
    }

    /**
     * Fix weights entering this neuron
     * @param learningRate learning rate for the fix
     */
    public void fixBackwardsWeights(double learningRate) {
        for (Edge edge : backwardEdges) {
            edge.fixWeight(learningRate);
        }
    }

    protected void output() {
        intermediateValue = accumulatedInput;
        output = activation.activate(intermediateValue);
        accumulatedInput = 0;
    }

    private void calculateError() {
        error = accumulatedDerivedError * activation.derivative(intermediateValue);
        accumulatedDerivedError = 0;
    }
}
