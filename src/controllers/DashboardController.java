package controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import entities.AssegnazioneDocu;
import entities.AssegnazioneSviluppo;
import entities.Attivita;
import entities.AttivitaDocumentazione;
import entities.AttivitaSviluppo;
import entities.Membro;
import entities.Progetto;
import exceptions.LoadDataException;

public class DashboardController {
	MembroController membroController;
	TaskController taskController;
	ProgettoController progettoController;
	
	ArrayList<Attivita> listaTasks;
	ArrayList<Membro> listaMembri;
	ArrayList<AssegnazioneDocu> listaAssegnazioneTaskDocu;
	ArrayList<AssegnazioneSviluppo> listaAssegnazioneTaskSviluppo;
	
	public DashboardController(MembroController membroController, TaskController taskController, ProgettoController progettoController) {
		this.membroController = membroController;
		this.taskController = taskController;
		this.progettoController = progettoController;
	}
	
	public void loadData() throws Exception{
		
		Progetto progetto = SessionController.getInstance().getCorrenteProgetto();
		
		if(progetto == null) throw new LoadDataException();
		
		this.listaTasks = taskController.getTasksByProgetto(progetto);
		this.listaMembri = membroController.getMembriByProgetto(progetto);	
		this.listaAssegnazioneTaskDocu = new ArrayList<>();
		this.listaAssegnazioneTaskSviluppo = new ArrayList<>();
		
		for(Membro membro : listaMembri) {
			
			for(Attivita task : taskController.getTasksAssignedTo(membro)) {
				
				if(task instanceof AttivitaDocumentazione t) {
					
					AssegnazioneDocu ad = new AssegnazioneDocu(t, membro, progetto);
					this.listaAssegnazioneTaskDocu.add(ad);
					
				}
				if(task instanceof AttivitaSviluppo t) {
					
					AssegnazioneSviluppo as = new AssegnazioneSviluppo(t, membro, progetto);
					this.listaAssegnazioneTaskSviluppo.add(as);
					
				}
			}
			
			
			
		}
		
	}
	
	public ArrayList<Attivita> getListaTasks(){
		return this.listaTasks;
	}
	
	public ArrayList<Attivita> getListaTasksFiltered(String stato, String tipo, String scadenza, String membro) {
		
		ArrayList<Attivita> listaTasksFiltrata = listaTasks;
		
		listaTasksFiltrata = filterByStato(listaTasksFiltrata, stato);
		listaTasksFiltrata = filterByTipo(listaTasksFiltrata, tipo);
		listaTasksFiltrata = filterByScadenza(listaTasksFiltrata, scadenza);
		listaTasksFiltrata = filterByMembro(listaTasksFiltrata, membro);
		
		return listaTasksFiltrata;
	}
	
	private ArrayList<Attivita> filterByStato(ArrayList<Attivita> lista, String stato) {
		ArrayList<Attivita> filtrata = new ArrayList<>();
		for (Attivita t : lista) {
			if (t.getStatoAvanzamento().equals(stato)) {
				filtrata.add(t);
			}
		}
		return filtrata;
	}

	private ArrayList<Attivita> filterByTipo(ArrayList<Attivita> lista, String tipoFiltro) {
		if (tipoFiltro.equals("Tutte")) {
			return lista;
		}
		
		ArrayList<Attivita> filtrata = new ArrayList<>();
		for (Attivita t : lista) {
			if (tipoFiltro.equals("Sviluppo") && t instanceof entities.AttivitaSviluppo) {
				filtrata.add(t);
			} else if (tipoFiltro.equals("Documentazione") && t instanceof entities.AttivitaDocumentazione) {
				filtrata.add(t);
			}
		}
		return filtrata;
	}

	private ArrayList<Attivita> filterByScadenza(ArrayList<Attivita> lista, String scadenzaFiltro) {
		if (scadenzaFiltro.equals("Tutte le date")) {
			return lista;
		}

		ArrayList<Attivita> filtrata = new ArrayList<>();
		LocalDate oggi = LocalDate.now();

		for (Attivita t : lista) {
			if (scadenzaFiltro.equals("Scadute") && t.getScadenza().isBefore(oggi)) {
				filtrata.add(t);
			} else if (scadenzaFiltro.equals("In scadenza")) {
				long giorni = ChronoUnit.DAYS.between(oggi, t.getScadenza());
				if (giorni >= 0 && giorni <= 7) {
					filtrata.add(t);
				}
			}
		}
		return filtrata;
	}

	private ArrayList<Attivita> filterByMembro(ArrayList<Attivita> lista, String membroFiltro) {
		if (membroFiltro.equals("Tutti i membri")) {
			return lista;
		}

		ArrayList<Attivita> filtrata = new ArrayList<>();

		for (Attivita t : lista) {
			// Richiama il nuovo metodo per ottenere l'elenco completo
			String assegnatariDellaTask = getNomiAssegnatariTask(t);

			if (membroFiltro.equals("Nessuno")) {
				// Se cerchiamo i task non assegnati, l'uguaglianza deve essere esatta
				if (assegnatariDellaTask.equals("Nessuno")) {
					filtrata.add(t);
				}
			} else {
				// Se cerchiamo un utente specifico, verifichiamo che sia presente nella lista concatenata
				if (assegnatariDellaTask.contains(membroFiltro)) {
					filtrata.add(t);
				}
			}
		}
		
		return filtrata;
	}
	
	public ArrayList<Membro> getListaMembri(){
		return this.listaMembri;
	}
	
	public TaskController getTaskController() {
		return this.taskController;
	}
	
	public String getNomiAssegnatariTask(Attivita t) {
		StringBuilder assegnatari = new StringBuilder();
		boolean primoAggiunto = false; // Flag per gestire l'inserimento della virgola

		if (t instanceof entities.AttivitaDocumentazione) {
			for (entities.AssegnazioneDocu ad : this.listaAssegnazioneTaskDocu) {
				if (ad.getAttivitaDocu().getIdAttivita() == t.getIdAttivita()) {
					if (primoAggiunto) assegnatari.append(", ");
					assegnatari.append(ad.getMembro().getUtente().getNome()).append(" ").append(ad.getMembro().getUtente().getCognome());
					primoAggiunto = true;
				}
			}
		} else if (t instanceof entities.AttivitaSviluppo) {
			for (entities.AssegnazioneSviluppo as : this.listaAssegnazioneTaskSviluppo) {
				if (as.getAttivitaSviluppo().getIdAttivita() == t.getIdAttivita()) {
					if (primoAggiunto) assegnatari.append(", ");
					assegnatari.append(as.getMembro().getUtente().getNome()).append(" ").append(as.getMembro().getUtente().getCognome());
					primoAggiunto = true;
				}
			}
		}
		
		// Verifica finale: se il costruttore di stringhe è vuoto, non ci sono assegnatari
		if (assegnatari.length() == 0) {
			return "Nessuno";
		}
		
		return assegnatari.toString();
	}
	
}
 