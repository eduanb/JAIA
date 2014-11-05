package AI.SEARCH.GA;

import java.util.Date;
import java.util.Random;

public class RandomSelection implements SelectionStrategy
{
	int c = 0;

	@Override
	public Chromosome[] select(Chromosome[] solutionArray)
	{
		Chromosome[] parents = new Chromosome[2];
		int parent1 = -1;
		int parent2 = -1;
		// c makes sure random doesn't always return the same 2 random parents
		c++;
		Random random = new Random();
		random.setSeed(new Date().getTime() + c);

		// get first parent
		parent1 = random.nextInt(solutionArray.length);
		
		//get second parent and make sure that it is not the same as the first
		do
		{
			parent2 = random.nextInt(solutionArray.length);
		} while (parent1 == parent2);

		parents[0] = solutionArray[parent1];
		parents[1] = solutionArray[parent2];

		return parents;
	}

}
