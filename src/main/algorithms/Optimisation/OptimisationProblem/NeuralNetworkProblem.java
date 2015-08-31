package algorithms.Optimisation.OptimisationProblem;

import RandomWalk.PatternFile;
import algorithms.NeuralNetwork.FeedForward.NeuralNetwork;
import algorithms.Optimisation.Solution.Solution;

public class NeuralNetworkProblem implements OptimisationProblem
{
	private int problemDimention;

	private  NeuralNetwork neuralNetwork;

	private PatternFile trainingSet;
	private PatternFile generalisationSet;

	public NeuralNetworkProblem(NeuralNetwork neuralNetwork, PatternFile trainingSet, PatternFile generalisationSet)
	{
		this.neuralNetwork = neuralNetwork;
		this.problemDimention = neuralNetwork.getAllWeights().size();
		this.trainingSet = trainingSet;
		this.generalisationSet = generalisationSet;
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
		return problemDimention;
	}

	@Override
	public double getTargetValue()
	{
		return 0;
	}

	@Override
	public double getMax()
	{
		return 5;
	}

	@Override
	public double getMin()
	{
		return -5;
	}

	@Override
	public String getName()
	{
		return "Neural Network";
	}
}
