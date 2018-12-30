### Autor: Giuseppe Fiorentino Neto

## Descrição:
Holiday Challenges
Ligue-me

Ligue-me mais tarde:
(166) 43 2 1664 3232

 1. Na chall há um arquivo anexado chamado **71e08995-d62b-40e4-b24a-f6ace32e7a4c.txt**

 2. Ao lermos este arquivo, percebemos que este encontra-se criptografado, e em primeira vista parece que encontra-se criptografado em base 16.

 3. Antes de baixar o arquivo, devemos analisar o que a chall nos disponibiliza como informações, que é um suposto número de telefone que devemos ligar
	> Analisando este número percebemos que, juntando em pares, obtemos uma sequência de bases criptograficas.
	
		16 64 32 16 64 32 32
 4. Seguinto esta sequência obtemos um assim um número que de cara não parece esta mais criptografado.
	> 5397237466347843267379682737335464

 5. Se continuarmos analisando o número percebemos que este se encontra dentre um range de [2,9] o que parece muito com o range dos antigos teclados dos celulares. Mantendo essa linha de raciocínio e estudando um pouco mais sobre os antigos telefones celulares nos deparamos com uma tecnologia de predição de textos o T9. 
	> O objetivo do T9 era facilitar a entrada de mensagens de texto. Permitindo que uma mensagem fosse formada ao pressionar uma única tecla.
	
		https://en.wikipedia.org/wiki/T9_(predictive_text)

 6. Pesquisando sobre T9 na internet encontramos varios emuladores. 
 	> Dentre eles há a ferramenta **DCODE**, que possui uma vasta gama de ferramentas na área de criptografia
	
	https://www.dcode.fr/t9-cipher  
	https://www.sainsmograf.com/labs/t9-emulator/
 7. Ao utilizar o *t9-emulator* vamos digitando cada letra e percebemos que aos poucos vão sendo formado palavras, e clicando em cycle obtemos uma gama de possiblidades para um conjunto escrito, e devemos anotar todas as possíveis saídas para que ao final possamos encontrar nossa resposta.
	> Temos como exemplo as saídas ao digitar 539
		jew
		lew
		kex
		key
8. Após digitarmos e analisarmos obtemos a mensagem
	> keypadphoneisthe*****thatyouareseeking
	
		Devemos atentar pelo fato de que há um erro de ortografia e ao invés de **ANSWER** temos **ANSER**,
		por isso mesmo coloquei um '*', porém não impede de lermos a mensagem e entendermos qual a nossa key.

9. Após encontrarmos nossa key vamos ao shellter e digitamos a flag shellter{keypadphone}
