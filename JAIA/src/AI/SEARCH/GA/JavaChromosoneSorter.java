package AI.SEARCH.GA;

import java.util.Arrays;
import java.util.Comparator;

public class JavaChromosoneSorter implements ChromosomeSorter
{
	ChromosomeAscendingComparator AscComparator;
	ChromosomeDescendingComparator DescComparator;

	JavaChromosoneSorter()
	{
		AscComparator = new ChromosomeAscendingComparator();
		DescComparator = new ChromosomeDescendingComparator();
	}

	@Override
	public Chromosome[] sortAscending(Chromosome[] chromosomes)
	{
		Arrays.sort(chromosomes, AscComparator);
		return chromosomes;
	}

	@Override
	public Chromosome[] sorrDescending(Chromosome[] chromosomes)
	{
		Arrays.sort(chromosomes, DescComparator);
		return chromosomes;
	}

	class ChromosomeAscendingComparator implements Comparator<Chromosome>
	{
		@Override
		public int compare(Chromosome c1, Chromosome c2)
		{
			try
			{
				return Double.compare(c1.getFitness(), c2.getFitness());
			}
			catch (ChromosomeEmptyException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}
	}

	class ChromosomeDescendingComparator implements Comparator<Chromosome>
	{
		@Override
		public int compare(Chromosome c1, Chromosome c2)
		{
			try
			{
				return -1 * Double.compare(c1.getFitness(), c2.getFitness());
			}
			catch (ChromosomeEmptyException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}
	}

}
