package algorithms.Optimisation.OptimisationProblem.BenchmarkFunctions;

import algorithms.Optimisation.OptimisationProblem.OptimisationProblem;
import algorithms.Optimisation.Solution.Solution;

public class Bohachevsky1Problem implements OptimisationProblem
{
    @Override
    public double evaluate(Solution solution)
    {
        Double X1 = solution.getVariable(0);
        Double X2 = solution.getVariable(1);
        double result = X1 * X1;
        result += 2 * (X2 * X2);
        result += -0.3 * Math.cos(3 * Math.PI * X1);
        result += -0.4 * Math.cos(4 * Math.PI * X2);
        result += 0.7;

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
        return 2;
    }

    @Override
    public double getTargetValue()
    {
        return 0;
    }

    @Override
    public double getMin()
    {
        return -50;
    }

    @Override
    public double getMax()
    {
        return 50;
    }

    @Override
    public String getName()
    {
        return "Bohachevsky1Problem";
    }
}
