package algorithms.Optimisation.Solution;

import algorithms.Optimisation.OptimisationProblem.OptimisationProblem;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by eduan on 2015/08/26.
 */
public class SolutionList {

    private Solution[] solutions;
    SolutionAscendingComparator AscComparator;
    SolutionDescendingComparator DescComparator;
    int size;
    int count;

    public int getSize() {
        return size;
    }

    public SolutionList(int size)
    {
        this.size = size;
        solutions = new Solution[size];
        count = 0;
    }

    public void updateFitness(OptimisationProblem ff)
    {
        for(Solution solution:solutions)
        {
            solution.calculateFitness(ff);
        }
    }

    public Solution getSolution(int index)
    {
        return solutions[index];
    }

    public void setSolution(int index, Solution solution)
    {
        solutions[index] = solution;
    }

    public SolutionList(Solution[] solutions)
    {
        this.solutions = solutions;
        AscComparator = new SolutionAscendingComparator();
        DescComparator = new SolutionDescendingComparator();
        size = solutions.length;
        count = size;
    }

    public SolutionList(Class clazz, int size, int variableCount)
    {
        this.size = size;
        solutions = new Solution[size];
        for(int i = 0; i < size; i++)
        {
            solutions[0] = new Solution(variableCount);
        }
        this.count = size;
        AscComparator = new SolutionAscendingComparator();
        DescComparator = new SolutionDescendingComparator();
    }

    public void addSolution(Solution solution) throws SolutionListFullException
    {
        if(count == size)
            throw new SolutionListFullException();
        solutions[count++] = solution;
    }

    public Solution[] getSolutions() {
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
            catch (SolutionException e)
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
            catch (SolutionException e)
            {
                e.printStackTrace();
                return 0;
            }
        }
    }
}
