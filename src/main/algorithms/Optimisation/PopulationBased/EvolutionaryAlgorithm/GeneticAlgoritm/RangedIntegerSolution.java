package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.IntegerSolution;
import algorithms.Optimisation.Solution.SolutionEmptyException;

import java.util.Date;

public class RangedIntegerSolution extends IntegerSolution
{
	private int min = Integer.MIN_VALUE;
	private int max = Integer.MAX_VALUE;
	public RangedIntegerSolution(int[] genes, FitnessFunction fitnessFunction, int min, int max)
	{
		super(genes,fitnessFunction);
		this.min = min;
		this.max = max;
	}

	public RangedIntegerSolution(int size, FitnessFunction fitnessFunction, int min, int max)
	{
		super(size, fitnessFunction);
		this.min = min;
		this.max = max;
	}

	/**
	 * Randomizes the solution to any values
	 */
	@Override
	public void randomizeSolution() throws SolutionEmptyException
	{
		if (genes == null || genes.length == 0)
			throw new SolutionEmptyException();
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
