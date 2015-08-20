package Bechmark;

import Algorithm.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

/**
 * Created by Eduan on 2015-07-30.
 */
public class Main {
    static final int ITERATIONS = 10000;
    static final int FDC_COUNT = 10;
    static final int RETRY = 30;
    static final double STEP_SIZE = 0.04;
    static final double SEARCH_SPACE_BOUNDARY = 2;
    static final String OUTPUT_DIR = "C:\\Users\\Eduan\\Google Drive\\Universiteit\\2015\\COS 700 - Research Methods and Project\\Results\\iris\\2;0.04\\";
    static StringBuilder stringBuilder;

    private static void Run(int itereation, NeuralNetwork NN,PatternFile patternFile, RandomWalk randomWalk)
    {
        randomWalk.InitialiseNetwork(NN);

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
            NN = randomWalk.AbsoluteStep(NN, STEP_SIZE);
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

        stringBuilder.append("\n"+itereation+","+avgMSE+","+stdevMSE+","+avgStep+","+stdevStep);
    }

    private static NeuralNetwork createNetwork1()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<HiddenLayer>();
        hl.add(new HiddenLayer(4, 4));
        NeuralNetwork NN = new NeuralNetwork(new InputLayer(4),hl,new OutputLayer(3,4));
        return NN;
    }

    private static NeuralNetwork createNetwork2()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<HiddenLayer>();
        hl.add(new HiddenLayer(8, 4));
        NeuralNetwork NN = new NeuralNetwork(new InputLayer(4),hl,new OutputLayer(3,8));
        return NN;
    }

    private static NeuralNetwork createNetwork3()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<HiddenLayer>();
        hl.add(new HiddenLayer(2, 4));
        hl.add(new HiddenLayer(2, 2));
        NeuralNetwork NN = new NeuralNetwork(new InputLayer(4),hl,new OutputLayer(3,2));
        return NN;
    }

    private static NeuralNetwork createNetwork4()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<HiddenLayer>();
        hl.add(new HiddenLayer(2, 4));
        hl.add(new HiddenLayer(2, 2));
        hl.add(new HiddenLayer(2, 2));
        NeuralNetwork NN = new NeuralNetwork(new InputLayer(4),hl,new OutputLayer(3,2));
        return NN;
    }

    static void doFDC(PatternFile patternFile)
    {
        NeuralNetwork[] NNs = new NeuralNetwork[FDC_COUNT];
        double[] fitnessError = new double[FDC_COUNT];
        double[] fitnessMSE = new double[FDC_COUNT];
        double bestError = Double.MAX_VALUE;
        double bestMSE = Double.MAX_VALUE;
        NeuralNetwork bestNNError = null;
        NeuralNetwork bestNNMSE = null;
        for(int i = 0; i < FDC_COUNT; i++)
        {
            LinkedList<HiddenLayer> hl = new LinkedList<HiddenLayer>();
            hl.add(new HiddenLayer(4, 4));
            NNs[i] = new NeuralNetwork(new InputLayer(4),hl,new OutputLayer(3,4));
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
        double totalError = 0;
        double totalMSE = 0;
        double[] ErrorDist = new double[FDC_COUNT];
        double[] MSEDist = new double[FDC_COUNT];
        for(int i = 0; i < FDC_COUNT; i++)
        {
            ErrorDist[i] = euclideanDist(NNs[i].getAllWeights(),bestNNError.getAllWeights());
            totalError += (fitnessError[i] - bestError) * ErrorDist[i];

            MSEDist[i] =  euclideanDist(NNs[i].getAllWeights(),bestNNMSE.getAllWeights());
            totalMSE += (fitnessMSE[i] - bestMSE) * MSEDist[i];
        }
        totalError /= FDC_COUNT;
        totalMSE /= FDC_COUNT;

        double FDCMSE = totalMSE / (std_dev(fitnessMSE) * std_dev(MSEDist));
        double FDCError = totalError / (std_dev(fitnessError) * std_dev(ErrorDist));

        System.out.println(FDCMSE);
        System.out.println(FDCError);
    }

    private static double euclideanDist(LinkedList<Double> a, LinkedList<Double> b)
    {
        double Sum = 0.0;
        for(int i=0;i<a.size();i++) {
            Sum = Sum + Math.pow((a.get(i)-b.get(i)),2.0);
        }
        return Math.sqrt(Sum);
    }

    public static double std_dev(double a[]) {
        double sum = 0;
        double sq_sum = 0;
        for(int i = 0; i < a.length; ++i) {
            sum += a[i];
        }
        double mean = sum / a.length;
        for(int i = 0; i < a.length; ++i) {
            sq_sum += Math.pow(a[i] - mean,2);
        }
        sq_sum = sq_sum/(a.length);
        return Math.sqrt(sq_sum);
    }

    public static void main(String[] args)
    {
        long start = System.nanoTime();
//        RandomWalk randomWalk = new RandomWalk(-SEARCH_SPACE_BOUNDARY, SEARCH_SPACE_BOUNDARY);
        PatternFile iris = new PatternFile("benchmark/iris.csv",4,3);
        doFDC(iris);
//        //============================
//        stringBuilder = new StringBuilder();
//        stringBuilder.append("sep=,\nRUN,G(avg)(MSE),G(dev)(MSE),G(avg)(STEP),G(dev)(STEP)");
//        NeuralNetwork NN1 = createNetwork1();
//        for(int i = 0; i < RETRY; i++) {
//            System.out.println("Run: " + i);
//            Run(i,NN1, iris, randomWalk);
//        }
//        printFile("4-4-3.csv",stringBuilder);
//        //============================
//
//        //============================
//        stringBuilder = new StringBuilder();
//        stringBuilder.append("sep=,\nRUN,G(avg)(MSE),G(dev)(MSE),G(avg)(STEP),G(dev)(STEP)");
//        NeuralNetwork NN2 = createNetwork2();
//        for(int i = 0; i < RETRY; i++) {
//            System.out.println("Run: " + i);
//            Run(i,NN2, iris, randomWalk);
//        }
//        printFile("4-8-3.csv",stringBuilder);
//        //============================
//
//        //============================
//        stringBuilder = new StringBuilder();
//        stringBuilder.append("sep=,\nRUN,G(avg)(MSE),G(dev)(MSE),G(avg)(STEP),G(dev)(STEP)");
//        NeuralNetwork NN3 = createNetwork3();
//        for(int i = 0; i < RETRY; i++) {
//            System.out.println("Run: " + i);
//            Run(i,NN3, iris, randomWalk);
//        }
//        printFile("4-2-2-3.csv",stringBuilder);
//        //============================
//
//        //============================
//        stringBuilder = new StringBuilder();
//        stringBuilder.append("sep=,\nRUN,G(avg)(MSE),G(dev)(MSE),G(avg)(STEP),G(dev)(STEP)");
//        NeuralNetwork NN4 = createNetwork4();
//        for(int i = 0; i < RETRY; i++) {
//            System.out.println("Run: " + i);
//            Run(i,NN4, iris, randomWalk);
//        }
//        printFile("4-2-2-2-3.csv",stringBuilder);
//        //============================
//

        long end = System.nanoTime();
        System.out.println("\nTotal Time: " + HelperFunctions.timeToString(end - start));
    }
    private static void printFile(String fileName, StringBuilder stringBuilder)
    {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(OUTPUT_DIR + fileName, "UTF-8");
            writer.println(stringBuilder);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
