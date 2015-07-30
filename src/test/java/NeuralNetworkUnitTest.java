import Algorithm.HiddenLayer;
import Algorithm.InputLayer;
import Algorithm.NeuralNetwork;
import Algorithm.OutputLayer;
import org.junit.Test;

import java.util.LinkedList;

import static junit.framework.Assert.assertEquals;

public class NeuralNetworkUnitTest
{
    @Test
    public void OneOneOne()
    {
        InputLayer inputLayer = new InputLayer(1, 1);
        LinkedList<HiddenLayer> hiddenLayers = new LinkedList<>();
        hiddenLayers.add(new HiddenLayer(1, 1));
        OutputLayer outputLayer = new OutputLayer(1, 1);
        NeuralNetwork nn = new NeuralNetwork(inputLayer, hiddenLayers, outputLayer);
        nn.setAllWeights(1.0);

        double[] input = {1};
        double[] out = nn.getInputLayer().getOutput(input);
        assertEquals(1.0, out[0]);
        out = hiddenLayers.get(0).getOutput(out);
        assertEquals(0.7310585786300049, out[0]);

		nn.setAllWeights(2.0);
		double[] input2 = { 1 };
        out = nn.getInputLayer().getOutput(input2);
        assertEquals(1.0, out[0]);

		out = hiddenLayers.get(0).getOutput(out);
        assertEquals(0.8807970779778823, out[0]);

        out = outputLayer.getOutput(out);
        assertEquals(0.8534092045709026, out[0]);
    }

    @Test
    public void OneTwoOne()
    {
        InputLayer inputLayer = new InputLayer(1, 1);
        LinkedList<HiddenLayer> hiddenLayers = new LinkedList<>();
        hiddenLayers.add(new HiddenLayer(2, 1));
        OutputLayer outputLayer = new OutputLayer(1, 2);
        NeuralNetwork nn = new NeuralNetwork(inputLayer, hiddenLayers, outputLayer);
		nn.setAllWeights(1.0);

		double[] input3 = { 1 };
        double[] out = nn.getInputLayer().getOutput(input3);

		out = nn.getHiddenLayers().get(0).getOutput(out);
        assertEquals(0.7310585786300049,out[0]);
        assertEquals(0.7310585786300049,out[1]);

		out = nn.getOutputLayer().getOutput(out);
		assertEquals(0.8118562749129378,out[0]);

	}
}
