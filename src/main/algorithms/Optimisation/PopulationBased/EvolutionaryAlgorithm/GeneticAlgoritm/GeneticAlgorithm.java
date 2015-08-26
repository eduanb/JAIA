package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

public class GeneticAlgorithm
{
	Crossover crossover;
	Mutate mutate;
	int iterationCount;
	public GeneticAlgorithm(Crossover crossover, Mutate mutate)
	{
		super();
		this.crossover = crossover;
		this.mutate = mutate;
		this.iterationCount = 0;
	}
	
	public Chromosome[] runUntilCondition(Chromosome[] population,double crossoverRate, double mutationRate, TerminationCondition ... conditions) throws ChromosomeTypeException, ChromosomeEmptyException
	{
		Chromosome[] result = population;
		while(!terminateCondition(conditions,result,iterationCount))
		{
			iterationCount++;
			result =  crossover.crossover(result, crossoverRate);
			result = mutate.mutate(result, mutationRate);
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
