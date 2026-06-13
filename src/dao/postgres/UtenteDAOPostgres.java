package dao.postgres;
import java.sql.*;

import dao.UtenteDAO;
import entities.Utente;

public class UtenteDAOPostgres implements UtenteDAO{

	@Override
	public boolean insertUtente(Utente newUtente) {

		Connection connectionToDatabase = DatabaseConnection.getInstance();
		
		String query = "INSERT INTO utente (matricola, nome, cognome, email_istituzionale, password) VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = connectionToDatabase.prepareStatement(query);
			
			pstmt.setString(1, newUtente.getMatricola());
			pstmt.setString(2, newUtente.getNome());
			pstmt.setString(3, newUtente.getCognome());
			pstmt.setString(4, newUtente.getEmailIstituzionale());
			pstmt.setString(5, newUtente.getPassword());
			
			int numeroRigheInserite = pstmt.executeUpdate();
			
			if(numeroRigheInserite > 0) {
				return true;
			}
			
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
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
