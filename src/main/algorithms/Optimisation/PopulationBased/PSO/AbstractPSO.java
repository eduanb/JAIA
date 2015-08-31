package algorithms.Optimisation.PopulationBased.PSO;


import algorithms.Optimisation.AbstractOptimisationAlgorithm;
import algorithms.Optimisation.OptimisationProblem.OptimisationProblem;
import algorithms.Optimisation.Solution.Particle;
import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.SolutionException;
import algorithms.Optimisation.Solution.SolutionList;
import algorithms.Optimisation.StoppingCondition.StoppingCondition;

import java.util.Random;


public abstract class AbstractPSO extends AbstractOptimisationAlgorithm
{
	Random generator;

	public AbstractPSO()
	{
		generator = new Random();
	}

	public abstract Particle getGBest(SolutionList swarm);

	private void updatePosition(Particle particle)
	{
		for (int j = 0; j < particle.getNumberOfVariables(); j++)
		{
			particle.setVariable(j,particle.getVariable(j) + particle.getVelocity()[j]);
		}
	}

	private void updateVelocity(Particle particle, Particle gbest, double w)
	{
		double[] newVel = new double[particle.getNumberOfVariables()];
		for (int j = 0; j < particle.getNumberOfVariables(); j++) {
			double r1 = generator.nextDouble();
			double r2 = generator.nextDouble();
			newVel[j] = (w * particle.getVelocity()[j] + (r1 * C1) * (particle.getBestPosition()[j] - particle.getVariables()[j]) + (r2 * C2) * (gbest.getBestPosition()[j] - particle.getVariables()[j]));
		}
		particle.setVelocity(newVel);
	}

	protected double C1,C2;
	protected double getMomentum(int iteration)
	{
		return 1.0;
	}

	@Override
	public SolutionList runUntilCondition(SolutionList swarm, OptimisationProblem optimisationProblem, StoppingCondition... conditions) throws SolutionException
	{
		swarm.updateFitness(optimisationProblem);
		while(!terminateCondition(conditions,swarm,iterationCount++))
		{
			Particle gbest = getGBest(swarm);
			double w = getMomentum(iterationCount);

			for (Solution solution : swarm.getSolutions())
			{
				Particle particle = (Particle)solution;
				updateVelocity(particle,gbest,w);
				updatePosition(particle);
			}
			swarm.updateFitness(optimisationProblem);
		}
		return swarm;
	}

}
