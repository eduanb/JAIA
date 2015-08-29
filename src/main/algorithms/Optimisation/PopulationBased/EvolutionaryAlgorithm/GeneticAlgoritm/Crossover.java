package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.*;

public interface Crossover
{
	public SolutionList crossover(SolutionList population) throws SolutionException;
}
