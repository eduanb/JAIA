package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.*;

import java.util.Date;
import java.util.Random;

public class RandomOnePointCrossover implements Crossover
{
	private SelectionStrategy selectionStrategy;
	private int c;
	double crossoverRate;

	public RandomOnePointCrossover(SelectionStrategy selectionStrategy, double crossoverRate)
	{
		this.selectionStrategy = selectionStrategy;
		this.crossoverRate = crossoverRate;
	}

	@Override
	public SolutionList crossover(SolutionList population) throws SolutionException
	{
		Random random = new Random();
		SolutionList offspring = new SolutionList(population.getSize());
		
		int i = 0;
		while(i < population.getSize())
		{
			SolutionList parents = selectionStrategy.select(population);
			if(random.nextDouble() > crossoverRate)
			{
				//no crossover
				offspring.setSolution(i++,parents.getSolution(0).clone());
				offspring.setSolution(i++,parents.getSolution(1).clone());
			}
			else
			{
				//crossover
				Solution Child1 = parents.getSolution(0).clone();
				Solution Child2 = parents.getSolution(1).clone();
				int point = random.nextInt(Child1.getNumberOfVariables());
				Child1.swop(Child2,point);
				offspring.setSolution(i++,Child1);
				offspring.setSolution(i++,Child2);
			}
		}
		return offspring;
	}

}
