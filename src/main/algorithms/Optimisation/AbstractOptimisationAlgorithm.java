package algorithms.Optimisation;

import algorithms.Optimisation.OptimisationProblem.OptimisationProblem;
import algorithms.Optimisation.Solution.SolutionException;
import algorithms.Optimisation.Solution.SolutionList;
import algorithms.Optimisation.StoppingCondition.StoppingCondition;

/**
 * Created by Eduan on 2015-08-29.
 */
public abstract class AbstractOptimisationAlgorithm
{

    protected int iterationCount = 0;

    public int getIterationCount()
    {
        return iterationCount;
    }

    public abstract SolutionList runUntilCondition(SolutionList population, OptimisationProblem optimisationProblem, StoppingCondition... conditions) throws SolutionException;

    protected boolean terminateCondition(StoppingCondition[] conditions, SolutionList population, int iterationCount)
    {
        for (StoppingCondition t : conditions) {
            if (t.TerminationCondition(population, iterationCount))
                return true;
        }
        return false;
    }
}
