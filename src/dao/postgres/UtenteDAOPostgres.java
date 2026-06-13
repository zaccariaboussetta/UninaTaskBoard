package dao.postgres;
import java.sql.*;

import dao.UtenteDAO;
import entities.Utente;

public class UtenteDAOPostgres implements UtenteDAO{

	@Override
	public boolean insertUtente(Utente newUtente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Utente getUtenteByEmail(String email) {

		Connection connectionToDatabase = DatabaseConnection.getInstance();
		
		//Qui c'un bel esempio di vulnerabilità da SQL Injection. Lo teniamo perché interessante didatticamente.
		String query = "SELECT * FROM utente WHERE email_istituzionale = '" + email + "';";
		
		try {
			PreparedStatement pstmt = connectionToDatabase.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return new Utente(
						rs.getString("nome"),
						rs.getString("cognome"),
						rs.getString("matricola"),
						rs.getString("email_istituzionale"),
						rs.getString("password"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}

}
