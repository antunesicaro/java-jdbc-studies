package db;

/*sobre a DbIntegrityException
 * Ela estende a classe RuntimeException, que é uma classe de exceção não verificada em Java. Isso significa que não é necessário declarar a exceção ou tratá-la explicitamente em blocos try-catch. Ao estender RuntimeException, essa exceção personalizada herda todas as funcionalidades e comportamentos de uma exceção não verificada.
 * */
public class DbIntegrityException extends RuntimeException { 

	
	private static final long serialVersionUID = 1L;
	
	/*
	 * sobre o construtor
	 * A classe DbIntegrityException possui um construtor público que recebe uma mensagem de erro como argumento. Esse construtor chama o construtor da classe pai RuntimeException usando a palavra-chave super(msg). A mensagem de erro fornecida como argumento é repassada para o construtor da classe pai, que trata de inicializar a mensagem de erro da exceção.

Ao lançar essa exceção personalizada em algum ponto do código, você pode passar uma mensagem específica que descreva o erro ou a violação de integridade de dados que ocorreu no banco de dados. Essa mensagem será armazenada e pode ser acessada por meio do método getMessage() ou exibida no console ao imprimir a pilha de exceções.
	 * */
	public DbIntegrityException(String msg) {
		super(msg);
	}

}
