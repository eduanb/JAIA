package usage.Classification;

import algorithms.NeuralNetwork.FeedForward.PatternFile;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Eduan on 2015-08-31.
 */
public class MNISTReader
{
    public static PatternFile read(String imagesFile, String labelsFile) throws IOException
    {

        DataInputStream labels = new DataInputStream(new FileInputStream(labelsFile));
        DataInputStream images = new DataInputStream(new FileInputStream(imagesFile));
        int magicNumber = labels.readInt();
        assert (magicNumber == 2049);
        magicNumber = images.readInt();
        assert (magicNumber == 2051);
        int numLabels = labels.readInt();
        int numImages = images.readInt();
        int numRows = images.readInt();
        int numCols = images.readInt();
        assert (numLabels != numImages);

        int numRead = 0;
        PatternFile result = new PatternFile(numCols * numRows,10);
        while (labels.available() > 0 && numRead < numLabels)
        {
            byte label = labels.readByte();
            double[] output = new double[10]; //Default to 0 in Java
            output[label] = 1;
            numRead++;
            double[] image = new double[numCols * numRows];
            int pos = 0;
            for (int colIdx = 0; colIdx < numCols; colIdx++)
            {
                for (int rowIdx = 0; rowIdx < numRows; rowIdx++)
                {
                    image[pos++] = images.readUnsignedByte();
                }
            }
            result.addInput(image);
            result.addOutput(output);
        }
        return result;
    }
}
