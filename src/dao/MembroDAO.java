package dao;

import java.util.ArrayList;

import entities.Membro;
import entities.Progetto;

public interface MembroDAO {
	
	public ArrayList<Membro> getMembriByProgetto(Progetto progetto);
	
}
