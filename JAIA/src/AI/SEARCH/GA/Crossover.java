package AI.SEARCH.GA;

public interface Crossover
{
	public Chromosome[] crossover(Chromosome[] population, double crossoverRate) throws ChromosomeTypeException, ChromosomeEmptyException;
}
