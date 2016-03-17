package algorithms.LandscapeMesures;

import algorithms.NeuralNetwork.FeedForward.NeuralNetwork;
import algorithms.Optimisation.OptimisationProblem.AbstractNeuralNetworkProblem;
import algorithms.RandomWalk.RandomWalk;


public class RuggedMeasures implements Measure
{
    int numWalks;
    int numSteps; // number of steps on each walk
    AbstractNeuralNetworkProblem problem;
    double stepSize; // maximum step size
    double range;  // xMin - xMax
    int dim;  // dimension of the problem
    int[][] S;   // Array of strings of 0, 1, -1 elements. One array of strings for each walk
    private double epsilonStar;  // value of epsilon at which the landscape becomes flat.

    public RuggedMeasures(AbstractNeuralNetworkProblem p)
    {
        this(p, 1000, 0.01);  // 1% of range
    }

    public RuggedMeasures(AbstractNeuralNetworkProblem p, int ns, double ssD)
    {  // 3nd parameter is step size determiner (proportion of range)
        problem = p;
        range = p.getMax() - p.getMin();
        dim = problem.getProblemDimension();
        stepSize = range * ssD;
        numWalks = dim;
        numSteps = ns;
        S = new int[numWalks][numSteps - 1];
        //numStepsActual = new int[numWalks]; // exact sizes
        //BinaryFlag bf = new BinaryFlag(dim);  // used for specifying the walks
        //int numStartingZones = (int) Math.pow(2, dim);  // number of possible starting zones
        //int numSkips = (int) Math.round(numStartingZones / numWalks);  // number of starting zones to skip each time
        epsilonStar = getInfoStability();
    }

    public double getInfoStability()
    {
        double epsilonBase = 10;
        double epsilonStep = 10;
        double epsilon = 0;
        int epsilonOrder = 0;
        boolean foundBigOrder = false;
        // The first while loop is to quickly reach the order of magnitude required, otherwise it takes too
        // long to work out epsilonStar for problems with huge fitness ranges.
        while (!foundBigOrder) {
            if (isLandscapeFlat(epsilon)) {
                foundBigOrder = true;
                epsilonStep = epsilonStep / epsilonBase;
            } else {
                epsilon = epsilonStep;
                epsilonOrder++;
                epsilonStep *= epsilonBase;
            }
        }
        //	System.out.println("big order:" + epsilonOrder);

        double smallestStep = 0.01 * Math.pow(10, epsilonOrder);  // a resonable accuracy for the problem
        boolean notFoundPrecise = true;
        while (notFoundPrecise) {
            if (isLandscapeFlat(epsilon)) {
                if (epsilonStep <= smallestStep)
                    notFoundPrecise = false;
                else // find a more precise value for epsilon:
                {
                    epsilon = epsilon - epsilonStep; // go back one big step
                    epsilonStep = epsilonStep / epsilonBase; // decrease size of step
                    epsilon = epsilon + epsilonStep; // add one smaller step
                }
            } else { // landscape is not flat, so increase EPSILON
                epsilon += epsilonStep;
            }
        }
        //	System.out.println("epsilon*:" + epsilon);
        return epsilon;
    }

    private boolean isLandscapeFlat(double e)
    {
        computeS(e);
        for (int w = 0; w < numWalks; w++) {
            for (int i = 0; i < (numSteps - 1); i++)
                if (S[w][i] != 0)
                    return false;
        }
        return true;
    }

    private void computeS(double epsilon)
    {
        for (int w = 0; w < numWalks; w++) {
            RandomWalk randomWalk = new RandomWalk(problem.getMin(), problem.getMax());
            NeuralNetwork nn = randomWalk.InitialiseNetwork(problem.getNeuralNetwork());
            double prev = problem.evaluate(nn);
            for (int i = 1; i < numSteps; i++) {
                nn = randomWalk.AbsoluteStep(nn, stepSize);
                double current = problem.evaluate(nn);
                if ((current - prev) < -epsilon)
                    S[w][i - 1] = -1;
                else if (Math.abs((current - prev)) <= epsilon)
                    S[w][i - 1] = 0;
                else if ((current - prev) > epsilon)
                    S[w][i - 1] = 1;
                prev = current;
            }
        }
    }

