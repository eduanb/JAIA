package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.Solution.Solution;

public class GenerationMaxTerminateCondition implements TerminationCondition
{
	private int maximum;

	public GenerationMaxTerminateCondition(int maximum)
	{
		this.maximum = maximum;
	}

	@Override
	public boolean TerminationCondition(Solution[] population, int iterationCount)
	{
		return iterationCount >= maximum;
	}

}
