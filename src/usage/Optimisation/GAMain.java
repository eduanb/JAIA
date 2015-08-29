package Optimisation;

import algorithms.Optimisation.OptimisationProblem.BenchmarkFunctions.SphericalProblem;
import algorithms.Optimisation.OptimisationProblem.OptimisationProblem;
import algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm.*;
import algorithms.Optimisation.Solution.SolutionException;
import algorithms.Optimisation.Solution.SolutionList;
import algorithms.Optimisation.StoppingCondition.IterationMaxStoppingCondition;
import algorithms.Optimisation.StoppingCondition.StoppingCondition;

/**
 * Created by Eduan on 2015-08-29.
 */
public class GAMain {
    public static void main(String[] args) throws SolutionException {
        OptimisationProblem problem = new SphericalProblem(10);
        SelectionStrategy selectionStrategy = new TournamentSelection(5);
        Crossover crossover = new RandomOnePointCrossover(selectionStrategy,0.8);
        Mutate mutate = new RandomMutate(0.02,problem.getMin(),problem.getMax());
        GeneticAlgorithm ga = new GeneticAlgorithm(crossover,mutate);
        StoppingCondition stoppingCondition = new IterationMaxStoppingCondition(1000);
        SolutionList solutionList = new SolutionList(30,problem.getProblemDimension());
        solutionList.initialiseList(problem.getMin(),problem.getMax());
        SolutionList result = ga.runUntilCondition(solutionList,problem,stoppingCondition);
        System.out.println(result);
    }
}
