package algorithms.Optimisation.Solution;

/**
 * Created by Eduan on 2015-08-29.
 */
public class SolutionFitnessNotSetException extends SolutionException
{
    private static final long serialVersionUID = 1L;
    private static final String ERROR = "Solution fitness has not been set yet.";

    SolutionFitnessNotSetException()
    {
        super(ERROR);
    }

    @Override
    public String toString()
    {
        return ERROR;
    }
}
