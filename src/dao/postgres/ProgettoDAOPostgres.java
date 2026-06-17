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
	public boolean inserisiciNuovoProgettoGenerico(Progetto project) {
		
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
	public boolean inserisiciNuovoProgettoSviluppo(Progetto project) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean inserisiciNuovoProgettoEsame(Progetto project) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean inserisiciNuovoProgettoSviluppoEsame(Progetto project) {
		// TODO Auto-generated method stub
		return false;
	}

}
