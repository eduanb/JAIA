package Bechmark;

import Algorithm.HelperFunctions;

/**
 * Created by Eduan on 2015-07-30.
 */
public class Main {
    public static void main(String[] args)
    {
        long start = System.nanoTime();
        System.out.println("Hello World!");
        long end = System.nanoTime();
        System.out.println("Total Time: " + HelperFunctions.timeToString(end - start));
    }

}
