package Algorithm;

public class InputLayer extends NeuralNetworkLayer
{
	public InputLayer(int _neuronCount, int _inputCount)
	{
		super(_neuronCount, _inputCount);
	}

	@Override
	public double[] getOutput(double[] input)
	{
		return input;
	}

}
