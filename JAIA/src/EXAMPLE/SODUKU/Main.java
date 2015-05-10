package EXAMPLE.SODUKU;

import AI.SEARCH.GA.*;

import java.io.File;

public class Main
{

	public static void main(String[] args) throws ChromosomeEmptyException, ChromosomeTypeException
	{
		double crossoverRate = 0.2;
		double mutationRate = 0.01;
		// Create GA
		SelectionStrategy selectionStrategy = new FitnessProportionalSelection(new JavaChromosomeSorter());
		Crossover crossover = new RandomOnePointCrossover(selectionStrategy);
		Mutate mutate = new RandomMutate();
		GeneticAlgorithm GA = new GeneticAlgorithm(crossover, mutate);

		// Create chromosomes
		Chromosome[] chromosomes = new Chromosome[10];
		SodokuFit zero = new SodokuFit(new File("test.txt"));
		for(int i = 0; i < 10; i++)
		{
			chromosomes[i] =  new BinaryChromosome(10, zero);
			chromosomes[i].randomizeChromosome();
		}
		boolean[] arr = {true,true,true,true,true,true,true,true,true,true};
		chromosomes[0] = new BinaryChromosome(arr, zero);
		System.out.println(chromosomes[0]);
		System.out.println(fitAvg(chromosomes));
		chromosomes = GA.runUntilCondition(chromosomes, crossoverRate,mutationRate, new GenerationMaxTerminateCondition(100));
		//chromosomes = GA.nextGeneration(chromosomes,crossoverRate,mutationRate);
		System.out.println(fitAvg(chromosomes));
		System.out.println(chromosomes[0]);
	}
	 static double fitAvg(Chromosome[] c)
	{
		double total = 0;
		for(int i = 0; i < c.length; i ++)
		{
			try
			{
				total += c[i].getFitness();
			}
			catch (ChromosomeEmptyException e)
			{
				e.printStackTrace();
			}
		}
		return total/c.length;
	}
}