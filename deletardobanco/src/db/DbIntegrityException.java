package db;

/*sobre a DbIntegrityException
 * Ela estende a classe RuntimeException, que � uma classe de exce��o n�o verificada em Java. Isso significa que n�o � necess�rio declarar a exce��o ou trat�-la explicitamente em blocos try-catch. Ao estender RuntimeException, essa exce��o personalizada herda todas as funcionalidades e comportamentos de uma exce��o n�o verificada.
 * */
public class DbIntegrityException extends RuntimeException { 

	
	private static final long serialVersionUID = 1L;
	
	/*
	 * sobre o construtor
	 * A classe DbIntegrityException possui um construtor p�blico que recebe uma mensagem de erro como argumento. Esse construtor chama o construtor da classe pai RuntimeException usando a palavra-chave super(msg). A mensagem de erro fornecida como argumento � repassada para o construtor da classe pai, que trata de inicializar a mensagem de erro da exce��o.

Ao lan�ar essa exce��o personalizada em algum ponto do c�digo, voc� pode passar uma mensagem espec�fica que descreva o erro ou a viola��o de integridade de dados que ocorreu no banco de dados. Essa mensagem ser� armazenada e pode ser acessada por meio do m�todo getMessage() ou exibida no console ao imprimir a pilha de exce��es.
	 * */
	public DbIntegrityException(String msg) {
		super(msg);
	}

}
