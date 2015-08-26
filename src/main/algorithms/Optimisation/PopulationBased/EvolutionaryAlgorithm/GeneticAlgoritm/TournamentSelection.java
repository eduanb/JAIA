package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import java.util.*;

public class TournamentSelection implements SelectionStrategy
{
	Random random;
	int c;
	ChromosomeSorter sorter;
	int size;

	public TournamentSelection(ChromosomeSorter sorter, int size)
	{
		random = new Random();
		this.sorter = sorter;
		this.size = size;
	}

	@Override
	public Chromosome[] select(Chromosome[] chromosomeArray) throws ChromosomeEmptyException
	{
		Chromosome[] tournament = pickNRandom(chromosomeArray, size);
		Chromosome[] result = new Chromosome[2];
		result[0] = tournament[0];
		result[1] = tournament[1];
		return result;
	}

	private Chromosome[] pickNRandom(Chromosome[] array, int n) {

		List<Chromosome> list = new ArrayList<>(array.length);
		for (Chromosome chromosome : array)
			list.add(chromosome);
		Collections.shuffle(list);

		Chromosome[] answer = new Chromosome[n];
		for (int i = 0; i < n; i++)
			answer[i] = list.get(i);
		sorter.sortAscending(answer);

		return answer;

	}

}
