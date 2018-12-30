package build;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MultiLayerPerceptron implements Cloneable
{
	protected double			TxAprendizado; //learning Rate
	protected Camada[]			cmds; //todas as camadas
	protected TransferFunction 	ActvateF; //Funcao de ativacao

	//Entrada: camadas, learning rate e a funcao de ativacao
	//Com isso eh criado todas as camadas e os valores
	public MultiLayerPerceptron(int[] camadas, double learningRate, TransferFunction fun)
	{
		TxAprendizado = learningRate;
		ActvateF = fun;

		cmds = new Camada[camadas.length];
		//Vai criando as camadas
		//A primeira camada nao tem anterior entao o valor anterior eh 0
		for(int i = 0; i < camadas.length; i++)
		{
			if(i != 0)
			{

				cmds[i] = new Camada(camadas[i], camadas[i - 1]);
			}
			else
			{
				cmds[i] = new Camada(camadas[i], 0);
			}
		}
	}


	//Eh entrado na funcao os inputs da primeira camada
	public double[] execute(double[] input){
		double output[] = new double[cmds[cmds.length - 1].Tamanho]; // Output eh criada um vetor do tamanho da ultima camada

		// Seta as entradas na primeira camada
		for(int i = 0; i < cmds[0].Tamanho; i++)
		{
			cmds[0].Neuronios[i].Valor = input[i];
		}

		double new_value;
		// Calcula a partir da camada 1 ate a camada X, que eh a ultima camada
		//Os calculos sao feitos na camada intermediaria, ou hidden laders, da seguinte forma  hiddens + output
		//Baseada na equacao build/eq.png
		for(int k = 1; k < cmds.length; k++)
		{
			for(int i = 0; i < cmds[k].Tamanho; i++)
			{
				new_value = 0.0;
				//Calculado o somatorio das camadas intermediarias. Cada uma delas calculara seu somatorio com o da saida anterior
				for(int j = 0; j < cmds[k - 1].Tamanho; j++) // comeca com j=0 e vai ate o tamanho da camada k-1, ou seja k=1 o cmds sera o 0 que sera seu tamanho;
					new_value += cmds[k].Neuronios[i].Pesos[j] * cmds[k - 1].Neuronios[j].Valor; // Eh selecionado o pedo do neuronio da camada e feito o somatorio


				new_value += cmds[k].Neuronios[i].Bias; //Soma o valor do somatorio com o bias
				cmds[k].Neuronios[i].Valor = ActvateF.evalute(new_value); // Usa o resultado Z para entrada da funcao de ativacao
			}
		}


		// Get output
		for(int i = 0; i < cmds[cmds.length - 1].Tamanho; i++)
		{
			output[i] = cmds[cmds.length - 1].Neuronios[i].Valor;
		}

		return output;
	}

/*
	public double backPropagateMultiThread(double[] input, double[] output, int nthread)
	{
		return 0.0;
	}
*/



	public double backPropagate(double[] input, double[] output)
	{
		double new_output[] = execute(input); //Inicializar todos os pesos da rede com pequenos valores aleatórios Fornecer
											 // dados de entrada à rede e calcular o valor da função de erro obtida,
											// ao comparar com o valor de saída esperado.
		double error; //Valor da funcao de erro
		//calcula os deltas na ida

		for(int i = 0; i < cmds[cmds.length - 1].Tamanho; i++)
		{
			error = output[i] - new_output[i]; //
			cmds[cmds.length - 1].Neuronios[i].Delta = error * ActvateF.evaluteDerivate(new_output[i]); //Cada neuronio decada
		}
		//TODO
		//MODIFICAR OS CALCULOS usando as c do professor
		//recalcula os erros na volta
		for(int k = cmds.length - 2; k >= 0; k--) //calculo feito em todas as camadas intermediarias. Retirando a primeira e a ultima camada que sao as de input e output
		{
			// Calcula o erro da camada atual e recalcula os deltas
			for(int i = 0; i < cmds[k].Tamanho; i++)
			{
				error = 0.0;
				for(int j = 0; j < cmds[k + 1].Tamanho; j++){
					error += cmds[k + 1].Neuronios[j].Delta * cmds[k + 1].Neuronios[j].Pesos[i];
				}
				cmds[k].Neuronios[i].Delta = error * ActvateF.evaluteDerivate(cmds[k].Neuronios[i].Valor); //calcula o delta
			}

			// Atualiza os pesos da próxima camada
			for(int i = 0; i < cmds[k + 1].Tamanho; i++)
			{
				for(int j = 0; j < cmds[k].Tamanho; j++)
					cmds[k + 1].Neuronios[i].Pesos[j] += TxAprendizado * cmds[k + 1].Neuronios[i].Delta *
							cmds[k].Neuronios[j].Valor;
				cmds[k + 1].Neuronios[i].Bias += TxAprendizado * cmds[k + 1].Neuronios[i].Delta;
			}
		}

		// Calcula o erro final e retorna
		error = 0.0;

		for(int i = 0; i < output.length; i++)
		{

			error += Math.abs(new_output[i] - output[i]);

			//System.out.println(output[i]+" "+new_output[i]);

		}
		error = error / output.length;
		return error;
	}

	public double getLearningRate()
	{
		return TxAprendizado;
	}

	public void	setLearningRate(double rate)
	{
		TxAprendizado = rate;
	}

	public void setTransferFunction(TransferFunction fun)
	{
		ActvateF = fun;
	}

	public int getInputLayerSize()
	{
		return cmds[0].Tamanho;
	}

	public int getOutputLayerSize()
	{
		return cmds[cmds.length - 1].Tamanho;
	}

}

