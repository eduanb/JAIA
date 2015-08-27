package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.Solution;

public interface TerminationCondition
{
	public boolean TerminationCondition(Solution[] population, int iterationCount);
}
