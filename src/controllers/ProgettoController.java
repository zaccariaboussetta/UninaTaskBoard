package controllers;

import java.util.ArrayList;

import dao.ProgettoDAO;
import entities.Progetto;

public class ProgettoController {
	
	private ProgettoDAO projectDAO;
	
	public ProgettoController(ProgettoDAO projectDAO) {
		
		this.projectDAO = projectDAO;
		
		}
	
	public ArrayList<Progetto> getProgettiUtente(){
		
		ArrayList<Progetto> listProgetti = new ArrayList();
		listProgetti = projectDAO.getProjectsByUtente(SessionController.getInstance().getUtenteLoggato());
		
		
		if(!listProgetti.isEmpty()) {
			
			return listProgetti;
			
		}
		
		return null;
	}
	
}
