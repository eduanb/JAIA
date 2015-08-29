package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.SolutionException;
import algorithms.Optimisation.Solution.SolutionList;

import java.util.Date;
import java.util.Random;

public class RandomSelection implements SelectionStrategy
{
	int c = 0;

	@Override
	public SolutionList select(SolutionList solutionArray) throws SolutionException
	{
		SolutionList parents = new SolutionList(2);
		int parent1 = -1;
		int parent2 = -1;
		// c makes sure random doesn't always return the same 2 random parents
		c++;
		Random random = new Random();
		random.setSeed(new Date().getTime() + c);

		// get first parent
		parent1 = random.nextInt(solutionArray.getSize());
		
		//get second parent and make sure that it is not the same as the first
		do
		{
			parent2 = random.nextInt(solutionArray.getSize());
		} while (parent1 == parent2);

		parents.setSolution(0,solutionArray.getSolution(parent1));
		parents.setSolution(1,solutionArray.getSolution(parent2));

		return parents;
	}

}
