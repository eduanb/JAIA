package algorithms.Optimisation.Solution;

/**
 * Created by Eduan on 2015-08-29.
 */
public class SolutionListFullException extends SolutionException
{
    private static final long serialVersionUID = 1L;
    private static final String ERROR = "Solution list is full.";

    SolutionListFullException()
    {
        super(ERROR);
    }

    @Override
    public String toString()
    {
        return ERROR;
    }
}
