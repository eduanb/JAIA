package UTILS;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by eduan on 2015/05/06.
 */
public class FileIO
{
    public void printFile(String FileName, String content)
    {
        PrintWriter writer;
        try
        {
            writer = new PrintWriter(FileName,"UTF-8");
            writer.print(content);
            writer.close();
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    public static String ReadFile(String fileName)
    {
        return ReadFile(new File(fileName));
    }

    public static String ReadFile(File file)
    {
        Scanner scanner;
        StringBuilder result = new StringBuilder();
        try
        {
            scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                result.append(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File does not exist.");
            System.exit(-1);//Exit with error
        }
        return result.toString();
    }

    public static String ReadFileIgnoreWhitespace(String fileName)
    {
        return ReadFileIgnoreWhitespace(new File(fileName));
    }

    public static String ReadFileIgnoreWhitespace(File file)
    {
        Scanner scanner;
        StringBuilder result = new StringBuilder();
        try
        {
            scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                //removes whitespace
                line = line.replaceAll("\\s", "");
                if(line.equals("")) continue;
                result.append(line);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File does not exist.");
            System.exit(-1);//Exit with error
        }
        return result.toString();
    }

    public static String ReadFileIgnoreWhitespaceAndComments(String fileName)
    {
        return ReadFileIgnoreWhitespaceAndComments(new File(fileName));
    }

    public static String ReadFileIgnoreWhitespaceAndComments(File file)
    {
        Scanner scanner;
        StringBuilder result = new StringBuilder();
        try
        {
            scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                //removes whitespace
                line = line.replaceAll("\\s", "");
                line = line.split("//")[0];
                if(line.equals("")) continue;
                result.append(line);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File does not exist.");
            System.exit(-1);//Exit with error
        }
        return result.toString();
    }
}
