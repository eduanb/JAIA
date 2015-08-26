package pso;

// this is the heart of the PSO program
// the code is for 2-dimensional space problem
// but you can easily modify it to solve higher dimensional space problem

import benchmark.ProblemSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SigmaPSO implements PSO,PSOConstants
{
	Random generator;

	public SigmaPSO()
	{
		generator = new Random();
	}

	@Override
	public void execute(ProblemSet problemSet)
	{
		List<Particle> swarm;
		double[] pBest = new double[SWARM_SIZE];
		List<Location> pBestLocation = new LinkedList<>();
		double gBest = 0;
		Location gBestLocation = null;
		double[] fitnessValueList = new double[SWARM_SIZE];

		swarm = problemSet.initializeSwarm();
		updateFitnessList(fitnessValueList,swarm);

		for (int i = 0; i < SWARM_SIZE; i++)
		{
			pBest[i] = fitnessValueList[i];
			pBestLocation.add(swarm.get(i).getLocation());
		}

		int t = 0;

		while (t < MAX_ITERATION)
		{
            updateSearchSpace();
            if(detectChange(swarm))
            {
                for (int i = 0; i < SWARM_SIZE/3; i++)
                {
                    problemSet.resetParticle(swarm.get(i));
                }
            }
			// step 1 - update pBest
			for (int i = 0; i < SWARM_SIZE/3; i++)
			{
				if (fitnessValueList[i] < pBest[i])
				{
					pBest[i] = fitnessValueList[i];
					pBestLocation.set(i, swarm.get(i).getLocation());
				}
			}

			// step 2 - update gBest
			int bestParticleIndex = PSOUtility.getMinPos(fitnessValueList,SWARM_SIZE/3);
			if (t == 0 || fitnessValueList[bestParticleIndex] < gBest)
			{
				gBest = fitnessValueList[bestParticleIndex];
				gBestLocation = swarm.get(bestParticleIndex).getLocation();
			}

			for (int i = 0; i < SWARM_SIZE/3; i++)
			{

				double r1 = generator.nextDouble();
				double r2 = generator.nextDouble();

				Particle p = swarm.get(i);

				// step 3 - update velocity
				double[] newVel = new double[problemSet.getProblemDimention()];
				for (int j = 0; j < problemSet.getProblemDimention(); j++)
				{
					newVel[j] = (p.getVelocity().getPos()[j]) + (r1 * C1)
										* (pBestLocation.get(i).getLoc()[j] - p.getLocation().getLoc()[j]) + (r2 * C2)
										* (gBestLocation.getLoc()[j] - p.getLocation().getLoc()[j]);
				}
				Velocity vel = new Velocity(newVel);
				p.setVelocity(vel);

				// step 4 - update location
				double[] newLoc = new double[problemSet.getProblemDimention()];

				for (int j = 0; j < problemSet.getProblemDimention(); j++)
				{
					newLoc[j] = p.getLocation().getLoc()[j] + newVel[j];
				}
				Location loc = new Location(newLoc);
				p.setLocation(loc);
			}

			t++;
            sigmaUpdate(swarm, problemSet,pBestLocation);
            int first = 2 * (swarm.size()/3);
            for (int i = 0; first < SWARM_SIZE; i++)
            {

                double r1 = generator.nextDouble();
                double r2 = generator.nextDouble();
                double r3 = generator.nextDouble();
                r3 = r3 * (1000 * Math.exp(-t/10));
                Particle p = swarm.get(i);

                // step 3 - update velocity
                double[] newVel = new double[problemSet.getProblemDimention()];
                for (int j = 0; j < problemSet.getProblemDimention(); j++)
                {
                    newVel[j] = (p.getVelocity().getPos()[j]) + (r1 * C1)
                            * (pBestLocation.get(i).getLoc()[j] - p.getLocation().getLoc()[j]) + (r2 * C2)
                            * (gBestLocation.getLoc()[j] - p.getLocation().getLoc()[j]);
                }
                Velocity vel = new Velocity(newVel);
                p.setVelocity(vel);

                // step 4 - update location
                double[] newLoc = new double[problemSet.getProblemDimention()];

                for (int j = 0; j < problemSet.getProblemDimention(); j++)
                {
                    newLoc[j] = r3 * p.getLocation().getLoc()[j] + newVel[j];
                }
                Location loc = new Location(newLoc);
                p.setLocation(loc);
            }
			updateFitnessList(fitnessValueList,swarm);
		}

		System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
		for (int j = 0; j < problemSet.getProblemDimention(); j++)
		{
			System.out.println("     Best X" + (j + 1) + ": " + gBestLocation.getLoc()[j]);
		}
	}

    private void sigmaUpdate(List<Particle> swarm, ProblemSet problemSet,List<Location> pBestLocation)
    {
        int first = swarm.size()/3;
        int last = first * 2;
        Location gBestLocation = swarm.get(findSigmaGlobalBest(swarm,problemSet)).getLocation();
        for (int i = first; i < last; i++)
        {

            double r1 = generator.nextDouble();
            double r2 = generator.nextDouble();

            Particle p = swarm.get(i);

            // step 3 - update velocity
            double[] newVel = new double[problemSet.getProblemDimention()];
            for (int j = 0; j < problemSet.getProblemDimention(); j++)
            {
                newVel[j] = (p.getVelocity().getPos()[j]) + (r1 * C1)
                        * (pBestLocation.get(i).getLoc()[j] - p.getLocation().getLoc()[j]) + (r2 * C2)
                        * (gBestLocation.getLoc()[j] - p.getLocation().getLoc()[j]);
            }
            Velocity vel = new Velocity(newVel);
            p.setVelocity(vel);

            // step 4 - update location
            double[] newLoc = new double[problemSet.getProblemDimention()];

            for (int j = 0; j < problemSet.getProblemDimention(); j++)
            {
                newLoc[j] = p.getLocation().getLoc()[j] + newVel[j];
            }
            Location loc = new Location(newLoc);
            p.setLocation(loc);
        }
    }

    private int findSigmaGlobalBest(List<Particle> swarm, ProblemSet problemSet)
    {
        int first = swarm.size()/3;
        int last = first * 2;
        double[] sigmaj = new double[first];
        double[][] particleSigma = new double[first][];
        for(int i = 0; i < first; i++)
        {
            sigmaj[i] = problemSet.getTargetValue();
        }
        sigmaj = sigma(sigmaj);
        double dist = Double.MAX_VALUE;
        int index = 0;
        for(int i = first; i < last; i++)
        {
            particleSigma[i] = sigma(swarm.get(i).getLocation().getLoc());
            double tempDist = distance(sigmaj,particleSigma[i]);
            if(tempDist < dist)
            {
                dist = tempDist;
                index = i;
            }

        }
        return index;

    }

    private double distance(double[] a, double[] b)
    {
        double result = 0;
        for(int i = 0; i < a.length; i++)
        {
            result += (a[i] - b[i]) * (a[i] - b[i]);
        }
        result = Math.sqrt(result);
        return result;
    }
    private double[] sigma(double[] arr)
    {
        double[] result = new double[arr.length];
        int squareSum = 0;
        for(int i = 0; i < arr.length; i++)
        {
            squareSum += arr[i] * arr[i];
        }
        for(int i = 0; i < arr.length; i++)
        {
            int other = i + 1;
            if (other >= arr.length)
                other = 0;
            result[i] = (arr[i] * arr[i]) - (arr[other] * arr[other]);
            result[i] /= squareSum;
        }
        return result;
    }
	private double[] updateFitnessList(double[] fitnessValueList, List<Particle> swarm)
	{
		for (int i = 0; i < SWARM_SIZE; i++)
		{
			fitnessValueList[i] = swarm.get(i).getFitnessValue();
		}
		return fitnessValueList;
	}

    private void updateSearchSpace()
    {
        //todo;
    }

    private boolean detectChange(List<Particle> swarm)
    {
        for(int i = 0 ; i < 5; i++)
        {
            if(swarm.get(i).getOldFitness() != swarm.get(i).getFitnessValue())
                return true;
        }
        return false;
    }

}
