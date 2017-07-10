import model.NeuralLayer;
import model.NeuralNetwork;
import model.activation.ActivationFunction;
import model.activation.SigmoidActivationFunction;
import model.loss.QuadraticCostfunction;
import org.junit.Test;
import utils.NetworkBuilderHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by yuvalapidot.
 */
public class NeuralNetworkTest {

    @Test
    public void xorNetworkTest() {
        NeuralNetwork network = buildSimpleNeuralNetwork(2);
        int xor00 = 0;
        int xor10 = 1;
        int xor01 = 1;
        int xor11 = 0;
        List<double[]> trainInputs = Arrays.asList(
                new double[] {0, 0},
                new double[] {0, 1},
                new double[] {1, 0},
                new double[] {1, 1});
        List<double[]> trainOutputs = Arrays.asList(
                new double[] {xor00},
                new double[] {xor01},
                new double[] {xor10},
                new double[] {xor11});
        network.train(trainInputs, trainOutputs, new QuadraticCostfunction(), 100000, trainInputs.size(), 0.1);
        double result00 = network.result(new double[] {0, 0})[0];
        assertEquals(xor00, Math.round(result00));
        double result10 = network.result(new double[] {1, 0})[0];
        assertEquals(xor10, Math.round(result10));
        double result01 = network.result(new double[] {0, 1})[0];
        assertEquals(xor01, Math.round(result01));
        double result11 = network.result(new double[] {1, 1})[0];
        assertEquals(xor11, Math.round(result11));
    }

    @Test
    public void andNetworkTest() {
        NeuralNetwork network = buildSimpleNeuralNetwork(2);
        int and00 = 0;
        int and10 = 0;
        int and01 = 0;
        int and11 = 1;
        List<double[]> trainInputs = Arrays.asList(
                new double[] {0, 0},
                new double[] {0, 1},
                new double[] {1, 0},
                new double[] {1, 1});
        List<double[]> trainOutputs = Arrays.asList(
                new double[] {and00},
                new double[] {and01},
                new double[] {and10},
                new double[] {and11});
        network.train(trainInputs, trainOutputs, new QuadraticCostfunction(), 100000, trainInputs.size(), 0.1);
        double result00 = network.result(new double[] {0, 0})[0];
        assertEquals(and00, Math.round(result00));
        double result10 = network.result(new double[] {1, 0})[0];
        assertEquals(and10, Math.round(result10));
        double result01 = network.result(new double[] {0, 1})[0];
        assertEquals(and01, Math.round(result01));
        double result11 = network.result(new double[] {1, 1})[0];
        assertEquals(and11, Math.round(result11));
    }

    @Test
    public void orNetworkTest() {
        NeuralNetwork network = buildSimpleNeuralNetwork(2);
        int or00 = 0;
        int or10 = 1;
        int or01 = 1;
        int or11 = 1;
        List<double[]> trainInputs = Arrays.asList(
                new double[] {0, 0},
                new double[] {0, 1},
                new double[] {1, 0},
                new double[] {1, 1});
        List<double[]> trainOutputs = Arrays.asList(
                new double[] {or00},
                new double[] {or01},
                new double[] {or10},
                new double[] {or11});
        network.train(trainInputs, trainOutputs, new QuadraticCostfunction(), 100000, trainInputs.size(), 0.1);
        double result00 = network.result(new double[] {0, 0})[0];
        assertEquals(or00, Math.round(result00));
        double result10 = network.result(new double[] {1, 0})[0];
        assertEquals(or10, Math.round(result10));
        double result01 = network.result(new double[] {0, 1})[0];
        assertEquals(or01, Math.round(result01));
        double result11 = network.result(new double[] {1, 1})[0];
        assertEquals(or11, Math.round(result11));
    }

    @Test
    public void orAndNetworkTest() {
        NeuralNetwork network = buildSimpleNeuralNetwork(3);
        int or00and0 = 0;
        int or10and0 = 0;
        int or01and0 = 0;
        int or11and0 = 0;
        int or00and1 = 0;
        int or10and1 = 1;
        int or01and1 = 1;
        int or11and1 = 1;
        List<double[]> trainInputs = Arrays.asList(
                new double[] {0, 0, 0},
                new double[] {1, 0, 0},
                new double[] {0, 1, 0},
                new double[] {1, 1, 0},
                new double[] {0, 0, 1},
                new double[] {1, 0, 1},
                new double[] {0, 1, 1},
                new double[] {1, 1, 1});
        List<double[]> trainOutputs = Arrays.asList(
                new double[] {or00and0},
                new double[] {or10and0},
                new double[] {or01and0},
                new double[] {or11and0},
                new double[] {or00and1},
                new double[] {or10and1},
                new double[] {or01and1},
                new double[] {or11and1});
        network.train(trainInputs, trainOutputs, new QuadraticCostfunction(), 100000, trainInputs.size(), 0.1);
        double result000 = network.result(new double[] {0, 0, 0})[0];
        assertEquals(or00and0, Math.round(result000));
        double result100 = network.result(new double[] {1, 0, 0})[0];
        assertEquals(or10and0, Math.round(result100));
        double result010 = network.result(new double[] {0, 1, 0})[0];
        assertEquals(or01and0, Math.round(result010));
        double result110 = network.result(new double[] {1, 1, 0})[0];
        assertEquals(or11and0, Math.round(result110));
        double result001 = network.result(new double[] {0, 0, 1})[0];
        assertEquals(or00and1, Math.round(result001));
        double result101 = network.result(new double[] {1, 0, 1})[0];
        assertEquals(or10and1, Math.round(result101));
        double result011 = network.result(new double[] {0, 1, 1})[0];
        assertEquals(or01and1, Math.round(result011));
        double result111 = network.result(new double[] {1, 1, 1})[0];
        assertEquals(or11and1, Math.round(result111));
    }

    private NeuralNetwork buildSimpleNeuralNetwork(int inputSize) {
        Random random = new Random(0);
        NeuralNetwork network = new NeuralNetwork();
        ActivationFunction activation = new SigmoidActivationFunction();
        NeuralLayer inputLayer = NetworkBuilderHelper.createInputLayer(inputSize, true);
        NeuralLayer hiddenLayer = NetworkBuilderHelper.createLayer(3, true, activation);
        NeuralLayer outputLayer = NetworkBuilderHelper.createLayer(1, false, activation);
        NetworkBuilderHelper.connectLayers(random, inputLayer, hiddenLayer);
        NetworkBuilderHelper.connectLayers(random, hiddenLayer, outputLayer);
        network.addLayer(inputLayer);
        network.addLayer(hiddenLayer);
        network.addLayer(outputLayer);
        return network;
    }
}
