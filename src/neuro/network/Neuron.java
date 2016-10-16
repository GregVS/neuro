package neuro.network;

import neuro.math.MathFunctions;

import java.io.Serializable;

/**
 * Created by Gregory on 10/2/16.
 */
public class Neuron implements Serializable {

    private int indexInLayer_;
    private float[] weights_;
    private float bias_;
    private float output_;
    private float gradient_;

    public Neuron(float[] weights, float bias) {
        weights_ = weights;
        bias_ = bias;
    }

    //FEED-FORWARD
    float evaluate(float[] inputs) {
        float activation = 0;
        for (int i = 0; i < inputs.length; i++) {
            activation += weights_[i] * inputs[i];
        }
        output_ = MathFunctions.sigmoid(activation + bias_);
        return output_;
    }

    //BACK-PROPAGATION
    //calculate sum of gradient in layer times the connecting weight
    private float sumLayerGradients(NeuralLayer layer) {
        float sum = 0;
        for (Neuron neuron : layer.getNeurons()) {
            sum += neuron.getGradient() * neuron.getWeights()[indexInLayer_];
        }
        return sum;
    }

    //calculate gradients
    void calcHiddenGradient(NeuralLayer nextLayer) { gradient_ = output_ * (1 - output_) * sumLayerGradients(nextLayer); }
    void calcOutputGradient(float target) { gradient_ = output_ * (1 - output_) * (output_ - target); }

    //GETTERS AND SETTERS
    void setIndexInLayer(int index) { indexInLayer_ = index; }
    float getGradient() { return gradient_; }
    float[] getWeights() { return weights_; }
    float getBias() { return bias_; }
    void setBias(float delta) { bias_ = delta; }
}

