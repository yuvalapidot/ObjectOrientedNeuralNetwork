import model.NeuralLayer;
import model.NeuralNetwork;
import model.activation.ActivationFunction;
import model.activation.SigmoidActivationFunction;
import utils.NetworkBuilderHelper;

public class Main {

    public static void main(String args[]) {



        NeuralNetwork network = new NeuralNetwork();
        ActivationFunction activation = new SigmoidActivationFunction();
        NeuralLayer inputLayer = NetworkBuilderHelper.createInputLayer(5, true);
        NeuralLayer hiddenLayer = NetworkBuilderHelper.createLayer(3, true, activation);
        NeuralLayer outputLayer = NetworkBuilderHelper.createLayer(1, false, activation);
        NetworkBuilderHelper.connectLayers(inputLayer, hiddenLayer);
        NetworkBuilderHelper.connectLayers(hiddenLayer, outputLayer);
        network.addLayer(inputLayer);
        network.addLayer(hiddenLayer);
        network.addLayer(outputLayer);
    }

}
