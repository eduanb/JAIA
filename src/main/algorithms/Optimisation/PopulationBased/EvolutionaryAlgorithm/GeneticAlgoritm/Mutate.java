package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.SolutionException;
import algorithms.Optimisation.Solution.SolutionList;

public interface Mutate
{
    public SolutionList mutate(SolutionList population) throws SolutionException;
}
