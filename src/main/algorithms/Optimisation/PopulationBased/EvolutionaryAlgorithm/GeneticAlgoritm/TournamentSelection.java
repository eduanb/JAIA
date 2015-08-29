package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.SolutionException;
import algorithms.Optimisation.Solution.SolutionList;

import java.util.*;

public class TournamentSelection implements SelectionStrategy
{
	Random random;
	int c;
	int size;

	public TournamentSelection(int size)
	{
		random = new Random();
		this.size = size;
	}

	@Override
	public SolutionList select(SolutionList solutionArray) throws SolutionException
	{
		SolutionList tournament = pickNRandom(solutionArray, size);
		tournament.sortAscending();
		SolutionList result = new SolutionList(2);
		result.setSolution(0,solutionArray.getSolution(0));
		result.setSolution(1, solutionArray.getSolution(1));
		return result;
	}

	private SolutionList pickNRandom(SolutionList array, int n) {

		List<Solution> list = new ArrayList<>(array.getSize());
		for (Solution solution : array.getSolutions())
			list.add(solution);
		Collections.shuffle(list);

		SolutionList result = new SolutionList(n);
		for (int i = 0; i < n; i++)
			result.setSolution(i,list.get(i));

		return result;
	}

}
