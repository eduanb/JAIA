package AI.SEARCH.GA;

public class SolutionEmptyException extends Exception
{
	private static final long serialVersionUID = 1L;
	@Override
	public String toString()
	{
		return "Solution is empty.";
	}
}
