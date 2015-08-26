package Algorithm.NeuralNetwork;

public class InputLayer extends NeuralNetworkLayer
{
	public InputLayer(int _neuronCount)
	{
		super(_neuronCount, 1);
	}

	@Override
	public double[] getOutput(double[] input)
	{
		return input;
	}

}
