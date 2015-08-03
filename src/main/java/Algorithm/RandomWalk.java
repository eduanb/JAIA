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

    public RandomWalk(double min, double max)
    {
        this.min = min;
        this.max = max;
        random = new Random();
        random.setSeed(System.currentTimeMillis());
    }

    public NeuralNetwork InitialiseNetwork(NeuralNetwork neuralNetwork)
    {
        LinkedList<HiddenLayer> hiddenLayers = neuralNetwork.getHiddenLayers();
        for (HiddenLayer hl : hiddenLayers)
        {
            for(int i = 0; i < hl.getInputCount(); i++)
            {
                for(int j = 0; j < hl.getNeuronCount(); j++)
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
            for(int j = 0; j < outputLayer.getNeuronCount(); j++)
            {
                boolean randomDirection = random.nextBoolean();
                double weight = getRandomEdge(randomDirection);
                outputLayer.getRandomWalkDirection()[i][j] = randomDirection;
                outputLayer.getWeights()[i][j] = weight;
            }
        }
        return neuralNetwork;
    }

    public NeuralNetwork AbsoluteStep(NeuralNetwork neuralNetwork, double stepSize)
    {
        double delta = stepSize;
        return updateWeight(neuralNetwork, delta);
    }

    public NeuralNetwork RelativeStep(NeuralNetwork neuralNetwork, double stepSize)
    {
        double delta = stepSize;
        return updateWeight(neuralNetwork, delta);
    }

    private NeuralNetwork updateWeight(NeuralNetwork neuralNetwork, double delta)
    {
        int totalWeights = neuralNetwork.getTotalWeights();
        int weightpos = random.nextInt(totalWeights + 1);
        for(HiddenLayer hiddenLayer: neuralNetwork.getHiddenLayers())
        {
            int weightsInLayer = hiddenLayer.getNeuronCount() * hiddenLayer.getInputCount();
            if(weightpos < weightsInLayer)
            {
                for(int k = 0; k < hiddenLayer.getInputCount(); k++)
                {
                    for (int l = 0; l < hiddenLayer.getNeuronCount(); l++)
                    {
                        weightpos--;
                        if (weightpos == 0)
                        {
                            hiddenLayer.setRandomWalkDirection(k, l, newDirection(hiddenLayer.getRandomWalkDirection()[k][l], hiddenLayer.getWeights()[k][l]));
                            hiddenLayer.setWeight(k, l, updateWeight(hiddenLayer.getRandomWalkDirection()[k][l], hiddenLayer.getWeights()[k][l], delta));
                            return neuralNetwork;
                        }
                    }
                }

                return neuralNetwork;
            }
            weightpos -= weightsInLayer;
        }
        for(int k = 0; k < neuralNetwork.getOutputLayer().getInputCount(); k++)
        {
            for (int l = 0; l < neuralNetwork.getOutputLayer().getNeuronCount(); l++)
            {
                weightpos--;
                if (weightpos == 0)
                {
                    neuralNetwork.getOutputLayer().setRandomWalkDirection(k, l, newDirection(neuralNetwork.getOutputLayer().getRandomWalkDirection()[k][l], neuralNetwork.getOutputLayer().getWeights()[k][l]));
                    neuralNetwork.getOutputLayer().setWeight(k, l, updateWeight(neuralNetwork.getOutputLayer().getRandomWalkDirection()[k][l], neuralNetwork.getOutputLayer().getWeights()[k][l], delta));
                }
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
        return in ? (current - delta) : (current + delta);
    }

    private double getRandomEdge(boolean in)
    {
        return in ? min : max;
    }
}
