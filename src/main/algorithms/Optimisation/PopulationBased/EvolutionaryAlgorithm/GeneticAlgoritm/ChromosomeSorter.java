package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

public interface ChromosomeSorter
{
	Chromosome[] sortAscending(Chromosome[] chromosomes);
	Chromosome[] sortDescending(Chromosome[] chromosomes);
}
