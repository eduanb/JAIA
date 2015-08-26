package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import java.util.Date;
import java.util.Random;

public class BinaryChromosome implements Chromosome
{
	boolean[] genes;
	int c = 0;
	Random random;
	FitnessFunction fitnessFunction;
	double fitness = -1;

	public BinaryChromosome(boolean[] genes, FitnessFunction fitnessFunction)
	{
		this.genes = genes;
		random = new Random();
		this.fitnessFunction = fitnessFunction;
		fitness = fitnessFunction.calculateFitness(this);
	}

	public BinaryChromosome(int size, FitnessFunction fitnessFunction)
	{
		genes = new boolean[size];
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

	@Override
	public Boolean getGene(int position) {
		return genes[position];
	}

}
