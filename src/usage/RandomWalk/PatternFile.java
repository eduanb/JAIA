package RandomWalk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Eduan on 2015-08-03.
 */
public class PatternFile {
    File file;
    int numIn;
    int numOut;
    LinkedList<double[]> inputs;
    LinkedList<double[]> outputs;

    public File getFile() {
        return file;
    }

    public int getNumIn() {
        return numIn;
    }

    public int getNumOut() {
        return numOut;
    }

    public LinkedList<double[]> getInputs() {
        return inputs;
    }

    public LinkedList<double[]> getOutputs() {
        return outputs;
    }

    public PatternFile(String fileName, int numIn, int numOut) {
        this.file = new File(fileName);
        this.numIn = numIn;
        this.numOut = numOut;
        inputs = new LinkedList<>();
        outputs = new LinkedList<>();
        readFile();
    }

    private void readFile() {
        Scanner scanner;
        try
        {
            scanner = new Scanner(file);
            scanner.nextLine();//skips first line
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] temp = line.split(",");
                double[] in = new double[numIn];
                double[] out = new double[numOut];
                for(int i = 0; i < numIn; i++)
                {
                    in[i] = Double.parseDouble(temp[i]);
                }
                for(int i = numIn; i < numIn + numOut; i++)
                {
                    out[i - numIn] = Double.parseDouble(temp[i]);
                }
                inputs.add(in);
                outputs.add(out);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Pattern file=\""+file.getAbsolutePath()+"\" not found.");
            System.exit(-1);//Exit with error
        }
    }
}
