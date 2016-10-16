package neuro.network;

import neuro.math.MathFunctions;

import java.io.Serializable;

/**
 * Created by Gregory on 10/7/16.
 */
public class NeuralLayer implements Serializable {

    private Neuron[] neurons_;
    private float[] inputs_;

    public NeuralLayer(Neuron[] neurons) {
        neurons_ = neurons;
        for(int i = 0; i < neurons.length; i++) {
            neurons[i].setIndexInLayer(i);
        }
    }

    float[] evaluate(float[] inputs) {
        inputs_ = inputs;
        float[] outputs = new float[neurons_.length];
        for(int i = 0; i < neurons_.length; i++) {
            outputs[i] = neurons_[i].evaluate(inputs);
        }
        return outputs;
    }

    Neuron[] getNeurons() {
        return neurons_;
    }

    void updateOutputLayer(float[] targets) {
        for(int i = 0; i < neurons_.length; i++) {
            neurons_[i].calcOutputGradient(targets[i]);
            updateNeuron(neurons_[i]);
        }
    }

    void updateHiddenLayer(NeuralLayer nextLayer) {
        for (Neuron neuron : neurons_) {
            neuron.calcHiddenGradient(nextLayer);
            updateNeuron(neuron);
        }
    }

    private void updateNeuron(Neuron neuron) {
        float[] weights = neuron.getWeights();
        float finalGradient = -MathFunctions.LEARNING_RATE * neuron.getGradient();
        for (int w = 0; w < weights.length; w++) {
            weights[w] += finalGradient * inputs_[w];
        }
        neuron.setBias(neuron.getBias() + finalGradient);
    }

}
