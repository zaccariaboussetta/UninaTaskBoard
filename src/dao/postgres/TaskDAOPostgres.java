package dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.TaskDAO;
import entities.Attivita;
import entities.AttivitaDocumentazione;
import entities.AttivitaSviluppo;
import entities.Membro;
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

	public ArrayList<Attivita> getTasksAssignedTo(Membro membro) {
	    
	    ArrayList<Attivita> listAttivita = new ArrayList<>();
	    
	    String queryDocu = "SELECT ad.id_attivita_docu, ad.descrizione, ad.data_creazione, ad.scadenza, "
	                     + "ad.stato_avanzamento, ad.titolo_docu, ad.formato, ad.sezione, ad.link_risorsa "
	                     + "FROM attivita_documentazione ad "
	                     + "JOIN assegnazione_docu asd ON ad.id_attivita_docu = asd.id_attivita_docu "
	                     + "WHERE asd.matricola = ? AND asd.id_progetto = ?;";
	    
	    String querySviluppo = "SELECT asv.id_attivita_sviluppo, asv.descrizione, asv.data_creazione, asv.scadenza, "
	                         + "asv.stato_avanzamento, asv.tipologia_sviluppo, asv.linguaggio_programmazione, asv.nome_branch "
	                         + "FROM attivita_sviluppo asv "
	                         + "JOIN assegnazione_sviluppo ass ON asv.id_attivita_sviluppo = ass.id_attivita_sviluppo "
	                         + "WHERE ass.matricola = ? AND ass.id_progetto = ?;";
	    
	    try (Connection connectionToDatabase = DatabaseConnection.getInstance()) {
	        
	        try (PreparedStatement pstmtDocu = connectionToDatabase.prepareStatement(queryDocu)) {
	            pstmtDocu.setString(1, membro.getUtente().getMatricola());
	            pstmtDocu.setInt(2, membro.getProgetto().getIdProgetto());
	            
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
	                            membro.getProgetto()
	                    );
	                    listAttivita.add(attDocu);
	                }
	            }
	        }
	        
	        try (PreparedStatement pstmtSvilu = connectionToDatabase.prepareStatement(querySviluppo)) {
	            pstmtSvilu.setString(1, membro.getUtente().getMatricola());
	            pstmtSvilu.setInt(2, membro.getProgetto().getIdProgetto());
	            
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
	                            membro.getProgetto()
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

	@Override
	public void updateTaskStatus(Progetto progetto, Attivita task, String status) {
		
		if (status.equalsIgnoreCase("in_progress")) {
			status = "In_progress";
		}
		
		String query = "";
		
		if (task instanceof AttivitaSviluppo) {
			query = "UPDATE attivita_sviluppo SET stato_avanzamento = ? WHERE id_attivita_sviluppo = ?";
		} else if (task instanceof AttivitaDocumentazione) {
			query = "UPDATE attivita_documentazione SET stato_avanzamento = ? WHERE id_attivita_docu = ?";
		} else {
			System.err.println("Errore: Tipologia di task sconosciuta.");
			return;
		}
		
		try (Connection connectionToDatabase = DatabaseConnection.getInstance();
		     PreparedStatement pstmt = connectionToDatabase.prepareStatement(query)) {
			
			pstmt.setString(1, status);
			pstmt.setInt(2, task.getIdAttivita());
			pstmt.executeUpdate();
			task.setStatoAvanzamento(status);
			
		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}

	@Override
	public void assignTaskTo(Membro membro, Progetto progetto, Attivita task) {
		
		String query = "";
		
		if (task instanceof AttivitaSviluppo) {
			query = "INSERT INTO assegnazione_sviluppo (id_attivita_sviluppo, matricola, id_progetto) VALUES (?, ?, ?)";
		} else if (task instanceof AttivitaDocumentazione) {
			query = "INSERT INTO assegnazione_docu (id_attivita_docu, matricola, id_progetto) VALUES (?, ?, ?)";
		} else {
			System.err.println("Errore: Tipologia di task sconosciuta.");
			return;
		}
		
		try (Connection connectionToDatabase = DatabaseConnection.getInstance();
		     PreparedStatement pstmt = connectionToDatabase.prepareStatement(query)) {
			
			pstmt.setInt(1, task.getIdAttivita());
			pstmt.setString(2, membro.getUtente().getMatricola());
			pstmt.setInt(3, progetto.getIdProgetto());
			
			pstmt.executeUpdate();
			
		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}
	
	public int inserisciNuovaAttivita(Attivita task, Progetto progetto) {
		int generatedId = -1;
		String query = "";
		
		if (task instanceof AttivitaSviluppo) {
			query = "INSERT INTO attivita_sviluppo (descrizione, data_creazione, scadenza, stato_avanzamento, tipologia_sviluppo, linguaggio_programmazione, nome_branch, id_progetto) VALUES (?, CURRENT_DATE, ?, 'Todo', ?, ?, ?, ?)";
		} else if (task instanceof AttivitaDocumentazione) {
			query = "INSERT INTO attivita_documentazione (descrizione, data_creazione, scadenza, stato_avanzamento, titolo_docu, formato, sezione, link_risorsa, id_progetto) VALUES (?, CURRENT_DATE, ?, 'Todo', ?, ?, ?, ?, ?)";
		} else {
			return generatedId;
		}
		
		try (Connection connectionToDatabase = DatabaseConnection.getInstance();
		     PreparedStatement pstmt = connectionToDatabase.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS)) {
			
			pstmt.setString(1, task.getDescrizione());
			pstmt.setDate(2, java.sql.Date.valueOf(task.getScadenza()));
			
			if (task instanceof AttivitaSviluppo) {
				AttivitaSviluppo as = (AttivitaSviluppo) task;
				pstmt.setString(3, as.getTipologiaSviluppo());
				pstmt.setString(4, as.getLinguaggioProgrammazione());
				pstmt.setString(5, as.getNomeBranch());
				pstmt.setInt(6, progetto.getIdProgetto());
			} else {
				AttivitaDocumentazione ad = (AttivitaDocumentazione) task;
				pstmt.setString(3, ad.getTitoloDocu());
				pstmt.setString(4, ad.getFormato());
				pstmt.setString(5, ad.getSezione());
				pstmt.setString(6, ad.getLinkRisorsa());
				pstmt.setInt(7, progetto.getIdProgetto());
			}
			
			int affectedRows = pstmt.executeUpdate();
			
			if (affectedRows > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						generatedId = rs.getInt(1);
						task.setIdAttivita(generatedId);
					}
				}
			}
			
		} catch (Exception exe) {
			exe.printStackTrace();
		}
		
		return generatedId;
	}

}

