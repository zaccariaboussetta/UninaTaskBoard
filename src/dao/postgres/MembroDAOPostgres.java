package dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.MembroDAO;
import entities.Membro;
import entities.Progetto;
import entities.Utente;

public class MembroDAOPostgres implements MembroDAO {

	@Override
	public ArrayList<Membro> getMembriByProgetto(Progetto progetto) {
		
		ArrayList<Membro> listMembri = new ArrayList<>();
		
		String query = "SELECT m.ruolo, m.data_adesione, m.stato_partecipazione, m.ore_lavoro, "
				     + "u.matricola, u.nome, u.cognome "
				     + "FROM membro m JOIN utente u ON m.matricola = u.matricola "
				     + "WHERE m.id_progetto = ?;";
		
		try (Connection connectionToDatabase = DatabaseConnection.getInstance();
		     PreparedStatement pstmt = connectionToDatabase.prepareStatement(query)) {
			
			pstmt.setInt(1, progetto.getIdProgetto());
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					Utente utente = new Utente(
							rs.getString("nome"),
							rs.getString("cognome"),
							rs.getString("matricola")
					);
					
					Membro membro = new Membro(
							rs.getString("ruolo"),
							rs.getDate("data_adesione").toLocalDate(),
							rs.getString("stato_partecipazione"),
							rs.getFloat("ore_lavoro"),
							progetto,
							utente
					);
					
					listMembri.add(membro);
				}
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return listMembri;
	}
}
