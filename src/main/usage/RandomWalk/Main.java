package usage.RandomWalk;


import algorithms.NeuralNetwork.FeedForward.*;
import algorithms.RandomWalk.ManhattanRandomWalk;
import utils.FileIO;
import utils.HelperFunctions;

import java.util.LinkedList;

/**
 * Created by Eduan on 2015-07-30.
 */
public class Main {
    static final int ITERATIONS = 10000;
    static final int FDC_COUNT = 10000;
    static final int RETRY = 30;
    static final double STEP_SIZE = 0.04;
    static final double SEARCH_SPACE_BOUNDARY = 2;
    static final String OUTPUT_DIR = "C:\\Users\\Eduan\\Google Drive\\Universiteit\\2015\\COS 700 - Research Methods and Project\\Results\\G(avg) + G(dev)\\iris\\";
    static StringBuilder stringBuilder;

    private static void doManhattan(int itereation, NeuralNetwork NN, PatternFile patternFile, ManhattanRandomWalk manhattanRandomWalk)
    {
        manhattanRandomWalk.InitialiseNetwork(NN);

        double totalMeanSquare = 0.0;
        double totalStep = 0.0;
        double avgMSE = 0.0;
        double avgStep = 0.0;
        double stdevMSE = 0.0;
        double stdevStep = 0.0;
        double step = NN.getTrainingError(patternFile.getInputs(), patternFile.getOutputs());
        double mean = NN.getMeanSquareError(patternFile.getInputs(), patternFile.getOutputs());
        double[] gradientsStep = new double[ITERATIONS];
        double[] gradientsMSE = new double[ITERATIONS];
        for(int i = 0; i < ITERATIONS; i++) {
            double tempStep = NN.getTrainingError(patternFile.getInputs(), patternFile.getOutputs());
            double tempMean = NN.getMeanSquareError(patternFile.getInputs(), patternFile.getOutputs());
            gradientsStep[i] += Math.abs((step - tempStep) / STEP_SIZE);
            gradientsMSE[i] += Math.abs((mean - tempMean) / STEP_SIZE);
            totalStep += gradientsStep[i];
            totalMeanSquare += gradientsMSE[i];
            step = tempStep;
            mean = tempMean;
            NN = manhattanRandomWalk.AbsoluteStep(NN, STEP_SIZE);
        }
        avgMSE = totalMeanSquare / ITERATIONS;
        avgStep = totalStep / ITERATIONS;

        for(int i = 0; i < ITERATIONS; i++) {
            stdevMSE += Math.pow(avgMSE - gradientsMSE[i], 2);
            stdevStep += Math.pow(avgStep - gradientsStep[i], 2);
        }

        stdevMSE /= ITERATIONS;
        stdevStep /= ITERATIONS;

        stdevMSE = Math.sqrt(stdevMSE);
        stdevStep = Math.sqrt(stdevStep);

        stringBuilder.append("\n" + itereation + "," + avgMSE + "," + stdevMSE + "," + avgStep + "," + stdevStep);
    }

    static void doFDC(PatternFile patternFile,NeuralNetwork[] NNs)
    {
        double[] fitnessError = new double[FDC_COUNT];
        double[] fitnessMSE = new double[FDC_COUNT];
        double bestError = Double.MAX_VALUE;
        double bestMSE = Double.MAX_VALUE;
        NeuralNetwork bestNNError = null;
        NeuralNetwork bestNNMSE = null;
        for(int i = 0; i < FDC_COUNT; i++)
        {
            NNs[i].randomiseAllWeights(SEARCH_SPACE_BOUNDARY);
            fitnessMSE[i] = NNs[i].getMeanSquareError(patternFile.getInputs(), patternFile.getOutputs());
            fitnessError[i] = NNs[i].getTrainingError(patternFile.getInputs(), patternFile.getOutputs());
            if(fitnessError[i] < bestError)
            {
                bestNNError = NNs[i];
                bestError = fitnessError[i];
            }
            if(fitnessMSE[i] < bestMSE)
            {
                bestNNMSE = NNs[i];
                bestMSE = fitnessMSE[i];
            }
        }

        double[] ErrorDist = new double[FDC_COUNT];
        double[] MSEDist = new double[FDC_COUNT];

        for(int i = 0; i < FDC_COUNT; i++)
        {
            ErrorDist[i] = HelperFunctions.euclideanDist(NNs[i].getAllWeights(), bestNNError.getAllWeights());

            MSEDist[i] =  HelperFunctions.euclideanDist(NNs[i].getAllWeights(), bestNNMSE.getAllWeights());
        }
        double errorAvgDist = HelperFunctions.avarage(ErrorDist);
        double MSEAvgDist = HelperFunctions.avarage(MSEDist);
        double errorAvgFit = HelperFunctions.avarage(fitnessError);
        double MSEAvgFit = HelperFunctions.avarage(fitnessMSE);
        double totalError = 0;
        double totalMSE = 0;
        for(int i = 0; i < FDC_COUNT; i++)
        {
            totalError += (fitnessError[i] - errorAvgFit) * (ErrorDist[i] - errorAvgDist);

            totalMSE += (fitnessMSE[i] - MSEAvgFit) * (MSEDist[i] - MSEAvgDist);
        }

        totalError /= FDC_COUNT;
        totalMSE /= FDC_COUNT;

        double FDCMSE = totalMSE / (HelperFunctions.std_dev(fitnessMSE) * HelperFunctions.std_dev(MSEDist));
        double FDCError = totalError / (HelperFunctions.std_dev(fitnessError) * HelperFunctions.std_dev(ErrorDist));

        System.out.println(FDCMSE);
        System.out.println(FDCError);
    }

