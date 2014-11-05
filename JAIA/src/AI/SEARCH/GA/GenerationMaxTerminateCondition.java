package AI.SEARCH.GA;

public class GenerationMaxTerminateCondition implements TerminationCondition
{
	private int maximum;
	GenerationMaxTerminateCondition(int maximum)
	{
		this.maximum = maximum;
	}
	@Override
	public boolean TerminationCondition(Chromosome[] population, int iterationCount)
	{
		return iterationCount >= maximum;
	}

}
