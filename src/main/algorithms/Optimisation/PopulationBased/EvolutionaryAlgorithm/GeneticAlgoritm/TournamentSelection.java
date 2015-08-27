package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.SolutionEmptyException;
import algorithms.Optimisation.Solution.SolutionSorter;

import java.util.*;

public class TournamentSelection implements SelectionStrategy
{
	Random random;
	int c;
	SolutionSorter sorter;
	int size;

	public TournamentSelection(SolutionSorter sorter, int size)
	{
		random = new Random();
		this.sorter = sorter;
		this.size = size;
	}

	@Override
	public Solution[] select(Solution[] solutionArray) throws SolutionEmptyException
	{
		Solution[] tournament = pickNRandom(solutionArray, size);
		Solution[] result = new Solution[2];
		result[0] = tournament[0];
		result[1] = tournament[1];
		return result;
	}

	private Solution[] pickNRandom(Solution[] array, int n) {

		List<Solution> list = new ArrayList<>(array.length);
		for (Solution solution : array)
			list.add(solution);
		Collections.shuffle(list);

		Solution[] answer = new Solution[n];
		for (int i = 0; i < n; i++)
			answer[i] = list.get(i);
		sorter.sortAscending(answer);

		return answer;

	}

}
