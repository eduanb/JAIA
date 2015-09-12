package usage.Optimisation;

import algorithms.Optimisation.OptimisationProblem.BenchmarkFunctions.SphericalProblem;
import algorithms.Optimisation.OptimisationProblem.OptimisationProblem;
import algorithms.Optimisation.PopulationBased.PSO.GbestPSO;
import algorithms.Optimisation.Solution.ParticleList;
import algorithms.Optimisation.Solution.SolutionException;
import algorithms.Optimisation.Solution.SolutionList;
import algorithms.Optimisation.StoppingCondition.IterationMaxStoppingCondition;
import algorithms.Optimisation.StoppingCondition.StoppingCondition;

/**
 * Created by Eduan on 2015-08-29.
 */
public class PSOMain {
    static final int ITERATION_MAX = 1000;
    public static void main(String[] args) throws SolutionException {
        OptimisationProblem problem = new SphericalProblem(2);
        StoppingCondition stoppingCondition = new IterationMaxStoppingCondition(ITERATION_MAX);
        GbestPSO pso = new GbestPSO(2,2);
        ParticleList particleList = new ParticleList(30,problem.getProblemDimension());
        particleList.initialiseList(problem.getMin(), problem.getMax());
        SolutionList result = pso.runUntilCondition(particleList,problem,stoppingCondition);
        System.out.println(result);
    }
}
