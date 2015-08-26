package algorithms.Optimisation.PopulationBased.PSO;

public class PSOUtility
{
	public static int getMinPos(double[] list)
	{
		int pos = 0;
		double minValue = list[0];

		for (int i = 0; i < list.length; i++)
		{
			if (list[i] < minValue)
			{
				pos = i;
				minValue = list[i];
			}
		}

		return pos;
	}
    public static int getMinPos(double[] list, int maxIndex)
    {
        int pos = 0;
        double minValue = list[0];

        for (int i = 0; i < maxIndex; i++)
        {
            if (list[i] < minValue)
            {
                pos = i;
                minValue = list[i];
            }
        }

        return pos;
    }
}