    public double calculateMeasure()
    {
        return calculateFEM();
    }

    public double calculateFEM()
    {  // determine the FEM measure
        double increment = 0.05;
        int numEpsilons = (int) (1.0 / increment) + 1;
        double[] epsilon = new double[numEpsilons];

        double multiplier = 0;
        for (int e = 0; e < numEpsilons; e++) {
            epsilon[e] = epsilonStar * multiplier;
            multiplier += increment;
        }

        double[] H = new double[numEpsilons];   // store H value at each epsilon
        // calculate H(epsilon) for different values of epsilon:
        double HMax = 0;
        // Last one can be ignored, because H(e*) will be 0.
        for (int numE = 0; numE < numEpsilons; numE++) {
            H[numE] = getInfoContent(epsilon[numE]);
            if (H[numE] > HMax)
                HMax = H[numE];
        }
        return HMax;
    }

    public double getInfoContent(double e)
    {
        computeS(e);
        double He = 0;
        int[] num = new int[6]; // number of occurrences of each different sequence
        // e.g. num[0] = num of occurrences of 0 1
        //     num[1] = num of occurrences of 0 -1 etc.
        for (int i = 0; i < 6; i++)
            num[i] = 0;
        int totalNum_pq = 0;
        for (int w = 0; w < numWalks; w++) {
            int q = 1; // counter for S: start with 2nd element
            int p = 0; // previous element
            while (q < (numSteps - 1)) // while q has not gone beyond the actual data in S
            {
                if (S[w][p] != S[w][q]) {  // only look at subsequent values that differ
                    if ((S[w][p] == 0) && (S[w][q] == 1))
                        num[0]++;
                    else if ((S[w][p] == 0) && (S[w][q] == -1))
                        num[1]++;
                    else if ((S[w][p] == 1) && (S[w][q] == 0))
                        num[2]++;
                    else if ((S[w][p] == 1) && (S[w][q] == -1))
                        num[3]++;
                    else if ((S[w][p] == -1) && (S[w][q] == 0))
                        num[4]++;
                    else if ((S[w][p] == -1) && (S[w][q] == 1))
                        num[5]++;
                }
                q++;
                p = q - 1; // previous element
                totalNum_pq++;
            }
        }

        int n = totalNum_pq;  // total number of objects pq
        for (int i = 0; i < 6; i++) {
            double Prob = (double) num[i] / n;
            // print out probabilities for debugging:
           /* System.out.println("num[" + i + "] = " + num[i] + "\t Prob = " + Prob);*/
            if (Prob != 0)
                He = He + (Prob * (Math.log(Prob) / Math.log(6)));
            //   System.out.println("\t He = " + He);
        }
        if (He != 0)
            return -He;
        else
            return 0;
    }

    public void printS()
    {
        // print S for debugging:
        for (int w = 0; w < numWalks; w++) {
            System.out.println("String S" + w + ":");
            for (int i = 0; i < numSteps; i++)
                System.out.print(S[w][i] + "\t");
            System.out.println();
        }
    }

    /* Vassilev et al's Information Content entropic measure for estimating
     * ruggedness (distribution of optima) of a fitness landscape
     * parameter e: epsilon
     */
    public double getH(double e)
    {
        return getInfoContent(e);
    }

    /* Computes Vassilve et al's information stability measure - the magnitude of
     * the landscape optima (i.e. the smallest value of epsilon for which the
     * landscape becomes flat)
     * return value: the smallest value of epsilon for which S is a string
     *               of zeros, within the range [0,1]. If S does not become a
     *               string of zeros by the time epsilon reaches 1, the method
     *               returns -1.
     */
    public double getEpsilonStar()
    {
        return getInfoStability();
    }

}