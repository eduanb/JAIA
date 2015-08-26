package EXAMPLE.SODUKU;

import AI.SEARCH.GA.*;

import java.io.File;

public class Main
{

	public static void main(String[] args) throws ChromosomeEmptyException, ChromosomeTypeException
	{
		double crossoverRate = 0.5;
		double mutationRate = 0.5;
		// Create GA
		SelectionStrategy selectionStrategy = new TournamentSelection(new JavaChromosomeSorter(),3);
		Crossover crossover = new RandomOnePointCrossover(selectionStrategy);
		Mutate mutate = new RandomMutate();
		GeneticAlgorithm GA = new GeneticAlgorithm(crossover, mutate);

		// Create chromosomes
		Chromosome[] chromosomes = new Chromosome[30];
		File file = new File("JAIA/board1.txt");
		System.out.println(file.getAbsolutePath());
		SodokuFit sodokuFit = new SodokuFit(file);
		int size = sodokuFit.getSizeOfSolution();
		for(int i = 0; i < 30; i++)
		{
			chromosomes[i] =  new RangedIntegerChromosome(size, sodokuFit,  1, 9);
			chromosomes[i].randomizeChromosome();
		}
		System.out.println("AVG:" + fitAvg(chromosomes));
		chromosomes = GA.runUntilCondition(chromosomes, crossoverRate,mutationRate, new GenerationMaxTerminateCondition(100));
		//chromosomes = GA.nextGeneration(chromosomes,crossoverRate,mutationRate);
		double bestfit = 0;
		int bestpos = 0;
		for(int i = 0; i < 30; i++)
		{
			if(chromosomes[i].getFitness() > bestfit)
			{
				bestfit = chromosomes[i].getFitness();
				bestpos = i;
			}
		}
		System.out.println("AVG:" + fitAvg(chromosomes));
		System.out.println("Best:" + bestfit);
		sodokuFit.printBoard(chromosomes[1]);
		System.out.println();
		sodokuFit.printBoard(chromosomes[0]);
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