package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

public class GenerationMaxTerminateCondition implements TerminationCondition
{
	private int maximum;

	public GenerationMaxTerminateCondition(int maximum)
	{
		this.maximum = maximum;
	}

	@Override
	public boolean TerminationCondition(Chromosome[] population, int iterationCount)
	{
		return iterationCount >= maximum;
	}

}