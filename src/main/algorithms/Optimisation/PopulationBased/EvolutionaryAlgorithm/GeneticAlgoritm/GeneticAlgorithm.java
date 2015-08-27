package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.SolutionEmptyException;
import algorithms.Optimisation.Solution.SolutionTypeException;

public class GeneticAlgorithm
{
	Crossover crossover;
	Mutate mutate;
	int iterationCount;
	public GeneticAlgorithm(Crossover crossover, Mutate mutate)
	{
		super();
		this.crossover = crossover;
		this.mutate = mutate;
		this.iterationCount = 0;
	}
	
	public Solution[] runUntilCondition(Solution[] population,double crossoverRate, double mutationRate, TerminationCondition ... conditions) throws SolutionTypeException, SolutionEmptyException
	{
		Solution[] result = population;
		while(!terminateCondition(conditions,result,iterationCount))
		{
			iterationCount++;
			result =  crossover.crossover(result, crossoverRate);
			result = mutate.mutate(result, mutationRate);
		}
		return result;
	}

	private boolean terminateCondition(TerminationCondition[] conditions, Solution[] population, int iterationCount)
	{
		for(TerminationCondition t : conditions)
		{
			if(t.TerminationCondition(population, iterationCount)) return true;
		}
		return false;
	}
	
}
