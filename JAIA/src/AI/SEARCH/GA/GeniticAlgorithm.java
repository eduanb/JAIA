package AI.SEARCH.GA;

public class GeniticAlgorithm
{
	Crossover crossover;
	Mutate mutate;
	int iterationCount;
	public GeniticAlgorithm(Crossover crossover, Mutate mutate)
	{
		super();
		this.crossover = crossover;
		this.mutate = mutate;
		this.iterationCount = 0;
	}
	
	public Chromosome[] nextGeneration(Chromosome[] population,double crossoverRate, double mutationRate) throws ChromosomeTypeException, ChromosomeEmptyException
	{
		iterationCount++;
		Chromosome[] result;
		result =  crossover.crossover(population, crossoverRate);
		result = mutate.mutate(result, mutationRate);
		//TODO: Elitism + generationGap
		return result;
	}
	
	public Chromosome[] runUntilCondition(Chromosome[] population,double crossoverRate, double mutationRate, TerminationCondition ... conditions) throws ChromosomeTypeException, ChromosomeEmptyException
	{
		Chromosome[] result = population;
		while(!terminateCondition(conditions,population,iterationCount))
		{
			result = nextGeneration(result,crossoverRate,mutationRate);
		}
		return result;
	}

	private boolean terminateCondition(TerminationCondition[] conditions, Chromosome[] population, int iterationCount)
	{
		for(TerminationCondition t : conditions)
		{
			if(t.TerminationCondition(population, iterationCount)) return true;
		}
		return false;
	}
	
}
