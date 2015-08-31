package algorithms.NeuralNetwork.FeedForward;

import java.util.LinkedList;
import java.util.List;

public class NeuralNetwork
{
	InputLayer inputLayer = null;
	LinkedList<HiddenLayer> hiddenLayers = null;
	OutputLayer outputLayer = null;
    int totalWeights;

    public int getTotalWeights() {
        return totalWeights;
    }

    public NeuralNetwork(InputLayer inputLayer, LinkedList<HiddenLayer> hiddenLayers, OutputLayer outputLayer)
	{
        this.inputLayer = inputLayer;
        this.hiddenLayers = hiddenLayers;
        this.outputLayer = outputLayer;
        for (HiddenLayer hiddenLayer: hiddenLayers)
            totalWeights += (hiddenLayer.getInputCount() * hiddenLayer.getNeuronCount());
        totalWeights += (outputLayer.getInputCount() * outputLayer.getNeuronCount());

	}

	public InputLayer getInputLayer() {
		return inputLayer;
	}

	public LinkedList<HiddenLayer> getHiddenLayers() {
		return hiddenLayers;
	}

	public OutputLayer getOutputLayer() {
		return outputLayer;
	}

	public double[] getOutput(double[] inputs)
	{
		double[] lastOuts;
		lastOuts = inputLayer.getOutput(inputs);
		for(HiddenLayer hiddenLayer : hiddenLayers)
        {
            lastOuts = hiddenLayer.getOutput(lastOuts);
        }
		return outputLayer.getOutput(lastOuts);
	}

	public double[] getStepOutput(double[] inputs)
	{
		double[] lastOuts;
		lastOuts = inputLayer.getOutput(inputs);
		for(HiddenLayer hiddenLayer : hiddenLayers)
		{
			lastOuts = hiddenLayer.getOutput(lastOuts);
		}
		return outputLayer.getStepOutput(lastOuts);
	}

	public double getTrainingError(LinkedList<double[]> inputs, LinkedList<double[]> expectedOutputs)
	{
		double result = inputs.size();
		for(int i = 0; i < inputs.size(); i++)
		{
			result -= 1;
			double[] stepOutputs = getStepOutput(inputs.get(i));
			for(int j = 0; j < stepOutputs.length; j++)
			{
				if(stepOutputs[j] != expectedOutputs.get(i)[j])
				{
					result += 1;
					break;
				}
			}
		}
		if(result == 0) return 0;
		return result/inputs.size();
	}

	public double getMeanSquareError(List<double[]> inputs, LinkedList<double[]> expectedOutputs)
	{
		double result = 0;
		for(int i = 0; i < inputs.size(); i++) //for each pattern
		{
			double[] temp = inputs.get(i); // for speedup
			double[] actualOutputs = getOutput(temp);
			for(int j = 0; j < actualOutputs.length; j++)
			{
				result += Math.pow((temp[j] - actualOutputs[j]), 2);
			}
		}
		return result/inputs.size();
	}

    public void setAllWeights(double w)
    {
        for(HiddenLayer hiddenLayer : hiddenLayers)
        {
            hiddenLayer.setAllWeights(w);
        }
        outputLayer.setAllWeights(w);
    }

	public void randomiseAllWeights(double range)
	{
		for(HiddenLayer hiddenLayer : hiddenLayers)
		{
			hiddenLayer.randomizeAllWeightsToRange(range);
		}
		outputLayer.randomizeAllWeightsToRange(range);
	}

	public void printNetwork()
	{
		System.out.print("hiddenWeights:");
        for(HiddenLayer hiddenLayer : hiddenLayers)
        {
            double[][] hiddenWeights = hiddenLayer.getWeights();
            for (double[] d : hiddenWeights)
            {
                for (int i = 0; i < d.length; i++)
                {
                    System.out.print(d[i] + ", ");
                }
            }
        }
		System.out.println();
		System.out.print("outWeights:");
		double[][] outWeights = outputLayer.getWeights();
		for (double[] d : outWeights)
		{
			for (int i = 0; i < d.length; i++)
			{
				System.out.print(d[i] + ", ");
			}
		}
		System.out.println();
	}

	public LinkedList<Double> getAllWeights()
	{
		LinkedList<Double> result = new LinkedList<>();
		for(HiddenLayer hiddenLayer : hiddenLayers)
		{
			result.addAll(hiddenLayer.getAllWeights());
		}
		result.addAll(outputLayer.getAllWeights());
		return result;
	}

	public void setWeights(double[] variables) {
		int pos = 0;
		for(HiddenLayer hiddenLayer : hiddenLayers)
		{
			double[][] hiddenLayerWeights = new double[hiddenLayer.getInputCount()][hiddenLayer.getNeuronCount()];
			for(int i = 0; i < hiddenLayer.getInputCount(); i++)
			{
				for(int j = 0; j < hiddenLayer.getNeuronCount(); j++)
				{
					hiddenLayerWeights[i][j] = variables[pos++];
				}
			}
			hiddenLayer.setWeights(hiddenLayerWeights);
		}
		double[][] outputWeights = new double[outputLayer.getInputCount()][outputLayer.getNeuronCount()];
		for(int i = 0; i < outputLayer.getInputCount(); i++)
		{
			for(int j = 0; j < outputLayer.getNeuronCount(); j++)
			{
				outputWeights[i][j] = variables[pos++];
			}
		}
		outputLayer.setWeights(outputWeights);
	}
}