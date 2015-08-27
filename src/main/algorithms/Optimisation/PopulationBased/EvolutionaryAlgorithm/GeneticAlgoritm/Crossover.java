package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.SolutionEmptyException;
import algorithms.Optimisation.Solution.SolutionTypeException;

public interface Crossover
{
	public Solution[] crossover(Solution[] population, double crossoverRate) throws SolutionTypeException, SolutionEmptyException;
}
