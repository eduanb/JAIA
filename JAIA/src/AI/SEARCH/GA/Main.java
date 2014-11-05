package AI.SEARCH.GA;

public class Main
{

	public static void main(String[] args) throws ChromosomeEmptyException, ChromosomeTypeException
	{
		double crossoverRate = 0.2;
		double mutationRate = 0.01;
		// Create GA
		SelectionStrategy selectionStrategy = new FitnessProportianalSelection(new JavaChromosoneSorter());
		Crossover crossover = new RandomOnePointCrossover(selectionStrategy);
		Mutate mutate = new RandomMutate();
		GeniticAlgorithm GA = new GeniticAlgorithm(crossover, mutate);

		// Create chromosomes
		Chromosome[] chromosomes = new Chromosome[10];
		zeroFit zero = new zeroFit(); 
		for(int i = 0; i < 10; i++)
		{
			chromosomes[i] =  new BinaryChromosome(10, zero);
			chromosomes[i].randomizeChromosome();
		}
		boolean[] arr = {true,true,true,true,true,true,true,true,true,true};
		chromosomes[0] = new BinaryChromosome(arr, zero);
		System.out.println(chromosomes[0]);
		System.out.println(fitAvg(chromosomes));
		chromosomes = GA.runUntilCondition(chromosomes, crossoverRate,mutationRate, new GenerationMaxTerminateCondition(100));
		//chromosomes = GA.nextGeneration(chromosomes,crossoverRate,mutationRate);
		System.out.println(fitAvg(chromosomes));
		System.out.println(chromosomes[0]);
	}
	 static double fitAvg(Chromosome[] c)
	{
		double total = 0;
		for(int i = 0; i < c.length; i ++)
		{
			try
			{
				total += c[i].getFitness();
			}
			catch (ChromosomeEmptyException e)
			{
				e.printStackTrace();
			}
		}
		return total/c.length;
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