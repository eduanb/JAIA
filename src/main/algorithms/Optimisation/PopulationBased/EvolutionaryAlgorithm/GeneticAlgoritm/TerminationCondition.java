package AI.SEARCH.GA;

public interface TerminationCondition
{
	public boolean TerminationCondition(Chromosome[] population, int iterationCount);
}
