package Optimisation.BenchmarkFunctions;

import algorithms.Optimisation.FitnessFunction;
import algorithms.Optimisation.Solution.Solution;

public class SphericalProblem implements FitnessFunction
{
	private int problemDimention;
	
	public SphericalProblem(int dimention)
	{
		this.problemDimention = dimention;
	}
	
	@Override
	public double evaluate(Solution solution)
	{
		double result = 0;
		for(int i = 0; i < problemDimention; i++)
		{
			result += (Double)solution.getVariable(i) * (Double)solution.getVariable(i);
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
		return problemDimention;
	}

	@Override
	public double getTargetValue()
	{
		return 0;
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
		return "Spherical";
	}
}
