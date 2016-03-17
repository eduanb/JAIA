package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by eduan on 2015/05/06.
 */
public class FileIO
{
    public static void printFile(String FileName, String content)
    {
        PrintWriter writer;
        try {
            new File(FileName).mkdirs();
            writer = new PrintWriter(FileName, "UTF-8");
            writer.print(content);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
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
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                result.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
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
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //removes whitespace
                line = line.replaceAll("\\s", "");
                if (line.equals(""))
                    continue;
                result.append(line);
            }
        } catch (FileNotFoundException e) {
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
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //removes whitespace
                line = line.replaceAll("\\s", "");
                line = line.split("//")[0];
                if (line.equals(""))
                    continue;
                result.append(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            System.exit(-1);//Exit with error
        }
        return result.toString();
    }

    public static String[] ReadArray(String fileName)
    {
        return ReadArray(new File(fileName));
    }

    public static String[] ReadArray(File file)
    {
        Scanner scanner;
        List<String> result = new LinkedList<>();
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            System.exit(-1);//Exit with error
        }
        return (String[]) result.toArray();
    }

    public static String[][] Read2DArray(String fileName, String separator)
    {
        return Read2DArray(new File(fileName), separator);
    }

    public static String[][] Read2DArray(File file, String separator)
    {
        Scanner scanner;
        List<String[]> result = new LinkedList<>();
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine().split(separator));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            System.exit(-1);//Exit with error
        }
        return (String[][]) result.toArray();
    }

    public static int[][] Read2DArrayAsInt(String fileName, String separator)
    {
        return Read2DArrayAsInt(new File(fileName), separator);
    }

    public static int[][] Read2DArrayAsInt(File file, String separator)
    {
        Scanner scanner;
        int[][] result = new int[9][];
        int pos = 0;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] splitString = scanner.nextLine().split(separator);
                int[] splitInt = new int[splitString.length];
                for (int i = 0; i < splitString.length; i++) {
                    if (splitString[i].equals("") || splitString[i].equals(" "))
                        splitInt[i] = Integer.MIN_VALUE;
                    else
                        splitInt[i] = Integer.parseInt(splitString[i]);
                }
                result[pos++] = splitInt;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            System.exit(-1);//Exit with error
        }
        return result;
    }
}
