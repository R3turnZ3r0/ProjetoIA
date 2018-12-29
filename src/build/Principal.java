package build;
import build.transferfunctions.*;

import java.io.IOException;

public class Principal {
    public static void main(String[] args)
    {
        double error = 0.0;
        double maxit = 286;
        int[] layers = new int[]{9,8,7,6,5,4,3,2,1 };

        MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.6, new HyperbolicTransfer());

        String pattern = "/home/giuseppe/Downloads/2018.2/IA/Projeto/project/src/build/breast-cancer.arff"; //cortar para ter teste e training

        try {

            ArffProcessing a = new ArffProcessing();
            a.loadArff(pattern);
            double[][] inputs = a.getInput();
            double[][] output = a.getOutput();

            int i = 0;

            while((i < maxit))
            {
                if(inputs[i] == null)
                {
                    i++;
                    continue;
                }

                if(output[i] == null)
                {
                    i++;
                    continue;
                }

                System.out.println();
                // Training
                error = net.backPropagate(inputs[i], output[i]);
                System.out.println("Error at step "+i+" is "+error);

                i++;
            }

            System.out.println("Learning completed!");

            /* Test */
            double [] inputT = {8.0,
                    1.0,
                    8.0,
                    0.0,
                    1.0,
                    2.0,
                    0.0,
                    2.0,
                    1.0};
            double [] outputT=net.execute(inputT);

            System.out.println("RESP "+outputT[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
