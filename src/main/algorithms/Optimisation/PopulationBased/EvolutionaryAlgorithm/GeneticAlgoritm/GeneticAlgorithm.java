package algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.GeneticAlgoritm;

import algorithms.Optimisation.OptimisationProblem.OptimisationProblem;
import algorithms.Optimisation.PopulationBased.EvolutionaryAlgorithm.AbstractEvolutionaryAlgorithm;
import algorithms.Optimisation.Solution.*;
import algorithms.Optimisation.StoppingCondition.StoppingCondition;

public class GeneticAlgorithm extends AbstractEvolutionaryAlgorithm
{
	Crossover crossover;
	Mutate mutate;
	public GeneticAlgorithm(Crossover crossover, Mutate mutate)
	{
		super();
		this.crossover = crossover;
		this.mutate = mutate;
	}

	@Override
	public SolutionList runUntilCondition(SolutionList population, OptimisationProblem optimisationProblem, StoppingCondition... conditions) throws SolutionException
	{
		SolutionList result = population;
		while(!terminateCondition(conditions,result,iterationCount++))
		{
			result.updateFitness(optimisationProblem);
			result =  crossover.crossover(result);
			result = mutate.mutate(result);
		}
		result.updateFitness(optimisationProblem);
		return result;
	}
}
