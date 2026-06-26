package controllers;

import dao.MembroDAO;

public class MembroController {

	MembroDAO membroDAO;
	
	public MembroController(MembroDAO m) {
		this.membroDAO = m;
	}
	
}
