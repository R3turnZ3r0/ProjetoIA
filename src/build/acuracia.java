package build;

public class acuracia {

    public static double calculo(double[][] realOutput, double[] saidaOutput){
        int vp=0;
        int vn=0;
        int fp=0;
        int fn=0;
        //no recurence - sadio - 0
        // recurence - doente  - 1
        for(int i=0;i<realOutput.length;i++){
            if(Math.round(saidaOutput[i])==0 && realOutput[i][0]==0){
                vn++;
            }else if(Math.round(saidaOutput[i])==0 && realOutput[i][0]==1) {
                fn++;
            }else  if(Math.round(saidaOutput[i])==1 && realOutput[i][0]==0){

                fp++;
            }else if(Math.round(saidaOutput[i])==1 && realOutput[i][0]==1) {
                vp++;
            }

        }
        double numerador=vp+vn;
        double denominador=vp+vn+fp+fn;
        double div=(double)numerador/denominador;

        double acuracia= div *100;


        return acuracia;
    }

}
