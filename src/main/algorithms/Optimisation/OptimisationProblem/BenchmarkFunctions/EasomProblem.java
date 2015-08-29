package algorithms.Optimisation.OptimisationProblem.BenchmarkFunctions;

import algorithms.Optimisation.OptimisationProblem.OptimisationProblem;
import algorithms.Optimisation.Solution.Solution;

public class EasomProblem implements OptimisationProblem
{
	@Override
	public double evaluate(Solution solution)
	{
		Double X1 = (Double) solution.getVariable(0);
		Double X2 = (Double) solution.getVariable(1);
		return (-Math.cos(X1) * Math.cos(X1) * Math.pow(Math.E, (-Math.pow(X1 - Math.PI, 2) - Math.pow(X2 - Math.PI, 2))));
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
		return -1;
	}

	@Override
	public double getMax()
	{
		return 100;
	}

	@Override
	public double getMin()
	{
		return -100;
	}
	
	@Override
	public String getName()
	{
		return "EasomProblem";
	}
}
