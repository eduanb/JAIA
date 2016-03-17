package algorithms.Optimisation.OptimisationProblem.BenchmarkFunctions;

import algorithms.Optimisation.OptimisationProblem.OptimisationProblem;
import algorithms.Optimisation.Solution.Solution;

public class SphericalProblem implements OptimisationProblem
{
    private int problemDimension;

    public SphericalProblem(int dimension)
    {
        this.problemDimension = dimension;
    }

    @Override
    public double evaluate(Solution solution)
    {
        double result = 0;
        for (int i = 0; i < problemDimension; i++) {
            result += solution.getVariable(i) * solution.getVariable(i);
        }
        return result;
    }

    @Override
    public double getErrTolerance()
    {
        return 1E-20;
    }

    @Override
    public int getProblemDimension()
    {
        return problemDimension;
    }

    @Override
    public double getTargetValue()
    {
        return 0;
    }

    @Override
    public double getMin()
    {
        return -100;
    }

    @Override
    public double getMax()
    {
        return 100;
    }

    @Override
    public String getName()
    {
        return "Spherical";
    }
}
