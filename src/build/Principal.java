package build;
import build.transferfunctions.*;
import weka.core.converters.ArffLoader;

import java.io.IOException;

public class Principal {
    public static void main(String[] args)
    {
        double error = 0.0;
        int npatt = 36;




        int[] layers = new int[]{ 9, 9, 9 };

        MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.6, new SigmoidalTransfer());

        String pattern = "/home/giuseppe/Downloads/2018.2/IA/Projeto/project/src/build/breast-cancer.arff"; //cortar para ter teste e training

        try {
            ArffProcessing.loadArff(pattern,9);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //double[] output = new double[npatt]; //COLOCAR OS OUTPUTS DO PATTERNR ESTRUTURADO AQ
  //      double[] inputT = {1,2,3}; // COLOCAR O PATTERN ESTRUTURADO AQ
        //double[] outputT = new double[npatt]; //COLOCAR OS OUTPUTS DO PATTERNR ESTRUTURADO AQ
    /*
        for(int j = 1; j < npatt+1; j++)
        {

            if(inputs == null || output == null)
            {
                System.out.println("Cant find "+pattern);
                continue;
            }

            // Training
            error = net.backPropagate(inputs, output);
            System.out.println("Error is "+error);
        }

        System.out.println("Learning completed!");
*/
        /* Test */
//        outputT=net.execute(inputT);

//        System.out.println(output);
    }

}
