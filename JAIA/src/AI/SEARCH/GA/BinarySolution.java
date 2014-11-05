package AI.SEARCH.GA;

import java.util.Date;
import java.util.Random;

public class BinarySolution implements Solution
{
	Boolean[] solution;
	int c = 0;
	
	public BinarySolution(Boolean[] solution)
	{
		this.solution = solution;
	}

	
	public BinarySolution(int size)
	{
		solution = new Boolean[size];
	}
	
	/**
	 * Randomizes the solution to any values
	 */
	public void randomize() throws SolutionEmptyException
	{
		if(solution == null || solution.length == 0)
			throw new SolutionEmptyException();
		c++;
		Random random = new Random();
		random.setSeed(new Date().getTime() + c);
		for(int i = 0; i < solution.length; i++)
		{ 
			solution[i] = random.nextBoolean();
		}
		
	}
	
	@Override
	public String toString()
	{
		if(solution == null || solution.length == 0)
			return "{}";
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < solution.length; i++)
		{
			result.append(solution[i] + ", ");
		}
		return result.toString();
	}


	@Override
	public void swop(Solution other, int point) throws SolutionTypeException
	{
		BinarySolution otherBinary;
		if(other instanceof BinarySolution)
		{
			otherBinary = (BinarySolution)other;
		}
		else
		{
			throw new SolutionTypeException("Binary Solution Expected!");
		}
		for(int i = 0; i < point; i++)
		{
			boolean temp = this.solution[i];
			this.solution[i] = otherBinary.solution[i];
			otherBinary.solution[i] = temp;
		}
		
	}

}
