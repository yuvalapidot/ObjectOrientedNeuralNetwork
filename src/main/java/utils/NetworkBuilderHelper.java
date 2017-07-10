package utils;

import model.Edge;
import model.NeuralLayer;
import model.activation.ActivationFunction;
import model.neuron.BiasNeuron;
import model.neuron.InputNeuron;
import model.neuron.Neuron;

import java.util.Random;

/**
 * Created by yuvalapidot.
 */
public class NetworkBuilderHelper {

    /**
     * Connect two layers from source to destination in a fully connected manner
     * @param random object to choose randomly
     * @param sourceLayer from which to connect the edges
     * @param destinationLayer to which to connect the edges
     */
    public static void connectLayers(Random random, NeuralLayer sourceLayer, NeuralLayer destinationLayer) {
        for (Neuron sourceNeuron : sourceLayer.getNeuronsAndBias()) {
            for (Neuron destinationNeuron : destinationLayer.getNeurons()) {
                Edge edge = new Edge(random, sourceNeuron, destinationNeuron);
                sourceNeuron.addForwardEdge(edge);
                destinationNeuron.addBackwardEdge(edge);
            }
        }
    }

    /**
     * Create an input layer with given parameters
     * @param numberOfInputs number of inputs neurons to put in the layer
     * @param bias whether to add a bias neuron
     * @return a new input layer
     */
    public static NeuralLayer createInputLayer(int numberOfInputs, boolean bias) {
        NeuralLayer layer = new NeuralLayer();
        for (int i = 0; i < numberOfInputs; i++) {
            layer.addNeuron(new InputNeuron());
        }
        if (bias) {
            layer.addNeuron(new BiasNeuron());
        }
        return layer;
    }

    /**
     * Create a layer with given parameters
     * @param numberOfNeurons number of neuron to put in the layer
     * @param bias whether to add a bias neuron
     * @param activation to set the neurons with
     * @return a new layer
     */
    public static NeuralLayer createLayer(int numberOfNeurons, boolean bias, ActivationFunction activation) {
        NeuralLayer layer = new NeuralLayer();
        for (int i = 0; i < numberOfNeurons; i++) {
            layer.addNeuron(new Neuron(activation));
        }
        if (bias) {
            layer.addNeuron(new BiasNeuron());
        }
        return layer;
    }
}
