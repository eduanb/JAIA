package Algorithm;

public class HiddenLayer extends NeuralNetworkLayer
{
	public HiddenLayer(int _neuronCount, int _inputCount)
	{
		super(_neuronCount, _inputCount);
	}

	public double[] getOutput(double[] input)
	{
		double[] result = new double[neuronCount];

		for (int k = 0; k < neuronCount; k++)
		{
			double[] in = new double[input.length];
			for (int i = 0; i < input.length; i++)
			{
				in[i] = input[i] * weights[i][k];
			}
			// apply neuron function

			result[k] = sigmoid(in);
		}

		return result;
	}
}
