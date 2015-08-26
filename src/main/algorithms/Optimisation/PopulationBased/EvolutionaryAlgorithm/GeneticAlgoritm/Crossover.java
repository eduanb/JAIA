package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

public interface Crossover
{
	public Chromosome[] crossover(Chromosome[] population, double crossoverRate) throws ChromosomeTypeException, ChromosomeEmptyException;
}
