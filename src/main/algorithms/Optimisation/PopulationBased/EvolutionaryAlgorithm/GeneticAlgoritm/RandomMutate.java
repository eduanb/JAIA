package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import java.util.Date;
import java.util.Random;

public class RandomMutate implements Mutate
{
	int c = 0;
	Random random;
	public RandomMutate()
	{
		random = new Random();
	}
	@Override
	public Chromosome[] mutate(Chromosome[] population, double mutationRate)
	{
		c++;
		for(int i = 0; i < population.length; i++)
		{
			for(int j = 0; j < population[i].getNumberOfGenes();j++)
			{
				if(random.nextDouble() < mutationRate)
				{
					//I is the chromosone in population
					//J is the gene to mutate
					population[i].randomMutateGene(j);
				}
			}
		}
		return population;
	}

}
