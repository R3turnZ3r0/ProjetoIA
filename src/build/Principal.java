package build;

import java.io.IOException;

public class Principal {
    //breast cancer
    public static void main(String[] args)
    {
        //variaveis iniciais
        double error;
        int[] layers = new int[]{9,12,1}; //LEYERS
        double lR;

        for(int v=0;v<=10;v++){ //LOOP PRA MOSTRAR QUAL O MELHOR RESULTADO COM UMA ACURACIA DE 0 ATE 1
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

                System.out.println("TREINANDO...");
                //loop de treino
                for(int k=0;k<10000;k++) { //deveria ter outro for modificando o k
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
                        System.out.println("Error is " + error); //mostrar os erros. com ela fica mais lento o processo

                        i++;
                    }
                    i=0;
                }
                System.out.println("Learning completed!");

                System.out.println("TESTANDO...");

                //Test
                double [][] inputT = a.getInPutTest(); //input de teste
                double[] saida = new double[a.getInPutTest().length]; // as saidas calculadas

                //obtem todas as saidas ao executar os testes
                for(i=0; i<a.getInPutTest().length;i++){
                    double [] outputT=net.execute(inputT[i]);
                    saida[i]=outputT[0];
                }
                double acuracia = build.acuracia.calculo(a.getOutPutTest(),saida); // calculo da acuracia
                System.out.println("acuracia"+v+" : "+acuracia);//print da acuracia

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
