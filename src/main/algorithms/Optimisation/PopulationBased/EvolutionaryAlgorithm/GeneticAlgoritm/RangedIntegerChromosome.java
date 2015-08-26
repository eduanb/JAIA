package AI.SEARCH.GA;

import java.util.Date;
import java.util.Random;

public class RangedIntegerChromosome extends IntegerChromosome
{
	private int min = Integer.MIN_VALUE;
	private int max = Integer.MAX_VALUE;
	public RangedIntegerChromosome(int[] genes, FitnessFunction fitnessFunction, int min, int max)
	{
		super(genes,fitnessFunction);
		this.min = min;
		this.max = max;
	}

	public RangedIntegerChromosome(int size, FitnessFunction fitnessFunction, int min, int max)
	{
		super(size, fitnessFunction);
		this.min = min;
		this.max = max;
	}

	/**
	 * Randomizes the solution to any values
	 */
	@Override
	public void randomizeChromosome() throws ChromosomeEmptyException
	{
		if (genes == null || genes.length == 0)
			throw new ChromosomeEmptyException();
		c++;
		random.setSeed(new Date().getTime() + c);
		for (int i = 0; i < genes.length; i++)
		{
			genes[i] = random.nextInt((max - min) + 1) + min;
		}
		fitness = fitnessFunction.calculateFitness(this);
	}

	@Override
	public void randomMutateGene(int chromosome)
	{
		genes[chromosome] = random.nextInt((max - min) + 1) + min;
		fitness = fitnessFunction.calculateFitness(this);
	}

}
