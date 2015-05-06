package AI.SEARCH.GA;

import java.util.Date;
import java.util.Random;

public class IntegerChromosome implements Chromosome
{
	protected int[] genes;
	protected int c = 0;
	protected Random random;
	protected FitnessFunction fitnessFunction;
	protected double fitness = -1;

	public IntegerChromosome(int[] genes, FitnessFunction fitnessFunction)
	{
		this.genes = genes;
		random = new Random();
		this.fitnessFunction = fitnessFunction;
		fitness = fitnessFunction.calculateFitness(this);
	}

	public IntegerChromosome(int size, FitnessFunction fitnessFunction)
	{
		genes = new int[size];
		random = new Random();
		this.fitnessFunction = fitnessFunction;
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
			genes[i] = random.nextInt();
		}
		fitness = fitnessFunction.calculateFitness(this);
	}

	@Override
	public String toString()
	{
		if (genes == null || genes.length == 0)
			return "{}";
		StringBuilder result = new StringBuilder().append("Fitness:").append(fitness).append(" {");
		for (int i = 0; i < genes.length; i++)
		{
			result.append(genes[i]);
			if(i < genes.length - 1) result.append(", ");
		}
		result.append("}");
		return result.toString();
	}

	@Override
	public void swop(Chromosome other, int point) throws ChromosomeTypeException
	{
		IntegerChromosome otherBinary;
		if (other instanceof IntegerChromosome)
		{
			otherBinary = (IntegerChromosome) other;
		}
		else
		{
			throw new ChromosomeTypeException("Binary Solution Expected!");
		}
		for (int i = 0; i < point; i++)
		{
			int temp = this.genes[i];
			this.genes[i] = otherBinary.genes[i];
			otherBinary.genes[i] = temp;
		}

	}

	@Override
	public void randomMutateGene(int chromosome)
	{
		genes[chromosome] = random. nextInt();
		fitness = fitnessFunction.calculateFitness(this);
	}

	@Override
	public int getNumberOfGenes()
	{
		return genes.length;
	}

	@Override
	public double getFitness() throws ChromosomeEmptyException
	{
		if (genes.length == 0)
			throw new ChromosomeEmptyException();
		if (fitness == -1)
			fitness = fitnessFunction.calculateFitness(this);
		return fitness;
	}

}
