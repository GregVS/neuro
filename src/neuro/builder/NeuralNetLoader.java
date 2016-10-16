package neuro.builder;

import neuro.network.NeuralNetwork;

import java.io.*;

/**
 * Created by Gregory on 10/7/16.
 */
public class NeuralNetLoader {

    public static void saveNeuralNet(String filepath, NeuralNetwork neuralNet) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filepath));
        fileOutputStream.write(serialize(neuralNet));
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static NeuralNetwork loadNeuralNet(String filepath) throws IOException, ClassNotFoundException {
        File file = new File(filepath);
        byte[] bytes = new byte[(int) file.length()];

        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(bytes);
        fileInputStream.close();

        return (NeuralNetwork) deserialize(bytes);
    }

    private static byte[] serialize(Object obj) throws IOException {
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(obj);
            }
            return b.toByteArray();
        }
    }

    private static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream o = new ObjectInputStream(b)){
                return o.readObject();
            }
        }
    }
}
