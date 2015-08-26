package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

public interface SelectionStrategy
{
	public Chromosome[] select(Chromosome[] chromosomeArray) throws ChromosomeEmptyException;
}
