package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null; //instancia para st ser do tipo PreparedStatement, nele tem como botar parâmetros para consulta sql
		
		try {
			conn = DB.getConnection(); //faz a conexao
			
			st = conn.prepareStatement("INSERT INTO seller (Name,Email,BirthDate,BaseSalary,DepartmentId) VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS); //criar um objeto PreparedStatement, que representa uma instrução SQL parametrizada que será executada em um banco de dados. usa o Statement.RETURN_GENERATED_KEYS pra depois q fizer a string total, usar.. fiz uma sobrecarga,retorna a chava gerada por autoincremento
			
			st.setString(1, "Icaro Antunes"); //trocar o primeiro ? (parametro 1) por segundo parametro de string("Icaro Antunes")
			st.setString(2, "icaro@gmail.com");
			//obs: instanciar data
			st.setDate(3, new java.sql.Date(sdf.parse("26/11/2001").getTime()));
			st.setDouble(4, 3000.000);
			st.setInt(5, 4);
			
			//executar o comando
			int rowsAffected = st.executeUpdate(); //o resultado da operação é um número inteiro que diz quantas linhas foram alteradas
			
			//se as linhas afetadas forem maior que zero, vou mostrar a chave que foi gerada, pois se afetou linha é pq deu certo, ai mostro a generatedkey
			if(rowsAffected > 0) {
				//para q eu possa pegar o código do novo registro inserido
				/*
				Essa função(getGeneratedKeys) é usada para recuperar as chaves geradas (auto incrementadas) durante a execução de uma instrução SQL que insere registros em uma tabela do banco de dados.
				Quando você insere registros em uma tabela que possui uma coluna com auto incremento, como uma coluna de ID, o banco de dados geralmente atribui um valor automático a essa coluna para cada registro inserido. A função getGeneratedKeys() permite que você obtenha esses valores gerados automaticamente.
				A linha de código ResultSet rs = st.getGeneratedKeys(); cria uma variável rs do tipo ResultSet e atribui a ela o objeto retornado pelo getGeneratedKeys(). Agora você pode usar essa variável rs para manipular os valores das chaves geradas.

				A partir desse ponto, você pode usar os métodos fornecidos pelo ResultSet para percorrer e acessar os valores das chaves geradas. Por exemplo, você pode usar o método next() para mover o cursor para a próxima linha do ResultSet e os métodos getInt(), getString(), entre outros, para obter os valores das colunas correspondentes às chaves geradas.
				*/
				ResultSet rs = st.getGeneratedKeys(); //retorna um objeto do  tipo ResultSet... st é do tipo resultset e o resultset serve pra perccorer e acessar os dados retornados pela consulta... que é justamente o que eu quero fazer. 
				
				while(rs.next()) {
					int id = rs.getInt(1); //quero o valor da primeira coluna q foi inserida
					System.out.println("id do primeiro valor q acabou de ser inserido é: " + id);
				}
			}else {
				System.out.println("nada foi alterado");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}