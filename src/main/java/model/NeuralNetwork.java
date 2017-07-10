package model;

import exception.EmptyNetworkException;
import exception.TrainSetDimensionMismatchException;
import model.loss.CostFunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by yuvalapidot.
 */
public class NeuralNetwork {

    private List<NeuralLayer> layers;
    Logger log = LogManager.getLogger(NeuralNetwork.class);

    /**
     * Constructs a new empty neural network
     */
    public NeuralNetwork() {
        layers = new ArrayList<NeuralLayer>();
    }

    /**
     * Add next layer to the network
     * @param layer to add to the network
     */
    public void addLayer(NeuralLayer layer) {
        layers.add(layer);
        log.info("Added layer to network at index " + (layers.size()));
    }

    /**
     * Add layer to the network at a given location
     * @param index to add the layer
     * @param layer to add to the network
     */
    public void addLayer(int index, NeuralLayer layer) {
        layers.add(index, layer);
        log.info("Added layer to network at index " + index);
    }

    /**
     * Train the network using the inputs, expected outputs and parameters
     * @param inputs for training - must be same number of outputs
     * @param outputs for training - must be same number of inputs
     * @param cost function to minimize during the train
     * @param epochs number of training rounds to do
     * @param batchSize size of batch to use
     * @param learningRate learning weight for fixing the weight
     */
    public void train(List<double[]> inputs, List<double[]> outputs, CostFunction cost, int epochs, int batchSize, double learningRate) {
        if (inputs.size() != outputs.size()) {
            log.error("Number of given inputs (" + inputs.size()
                    + ") does not match number of given outputs (" + outputs.size() + ")");
            throw new TrainSetDimensionMismatchException("Number of given inputs (" + inputs.size()
                    + ") does not match number of given outputs (" + outputs.size() + ")");
        }
        int trainSize = inputs.size();
        int trainIndex = 0;
        log.info("Starting training the network with " + trainSize + " instances, using " +
                cost.getClass() + ", " + epochs + " epochs, batches of " + batchSize +
                " instances and learning rate of " + learningRate);
        for (int epoch = 0; epoch < epochs; epoch++) {
            log.debug("Starting epoch " + epoch);
            for (int instance = 0; instance < batchSize; instance++) {
                input(inputs.get(trainIndex));
                feedForward();
                calculateError(outputs.get(trainIndex), cost);
                backPropagate();
                trainIndex = (trainIndex + 1) % trainSize;
            }
            log.debug("Finished epoch " + epoch + ", fixing the weights...");
            fixWeights(learningRate);
        }
        log.info("Finished training network on data");
    }

    /**
     * Get the result of the network from a given input
     * @param input to pass through the network
     * @return result of the network from given input
     */
    public double[] result(double[] input) {
        log.info("Feeding forward the network with input: " + Arrays.toString(input));
        input(input);
        feedForward();
        double[] output = getOutput();
        log.info("Received result from network: " + Arrays.toString(output));
        return output;
    }

    private void input(double[] inputs) {
        if (layers.isEmpty()) {
            throw new EmptyNetworkException();
        }
        layers.get(0).input(inputs);
    }

    private void feedForward() {
        for (NeuralLayer layer : layers) {
            layer.feedForward();
        }
    }

    private void calculateError(double[] expected, CostFunction cost) {
        if (layers.isEmpty()) {
            throw new EmptyNetworkException();
        }
        layers.get(layers.size() - 1).calculateError(expected, cost);
    }

    private void backPropagate() {
        for (int i = layers.size(); i > 0; i--) {
            layers.get(i - 1).backPropagate();
        }
    }

    private void fixWeights(double learningRate) {
        for (NeuralLayer layer : layers) {
            layer.fixWeights(learningRate);
        }
    }

    private double[] getOutput() {
        if (layers.isEmpty()) {
            throw new EmptyNetworkException();
        }
        return layers.get(layers.size() - 1).getOutput();
    }
}
