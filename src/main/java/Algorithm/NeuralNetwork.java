package Algorithm;

import java.util.LinkedList;

public class NeuralNetwork
{
	InputLayer inputLayer = null;
	LinkedList<HiddenLayer> hiddenLayers = null;
	OutputLayer outputLayer = null;

	public NeuralNetwork(InputLayer inputLayer, LinkedList<HiddenLayer> hiddenLayers, OutputLayer outputLayer)
	{
        this.inputLayer = inputLayer;
        this.hiddenLayers = hiddenLayers;
        this.outputLayer = outputLayer;
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