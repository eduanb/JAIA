package AI.SEARCH.GA;

public interface Chromosome
{
	public void swop(Chromosome other, int point) throws SolutionTypeException;

	public int getNumberOfGenes();

	public void randomMutateGene(int geneNumber);

	public void randomizeChromosome() throws SolutionEmptyException;
}
