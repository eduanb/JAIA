package algorithms.RandomWalk;

import algorithms.NeuralNetwork.FeedForward.HiddenLayer;
import algorithms.NeuralNetwork.FeedForward.NeuralNetwork;
import algorithms.NeuralNetwork.FeedForward.OutputLayer;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Eduan on 2015-07-28.
 */
public abstract class AbstractRandomWalk
{
    protected double min = Double.MAX_VALUE;
    protected double max = Double.MIN_VALUE;
    protected Random random;

    public AbstractRandomWalk(double min, double max)
    {
        this.min = min;
        this.max = max;
        random = new Random();
        random.setSeed(System.currentTimeMillis());
    }

    public NeuralNetwork InitialiseNetwork(NeuralNetwork neuralNetwork)
    {
        LinkedList<HiddenLayer> hiddenLayers = neuralNetwork.getHiddenLayers();
        for (HiddenLayer hl : hiddenLayers) {
            for (int i = 0; i < hl.getInputCount(); i++) {
                for (int j = 0; j < hl.getNeuronCount(); j++) {
                    boolean randomDirection = random.nextBoolean();
                    double weight = getRandomEdge(randomDirection);
                    hl.getRandomWalkDirection()[i][j] = randomDirection;
                    hl.getWeights()[i][j] = weight;
                }
            }
        }

        OutputLayer outputLayer = neuralNetwork.getOutputLayer();
        for (int i = 0; i < outputLayer.getInputCount(); i++) {
            for (int j = 0; j < outputLayer.getNeuronCount(); j++) {
                boolean randomDirection = random.nextBoolean();
                double weight = getRandomEdge(randomDirection);
                outputLayer.getRandomWalkDirection()[i][j] = randomDirection;
                outputLayer.getWeights()[i][j] = weight;
            }
        }
        return neuralNetwork;
    }

    private double getRandomEdge(boolean in)
    {
        return in ? min : max;
    }

    public abstract NeuralNetwork AbsoluteStep(NeuralNetwork neuralNetwork, double stepSize);

    public abstract NeuralNetwork RelativeStep(NeuralNetwork neuralNetwork, double stepSize);

    protected boolean newDirection(boolean in, double current)
    {
        if (current <= min || current >= max)
            return !in;
        return in;
    }

    protected double updateWeight(boolean in, double current, double delta)
    {
        return in ? (current - delta) : (current + delta);
    }
}
