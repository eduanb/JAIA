import java.util.Random;

public abstract class NeuralNetworkLayer
{
	int neuronCount;
	/*
	 * Total Neurons in previous layer
	 */
	int inputCount;
	double[][] weights = null;

	public NeuralNetworkLayer(int _neuronCount, int _inputCount)
	{
		neuronCount = _neuronCount;
		inputCount = _inputCount;
		weights = new double[_inputCount][_neuronCount];
	}
	public double[] getOutput(double[] input)
	{
		double[] result = new double[neuronCount];
		for(int i = 0; i < neuronCount;i++)
		{
			double temp = 0;
			for(int j = 0; j < input.length; j++)
			{
				temp += input[j] * weights[j][i];
			}
			result[i] = sigmoid(temp);
		}
		return result;
	}
	public void setAllWeights(double value)
	{
		for (double[] d : weights)
		{
			for (int i = 0; i < d.length; i++)
			{
				d[i] = value;
			}
		}
	}

	public void randomizeAllWeights()
	{
		Random r = new Random(System.nanoTime());
		double max = 1.0 / Math.sqrt(inputCount);
		double min = -max;
		for (double[] d : weights)
		{
			for (int i = 0; i < d.length; i++)
			{
				double weight = min + (max - min) * r.nextDouble();
				d[i] = weight;
			}
		}
	}
	
	protected double sigmoid(double[] input)
	{
		double count = 0.0;
		for(double i : input)
		{
			count += i;
		}
		return sigmoid(count);
	}
	protected double sigmoid(double x)
	{
	    return 1.0 / (1.0 + Math.exp(-x));
	}
	//abstract double[] getOutput(double[] input);

	public double[][] getWeights()
	{
		return weights;
	}
	
}
