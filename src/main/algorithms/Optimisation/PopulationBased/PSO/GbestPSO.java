package algorithms.Optimisation.PopulationBased.PSO;

import algorithms.Optimisation.Solution.Particle;
import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.SolutionException;
import algorithms.Optimisation.Solution.SolutionList;

/**
 * Created by Eduan on 2015-08-29.
 */
public class GbestPSO extends AbstractPSO {
    public GbestPSO(double C1, double C2)
    {
        this.C1 = C1;
        this.C2 = C2;
    }

    @Override
    public Particle getGBest(SolutionList swarm) {
        Particle best = (Particle)swarm.getSolution(0);
        try {
            for (Solution solution : swarm.getSolutions()) {
                Particle particle = (Particle) solution;
                if (particle.getFitness() > best.getFitness())
                    best = particle;
            }
        } catch (SolutionException e)
        {
            e.printStackTrace();
        }
        return best;
    }
}
