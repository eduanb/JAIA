package AI.SEARCH.GA;

public interface Mutate
{
	public Chromosome[] mutate(Chromosome[] population, double mutationRate);
}
