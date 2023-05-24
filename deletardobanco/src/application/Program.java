package application;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {
	
	//atualizar sal�rio do vendendor
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			
			st.setInt(1, 2);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println(rowsAffected);
			
		}
		catch(SQLException e) { 
			//e.printStackTrace();
			//caso d� erro, vou lan�ar a inha exception personalizada
			throw new DbIntegrityException(e.getMessage()); //captura de l� a messagem que est� no m�todo.
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}