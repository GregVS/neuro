package neuro.math;

import java.util.Random;

/**
 * Created by Gregory on 10/7/16.
 */
public class MathFunctions {

    public static final float LEARNING_RATE = 0.1f;
    private static final Random random = new Random();

    public static float sigmoid(float x) {
        return (float) (1f / (1f + Math.exp(-x)));
    }

    public static float randomClamped() {
        return random.nextFloat() * 2- 1;
    }

}
