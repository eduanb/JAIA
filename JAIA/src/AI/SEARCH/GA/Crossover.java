package AI.SEARCH.GA;

public interface Crossover
{
	public Solution[] crossover(Solution[] population, double crossoverRate) throws SolutionTypeException;
}
