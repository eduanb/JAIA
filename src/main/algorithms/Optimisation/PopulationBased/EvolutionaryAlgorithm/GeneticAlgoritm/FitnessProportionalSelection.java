package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import java.util.Date;
import java.util.Random;

public class FitnessProportionalSelection implements SelectionStrategy
{
	Random random;
	int c;
	ChromosomeSorter sorter;

	public FitnessProportionalSelection(ChromosomeSorter sorter)
	{
		random = new Random();
		this.sorter = sorter;
	}

	@Override
	public Chromosome[] select(Chromosome[] chromosomeArray) throws ChromosomeEmptyException
	{
		sorter.sortAscending(chromosomeArray);
		double[] probability = getProbability(chromosomeArray);
		Chromosome[] result = new Chromosome[2];
		c++;
		random.setSeed(new Date().getTime() + c);

		int parent1 = getParentIndex(probability, random.nextDouble());
		int parent2;
		// choose two different parents
		do
		{
			parent2 = getParentIndex(probability, random.nextDouble());
		} while (parent1 == parent2);

		result[0] = chromosomeArray[parent1];
		result[1] = chromosomeArray[parent2];
		return result;
	}

	private int getParentIndex(double[] probability, double value)
	{
		int index = 0;
		for (int i = 0; i < probability.length; i++)
		{
			if (probability[i] > value)
			{
				index = i;
				break;
			}
		}
		return index;
	}

	private double[] getProbability(Chromosome[] chromosomeArray) throws ChromosomeEmptyException
	{
		double[] result = new double[chromosomeArray.length];
		// calculate the totalFitness of all the solutions in s
		float totalFitness = 0;
		for (int i = 0; i < chromosomeArray.length; i++)
			totalFitness += chromosomeArray[i].getFitness();

		for (int i = 0; i < chromosomeArray.length; i++)
		{
			result[i] = chromosomeArray[i].getFitness() / totalFitness;
			if (i != 0)
				result[i] = result[i] + result[i - 1];
		}
		return result;
	}

}
