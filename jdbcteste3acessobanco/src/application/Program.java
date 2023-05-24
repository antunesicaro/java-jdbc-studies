package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		//como vai acessar o banco, é preciso tentar
		try {
			conn = DB.getConnection(); // já instanciou a conexão e salva em conn
			
			st = conn.createStatement(); // cria um statment e retorna para variavel e agora podemos fazer consultas sql a partir desse método
			
			rs = st.executeQuery("select * from department"); // ai recebo todo o resultado da query no rs que é do tipo ResultSet... rs é um objeto que tem a forma de tabela e por padrão começa na posição 0, que é antes de ter os dados.logo, precisamos percorrer os dados chamando a função next
			
			while(rs.next()) {//enquanto rs.next for retornando valores, continuar. quando ele for false(quando não tem dados mais pra percorrer o método automaticamente retorna o false) para 
				System.out.println(rs.getInt("Id") + "," + rs.getString("Name")); //vou percorrendo pegando os valores que estão no banco
				
			} 
			
		}catch(SQLException e) {
			e.printStackTrace(); //imprime no console uma representação detalhada do rastreamento de pilha da exceção no console. O rastreamento de pilha inclui informações sobre o tipo de exceção, a linha onde ocorreu e a sequência de chamadas de métodos que levou ao erro.


		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}