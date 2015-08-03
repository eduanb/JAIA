package Bechmark;

import Algorithm.*;

import java.util.LinkedList;

/**
 * Created by Eduan on 2015-07-30.
 */
public class Main {

    public static void main(String[] args)
    {
        System.out.println("Starting..");
        long start = System.nanoTime();
        //============================

        LinkedList<PatternFile> patterns = new LinkedList<>();
        PatternFile iris = new PatternFile("benchmark/iris.csv",4,3);
        LinkedList<HiddenLayer> hl = new LinkedList<HiddenLayer>();
        hl.add(new HiddenLayer(4, 4));
        NeuralNetwork NN = new NeuralNetwork(new InputLayer(4),hl,new OutputLayer(3,4));

        RandomWalk randomWalk = new RandomWalk(-5,5);
        randomWalk.InitialiseNetwork(NN);

        //NN.printNetwork();

        NN = randomWalk.AbsoluteStep(NN,0.1);

        //NN.printNetwork();
        for(int i = 0; i < 10000 * 30 * 10 * 3 * 10; i++) {
            if(NN.getTrainingError(iris.getInputs(), iris.getOutputs()) > 90) {
                System.out.println(NN.getTrainingError(iris.getInputs(), iris.getOutputs()));
                NN.printNetwork();
            }
            NN = randomWalk.AbsoluteStep(NN, 0.1);
        }







        patterns.add(iris);

        //============================
        long end = System.nanoTime();
        System.out.println("Total Time: " + HelperFunctions.timeToString(end - start));
    }

}
