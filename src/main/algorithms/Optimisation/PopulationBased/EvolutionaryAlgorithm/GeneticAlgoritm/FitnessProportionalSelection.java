package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.SolutionException;
import algorithms.Optimisation.Solution.SolutionList;

import java.util.Random;

public class FitnessProportionalSelection implements SelectionStrategy
{
    Random random;

    public FitnessProportionalSelection()
    {
        random = new Random();
    }

    @Override
    public SolutionList select(SolutionList solutionArray) throws SolutionException
    {
        double[] probability = getProbability(solutionArray);
        SolutionList result = new SolutionList(2);

        int parent1 = getParentIndex(probability, random.nextDouble());
        int parent2;
        // choose two different parents
        do {
            parent2 = getParentIndex(probability, random.nextDouble());
        } while (parent1 == parent2);

        result.setSolution(0, solutionArray.getSolution(parent1));
        result.setSolution(1, solutionArray.getSolution(parent2));
        return result;
    }

    private double[] getProbability(SolutionList solutionArray) throws SolutionException
    {
        double[] result = new double[solutionArray.getSize()];
        // calculate the totalFitness of all the solutions in s
        float totalFitness = 0;
        for (Solution solution : solutionArray.getSolutions())
            totalFitness += solution.getFitness();

        for (int i = 0; i < solutionArray.getSize(); i++) {
            result[i] = solutionArray.getSolution(i).getFitness() / totalFitness;
            if (i != 0)
                result[i] = result[i] + result[i - 1];
        }
        return result;
    }

    private int getParentIndex(double[] probability, double value)
    {
        int index = 0;
        for (int i = 0; i < probability.length; i++) {
            if (probability[i] > value) {
                index = i;
                break;
            }
        }
        return index;
    }

}
