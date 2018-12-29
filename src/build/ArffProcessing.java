package build;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import weka.core.Instances;
import weka.core.converters.ArffLoader;;

public class ArffProcessing {
    //private static ArrayList<Double> output;
    private static double[][] output;
    private static double[][] input;

    public double[][] getOutput() {
        return output;
    }

    public double[][] getInput() {
        return input;
    }

    public static void loadArff(String path) throws IOException {
        BufferedReader reader =  new BufferedReader(new FileReader(path));
        ArffLoader.ArffReader arff = new ArffLoader.ArffReader(reader);
        Instances data = arff.getData();
        data.setClassIndex(data.numAttributes() - 1);
        //System.out.println(data.instance(0).toString(9)); Da a classe do elemento
        double[][] dt= new double[data.numInstances()][10];
        for(int i=0; i<data.numInstances(); i++){
            dt[i] = data.instance(i).toDoubleArray();
        }
        output=new double[data.numInstances()][1];
        input=new double[data.numInstances()][9];

        double [] inputt= new double[9];
        for(int i=0; i<data.numInstances();i++) {
            for (int j = 0; j <10 ; j++) {
                if (j == 9) {
                    output[i][0] = dt[i][j];
                    break;
                } else {
                    inputt[j] = dt[i][j];
                }
            }
            input[i]= Arrays.copyOf(inputt,inputt.length);
        }

    }



}

