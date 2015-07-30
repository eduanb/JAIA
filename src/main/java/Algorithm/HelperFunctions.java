package Algorithm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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

    /**
     * Helper function to write files
     *
     * @param FileName
     *            The file to write to
     * @param content
     *            Content to write in the file
     */
    public static void printFile(String FileName, String content)
    {
        PrintWriter writer;
        try
        {
            writer = new PrintWriter(FileName, "UTF-8");
            writer.print(content);
            writer.close();
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }
}
