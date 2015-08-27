package algorithms.Optimisation.Solution;

import algorithms.Optimisation.FitnessFunction;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by eduan on 2015/08/26.
 */
public class SolutionList<T> {

    private Solution<T>[] solutions;
    SolutionAscendingComparator AscComparator;
    SolutionDescendingComparator DescComparator;

    public SolutionList(Solution<T>[] solutions)
    {
        this.solutions = solutions;
        AscComparator = new SolutionAscendingComparator();
        DescComparator = new SolutionDescendingComparator();
    }

    public SolutionList(Class<T> clazz, int size, FitnessFunction fitnessFunction)
    {
        solutions = new Solution[size];
        for(int i = 0; i < size; i++)
        {
            solutions[0] = new Solution<T>(clazz, fitnessFunction, size);
        }
        AscComparator = new SolutionAscendingComparator();
        DescComparator = new SolutionDescendingComparator();
    }

    public Solution<T>[] getSolutions() {
        return solutions;
    }


    public void sortAscending()
    {
        Arrays.sort(solutions, AscComparator);
    }

    public void sortDescending()
    {
        Arrays.sort(solutions, DescComparator);
    }

    class SolutionAscendingComparator implements Comparator<Solution>
    {
        @Override
        public int compare(Solution c1, Solution c2)
        {
            try
            {
                return Double.compare(c1.getFitness(), c2.getFitness());
            }
            catch (SolutionEmptyException e)
            {
                e.printStackTrace();
                return 0;
            }
        }
    }

    class SolutionDescendingComparator implements Comparator<Solution>
    {
        @Override
        public int compare(Solution c1, Solution c2)
        {
            try
            {
                return -1 * Double.compare(c1.getFitness(), c2.getFitness());
            }
            catch (SolutionEmptyException e)
            {
                e.printStackTrace();
                return 0;
            }
        }
    }
}
