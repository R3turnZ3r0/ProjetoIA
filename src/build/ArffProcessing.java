package build;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class ArffProcessing {
    private static double[][] output;
    private static double[][] input;
    private static double[][] inPutTrain;
    private static double[][] inPutTest;
    private static double[][] outPutTrain;
    private static double[][] outPutTest;
    private static int numCloumnG;



    //Divide output e input em treino e teste
    public static void mountProblem(){
        int sixtyprcnt = (int) Math.round(input.length*0.6);
        int fourtyprcnt= (int) Math.round(input.length*0.4);

        inPutTrain=new double[sixtyprcnt][numCloumnG-1];
        inPutTest=new double[fourtyprcnt][numCloumnG-1];

        outPutTrain=new double[sixtyprcnt][numCloumnG-1];
        outPutTest=new double[fourtyprcnt][numCloumnG-1];

        int s=0;
        int f=0;
        for(int i=0; i<input.length;i++) {
            if(i<sixtyprcnt){
                    inPutTrain[s] = Arrays.copyOf(input[i],input[i].length);
                    outPutTrain[s] = Arrays.copyOf(output[i],output[i].length);
                    s++;
            }
            else {
                    inPutTest[f] = Arrays.copyOf(input[i],input[i].length);
                    outPutTest[f] = Arrays.copyOf(output[i],output[i].length);
                    f++;
            }
        }


    }

    public static double[][] getInPutTrain() {
        return inPutTrain;
    }

    public static double[][] getInPutTest() {
        return inPutTest;
    }

    public static double[][] getOutPutTrain() {
        return outPutTrain;
    }

    public static double[][] getOutPutTest() {
        return outPutTest;
    }

    public static void loadArff(String path, int numCloumn) throws IOException {
        numCloumnG=numCloumn;

        BufferedReader reader =  new BufferedReader(new FileReader(path));
        ArffLoader.ArffReader arff = new ArffLoader.ArffReader(reader);
        Instances data = arff.getData();
        data.setClassIndex(data.numAttributes() - 1);
        double[][] dt= new double[data.numInstances()][numCloumn];
        for(int i=0; i<data.numInstances(); i++){
            dt[i] = data.instance(i).toDoubleArray();
        }
        output=new double[data.numInstances()][1];
        input=new double[data.numInstances()][numCloumn-1];


        double [] inputt= new double[numCloumn-1];
        for(int i=0; i<data.numInstances();i++) {
            for (int j = 0; j < numCloumn ; j++) {
                if (j == numCloumn-1) {
                    output[i][0] = dt[i][j];
                    break;
                } else {

                    if(Double.isNaN(dt[i][j])) {
                        inputt[j] = 0.0;
                    }
                    else
                        inputt[j] = dt[i][j];
                }
            }
            input[i]= Arrays.copyOf(inputt,inputt.length);
        }
        mountProblem();

    }



}

