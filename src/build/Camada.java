package build;

public class Camada
{
	public Neuronio Neuronios[];
	public int Tamanho;
	// Sao 3 camadas
	// 1 de entrada
	// 1 intermediaria - que contem x camadas
	// 1 de saida

	public Camada(int t, int ant)
	{
		Tamanho = t;
		Neuronios = new Neuronio[t];
		
		for(int j = 0; j < Tamanho; j++)
			Neuronios[j] = new Neuronio(ant);
	}
}
