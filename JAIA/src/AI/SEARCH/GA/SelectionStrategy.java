package AI.SEARCH.GA;

public interface SelectionStrategy
{
	public Chromosome[] select(Chromosome[] chromosomeArray) throws ChromosomeEmptyException;
}
