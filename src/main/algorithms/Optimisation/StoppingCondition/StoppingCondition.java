package algorithms.Optimisation.StoppingCondition;

import algorithms.Optimisation.Solution.SolutionList;

public interface StoppingCondition
{
	public boolean TerminationCondition(SolutionList population, int iterationCount);
}
