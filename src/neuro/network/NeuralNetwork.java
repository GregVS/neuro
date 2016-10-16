package neuro.network;

import java.io.Serializable;

/**
 * Created by Gregory on 10/7/16.
 */
public class NeuralNetwork implements Serializable {

    private NeuralLayer[] layers_;

    public NeuralNetwork(NeuralLayer[] layers) {
        layers_ = layers;
    }

    public float[] evaluate(float[] inputs) {
        for(NeuralLayer layer : layers_) {
            inputs = layer.evaluate(inputs); //inputs for next layer
        }
        return inputs;
    }

    public void learnFromTargets(float[] targets) {
        NeuralLayer outputLayer = layers_[layers_.length - 1];
        outputLayer.updateOutputLayer(targets);

        NeuralLayer nextLayer = outputLayer;
        for(int i = layers_.length - 2; i >= 0; i--) {
            layers_[i].updateHiddenLayer(nextLayer);
            nextLayer = layers_[i];
        }
    }
}
