package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.SolutionEmptyException;
import algorithms.Optimisation.Solution.SolutionSorter;

import java.util.Date;
import java.util.Random;

public class FitnessProportionalSelection implements SelectionStrategy
{
	Random random;
	int c;
	SolutionSorter sorter;

	public FitnessProportionalSelection(SolutionSorter sorter)
	{
		random = new Random();
		this.sorter = sorter;
	}

	@Override
	public Solution[] select(Solution[] solutionArray) throws SolutionEmptyException
	{
		sorter.sortAscending(solutionArray);
		double[] probability = getProbability(solutionArray);
		Solution[] result = new Solution[2];
		c++;
		random.setSeed(new Date().getTime() + c);

		int parent1 = getParentIndex(probability, random.nextDouble());
		int parent2;
		// choose two different parents
		do
		{
			parent2 = getParentIndex(probability, random.nextDouble());
		} while (parent1 == parent2);

		result[0] = solutionArray[parent1];
		result[1] = solutionArray[parent2];
		return result;
	}

	private int getParentIndex(double[] probability, double value)
	{
		int index = 0;
		for (int i = 0; i < probability.length; i++)
		{
			if (probability[i] > value)
			{
				index = i;
				break;
			}
		}
		return index;
	}

	private double[] getProbability(Solution[] solutionArray) throws SolutionEmptyException
	{
		double[] result = new double[solutionArray.length];
		// calculate the totalFitness of all the solutions in s
		float totalFitness = 0;
		for (int i = 0; i < solutionArray.length; i++)
			totalFitness += solutionArray[i].getFitness();

		for (int i = 0; i < solutionArray.length; i++)
		{
			result[i] = solutionArray[i].getFitness() / totalFitness;
			if (i != 0)
				result[i] = result[i] + result[i - 1];
		}
		return result;
	}

}
