package controllers;

import java.util.ArrayList;

import dao.postgres.ProgettoDAOPostgres;
import entities.Progetto;

public class ProgettoController {
	
	private ProgettoDAOPostgres projectDAO;
	
	public ProgettoController(ProgettoDAOPostgres projectDAO) {
		
		this.projectDAO = projectDAO;
		
		}
	
	public ArrayList<Progetto> getProgettiUtente(){
		
		projectDAO.getProjectsByUtente(SessionController.getInstance().getUtenteLoggato());
		
		return null;
	}
	
}
