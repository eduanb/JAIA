package Bechmark;

import Algorithm.*;

import java.util.LinkedList;

/**
 * Created by Eduan on 2015-07-30.
 */
public class Main {
    static final double ITERATIONS = 10000;
    static final int RETRY = 30;
    static final double STEP_SIZE = 0.1;
    static final double SEARCH_SPACE_BOUNDARY = 1000;

    private static void Run(NeuralNetwork NN,PatternFile patternFile, RandomWalk randomWalk)
    {
        randomWalk.InitialiseNetwork(NN);

        double totalMeanSquare = 0.0;
        double totalStep = 0.0;
        double step = NN.getTrainingError(patternFile.getInputs(), patternFile.getOutputs());
        double mean = NN.getMeanSquareError(patternFile.getInputs(), patternFile.getOutputs());

        for(int i = 0; i < ITERATIONS; i++) {
            double tempStep = NN.getTrainingError(patternFile.getInputs(), patternFile.getOutputs());
            double tempMean = NN.getMeanSquareError(patternFile.getInputs(), patternFile.getOutputs());
            totalStep += Math.abs((step - tempStep) / STEP_SIZE);
            totalMeanSquare += Math.abs((mean - tempMean) / STEP_SIZE);
            NN = randomWalk.AbsoluteStep(NN, STEP_SIZE);
            step = tempStep;
            mean = tempMean;
            NN = randomWalk.AbsoluteStep(NN, STEP_SIZE);
        }
        System.out.println("Mean Square gradient average:" + totalMeanSquare / ITERATIONS);
        System.out.println("Step gradient average:" + totalStep / ITERATIONS);
    }

    private static NeuralNetwork createNetwork1()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<HiddenLayer>();
        hl.add(new HiddenLayer(4, 4));
        NeuralNetwork NN = new NeuralNetwork(new InputLayer(4),hl,new OutputLayer(3,4));
        return NN;
    }

    public static void main(String[] args)
    {
        System.out.println("Starting..");
        long start = System.nanoTime();
        //============================
        RandomWalk randomWalk = new RandomWalk(-SEARCH_SPACE_BOUNDARY, SEARCH_SPACE_BOUNDARY);
        PatternFile iris = new PatternFile("benchmark/iris.csv",4,3);
        NeuralNetwork NN1 = createNetwork1();
        for(int i = 0; i < RETRY; i++) {
            System.out.println("Try " + i + ":");
            Run(NN1, iris, randomWalk);
            System.out.println("==============");
        }
        //============================
        long end = System.nanoTime();
        System.out.println("\nTotal Time: " + HelperFunctions.timeToString(end - start));
    }

}
