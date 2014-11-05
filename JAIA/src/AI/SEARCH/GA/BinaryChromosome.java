package AI.SEARCH.GA;

import java.util.Date;
import java.util.Random;

public class BinaryChromosome implements Chromosome
{
	Boolean[] genes;
	int c = 0;
	Random random;
	public BinaryChromosome(Boolean[] genes)
	{
		this.genes = genes;
		random = new Random();
	}

	public BinaryChromosome(int size)
	{
		genes = new Boolean[size];
		random = new Random();
	}

	/**
	 * Randomizes the solution to any values
	 */
	@Override
	public void randomizeChromosome() throws SolutionEmptyException
	{
		if (genes == null || genes.length == 0)
			throw new SolutionEmptyException();
		c++;
		random.setSeed(new Date().getTime() + c);
		for (int i = 0; i < genes.length; i++)
		{
			genes[i] = random.nextBoolean();
		}

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
	public void swop(Chromosome other, int point) throws SolutionTypeException
	{
		BinaryChromosome otherBinary;
		if (other instanceof BinaryChromosome)
		{
			otherBinary = (BinaryChromosome) other;
		}
		else
		{
			throw new SolutionTypeException("Binary Solution Expected!");
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

}
