package build;

import java.io.IOException;

public class Principal {
    //breast cancer
    public static void main(String[] args)
    {
        double error;
        int[] layers = new int[]{9,12,1};
        double lR=0;
        for(int v=0;v<=10;v++){
            lR=((double) v)/10;
            System.out.println(lR);
            MultiLayerPerceptron net = new MultiLayerPerceptron(layers, lR, new TransferFunction());

            String pattern = "src/build/breast-cancer.arff";

            try {
                ArffProcessing a = new ArffProcessing();
                a.loadArff(pattern, 10); // X1 || X2 = O -> 3 colunas

                double[][] inputs = a.getInPutTrain();
                double[][] output = a.getOutPutTrain();

                int i = 0;

                System.out.println("RODANDO...");

                for(int k=0;k<10000;k++) {
                    while ((i < inputs.length)) {
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
                        //System.out.println("Error is " + error);

                        i++;
                    }
                    i=0;
                }
                System.out.println("Learning completed!");

                //Test
                double [][] inputT = a.getInPutTest();
                double[] saida = new double[a.getInPutTest().length];

                for(i=0; i<a.getInPutTest().length;i++){
                    double [] outputT=net.execute(inputT[i]);
                    saida[i]=outputT[0];
                    //for(int j=0;j<inputT[i].length;j++) {
                    //    System.out.print(inputT[i][j]+" ");
                    //}

                    //System.out.println(" ="+Math.round(outputT[0]));
                }
                double acuracia = build.acuracia.calculo(a.getOutPutTest(),saida);
                System.out.println("acuracia"+v+" : "+acuracia);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
