package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DB {

	//conexão com banco de dados. Obs: conectar com banco de dados no jbdc é instaciar um objeto do tipo Connection, q é o q vai ser feito quando salvar o objeto instaciado na variavel conn por meio do DriverManager
	private static Connection conn = null;//esse método é do (o do java.sql)
	
	
	//método para conectar
	public static Connection getConnection() {//retorna a conexao
		if (conn == null) {//se tiver nulo, conecta com banco
			try {
				Properties props = loadProperties();//pego as propriedades de conexao por meio da funçao loadproperties
				String url = props.getProperty("dburl");//pego url do banco de dados e salvo em uma variavel do tipo String
				
				//Para fazer a conexão, chamo uma classe também do jbdc, passo pra ele a url e as propriedades de conexão.
				conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	//método para fechar a conexão
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();//se tiver existente e instacianda a conexão, mando fechar
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
	//método privado pois vai ser usado somente aqui dentro da classe, retorna um objeto que é do tipo Properties, q foi importado
	private static Properties loadProperties() {
		//tento entrar no arquivo db.properties, leio os dados e salvo em um objeto do tipo Properties
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);//o load do objeto properties faz a leitura do arquivo q é apontado pelo fs, q no caso é o db.properties q está na raiz e guarda esses dados no objeto props
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	//método auxiliar para tratar o fechamento de statments
	
	public static void closeStatement(Statement st) {
		//se o st estiver instanciado
		if(st!= null) {
			//chamo os fechamentos
			try {
				st.close();
			} catch (SQLException e) {
				//lanço a minha exeção personalizada, passando a mensagem da sql execption
				throw new DbException(e.getMessage());
				//não é obrigado a ficar tratando toda hora com try catch, já tem aqui
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		//se o st estiver instanciado
		if(rs!= null) {
			//chamo os fechamentos
			try {
				rs.close();
			} catch (SQLException e) {
				//lanço a minha exeção personalizada, passando a mensagem da sql execption
				throw new DbException(e.getMessage());
				//não é obrigado a ficar tratando toda hora com try catch, já tem aqui
			}
		}
	}
}