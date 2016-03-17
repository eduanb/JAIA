package algorithms.Optimisation.StoppingCondition;

import algorithms.Optimisation.Solution.SolutionList;

public class IterationMaxStoppingCondition implements StoppingCondition
{
    private int maximum;

    public IterationMaxStoppingCondition(int maximum)
    {
        this.maximum = maximum;
    }

    @Override
    public boolean TerminationCondition(SolutionList population, int iterationCount)
    {
        return iterationCount >= maximum;
    }

}
