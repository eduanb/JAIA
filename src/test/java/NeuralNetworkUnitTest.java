import org.junit.Test;

import java.util.LinkedList;

import static junit.framework.Assert.assertEquals;

public class NeuralNetworkUnitTest
{
    @Test
    public void SingleLayerSingleNeuronTest()
    {
        InputLayer inputLayer = new InputLayer(1, 1);
        LinkedList<HiddenLayer> hiddenLayers = new LinkedList<>();
        hiddenLayers.add(new HiddenLayer(1, 1));
        OutputLayer outputLayer = new OutputLayer(1, 1);
        NeuralNetwork nn = new NeuralNetwork(inputLayer, hiddenLayers, outputLayer);
        nn.setAllWeights(1.0);

        double[] input = {1};
        double[] out = nn.inputLayer.getOutput(input);
        assertEquals(1.0, out[0]);
        out = hiddenLayers.get(0).getOutput(out);
        assertEquals(0.7310585786300049, out[0]);

		nn.setAllWeights(2.0);
		double[] input2 = { 1 };
        out = nn.inputLayer.getOutput(input2);
        assertEquals(1.0, out[0]);

		out = hiddenLayers.get(0).getOutput(out);
        assertEquals(0.8807970779778823, out[0]);

        out = outputLayer.getOutput(out);
        assertEquals(0.8534092045709026, out[0]);
    }

    @Test
    public void TestTwo()
    {
//		// -----------------------------------------------------------------------------------------------
//		System.out.println("*********** Test2 *********");
//		nn = new NeuralNetwork(1, 1, 2,set);
//		nn.setAllWeights(1.0);
//		System.out.println("** nInputs:1");
//		System.out.println("** nOutputs:1");
//		System.out.println("** HiddenLayerCount:2");
//		System.out.println("*********** Test2.1 ********");
//		System.out.println("*** AllWeightsSetTo:1");
//		System.out.println("*** Input:1");
//		double[] input3 =
//		{ 1 };
//		System.out.println("*** ExpectedInputLayerOut:1.0");
//		out = nn.inputLayer.getOutput(input3);
//		System.out.println("*** ActualInputLayerOut: " + out[0]);
//		System.out.println("*** ExpectedHiddenLayerOut:0.7310585786300049  ,  0.7310585786300049");
//		out = nn.hiddenLayer.getOutput(out);
//		System.out.println("*** ActualHiddenLayerOut: " + out[0] + "  ,  " + out[1]);
//		if (0.7310585786300049 == out[0] && 0.7310585786300049 == out[1])
//			System.out.println("*** PASS");
//		else
//		{
//			System.out.println("*** FAIL");
//			failCount++;
//		}
//		System.out.println("*** ExpectedOutputLayerOut:0.8118562749129378");
//		out = nn.outputLayer.getOutput(out);
//		System.out.println("*** ActualOutputLayerOut: " + out[0]);
//		if (0.8118562749129378 == out[0])
//			System.out.println("*** PASS");
//		else
//		{
//			System.out.println("*** FAIL");
//			failCount++;
//		}
//
//		// --------------------- Test3
//
//		System.out.println("*********** Test3 *********");
//		nn = new NeuralNetwork(5, 1, 3,set);
//		nn.setAllWeights(1.0);
//		System.out.println("** nInputs:1");
//		System.out.println("** nOutputs:1");
//		System.out.println("** HiddenLayerCount:2");
//		System.out.println("*** AllWeightsSetTo:1");
//		System.out.println("*** Input:1,1,1,1,1");
//
//		double[] input4 =
//		{ 1, 1, 1, 1, 1 };
//		System.out.println("*** ExpectedInputLayerOut:1.0");
//		out = nn.inputLayer.getOutput(input4);
//		System.out.println("*** ActualInputLayerOut: " + out[0]);
//
//		System.out
//				.println("*** ExpectedHiddenLayerOut:0.9933071490757153  ,  0.9933071490757153 ...");
//		out = nn.hiddenLayer.getOutput(out);
//		System.out.println("*** ActualHiddenLayerOut: " + out[0] + "  ,  " + out[1]);
//
//		if (0.9933071490757153 == out[0] && 0.9933071490757153 == out[1])
//			System.out.println("*** PASS");
//		else
//		{
//			System.out.println("*** FAIL");
//			failCount++;
//		}
//		System.out.println("*** ExpectedOutputLayerOut:0.9516587576377415");
//		out = nn.outputLayer.getOutput(out);
//		System.out.println("*** ActualOutputLayerOut: " + out[0]);
//		if (0.9516587576377415 == out[0])
//			System.out.println("*** PASS");
//		else
//		{
//			System.out.println("*** FAIL");
//			failCount++;
//		}
//		System.out.println("************************* DONE ******************");
//		System.out.println("Fail Count: " + failCount);
	}
}
