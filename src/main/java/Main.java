

public class Main
{
	public static void main(String[] args)
	{
		long start = System.nanoTime();
        System.out.println("Hello World!");
		long end = System.nanoTime();
		System.out.println("Total Time: " + HelperFunctions.timeToString(end - start));
	}


}
