package build;

//Baseado no modelo de equacao /build/Modelocomputacionaldeumneuronio
public class Neuronio
{
	public double		Valor;
	public double[]		Pesos;
	public double		Bias;
	public double		Delta; //
	/*

	 */
	public Neuronio(int LenCamadaAnt)
	{
		Pesos = new double[LenCamadaAnt]; //Precisa da tamanho da camada anterior pq a saida da camada anterior eh a entrada da camada atual
		Bias = Math.random();
		Delta = Math.random();
		Valor = Math.random();
		
		for(int i = 0; i < Pesos.length; i++)
			Pesos[i] = Math.random(); // Pesos para cada uma das entradas
	}
}
