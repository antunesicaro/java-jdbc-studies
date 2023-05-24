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
		
		//como vai acessar o banco, � preciso tentar
		try {
			conn = DB.getConnection(); // j� instanciou a conex�o e salva em conn
			
			st = conn.createStatement(); // cria um statment e retorna para variavel e agora podemos fazer consultas sql a partir desse m�todo
			
			rs = st.executeQuery("select * from department"); // ai recebo todo o resultado da query no rs que � do tipo ResultSet... rs � um objeto que tem a forma de tabela e por padr�o come�a na posi��o 0, que � antes de ter os dados.logo, precisamos percorrer os dados chamando a fun��o next
			
			while(rs.next()) {//enquanto rs.next for retornando valores, continuar. quando ele for false(quando n�o tem dados mais pra percorrer o m�todo automaticamente retorna o false) para 
				System.out.println(rs.getInt("Id") + "," + rs.getString("Name")); //vou percorrendo pegando os valores que est�o no banco
				
			} 
			
		}catch(SQLException e) {
			e.printStackTrace(); //imprime no console uma representa��o detalhada do rastreamento de pilha da exce��o no console. O rastreamento de pilha inclui informa��es sobre o tipo de exce��o, a linha onde ocorreu e a sequ�ncia de chamadas de m�todos que levou ao erro.


		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}