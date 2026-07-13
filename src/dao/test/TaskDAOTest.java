package dao.test;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.TaskDAO;
import entities.Attivita;
import entities.AttivitaDocumentazione;
import entities.AttivitaSviluppo;
import entities.Membro;
import entities.Progetto;

public class TaskDAOTest implements TaskDAO {

	@Override
	public ArrayList<Attivita> getTasksByProgetto(Progetto progetto) {
		ArrayList<Attivita> tasks = new ArrayList<>();

		// 1.
		tasks.add(new AttivitaSviluppo(1, "Creazione UI Login", LocalDate.now().minusDays(2), LocalDate.now().plusDays(3), 
				"Todo", "Frontend", "Java Swing", "feature/login-ui", progetto));
		// 2.
		tasks.add(new AttivitaSviluppo(2, "Implementazione DAO", LocalDate.now(), LocalDate.now().plusDays(5), 
				"In_Progress", "Backend", "Java", "feature/database-dao", progetto));
		// 3.
		tasks.add(new AttivitaDocumentazione(3, "Stesura Documento di Specifica", LocalDate.now().minusDays(10), LocalDate.now().plusDays(1), 
				"Done", "Specifica Requisiti v1", "PDF", "Requisiti Funzionali", "http://drive.link/doc", progetto));
		// 4.
		tasks.add(new AttivitaSviluppo(4, "Setup DB Relazionale", LocalDate.now().minusDays(5), LocalDate.now().minusDays(1), 
				"Done", "Database", "SQL", "main", progetto));
		// 5.
		tasks.add(new AttivitaDocumentazione(5, "Manuale Utente - Bozza", LocalDate.now(), LocalDate.now().plusDays(10), 
				"Todo", "Manuale d'uso", "Word", "Introduzione", "http://drive.link/manuale", progetto));
		// 6.
		tasks.add(new AttivitaSviluppo(6, "Integrazione API Esterna", LocalDate.now().minusDays(1), LocalDate.now().plusDays(7), 
				"In_Progress", "Backend", "Java", "feature/api-integration", progetto));
		// 7.
		tasks.add(new AttivitaSviluppo(7, "Bugfix Crash su Avvio", LocalDate.now(), LocalDate.now().plusDays(1), 
				"Todo", "Bugfix", "Java", "hotfix/startup-crash", progetto));
		// 8.
		tasks.add(new AttivitaDocumentazione(8, "Aggiornamento Diagrammi UML", LocalDate.now().minusDays(3), LocalDate.now().plusDays(2), 
				"In_Progress", "Architettura", "PNG", "Design System", "http://drive.link/uml", progetto));
		// 9.
		tasks.add(new AttivitaSviluppo(9, "Refactoring Controller", LocalDate.now().minusDays(15), LocalDate.now().minusDays(10), 
				"Done", "Backend", "Java", "feature/refactor-controllers", progetto));
		// 10.
		tasks.add(new AttivitaSviluppo(10, "Aggiunta animazioni UI", LocalDate.now().plusDays(1), LocalDate.now().plusDays(4), 
				"Todo", "Frontend", "Java Swing", "feature/ui-animations", progetto));
		// 11.
		tasks.add(new AttivitaDocumentazione(11, "Verbali Riunione Kickoff", LocalDate.now().minusDays(20), LocalDate.now().minusDays(19), 
				"Done", "Meeting Notes", "PDF", "Management", "http://drive.link/notes", progetto));
		// 12.
		tasks.add(new AttivitaSviluppo(12, "Scrittura Test Unitari", LocalDate.now().minusDays(2), LocalDate.now().plusDays(5), 
				"In_Progress", "Testing", "JUnit", "feature/unit-tests", progetto));
		// 13.
		tasks.add(new AttivitaDocumentazione(13, "Stesura Privacy Policy", LocalDate.now(), LocalDate.now().plusDays(14), 
				"Todo", "Legale", "PDF", "Compliance", "http://drive.link/privacy", progetto));

		return tasks;
	}

	@Override
	public ArrayList<Attivita> getTasksAssignedTo(Membro membro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTaskStatus(Progetto progetto, Attivita task, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignTaskTo(Membro membro, Progetto progetto, Attivita task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int inserisciNuovaAttivita(Attivita task, Progetto progetto) {
		// TODO Auto-generated method stub
		return 0;
	}

}

