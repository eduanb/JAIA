package Algorithm.RandomWalk;

import Algorithm.NeuralNetwork.HiddenLayer;
import Algorithm.NeuralNetwork.NeuralNetwork;

/**
 * Created by Eduan on 2015-07-28.
 */
public class ManhattanRandomWalk extends AbstractRandomWalk
{
    public ManhattanRandomWalk(double min, double max)
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
                            hiddenLayer.setSpecificRandomWalkDirection(k, l, newDirection(hiddenLayer.getRandomWalkDirection()[k][l], hiddenLayer.getWeights()[k][l]));
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
                    neuralNetwork.getOutputLayer().setSpecificRandomWalkDirection(k, l, newDirection(neuralNetwork.getOutputLayer().getRandomWalkDirection()[k][l], neuralNetwork.getOutputLayer().getWeights()[k][l]));
                    neuralNetwork.getOutputLayer().setWeight(k, l, updateWeight(neuralNetwork.getOutputLayer().getRandomWalkDirection()[k][l], neuralNetwork.getOutputLayer().getWeights()[k][l], delta));
                }
            }
        }
        return neuralNetwork;
    }
}
