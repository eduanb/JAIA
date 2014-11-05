package AI.SEARCH.GA;

public class Main
{

	public static void main(String[] args) throws ChromosomeEmptyException, ChromosomeTypeException
	{
		// Create GA
		SelectionStrategy selectionStrategy = new FitnessProportianalSelection(new JavaChromosoneSorter());
		Crossover crossover = new RandomOnePointCrossover(selectionStrategy);
		Mutate mutate = new RandomMutate();
		GeniticAlgorithm GA = new GeniticAlgorithm(crossover, mutate);

		// Create chromosomes
		Chromosome[] chromosomes = new Chromosome[10];
		for(int i = 0; i < 10; i++)
		{
			chromosomes[i] =  new BinaryChromosome(10, new zeroFit());
			chromosomes[i].randomizeChromosome();
		}
		System.out.println(chromosomes[0]);
		chromosomes = GA.runUntilCondition(chromosomes, 0.5, 0, new GenerationMaxTerminateCondition(1000));
		System.out.println(chromosomes[0]);
	}
}

class zeroFit implements FitnessFunction
{
	@Override
	public double calculateFitness(Chromosome chromosone)
	{
		int result = 0;
		BinaryChromosome b = (BinaryChromosome) chromosone;
		for (int i = 0; i < 10; i++)
		{
			if (b.genes[i] == false)
				result++;
		}
		return result;
	}
}