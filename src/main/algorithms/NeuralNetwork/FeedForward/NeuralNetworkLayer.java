package algorithms.NeuralNetwork.FeedForward;

import java.util.LinkedList;
import java.util.Random;

public abstract class NeuralNetworkLayer
{
    int neuronCount;
    /*
     * Total Neurons in previous layer
     */
    int inputCount;
    boolean[][] randomWalkDirection = null;
    private double[][] weights = null;

    public NeuralNetworkLayer(int _neuronCount, int _inputCount)
    {
        neuronCount = _neuronCount;
        inputCount = _inputCount;
        weights = new double[_inputCount][_neuronCount];
        randomWalkDirection = new boolean[_inputCount][_neuronCount];
    }

    public int getNeuronCount()
    {
        return neuronCount;
    }

    public int getInputCount()
    {
        return inputCount;
    }

    public boolean[][] getRandomWalkDirection()
    {
        return randomWalkDirection;
    }

    public void setRandomWalkDirection(boolean[][] value) { randomWalkDirection = value;}

    public LinkedList<Double> getAllWeights()
    {
        LinkedList<Double> result = new LinkedList<>();
        for (int i = 0; i < inputCount; i++) {
            for (int j = 0; j < neuronCount; j++) {
                result.add(weights[i][j]);
            }
        }
        return result;
    }

    public void setAllWeights(double value)
    {
        for (double[] d : weights) {
            for (int i = 0; i < d.length; i++) {
                d[i] = value;
            }
        }
    }

    public double[] getOutput(double[] input)
    {
        double[] result = new double[neuronCount];
        for (int i = 0; i < neuronCount; i++) {
            double temp = 0;
            for (int j = 0; j < input.length; j++) {
                temp += input[j] * weights[j][i];
            }
            result[i] = sigmoid(temp);
        }
        return result;
    }

    protected double sigmoid(double x)
    {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    public double[] getStepOutput(double[] input)
    {
        double[] result = new double[neuronCount];
        for (int i = 0; i < neuronCount; i++) {
            double temp = 0;
            for (int j = 0; j < input.length; j++) {
                temp += input[j] * weights[j][i];
            }
            result[i] = sigmoid(temp);
            if (result[i] >= 0.5)
                result[i] = 1;
            else
                result[i] = 0;
        }
        return result;
    }

    public void randomizeAllWeights()
    {
        Random r = new Random(System.nanoTime());
        double max = 1.0 / Math.sqrt(inputCount);
        double min = -max;
        for (double[] d : weights) {
            for (int i = 0; i < d.length; i++) {
                double weight = min + (max - min) * r.nextDouble();
                d[i] = weight;
            }
        }
    }

    public void randomizeAllWeightsToRange(double range)
    {
        Random r = new Random(System.nanoTime());
        double max = range;
        double min = -max;
        for (double[] d : weights) {
            for (int i = 0; i < d.length; i++) {
                double weight = min + (max - min) * r.nextDouble();
                d[i] = weight;
            }
        }
    }
    //abstract double[] getOutput(double[] input);

    protected double sigmoid(double[] input)
    {
        double count = 0.0;
        for (double i : input) {
            count += i;
        }
        return sigmoid(count);
    }

    public double[][] getWeights()
    {
        return weights;
    }

    public void setWeights(double[][] weights)
    {
        this.weights = weights;
    }

    public void setWeight(int x, int y, double value) { weights[x][y] = value;}

    public void setSpecificRandomWalkDirection(int x, int y, boolean value) { randomWalkDirection[x][y] = value;}
}
