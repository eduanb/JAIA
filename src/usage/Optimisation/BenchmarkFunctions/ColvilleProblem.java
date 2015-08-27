package Optimisation.BenchmarkFunctions;

import algorithms.Optimisation.FitnessFunction;
import algorithms.Optimisation.Solution.Solution;

public class ColvilleProblem implements FitnessFunction
{

	@Override
	public double evaluate(Solution solution)
	{
		double X1 = (Double) solution.getVariable(0);
		double X2 = (Double) solution.getVariable(1);
		double X3 = (Double) solution.getVariable(2);
		double X4 = (Double) solution.getVariable(3);
		double result = 100*Math.pow(X2 - Math.pow(X1,2), 2);
		result += Math.pow(1 - X1, 2);
		result += 90 * Math.pow(X4 - Math.pow(X3, 2), 2);
		result += Math.pow(1 - X3, 2);
		result += 10.1 * ( Math.pow(X2 - 1, 2) + Math.pow(X4 - 1, 2));
		result += 19.8 * (X2 - 1) * (X4 - 1);
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
		return 4;
	}

	@Override
	public double getTargetValue()
	{
		return 0;
	}

	@Override
	public double getMax()
	{
		return 10;
	}

	@Override
	public double getMin()
	{
		return -10;
	}

	@Override
	public String getName()
	{

		return "ColvilleProblem";
	}
}
