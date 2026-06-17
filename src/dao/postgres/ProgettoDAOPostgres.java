package dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ProgettoDAO;
import entities.PreparazioneEsami;
import entities.Progetto;
import entities.SviluppoApplicativi;
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
	public boolean inserisiciNuovoProgetto(Progetto project) {
		
		Connection connectionToDatabase = DatabaseConnection.getInstance();
		
		String query = "INSERT INTO progetto(nome, descrizione, data_consegna, is_progetto_gruppo, matricola)"
				+ " VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = connectionToDatabase.prepareStatement(query);
			
			pstmt.setString(1, project.getNome());
			pstmt.setString(2, project.getDescrizione());
			pstmt.setObject(3, project.getDataConsegna());
			pstmt.setBoolean(4, project.isProgettoGruppo());
			pstmt.setString(5, project.getUserAdmin().getMatricola());
			
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
	public boolean inserisiciNuovoProgetto(SviluppoApplicativi project) {
	    
	    Connection connectionToDatabase = DatabaseConnection.getInstance();
	    
	    String queryPadre = "INSERT INTO progetto(nome, descrizione, data_consegna, is_progetto_gruppo, matricola) VALUES (?, ?, ?, ?, ?)";
	    
	    String queryFiglia = "INSERT INTO sviluppo_applicativi(repository_url, tech_stack, versione, id_progetto) VALUES (?, ?, ?, ?)";
	    
	    try {
	        connectionToDatabase.setAutoCommit(false);
	        
	        try (PreparedStatement pstmtPadre = connectionToDatabase.prepareStatement(queryPadre, Statement.RETURN_GENERATED_KEYS)) {
	            
	            pstmtPadre.setString(1, project.getNome());
	            pstmtPadre.setString(2, project.getDescrizione());
	            pstmtPadre.setObject(3, project.getDataConsegna());
	            pstmtPadre.setBoolean(4, project.isProgettoGruppo());
	            pstmtPadre.setString(5, project.getUserAdmin().getMatricola());
	            
	            int righePadre = pstmtPadre.executeUpdate();
	            
	            if (righePadre > 0) {
	                try (ResultSet generatedKeys = pstmtPadre.getGeneratedKeys()) {
	                    if (generatedKeys.next()) {
	                        int idProgettoGenerato = generatedKeys.getInt(1); 
	                        
	                        try (PreparedStatement pstmtFiglio = connectionToDatabase.prepareStatement(queryFiglia)) {
	                            
	                            pstmtFiglio.setString(1, project.getRepositoryURL());
	                            pstmtFiglio.setString(2, project.getTechStack());
	                            pstmtFiglio.setString(3, project.getVersione());
	                            pstmtFiglio.setInt(4, idProgettoGenerato);
	                            
	                            pstmtFiglio.executeUpdate();
	                        }
	                    }
	                }
	            }
	        }
	        
	        connectionToDatabase.commit();
	        return true;
	        
	    } catch (SQLException e) {
	        try {
	            if (connectionToDatabase != null) {
	                connectionToDatabase.rollback();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            connectionToDatabase.setAutoCommit(true);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	@Override
	public boolean inserisiciNuovoProgetto(PreparazioneEsami project) {
	    
	    Connection connectionToDatabase = DatabaseConnection.getInstance();
	    
	    String queryPadre = "INSERT INTO progetto(nome, descrizione, data_consegna, is_progetto_gruppo, matricola) VALUES (?, ?, ?, ?, ?)";
	    
	    String queryFiglia = "INSERT INTO preparazione_esame(codice_esame, nome_esame, cfu, docente, data_appello, id_progetto) VALUES (?, ?, ?, ?, ?, ?)";
	    
	    try {
	        
	        connectionToDatabase.setAutoCommit(false);
	        
	        
	        try (PreparedStatement pstmtPadre = connectionToDatabase.prepareStatement(queryPadre, Statement.RETURN_GENERATED_KEYS)) {
	            
	            pstmtPadre.setString(1, project.getNome());
	            pstmtPadre.setString(2, project.getDescrizione());
	            pstmtPadre.setObject(3, project.getDataConsegna());
	            pstmtPadre.setBoolean(4, project.isProgettoGruppo());
	            pstmtPadre.setString(5, project.getUserAdmin().getMatricola());
	            
	            int righePadre = pstmtPadre.executeUpdate();
	            
	            
	            if (righePadre > 0) {
	                try (ResultSet generatedKeys = pstmtPadre.getGeneratedKeys()) {
	                    if (generatedKeys.next()) {
	                        int idProgettoGenerato = generatedKeys.getInt(1); 
	                        
	                       
	                        try (PreparedStatement pstmtFiglio = connectionToDatabase.prepareStatement(queryFiglia)) {
	                            
	                        	pstmtFiglio.setString(1, project.getCodiceEsame());
	                            pstmtFiglio.setString(2, project.getNomeEsame()); 
	                            pstmtFiglio.setInt(3, project.getCfu());
	                            pstmtFiglio.setString(4, project.getDocente());
	                            pstmtFiglio.setObject(5, project.getDataAppello()); 
	                            pstmtFiglio.setInt(6, idProgettoGenerato);
	                            
	                            pstmtFiglio.executeUpdate();
	                        }
	                    }
	                }
	            }
	        }
	        
	        
	        connectionToDatabase.commit();
	        return true;
	        
	    } catch (SQLException e) {
	        
	        try {
	            if (connectionToDatabase != null) {
	                connectionToDatabase.rollback();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        
	        try {
	            connectionToDatabase.setAutoCommit(true);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	@Override
	public boolean inserisiciNuovoProgetto(PreparazioneEsami progettoEsame, SviluppoApplicativi progettoSviluppo) {
		Connection connectionToDatabase = DatabaseConnection.getInstance();
		String queryPadre = "INSERT INTO progetto(nome, descrizione, data_consegna, is_progetto_gruppo, matricola) VALUES (?, ?, ?, ?, ?)";
		String queryEsame = "INSERT INTO preparazione_esame(codice_esame, nome_esame, cfu, docente, data_appello, id_progetto) VALUES (?, ?, ?, ?, ?, ?)";
		String querySviluppo = "INSERT INTO sviluppo_applicativi(repository_url, tech_stack, versione, id_progetto) VALUES (?, ?, ?, ?)";
		
		try {
			connectionToDatabase.setAutoCommit(false);
			
			try (PreparedStatement pstmtPadre = connectionToDatabase.prepareStatement(queryPadre, Statement.RETURN_GENERATED_KEYS)) {
				pstmtPadre.setString(1, progettoEsame.getNome());
				pstmtPadre.setString(2, progettoEsame.getDescrizione());
				pstmtPadre.setObject(3, progettoEsame.getDataConsegna());
				pstmtPadre.setBoolean(4, progettoEsame.isProgettoGruppo());
				pstmtPadre.setString(5, progettoEsame.getUserAdmin().getMatricola());
				
				if (pstmtPadre.executeUpdate() > 0) {
					try (ResultSet generatedKeys = pstmtPadre.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							int idProgettoGenerato = generatedKeys.getInt(1); 
							
							try (PreparedStatement pstmtEsame = connectionToDatabase.prepareStatement(queryEsame)) {
								pstmtEsame.setString(1, progettoEsame.getCodiceEsame());
								pstmtEsame.setString(2, progettoEsame.getNomeEsame()); 
								pstmtEsame.setInt(3, progettoEsame.getCfu());
								pstmtEsame.setString(4, progettoEsame.getDocente());
								pstmtEsame.setObject(5, progettoEsame.getDataAppello()); 
								pstmtEsame.setInt(6, idProgettoGenerato); 
								
								pstmtEsame.executeUpdate();
							}
							
							try (PreparedStatement pstmtSviluppo = connectionToDatabase.prepareStatement(querySviluppo)) {
								pstmtSviluppo.setString(1, progettoSviluppo.getRepositoryURL());
								pstmtSviluppo.setString(2, progettoSviluppo.getTechStack());
								pstmtSviluppo.setString(3, progettoSviluppo.getVersione());
								pstmtSviluppo.setInt(4, idProgettoGenerato); 
								
								pstmtSviluppo.executeUpdate();
							}
						}
					}
				}
			}
			
			connectionToDatabase.commit();
			return true;
			
		} catch (SQLException e) {
			try {
				if (connectionToDatabase != null) {
					connectionToDatabase.rollback();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			return false;
			
		} finally {
			try {
				connectionToDatabase.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
