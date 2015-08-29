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
	public void updatePosition(Particle particle, Particle gbest)
	{

	}
	public void updateVelocity(Particle particle)
	{
//		double[] newVel = new double[problemDimension];
//		for (int j = 0; j < problemSet.getProblemDimention(); j++) {
//			newVel[j] = (w * particle.getVelocity().getPos()[j]) + (r1 * C1) * (pBestLocation.get(i).getLoc()[j] - particle.getLocation().getLoc()[j]) + (r2 * C2) * (gBestLocation.getLoc()[j] - particle.getLocation().getLoc()[j]);
//		}
	}

	protected double C1,C2;
	protected double getMomentum(int iteration)
	{
		return 1.0;
		//W_UPPERBOUND - (((double) t) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);
	}

	@Override
	public SolutionList runUntilCondition(SolutionList swarm, OptimisationProblem optimisationProblem, StoppingCondition... conditions) throws SolutionException
	{
		while(!terminateCondition(conditions,swarm,iterationCount++))
		{
			Particle gbest = getGBest(swarm);
			double w = getMomentum(iterationCount);

			for (Solution solution : swarm.getSolutions())
			{
				Particle particle = (Particle)solution;
				updateVelocity(particle);
				updatePosition(particle, gbest);
			}
			swarm.updateFitness(optimisationProblem);
		}
		return swarm;
	}

}
