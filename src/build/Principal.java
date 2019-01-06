package build;

import java.io.IOException;

public class Principal {
    //breast cancer
   /* public static void main(String[] args)
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

            int i = 0;m,
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

            /* Test *
            double [] inputT = new double[]{4.0,1.0,3.0,0.0,1.0,0.0,1.0,4.0,1.0};
            //double [] inputT = new double[]{0.0,0.0};
            double [] outputT=net.execute(inputT);

            System.out.println(Math.round(outputT[0])+" ("+outputT[0]+")");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    //OR ARFF
    public static void main(String[] args)
    {
        double error = 1.0;
        int[] layers = new int[]{2,2,1};

        MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.3, new TransferFunction());

        String pattern = "/home/giuseppe/Downloads/2018.2/IA/Projeto/project/src/build/or.arff";

        try {
            ArffProcessing a = new ArffProcessing();
            a.loadArff(pattern, 3); // X1 || X2 = O -> 3 colunas
            double[][] inputs = a.getInput();
            double[][] output = a.getOutput();

            int i = 0;

            System.out.println("RODANDO...");

            while (error > 0.0001) {
                    if (inputs[i] == null || output[i] == null) continue;

                    // Training
                    error = net.backPropagate(inputs[i], output[i]);
                    System.out.println("Error: " + error);
            }
            System.out.println("Learning completed!");

            //Test
            double [] inputT = new double[]{0.0,0.0};
            double [] outputT=net.execute(inputT);

            System.out.println(Math.round(outputT[0])+" ("+outputT[0]+")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
