package dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.ProgettoDAO;
import entities.PreparazioneEsami;
import entities.Progetto;
import entities.ProgettoEsameSviluppo;
import entities.SviluppoApplicativi;
import entities.Utente;

public class ProgettoDAOPostgres implements ProgettoDAO{

	@Override
	public ArrayList<Progetto> getProjectsByUtente(Utente utente) {
		
		ArrayList<Progetto> listProgetti = new ArrayList();
		
		Connection connectionToDatabase = DatabaseConnection.getInstance();
		
		String query = "SELECT progetto.id_progetto, nome, descrizione, data_creazione, data_consegna"
				+ " FROM progetto JOIN membro ON progetto.id_progetto = membro.id_progetto"
				+ " WHERE membro.matricola = '" + utente.getMatricola() +"';";
		
		try {
			PreparedStatement pstmt = connectionToDatabase.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Progetto utenteProgetto = new Progetto(
						rs.getInt("id_progetto"),
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
	public Progetto getProjectById(int id) {
	    
	    Connection connectionToDatabase = DatabaseConnection.getInstance();
	    Progetto progettoTrovato = null;
	    
	    String query = "SELECT p.nome, p.descrizione, p.data_creazione, p.data_consegna, p.is_progetto_gruppo, p.matricola, "
	                 + "pe.codice_esame, pe.nome_esame, pe.cfu, pe.docente, pe.data_appello, "
	                 + "sa.repository_url, sa.tech_stack, sa.versione "
	                 + "FROM progetto p "
	                 + "LEFT JOIN preparazione_esami pe ON p.id_progetto = pe.id_progetto "
	                 + "LEFT JOIN sviluppo_applicativi sa ON p.id_progetto = sa.id_progetto "
	                 + "WHERE p.id_progetto = ?";
	                 
	    try {
	        PreparedStatement pstmt = connectionToDatabase.prepareStatement(query);
	        pstmt.setInt(1, id);
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	        
	        	
	            String nome = rs.getString("nome");
	            String descrizione = rs.getString("descrizione");
	            LocalDate dataCreazione = rs.getDate("data_creazione").toLocalDate();
	            LocalDate dataConsegna = rs.getDate("data_consegna").toLocalDate();
	            boolean isProgettoGruppo = rs.getBoolean("is_progetto_gruppo");
	            
	            String matricola = rs.getString("matricola");
	            
	            

	            boolean isEsame = rs.getString("codice_esame") != null;
	            boolean isSviluppo = rs.getString("repository_url") != null;
	            
	            if (isEsame && isSviluppo) {
	                
	                String codiceEsame = rs.getString("codice_esame");
	                String nomeEsame = rs.getString("nome_esame");
	                int cfu = rs.getInt("cfu");
	                String docente = rs.getString("docente");
	                LocalDate dataAppello = rs.getDate("data_appello") != null ? rs.getDate("data_appello").toLocalDate() : null;
	                
	                String repoUrl = rs.getString("repository_url");
	                String techStack = rs.getString("tech_stack");
	                String versione = rs.getString("versione");

	                progettoTrovato = new ProgettoEsameSviluppo(
		                    id, nome, descrizione, dataCreazione, dataConsegna, isProgettoGruppo, matricola,
		                    codiceEsame, nomeEsame, cfu, docente, dataAppello,
		                    repoUrl, techStack, versione
		                );
	                
	            } else if (isEsame) {
	                
	                String codiceEsame = rs.getString("codice_esame");
	                String nomeEsame = rs.getString("nome_esame");
	                int cfu = rs.getInt("cfu");
	                String docente = rs.getString("docente");
	                LocalDate dataAppello = rs.getDate("data_appello") != null ? rs.getDate("data_appello").toLocalDate() : null;

	                progettoTrovato = new PreparazioneEsami(
	                    id, nome, descrizione, dataCreazione, dataConsegna, isProgettoGruppo,
	                    matricola, codiceEsame, nomeEsame, cfu, docente, dataAppello
	                );
	                
	            } else if (isSviluppo) {
	                
	                String repoUrl = rs.getString("repository_url");
	                String techStack = rs.getString("tech_stack");
	                String versione = rs.getString("versione");

	                progettoTrovato = new SviluppoApplicativi(
	                    id, nome, descrizione, dataCreazione, dataConsegna, isProgettoGruppo,
	                    matricola, repoUrl, techStack, versione
	                );
	                
	            } else {
	                
	                progettoTrovato = new Progetto(
	                    id, nome, descrizione, dataCreazione, dataConsegna, isProgettoGruppo, matricola
	                );
	                
	            }
	            
	            if (progettoTrovato != null) {
	                progettoTrovato.setIdProgetto(id);
	                progettoTrovato.setDataCreazione(dataCreazione);
	            }
	        }
	        
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return progettoTrovato;
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

