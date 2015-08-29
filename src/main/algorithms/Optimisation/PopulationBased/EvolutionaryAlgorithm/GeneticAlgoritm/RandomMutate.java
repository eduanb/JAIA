package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.SolutionList;
import java.util.Random;

public class RandomMutate implements Mutate
{
	Random random;
	double mutationRate;
	double min;
	double max;
	public RandomMutate(double mutationRate, double min, double max)
	{
		random = new Random();
		this.mutationRate = mutationRate;
		this.min = min;
		this.max = max;
	}
	@Override
	public SolutionList mutate(SolutionList population)
	{
		for(Solution solution: population.getSolutions())
		{
			for(int i = 0; i < solution.getNumberOfVariables(); i++)
			{
				if(random.nextDouble() < mutationRate)
				{
					double value = min + (random.nextDouble() * (max - min));
					solution.setVariable(i, value);
				}
			}
		}
		return population;
	}

}
