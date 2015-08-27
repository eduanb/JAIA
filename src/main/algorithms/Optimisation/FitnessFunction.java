package algorithms.Optimisation;

import algorithms.Optimisation.Solution.Solution;

public abstract interface FitnessFunction
{
	public double evaluate(Solution solution);

	public double getErrTolerance();

	public int getProblemDimension();

	public double getTargetValue();

	public double getMin();

	public double getMax();

	public String getName();
}
