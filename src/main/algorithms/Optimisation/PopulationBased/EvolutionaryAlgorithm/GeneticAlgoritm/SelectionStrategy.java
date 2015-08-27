package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.SolutionEmptyException;

public interface SelectionStrategy
{
	public Solution[] select(Solution[] solutionArray) throws SolutionEmptyException;
}
