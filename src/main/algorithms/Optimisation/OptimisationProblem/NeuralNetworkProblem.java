package algorithms.Optimisation.OptimisationProblem;

import algorithms.NeuralNetwork.FeedForward.NeuralNetwork;
import algorithms.NeuralNetwork.FeedForward.PatternFile;
import algorithms.Optimisation.Solution.Solution;

public class NeuralNetworkProblem extends AbstractNeuralNetworkProblem
{
    private int problemDimension;

    private NeuralNetwork neuralNetwork;

    private PatternFile trainingSet;
    private PatternFile generalisationSet;
    private double range;

    public NeuralNetworkProblem(NeuralNetwork neuralNetwork, PatternFile trainingSet, PatternFile generalisationSet, double range)
    {
        this.neuralNetwork = neuralNetwork;
        this.problemDimension = neuralNetwork.getAllWeights().size();
        this.trainingSet = trainingSet;
        this.generalisationSet = generalisationSet;
        this.range = range;
    }

    public NeuralNetwork getNeuralNetwork()
    {
        return neuralNetwork;
    }

    public double evaluate(NeuralNetwork nn)
    {
        return  nn.getMeanSquareError(trainingSet.getInputs(), trainingSet.getOutputs());
    }

    @Override
    public double evaluate(Solution solution)
    {
        neuralNetwork.setWeights(solution.getVariables());
        double MSE = neuralNetwork.getMeanSquareError(trainingSet.getInputs(), trainingSet.getOutputs());
        System.out.println("Training:" + MSE);
        System.out.println("Generalisation:" + neuralNetwork.getMeanSquareError(generalisationSet.getInputs(), generalisationSet.getOutputs()));
        return MSE;
    }

    @Override
    public double getErrTolerance()
    {
        return 1E-20;
    }

    @Override
    public int getProblemDimension()
    {
        return problemDimension;
    }

    @Override
    public double getTargetValue()
    {
        return 0;
    }

    @Override
    public double getMin()
    {
        return -range;
    }

    @Override
    public double getMax()
    {
        return range;
    }

    @Override
    public String getName()
    {
        return "Neural Network";
    }
}
