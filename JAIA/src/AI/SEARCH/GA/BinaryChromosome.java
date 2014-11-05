package AI.SEARCH.GA;

import java.util.Date;
import java.util.Random;

public class BinaryChromosome implements Chromosome
{
	Boolean[] genes;
	int c = 0;
	Random random;
	FitnessFunction fitnessFunction;
	double fitness = -1;

	public BinaryChromosome(Boolean[] genes, FitnessFunction fitnessFunction)
	{
		this.genes = genes;
		random = new Random();
		this.fitnessFunction = fitnessFunction;
		fitness = fitnessFunction.calculateFitness(this);
	}

	public BinaryChromosome(int size, FitnessFunction fitnessFunction)
	{
		genes = new Boolean[size];
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
			genes[i] = random.nextBoolean();
		}
		fitness = fitnessFunction.calculateFitness(this);
	}

	@Override
	public String toString()
	{
		if (genes == null || genes.length == 0)
			return "{}";
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < genes.length; i++)
		{
			result.append(genes[i] + ", ");
		}
		return result.toString();
	}

	@Override
	public void swop(Chromosome other, int point) throws ChromosomeTypeException
	{
		BinaryChromosome otherBinary;
		if (other instanceof BinaryChromosome)
		{
			otherBinary = (BinaryChromosome) other;
		}
		else
		{
			throw new ChromosomeTypeException("Binary Solution Expected!");
		}
		for (int i = 0; i < point; i++)
		{
			boolean temp = this.genes[i];
			this.genes[i] = otherBinary.genes[i];
			otherBinary.genes[i] = temp;
		}

	}

	@Override
	public void randomMutateGene(int chromosone)
	{
		genes[chromosone] = random.nextBoolean();
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
