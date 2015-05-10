package EXAMPLE.SODUKU;

import AI.SEARCH.GA.Chromosome;
import AI.SEARCH.GA.FitnessFunction;
import UTILS.FileIO;

import java.io.File;

/**
 * Created by eduan on 2015/05/06.
 */
public class SodokuFit implements FitnessFunction
{
    int[][] board;
    public SodokuFit(File sodokuFile)
    {
        board = FileIO.Read2DArrayAsInt(sodokuFile,";");
    }
    @Override
    public double calculateFitness(Chromosome chromosone) {
        return 0;
    }
}
