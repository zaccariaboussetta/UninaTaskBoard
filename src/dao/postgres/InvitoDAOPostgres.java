package dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.InvitoDAO;
import entities.Invito;
import entities.Utente;

public class InvitoDAOPostgres implements InvitoDAO {

	@Override
	public ArrayList<Invito> getinvitiByUtente(Utente utente) {
		
		Connection conectionDb = DatabaseConnection.getInstance();
		
		ArrayList<Invito> listInviti = new ArrayList();
		
		String query = "SELECT matricola_admin, id_progetto"
				+ "FROM invito"
				+ "WHERE matricola_invitato = "  + utente.getMatricola();
		
		try {
			PreparedStatement pstmt = conectionDb.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				Invito invito = new Invito(
						rs.getString("matricola_admin"),
						rs.getInt("id_progetto"));
				
				listInviti.add(invito);
			}
		
			return listInviti;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
