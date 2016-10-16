package neuro.builder;

import neuro.math.MathFunctions;
import neuro.network.NeuralLayer;
import neuro.network.NeuralNetwork;
import neuro.network.Neuron;

import java.util.ArrayList;

/**
 * Created by Gregory on 10/7/16.
 */
public class NeuralNetBuilder {

    private ArrayList<Integer> hiddenLayerSizeArray_ = new ArrayList<>();
    private int inputSize_ = 0;
    private int outputSize_ = 0;

    public NeuralNetwork buildNetwork() {
        NeuralLayer[] layers = new NeuralLayer[hiddenLayerSizeArray_.size() + 1]; //include output layer
        for(int i = 0; i <= hiddenLayerSizeArray_.size(); i++) {
            Neuron[] neurons = new Neuron[i == hiddenLayerSizeArray_.size() ? outputSize_ : hiddenLayerSizeArray_.get(i)];
            for(int n = 0; n < neurons.length; n++) {
                neurons[n] = buildNeuronForLayer(i);
            }
            layers[i] = new NeuralLayer(neurons);
        }
        return new NeuralNetwork(layers);
    }

    private Neuron buildNeuronForLayer(int layer) {
        float[] weights = new float[layer == 0 ? inputSize_ : hiddenLayerSizeArray_.get(layer-1)];
        for(int i = 0; i < weights.length; i++) {
            weights[i] = MathFunctions.randomClamped();
        }
        return new Neuron(weights, MathFunctions.randomClamped());
    }

    public void addHiddenLayer(int size) {
        hiddenLayerSizeArray_.add(size);
    }

    public void setOutputSize(int outputSize) {
        outputSize_ = outputSize;
    }

    public void setInputSize_(int inputSize) {
        inputSize_ = inputSize;
    }

}
