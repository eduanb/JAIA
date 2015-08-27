package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.SolutionEmptyException;
import algorithms.Optimisation.Solution.SolutionTypeException;

import java.util.Date;
import java.util.Random;

public class RandomOnePointCrossover implements Crossover
{
	private SelectionStrategy selectionStrategy;
	private int c;

	public RandomOnePointCrossover(SelectionStrategy selectionStrategy)
	{
		this.selectionStrategy = selectionStrategy;
	}

	@Override
	public Solution[] crossover(Solution[] population, double crossoverRate) throws SolutionTypeException, SolutionEmptyException
	{
		c++;
		Random random = new Random();
		random.setSeed(new Date().getTime() + c);
		Solution[] offspring = new Solution[population.length];
		
		int i = 0;
		while(i < population.length)
		{
			Solution[] parents = selectionStrategy.select(population);
			if(random.nextDouble() > crossoverRate)
			{
				//no crossover
				offspring[i++] = parents[0];
				offspring[i++] = parents[1];
			}
			else
			{
				//crossover
				Solution Child1 = parents[0];
				Solution Child2 = parents[1];
				int point = random.nextInt(population.length);
				Child1.swop(Child2,point);
				offspring[i++] = Child1;
				offspring[i++] = Child2;
			}
		}
		return offspring;
	}

}
