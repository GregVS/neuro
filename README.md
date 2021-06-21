# Neuro
**Note: I wrote this project in 7th grade. It was simply a way for me to learn about basic neural networks at a low level.**

Neuro is an open source neural network made with Java :)<br>
Feel free to distribute this program or modify it to your liking.
<br>
<b>NOTE: </b> This library currently only supports the Sigmoid activation function.

## Installation
First download the jar file from Github repository. It will be titled <b>Neuro.jar.</b><br>
Then, move it into your project directory.<br>
If you are using <b>Eclipse</b> then right click and choose Properties and then Build Path. Under Libraries, click add JAR and select the jar.<br>
If you are using <b>Intellij IDEA</b> then right click on your project and choose Open Module Settings. On the sidebar press Libraries and click the plus button. Choose Java and a box will pop up allowing you to select the jar.

## Usage
Neuro is super easy and noob friendly to use.<br>
#### Building a Randomly Populated Neural Net
```java
NeuralNetBuilder builder = new NeuralNetBuilder();
builder.setInputSize_(28 * 28);
builder.addHiddenLayer(100);
builder.setOutputSize(2);
NeuralNetwork net = builder.buildNetwork();
```
#### Running the Neural Network
```java
float[] inputs = /* some input values */
float[] outputs = net.evaluate(inputs);
```
If you would like to backpropagate the network then add this code after.
```java
float targets[] = {0.12f, 0.7f};
net.learnFromTargets(targets);
```
#### Saving the Neural Network
```java
NeuralNetLoader.saveNeuralNet("src/saved_network.net", net);
```

#### Loading the Neural Network
```java
NeuralNetwork net = NeuralNetLoader.loadNeuralNet("src/saved_network.net");
```
