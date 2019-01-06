package build;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class ArffProcessing {
    private static double[][] output;
    private static double[][] input;

    public double[][] getOutput() {
        return output;
    }

    public double[][] getInput() {
        return input;
    }

    public static void loadArff(String path, int numCloumn) throws IOException {
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

    }



}

