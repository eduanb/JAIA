package algorithms.Optimisation.Solution;

import algorithms.Optimisation.OptimisationProblem.OptimisationProblem;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by eduan on 2015/08/26.
 */
public class ParticleList extends SolutionList {

    public ParticleList(int size, int variableCount)
    {
        this.size = size;
        solutions = new Solution[size];
        for(int i = 0; i < size; i++)
        {
            solutions[i] = new Particle(variableCount);
        }
        this.count = size;
    }
}
