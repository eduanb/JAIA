package algorithms.Optimisation.Solution;

public class SolutionEmptyException extends SolutionException
{
	private static final long serialVersionUID = 1L;
	private static final String ERROR = "Solution is empty.";
	@Override
	public String toString()
	{
		return ERROR;
	}

	SolutionEmptyException()
	{
		super(ERROR);
	}
}
