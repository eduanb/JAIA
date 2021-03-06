package algorithms.Optimisation.Solution;

public class SolutionTypeException extends SolutionException
{
    private static final long serialVersionUID = 1L;
    private static final String ERROR = "Solution type mismatch.";

    SolutionTypeException()
    {
        super(ERROR);
    }

    @Override
    public String toString()
    {
        return ERROR;
    }

}
