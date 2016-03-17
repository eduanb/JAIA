package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.SolutionException;
import algorithms.Optimisation.Solution.SolutionList;

public interface SelectionStrategy
{
    public SolutionList select(SolutionList solutionArray) throws SolutionException;
}
