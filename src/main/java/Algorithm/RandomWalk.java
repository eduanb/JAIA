package Algorithm;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Eduan on 2015-07-28.
 */
public class RandomWalk
{
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    Random random;

    RandomWalk(double min, double max)
    {
        this.min = min;
        this.max = max;
        random = new Random();
        random.setSeed(System.currentTimeMillis());
    }

    NeuralNetwork InitialiseNetwork(NeuralNetwork neuralNetwork)
    {
        LinkedList<HiddenLayer> hiddenLayers = neuralNetwork.getHiddenLayers();
        for (HiddenLayer hl : hiddenLayers)
        {
            for(int i = 0; i < hl.getInputCount(); i++)
            {
                for(int j = 0; j < hl.getNeuronCount(); i++)
                {
                    boolean randomDirection = random.nextBoolean();
                    double weight = getRandomEdge(randomDirection);
                    hl.getRandomWalkDirection()[i][j] = randomDirection;
                    hl.getWeights()[i][j] = weight;
                }
            }
        }

        OutputLayer outputLayer = neuralNetwork.getOutputLayer();
        for(int i = 0; i < outputLayer.getInputCount(); i++)
        {
            for(int j = 0; j < outputLayer.getNeuronCount(); i++)
            {
                boolean randomDirection = random.nextBoolean();
                double weight = getRandomEdge(randomDirection);
                outputLayer.getRandomWalkDirection()[i][j] = randomDirection;
                outputLayer.getWeights()[i][j] = weight;
            }
        }
        return neuralNetwork;
    }

    NeuralNetwork AbsoluteStep(NeuralNetwork neuralNetwork, double stepSize)
    {
        double delta = stepSize;
        return updateWeights(neuralNetwork, delta);
    }

    NeuralNetwork RelativeStep(NeuralNetwork neuralNetwork, double stepSize)
    {
        double delta = stepSize;
        return updateWeights(neuralNetwork, delta);
    }

    private NeuralNetwork updateWeights(NeuralNetwork neuralNetwork, double delta)
    {
        LinkedList<HiddenLayer> hiddenLayers = neuralNetwork.getHiddenLayers();
        for (HiddenLayer hl : hiddenLayers)
        {
            for(int i = 0; i < hl.getInputCount(); i++)
            {
                for(int j = 0; j < hl.getNeuronCount(); i++)
                {
                    boolean newDirection = newDirection(hl.getRandomWalkDirection()[i][j], hl.getWeights()[i][j]);
                    hl.getRandomWalkDirection()[i][j] = newDirection;
                    hl.getWeights()[i][j] = updateWeight(newDirection, hl.getWeights()[i][j], delta);
                }
            }
        }

        OutputLayer outputLayer = neuralNetwork.getOutputLayer();
        for(int i = 0; i < outputLayer.getInputCount(); i++)
        {
            for(int j = 0; j < outputLayer.getNeuronCount(); i++)
            {
                boolean newDirection = newDirection(outputLayer.getRandomWalkDirection()[i][j], outputLayer.getWeights()[i][j]);
                outputLayer.getRandomWalkDirection()[i][j] = newDirection;
                outputLayer.getWeights()[i][j] = updateWeight(newDirection, outputLayer.getWeights()[i][j], delta);
            }
        }
        return neuralNetwork;
    }

    private boolean newDirection(boolean in, double current)
    {
        if ( current <= min || current >= max)
            return !in;
        return in;
    }
    private double updateWeight(boolean in, double current, double delta)
    {
        return in ? (current + delta) : (current - delta);
    }

    private double getRandomEdge(boolean in)
    {
        return in ? min : max;
    }
}
