package AI.SEARCH.GA;

public class GeniticAlgorithm
{
	Crossover crossover;
	Mutate mutate;
	int crossoverRate;
	int mutationRate;
	public GeniticAlgorithm(Crossover crossover, Mutate mutate, int crossoverRate, int mutationRate)
	{
		super();
		this.crossover = crossover;
		this.mutate = mutate;
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
	}
	
	public Chromosome[] nextGeneration(Chromosome[] population) throws ChromosomeTypeException
	{
		Chromosome[] result;
		result =  crossover.crossover(population, crossoverRate);
		result = mutate.mutate(result, mutationRate);
		//TODO: Elitism + generationGap
		return result;
	}
	
	public Chromosome[] runUntilCondition(Chromosome[] population,  TerminationCondition ... conditions) throws ChromosomeTypeException
	{
		Chromosome[] result = population;
		while(!terminateCondition(conditions))
		{
			result = nextGeneration(result);
		}
		return result;
	}

	private boolean terminateCondition(TerminationCondition[] conditions)
	{
		for(TerminationCondition t : conditions)
		{
			if(t.TerminationCondition()) return true;
		}
		return false;
	}
	
}
