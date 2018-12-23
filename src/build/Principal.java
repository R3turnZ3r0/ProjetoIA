package build;
import build.transferfunctions.*;


public class Principal {
    public static void main(String[] args)
    {
        int[] layers = new int[]{ 16*16, 16*16/2, 40 };

        MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.6, new HyperbolicTransfer());

        /* Learning */
        for(int i = 0; i < 100000; i++)
        {
            double[] inputs = new double[16*16];
            double[] output = new double[40];
            double error;

            for(int j = 0; j < inputs.length; j++)
                inputs[j] = 0;

            error = net.backPropagate(inputs, output);
            System.out.println("Error at step "+i+" is "+error);
        }

        System.out.println("Learning completed!");



    }
}
