package Algorithm;

import java.util.LinkedList;

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

	public double getTrainingError(LinkedList<double[]> inputs, LinkedList<double[]> outputs)
	{
		double result = 0;
		for(int i = 0; i < inputs.size(); i++)
		{
			result += 1;
			double[] stepOutputs = getStepOutput(inputs.get(i));
			for(int j = 0; j < stepOutputs.length; j++)
			{
				if(stepOutputs[j] != outputs.get(i)[j])
				{
					result -= 1;
					break;
				}
			}
		}
		return result;
	}

    public void setAllWeights(double w)
    {
        inputLayer.setAllWeights(w);
        for(HiddenLayer hiddenLayer : hiddenLayers)
        {
            hiddenLayer.setAllWeights(w);
        }
        outputLayer.setAllWeights(w);
    }

	public void printNetwork()
	{
		double[][] inWeights = inputLayer.getWeights();
		System.out.print("inputWeights:");
		for (double[] d : inWeights)
		{
			for (int i = 0; i < d.length; i++)
			{
				System.out.print(d[i] + ", ");
			}
		}
		System.out.println();
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
}