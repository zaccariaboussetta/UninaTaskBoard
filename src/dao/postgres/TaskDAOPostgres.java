package dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.TaskDAO;
import entities.Attivita;
import entities.AttivitaDocumentazione;
import entities.AttivitaSviluppo;
import entities.Progetto;

public class TaskDAOPostgres implements TaskDAO {

	@Override
	public ArrayList<Attivita> getTasksByProgetto(Progetto progetto) {
		
		ArrayList<Attivita> listAttivita = new ArrayList<>();
		
		String queryDocu = "SELECT id_attivita_docu, descrizione, data_creazione, scadenza, "
				         + "stato_avanzamento, titolo_docu, formato, sezione, link_risorsa "
				         + "FROM attivita_documentazione "
				         + "WHERE id_progetto = ?;";
		
		String querySviluppo = "SELECT id_attivita_sviluppo, descrizione, data_creazione, scadenza, "
				             + "stato_avanzamento, tipologia_sviluppo, linguaggio_programmazione, nome_branch "
				             + "FROM attivita_sviluppo "
				             + "WHERE id_progetto = ?;";
		
		try (Connection connectionToDatabase = DatabaseConnection.getInstance()) {
			
			try (PreparedStatement pstmtDocu = connectionToDatabase.prepareStatement(queryDocu)) {
				pstmtDocu.setInt(1, progetto.getIdProgetto());
				
				try (ResultSet rsDocu = pstmtDocu.executeQuery()) {
					while(rsDocu.next()) {
						java.sql.Date sqlScadenza = rsDocu.getDate("scadenza");
						java.time.LocalDate scadenza = (sqlScadenza != null) ? sqlScadenza.toLocalDate() : null;
						
						AttivitaDocumentazione attDocu = new AttivitaDocumentazione(
								rsDocu.getInt("id_attivita_docu"),
								rsDocu.getString("descrizione"),
								rsDocu.getDate("data_creazione").toLocalDate(),
								scadenza,
								rsDocu.getString("stato_avanzamento"),
								rsDocu.getString("titolo_docu"),
								rsDocu.getString("formato"),
								rsDocu.getString("sezione"),
								rsDocu.getString("link_risorsa"),
								progetto
						);
						listAttivita.add(attDocu);
					}
				}
			}
			
			try (PreparedStatement pstmtSvilu = connectionToDatabase.prepareStatement(querySviluppo)) {
				pstmtSvilu.setInt(1, progetto.getIdProgetto());
				
				try (ResultSet rsSvilu = pstmtSvilu.executeQuery()) {
					while(rsSvilu.next()) {
						java.sql.Date sqlScadenza = rsSvilu.getDate("scadenza");
						java.time.LocalDate scadenza = (sqlScadenza != null) ? sqlScadenza.toLocalDate() : null;
						
						AttivitaSviluppo attSvilu = new AttivitaSviluppo(
								rsSvilu.getInt("id_attivita_sviluppo"),
								rsSvilu.getString("descrizione"),
								rsSvilu.getDate("data_creazione").toLocalDate(),
								scadenza,
								rsSvilu.getString("stato_avanzamento"),
								rsSvilu.getString("tipologia_sviluppo"),
								rsSvilu.getString("linguaggio_programmazione"),
								rsSvilu.getString("nome_branch"),
								progetto
						);
						listAttivita.add(attSvilu);
					}
				}
			}
			
		} catch(Exception exe) {
			exe.printStackTrace();
		}
		
		return listAttivita;
	}
}
