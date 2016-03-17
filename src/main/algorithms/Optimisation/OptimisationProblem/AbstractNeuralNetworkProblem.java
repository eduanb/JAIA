package algorithms.Optimisation.OptimisationProblem;

import algorithms.NeuralNetwork.FeedForward.NeuralNetwork;

public abstract class AbstractNeuralNetworkProblem implements OptimisationProblem
{
    public abstract NeuralNetwork getNeuralNetwork();

    public abstract double evaluate(NeuralNetwork nn);
}
