package Optimisation;

import algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm.*;
import algorithms.Optimisation.Solution.JavaSolutionSorter;
import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.SolutionEmptyException;
import algorithms.Optimisation.Solution.SolutionTypeException;
import algorithms.Optimisation.StoppingCondition.IterationMaxStoppingCondition;

import java.io.File;

public class Main
{

	public static void main(String[] args) throws SolutionEmptyException, SolutionTypeException
	{
		double crossoverRate = 0.5;
		double mutationRate = 0.5;
		// Create GA
		SelectionStrategy selectionStrategy = new TournamentSelection(new JavaSolutionSorter(),3);
		Crossover crossover = new RandomOnePointCrossover(selectionStrategy);
		Mutate mutate = new RandomMutate();
		GeneticAlgorithm GA = new GeneticAlgorithm(crossover, mutate);

		// Create solutions
		Solution[] solutions = new Solution[30];
		File file = new File("JAIA/board1.txt");
		System.out.println(file.getAbsolutePath());
		SodokuFit sodokuFit = new SodokuFit(file);
		int size = sodokuFit.getSizeOfSolution();
		for(int i = 0; i < 30; i++)
		{
			solutions[i] =  new RangedIntegerSolution(size, sodokuFit,  1, 9);
			solutions[i].randomizeSolution();
		}
		System.out.println("AVG:" + fitAvg(solutions));
		solutions = GA.runUntilCondition(solutions, crossoverRate,mutationRate, new IterationMaxStoppingCondition(100));
		//solutions = GA.nextGeneration(solutions,crossoverRate,mutationRate);
		double bestfit = 0;
		int bestpos = 0;
		for(int i = 0; i < 30; i++)
		{
			if(solutions[i].getFitness() > bestfit)
			{
				bestfit = solutions[i].getFitness();
				bestpos = i;
			}
		}
		System.out.println("AVG:" + fitAvg(solutions));
		System.out.println("Best:" + bestfit);
		sodokuFit.printBoard(solutions[1]);
		System.out.println();
		sodokuFit.printBoard(solutions[0]);
	}
	 static double fitAvg(Solution[] c)
	{
		double total = 0;
		for(int i = 0; i < c.length; i ++)
		{
			try
			{
				total += c[i].getFitness();
			}
			catch (SolutionEmptyException e)
			{
				e.printStackTrace();
			}
		}
		return total/c.length;
	}
}