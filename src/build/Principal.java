package build;
import build.transferfunctions.*;

import java.io.IOException;

public class Principal {
    public static void main(String[] args)
    {
        double error = 0.0;
        double maxit = 286;
        int[] layers = new int[]{9,12,1};

        MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.3, new SigmoidalTransfer());

        String pattern = "/home/giuseppe/Downloads/2018.2/IA/Projeto/project/src/build/breast-cancer.arff"; //cortar para ter teste e training

        try {

            ArffProcessing a = new ArffProcessing();
            a.loadArff(pattern);
            double[][] inputs = a.getInput();
            double[][] output = a.getOutput();
            for(int i=0;i<9;i++) System.out.println(inputs[0][i]);
            System.out.println(output[0][0]);

            int i = 0;
            System.out.println("RODANDO...");

            for(int k=0;k<10000;k++) {
                while ((i < maxit)) {
                    if (inputs[i] == null) {
                        i++;
                        continue;
                    }

                    if (output[i] == null) {
                        i++;
                        continue;
                    }

                    // Training
                    error = net.backPropagate(inputs[i], output[i]);
                    System.out.println("Error at step " + i + " is " + error);

                    i++;
                }
                i=0;
            }
            System.out.println("Learning completed!");

            /* Test */
            double [] inputT = new double[]{4.0,1.0,3.0,0.0,1.0,0.0,1.0,4.0,1.0};
            //double [] inputT = new double[]{0.0,0.0};
            double [] outputT=net.execute(inputT);

            System.out.println(Math.round(outputT[0])+" ("+outputT[0]+")");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
