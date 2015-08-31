package Classification;

import RandomWalk.PatternFile;
import algorithms.NeuralNetwork.FeedForward.HiddenLayer;
import algorithms.NeuralNetwork.FeedForward.InputLayer;
import algorithms.NeuralNetwork.FeedForward.NeuralNetwork;
import algorithms.NeuralNetwork.FeedForward.OutputLayer;
import algorithms.Optimisation.OptimisationProblem.NeuralNetworkProblem;
import algorithms.Optimisation.OptimisationProblem.OptimisationProblem;
import algorithms.Optimisation.PopulationBased.PSO.GbestPSO;
import algorithms.Optimisation.Solution.ParticleList;
import algorithms.Optimisation.Solution.SolutionException;
import algorithms.Optimisation.Solution.SolutionList;
import algorithms.Optimisation.StoppingCondition.IterationMaxStoppingCondition;
import algorithms.Optimisation.StoppingCondition.StoppingCondition;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Eduan on 2015-08-31.
 */
public class NeuralNetworkMain
{
    static final int ITERATION_MAX = 100;
    public static void main(String[] args)
    {
        try {
            PatternFile generalisationSet = MNISTReader.read("C:\\Users\\Eduan\\Google Drive\\git\\JAIA\\src\\usage\\Classification\\t10k-images.idx3-ubyte", "C:\\Users\\Eduan\\Google Drive\\git\\JAIA\\src\\usage\\Classification\\t10k-labels.idx1-ubyte");
            //PatternFile trainingSet = generalisationSet;
            PatternFile trainingSet = MNISTReader.read("C:\\Users\\Eduan\\Google Drive\\git\\JAIA\\src\\usage\\Classification\\train-images.idx3-ubyte", "C:\\Users\\Eduan\\Google Drive\\git\\JAIA\\src\\usage\\Classification\\train-labels.idx1-ubyte");
            System.out.println("Done reading MNIST.");
            NeuralNetwork nn = createNetwork1(trainingSet.getNumIn(), trainingSet.getNumOut());
            OptimisationProblem optimisationProblem = new NeuralNetworkProblem(nn,trainingSet,generalisationSet);
            StoppingCondition stoppingCondition = new IterationMaxStoppingCondition(ITERATION_MAX);
            GbestPSO pso = new GbestPSO(2,2);
            ParticleList particleList = new ParticleList(30,optimisationProblem.getProblemDimension());
            particleList.initialiseList(optimisationProblem.getMin(), optimisationProblem.getMax());
            SolutionList result = pso.runUntilCondition(particleList,optimisationProblem,stoppingCondition);
            System.out.println(nn.getMeanSquareError(trainingSet.getInputs(),trainingSet.getOutputs()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolutionException e) {
            e.printStackTrace();
        }
    }
    private static NeuralNetwork createNetwork1(int numInputs, int numOut)
    {
        LinkedList<HiddenLayer> hl = new LinkedList<>();
        hl.add(new HiddenLayer(4, numInputs));
        return new NeuralNetwork(new InputLayer(numInputs),hl,new OutputLayer(numOut,4));
    }
}
