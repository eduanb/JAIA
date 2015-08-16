package Algorithm;

/**
 * Created by Eduan on 2015-07-28.
 */
public class RandomWalk extends AbstractRandomWalk
{
    public RandomWalk(double min, double max)
    {
        super(min, max);
    }

    @Override
    public NeuralNetwork AbsoluteStep(NeuralNetwork neuralNetwork, double stepSize)
    {
        double delta = stepSize;
        return updateWeight(neuralNetwork, delta);
    }

    @Override
    public NeuralNetwork RelativeStep(NeuralNetwork neuralNetwork, double stepSize)
    {
        double delta = stepSize;
        return updateWeight(neuralNetwork, delta);
    }

    private NeuralNetwork updateWeight(NeuralNetwork neuralNetwork, double max)
    {
        for(HiddenLayer hiddenLayer : neuralNetwork.getHiddenLayers())
        {
            hiddenLayer.setWeights(setAllWeights(hiddenLayer.getWeights(), hiddenLayer.getRandomWalkDirection(), max));
        }
        neuralNetwork.getOutputLayer().setWeights(setAllWeights(neuralNetwork.getOutputLayer().getWeights(),neuralNetwork.getOutputLayer().getRandomWalkDirection(), max));
        return neuralNetwork;
    }

    private double[][] setAllWeights(double[][] weights, boolean[][] directions, double max)
    {
        for(int x = 0; x < weights.length; x++)
        {
            for(int y = 0; y < weights[x].length; y++)
            {
                double delta = random.nextDouble() * max;
                weights[x][y] = updateWeight(directions[x][y],weights[x][y],delta);
            }
        }
        return weights;
    }

}
