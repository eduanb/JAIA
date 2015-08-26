package AI.SEARCH.GA;

public interface Chromosome<T>
{
	public void swop(Chromosome other, int point) throws ChromosomeTypeException;

	public int getNumberOfGenes();

	public void randomMutateGene(int geneNumber);

	public void randomizeChromosome() throws ChromosomeEmptyException;
	
	public double getFitness() throws ChromosomeEmptyException;

	public T getGene(int position);
}
