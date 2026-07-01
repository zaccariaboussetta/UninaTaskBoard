package controllers;

import java.util.ArrayList;
import dao.MembroDAO;
import entities.Membro;
import entities.Progetto;

public class MembroController {

	private MembroDAO membroDAO;
	
	public MembroController(MembroDAO m) {
		this.membroDAO = m;
	}
	
	// Metodo per richiedere i membri dal DAO
	public ArrayList<Membro> getMembriByProgetto(Progetto progetto) {
		return membroDAO.getMembriByProgetto(progetto);
	}
	
}
