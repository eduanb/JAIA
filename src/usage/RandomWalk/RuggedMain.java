package RandomWalk;

import algorithms.LandscapeMesures.RuggedMeasures;
import algorithms.NeuralNetwork.FeedForward.*;
import algorithms.Optimisation.OptimisationProblem.NeuralNetworkCEProblem;
import algorithms.Optimisation.OptimisationProblem.NeuralNetworkProblem;
import utils.FileIO;
import utils.HelperFunctions;

import java.util.LinkedList;

/**
 * Created by Eduan on 2015-09-07.
 */

public class RuggedMain {
    static final int numSteps = 1000;
    static final int retry = 30;
    static final double size = 0.01;
    static final int range = 2;
    static final String OUTPUT_DIR = "C:\\Users\\Eduan\\Google Drive\\Universiteit\\2015\\COS 700 - Research Methods and Project\\Results\\rugged\\micro\\diabetes\\";
    public static void main(String[] args) {
        System.out.println("Range:"+range);
        PatternFile pf = new  PatternFile("src/usage/classification/diabetes.csv", 8, 1);
        LinkedList<NeuralNetworkProblem> ll = new LinkedList();
        LinkedList<NeuralNetworkCEProblem> ll2 = new LinkedList();
        ll.add(new NeuralNetworkProblem(createNetwork1(), pf, pf,range));
        ll.add(new NeuralNetworkProblem(createNetwork2(), pf, pf,range));
        ll.add(new NeuralNetworkProblem(createNetwork3(), pf, pf,range));
        ll.add(new NeuralNetworkProblem(createNetwork4(), pf, pf,range));

        ll2.add(new NeuralNetworkCEProblem(createNetwork1(), pf, pf,range));
        ll2.add(new NeuralNetworkCEProblem(createNetwork2(), pf, pf,range));
        ll2.add(new NeuralNetworkCEProblem(createNetwork3(), pf, pf,range));
        ll2.add(new NeuralNetworkCEProblem(createNetwork4(), pf, pf,range));

        LinkedList<String> nnNames = new LinkedList<>();
        nnNames.add("8-6-1");
        nnNames.add("8-12-1");
        nnNames.add("8-4-4-1");
        nnNames.add("8-4-4-4-1");

        doRugged(ll,ll2,nnNames);
    }

    private static void doRugged(LinkedList<NeuralNetworkProblem> ll,LinkedList<NeuralNetworkCEProblem> ll2, LinkedList<String> nnNames)
    {
        int count = 0;
        for(NeuralNetworkProblem np : ll)
        {
            RuggedMeasures r1 = new RuggedMeasures(np, numSteps, size);
            RuggedMeasures r2 = new RuggedMeasures(ll2.get(count), numSteps, size);
            StringBuilder sb = new StringBuilder();
            sb.append("sep=,\nRUN,Ruggedness(MSE),Ruggedness(CE)");
            double[] mse = new double[retry];
            double[] ce = new double[retry];
            for(int i = 0; i < retry; i++)
            {
                System.out.println(i);
                sb.append("\n");
                sb.append(i);
                sb.append(",");
                mse[i] = r1.calculateMeasure();
                sb.append(mse[i]);

                sb.append(",");
                ce[i] = r2.calculateMeasure();
                sb.append(ce[i]);
            }
            sb.append("\n");
            sb.append("Average,");
            sb.append(HelperFunctions.avarage(mse));
            sb.append(",");
            sb.append(HelperFunctions.avarage(ce));

            sb.append("\n");
            sb.append("STD,");
            sb.append(HelperFunctions.std_dev(mse));
            sb.append(",");
            sb.append(HelperFunctions.std_dev(ce));

            FileIO.printFile(OUTPUT_DIR + "[-" + range + ";" + range + "]\\" + nnNames.get(count) + ".csv", sb.toString());
            count++;
        }

    }
    private static NeuralNetwork createNetwork1()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<>();
        hl.add(new HiddenLayer(6, 8));
        return new NeuralNetwork(new InputLayer(8),hl,new OutputLayer(1,6));
    }

    private static NeuralNetwork createNetwork2()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<>();
        hl.add(new HiddenLayer(12, 8));
        return new NeuralNetwork(new InputLayer(8),hl,new OutputLayer(1,12));
    }

    private static NeuralNetwork createNetwork3()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<>();
        hl.add(new HiddenLayer(4, 8));
        hl.add(new HiddenLayer(4, 4));
        return new NeuralNetwork(new InputLayer(8),hl,new OutputLayer(1,4));
    }

    private static NeuralNetwork createNetwork4()
    {
        LinkedList<HiddenLayer> hl = new LinkedList<>();
        hl.add(new HiddenLayer(4, 8));
        hl.add(new HiddenLayer(4, 4));
        hl.add(new HiddenLayer(4, 4));
        return new NeuralNetwork(new InputLayer(8),hl,new OutputLayer(1,4));
    }
}
