package algorithms.Optimisation.OptimisationProblem;

import algorithms.Optimisation.Solution.Solution;

public abstract interface OptimisationProblem
{
	public double evaluate(Solution solution);

	public double getErrTolerance();

	public int getProblemDimension();

	public double getTargetValue();

	public double getMin();

	public double getMax();

	public String getName();
}
