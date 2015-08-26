package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

public interface TerminationCondition
{
	public boolean TerminationCondition(Chromosome[] population, int iterationCount);
}
