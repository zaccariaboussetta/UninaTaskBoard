package dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProgettoDAO;
import entities.Progetto;
import entities.Utente;

public class ProgettoDAOPostgres implements ProgettoDAO{

	@Override
	public ArrayList<Progetto> getProjectsByUtente(Utente utente) {
		
		ArrayList<Progetto> listProgetti = new ArrayList();
		
		Connection connectionToDatabase = DatabaseConnection.getInstance();
		
		String query = "SELECT nome, descrizione, data_creazione, data_consegna"
				+ " FROM progetto JOIN membro ON progetto.id_progetto = membro.id_progetto"
				+ " WHERE membro.matricola = '" + utente.getMatricola() +"';";
		
		try {
			PreparedStatement pstmt = connectionToDatabase.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Progetto utenteProgetto = new Progetto(
						rs.getString("nome"),
						rs.getString("descrizione"),
						rs.getDate("data_creazione").toLocalDate(),
						rs.getDate("data_consegna").toLocalDate()
						);
				
				listProgetti.add(utenteProgetto);
				
			}
			
			return listProgetti;
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public void insertProject(Progetto project) {
		// TODO Auto-generated method stub
		
	}

}
