package Optimisation;

import algorithms.Optimisation.Solution.Solution;
import algorithms.Optimisation.Solution.IntegerSolution;
import utils.FileIO;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by eduan on 2015/05/06.
 */
public class SodokuFit implements FitnessFunction
{
    int[][] board;
    public SodokuFit(File sodokuFile)
    {
        board = FileIO.Read2DArrayAsInt(sodokuFile, ";");
    }

    public double calculateFitness(IntegerSolution chromosone) {
        //create temp board
        int c = 0;
        int[][] tempBoard = new int[board.length][board.length];
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                if(board[i][j] == Integer.MIN_VALUE)
                {
                    tempBoard[i][j] = chromosone.getGene(c++);
                }
                else tempBoard[i][j] = board[i][j];
            }
        }
        //calc max fit
        int fit = board.length * board.length;
        //calc row dif
        for(int i = 0; i < board.length; i++)
        {
            fit -= duplicates(tempBoard[i]);
        }

        return fit;
    }

    public void printBoard(Solution solution)
    {
        int[][] tempBoard = new int[board.length][board.length];
        int c = 0;
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                if(board[i][j] == Integer.MIN_VALUE)
                {
                    tempBoard[i][j] = (int) solution.getVariable(c++);
                }
                else tempBoard[i][j] = board[i][j];
            }
        }
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                System.out.print(tempBoard[i][j] + ";");
            }
            System.out.println();
        }
    }
    public int getSizeOfSolution()
    {
        int result = 0;
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                if(board[i][j] == Integer.MIN_VALUE)
                {
                    result++;
                }
            }
        }
        return result;
    }
    private int duplicates(final int[] arr)
    {
        int result = 0;
        Set<Integer> lump = new HashSet<Integer>();
        for (int i : arr)
        {
            if (lump.contains(i)) result++;
            lump.add(i);
        }
        return result;
    }

    @Override
    public double calculateFitness(Solution chromosone) {
        if(chromosone.getClass() != RangedIntegerSolution.class)
            return 0;
        return calculateFitness((IntegerSolution)chromosone);
    }
}
