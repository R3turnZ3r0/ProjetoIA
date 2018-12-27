package build;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import weka.core.Instances;
import weka.core.converters.ArffLoader;;

public class ArffProcessing {
    private static ArrayList<Double> output;
    private static ArrayList<Double> input;
    private int contOut;
    private int contInt;

    public ArffProcessing(){

    }
    public static void loadArff(String path, int len) throws IOException {
        BufferedReader reader =  new BufferedReader(new FileReader(path));
        ArffLoader.ArffReader arff = new ArffLoader.ArffReader(reader);
        Instances data = arff.getData();
        data.setClassIndex(data.numAttributes() - 1);
        //System.out.println(data.instance(0).toString(9)); Da a classe do elemento
        double[][] dt= new double[data.numInstances()][10];
        for(int i=0; i<data.numInstances(); i++){
            dt[i] = data.instance(0).toDoubleArray();
        }
        for(int i=0; i<data.numInstances();i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(dt[i][j]);
                if(j==9){
                    output.add(dt[i][j]);
                }
                else{
                    input.add(dt[i][j]);
                }
            }
        }
        for(int i=0; i<input.size();i++){
            System.out.println(input.get(i));
        }
        System.out.println("\n\n");
        for(int i=0; i<input.size();i++){
            System.out.println(input.get(i));
        }

    }



}