    private static void runFDC(LinkedList<PatternFile> patternFiles)
    {
        for(PatternFile patternFile : patternFiles) {
            NeuralNetwork[] NNs = new NeuralNetwork[FDC_COUNT];
            for (int i = 0; i < FDC_COUNT; i++) {
                NNs[i] = createNetwork1();
            }
            doFDC(patternFile, NNs);
        }
    }

    private static void runManhattan(LinkedList<PatternFile> patternFiles)
    {
        ManhattanRandomWalk manhattanRandomWalk = new ManhattanRandomWalk(-SEARCH_SPACE_BOUNDARY, SEARCH_SPACE_BOUNDARY);
        LinkedList<NeuralNetwork> neuralNetworks = new LinkedList<>();
        neuralNetworks.add(createNetwork1());
        neuralNetworks.add(createNetwork2());
        neuralNetworks.add(createNetwork3());
        neuralNetworks.add(createNetwork4());
        LinkedList<String> nnNames = new LinkedList<>();
        nnNames.add("4-4-3");
        nnNames.add("4-8-3");
        nnNames.add("4-2-2-3");
        nnNames.add("4-2-2-2-3");
        for(PatternFile patternFile : patternFiles) {
            int pos = 0;
            for(NeuralNetwork nn : neuralNetworks) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("sep=,\nRUN,G(avg)(MSE),G(dev)(MSE),G(avg)(STEP),G(dev)(STEP)");
                for (int i = 0; i < RETRY; i++) {
                    System.out.println("Run: " + i);
                    doManhattan(i, nn, patternFile, manhattanRandomWalk);
                }
                FileIO.printFile(OUTPUT_DIR + nnNames.get(pos++) + ".csv", stringBuilder.toString());
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println(createNetwork1().getAllWeights().size());
        System.out.println(createNetwork2().getAllWeights().size());
        System.out.println(createNetwork3().getAllWeights().size());
        System.out.println(createNetwork4().getAllWeights().size());
//        System.out.println("Main");
//        long start = System.nanoTime();
//        LinkedList<PatternFile> patternFiles = new LinkedList<>();
//        patternFiles.add(new PatternFile("src/usage/classification/iris.csv",4,3));
//        runFDC(patternFiles);
//        //runManhattan(patternFiles);
//        long end = System.nanoTime();
//        System.out.println("\nTotal Time: " + HelperFunctions.timeToString(end - start));
    }

    private static NeuralNetwork createNetwork1()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<>();
        hl.add(new HiddenLayer(4, 4));
        return new NeuralNetwork(new InputLayer(4),hl,new OutputLayer(3,4));
    }

    private static NeuralNetwork createNetwork2()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<>();
        hl.add(new HiddenLayer(8, 4));
        return new NeuralNetwork(new InputLayer(4),hl,new OutputLayer(3,8));
    }

    private static NeuralNetwork createNetwork3()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<>();
        hl.add(new HiddenLayer(2, 4));
        hl.add(new HiddenLayer(2, 2));
        return new NeuralNetwork(new InputLayer(4),hl,new OutputLayer(3,2));
    }

    private static NeuralNetwork createNetwork4()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<>();
        hl.add(new HiddenLayer(2, 4));
        hl.add(new HiddenLayer(2, 2));
        hl.add(new HiddenLayer(2, 2));
        return new NeuralNetwork(new InputLayer(4),hl,new OutputLayer(3,2));
    }
}
