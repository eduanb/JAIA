package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

public interface Mutate
{
	public Chromosome[] mutate(Chromosome[] population, double mutationRate);
}
