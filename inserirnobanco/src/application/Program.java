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
		PreparedStatement st = null; //instancia para st ser do tipo PreparedStatement, nele tem como botar par�metros para consulta sql
		
		try {
			conn = DB.getConnection(); //faz a conexao
			
			st = conn.prepareStatement("INSERT INTO seller (Name,Email,BirthDate,BaseSalary,DepartmentId) VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS); //criar um objeto PreparedStatement, que representa uma instru��o SQL parametrizada que ser� executada em um banco de dados. usa o Statement.RETURN_GENERATED_KEYS pra depois q fizer a string total, usar.. fiz uma sobrecarga,retorna a chava gerada por autoincremento
			
			st.setString(1, "Icaro Antunes"); //trocar o primeiro ? (parametro 1) por segundo parametro de string("Icaro Antunes")
			st.setString(2, "icaro@gmail.com");
			//obs: instanciar data
			st.setDate(3, new java.sql.Date(sdf.parse("26/11/2001").getTime()));
			st.setDouble(4, 3000.000);
			st.setInt(5, 4);
			
			//executar o comando
			int rowsAffected = st.executeUpdate(); //o resultado da opera��o � um n�mero inteiro que diz quantas linhas foram alteradas
			
			//se as linhas afetadas forem maior que zero, vou mostrar a chave que foi gerada, pois se afetou linha � pq deu certo, ai mostro a generatedkey
			if(rowsAffected > 0) {
				//para q eu possa pegar o c�digo do novo registro inserido
				/*
				Essa fun��o(getGeneratedKeys) � usada para recuperar as chaves geradas (auto incrementadas) durante a execu��o de uma instru��o SQL que insere registros em uma tabela do banco de dados.
				Quando voc� insere registros em uma tabela que possui uma coluna com auto incremento, como uma coluna de ID, o banco de dados geralmente atribui um valor autom�tico a essa coluna para cada registro inserido. A fun��o getGeneratedKeys() permite que voc� obtenha esses valores gerados automaticamente.
				A linha de c�digo ResultSet rs = st.getGeneratedKeys(); cria uma vari�vel rs do tipo ResultSet e atribui a ela o objeto retornado pelo getGeneratedKeys(). Agora voc� pode usar essa vari�vel rs para manipular os valores das chaves geradas.

				A partir desse ponto, voc� pode usar os m�todos fornecidos pelo ResultSet para percorrer e acessar os valores das chaves geradas. Por exemplo, voc� pode usar o m�todo next() para mover o cursor para a pr�xima linha do ResultSet e os m�todos getInt(), getString(), entre outros, para obter os valores das colunas correspondentes �s chaves geradas.
				*/
				ResultSet rs = st.getGeneratedKeys(); //retorna um objeto do  tipo ResultSet... st � do tipo resultset e o resultset serve pra perccorer e acessar os dados retornados pela consulta... que � justamente o que eu quero fazer. 
				
				while(rs.next()) {
					int id = rs.getInt(1); //quero o valor da primeira coluna q foi inserida
					System.out.println("id do primeiro valor q acabou de ser inserido �: " + id);
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