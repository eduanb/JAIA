package AI.SEARCH.GA;

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
	public Chromosome[] crossover(Chromosome[] population, double crossoverRate) throws ChromosomeTypeException, ChromosomeEmptyException
	{
		c++;
		Random random = new Random();
		random.setSeed(new Date().getTime() + c);
		Chromosome[] offspring = new Chromosome[population.length];
		
		int i = 0;
		while(i < population.length)
		{
			Chromosome[] parents = selectionStrategy.select(population);
			if(random.nextFloat() > crossoverRate)
			{
				//no crossover
				offspring[i++] = parents[0];
				offspring[i++] = parents[1];
			}
			else
			{
				//crossover
				Chromosome Child1 = parents[0];
				Chromosome Child2 = parents[1];
				int point = random.nextInt(population.length);
				Child1.swop(Child2,point);
				offspring[i++] = Child1;
				offspring[i++] = Child2;
			}
		}
		return offspring;
	}

}
