package utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

/**
 * Created by eduan on 2015/03/04.
 */
public class HelperFunctions
{
    /**
     * Helper function to convert Time in nanoseconds to a nice string
     *
     * @param ns
     *            nanoseconds
     * @return String from nanoseconds
     */
    public static String timeToString(long ns)
    {
        long MICROSECOND = 1000;
        long MILISECOND = MICROSECOND * 1000;
        long SECOND = 1000 * MILISECOND;
        long MINUTE = 60 * SECOND;
        long HOUR = 60 * MINUTE;
        long DAY = 24 * HOUR;
        StringBuffer text = new StringBuffer("");
        if (ns > DAY)
        {
            text.append(ns / DAY).append(" days ");
            ns %= DAY;
        }
        if (ns > HOUR)
        {
            text.append(ns / HOUR).append(" hours ");
            ns %= HOUR;
        }
        if (ns > MINUTE)
        {
            text.append(ns / MINUTE).append(" minutes ");
            ns %= MINUTE;
        }
        if (ns > SECOND)
        {
            text.append(ns / SECOND).append(" seconds ");
            ns %= SECOND;
        }
        if (ns > MILISECOND)
        {
            text.append(ns / MILISECOND).append(" milliseconds ");
            ns %= MILISECOND;
        }
        if (ns > MICROSECOND)
        {
            text.append(ns / MICROSECOND).append(" microseconds ");
            ns %= MICROSECOND;
        }
        text.append(ns + " ns");
        return text.toString();
    }

    public static double euclideanDist(LinkedList<Double> a, LinkedList<Double> b)
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

    public static double avarage(double a[]) {
        double sum = 0;
        for(int i = 0; i < a.length; ++i) {
            sum += a[i];
        }
        return sum / a.length;
    }
}
