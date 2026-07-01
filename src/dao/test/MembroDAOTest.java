package dao.test;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.MembroDAO;
import entities.Membro;
import entities.Progetto;
import entities.Utente;

public class MembroDAOTest implements MembroDAO {

	@Override
	public ArrayList<Membro> getMembriByProgetto(Progetto progetto) {
		ArrayList<Membro> membri = new ArrayList<>();

		// Creazione Utenti fittizi
		Utente u1 = new Utente("Mario", "Rossi", "M001", "mario.rossi@studenti.unina.it", "pass123");
		Utente u2 = new Utente("Giulia", "Bianchi", "M002", "giulia.bianchi@studenti.unina.it", "pass123");
		Utente u3 = new Utente("Luca", "Verdi", "M003", "luca.verdi@studenti.unina.it", "pass123");
		Utente u4 = new Utente("Anna", "Neri", "M004", "anna.neri@studenti.unina.it", "pass123");
		Utente u5 = new Utente("Marco", "Gialli", "M005", "marco.gialli@studenti.unina.it", "pass123");

		// Creazione Membri fittizi legati agli Utenti (progetto è null per ora)
		membri.add(new Membro("Sviluppatore", LocalDate.now().minusMonths(2), "Attivo", 120.5f, null, u1));
		membri.add(new Membro("Project Manager", LocalDate.now().minusMonths(3), "Attivo", 200.0f, null, u2));
		membri.add(new Membro("Tester", LocalDate.now().minusDays(15), "In pausa", 45.0f, null, u3));
		membri.add(new Membro("Designer UI/UX", LocalDate.now().minusMonths(1), "Attivo", 80.0f, null, u4));
		membri.add(new Membro("Sviluppatore", LocalDate.now().minusDays(5), "Inattivo", 10.0f, null, u5));

		return membri;
	}

}
