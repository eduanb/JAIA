package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.Solution;

public interface Mutate
{
	public Solution[] mutate(Solution[] population, double mutationRate);
}
