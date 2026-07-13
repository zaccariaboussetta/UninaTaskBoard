package entities;
import java.time.LocalDate;
import java.util.ArrayList;

public class Membro {
	private String ruolo;
	private LocalDate dataAdesione;
	private String statoPartecipazione;
	private float oreLavoro;
	private Progetto progetto;
	private ArrayList<Attivita> assignedTasksList;
	private Utente utente;
	
	//Costruttore per reperimento dati dal DB;
	public Membro(String ruolo, LocalDate dataAdesione, String statoPartecipazione, float oreLavoro, Progetto progetto, Utente utente) {
		this.ruolo = ruolo;
		this.dataAdesione = dataAdesione;
		this.statoPartecipazione = statoPartecipazione;
		this.oreLavoro = oreLavoro;
		this.progetto = progetto;
		this.utente = utente;
	}
	
	//Costruttore per inserimento dati nel DB;
	public Membro(String ruolo, LocalDate dataAdesione, Progetto progetto, Utente utente) {
		this.ruolo = ruolo;
		this.statoPartecipazione = "Attivo";
		this.progetto = progetto;
		this.utente = utente;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public LocalDate getDataAdesione() {
		return dataAdesione;
	}

	public void setDataAdesione(LocalDate dataAdesione) {
		this.dataAdesione = dataAdesione;
	}

	public String getStatoPartecipazione() {
		return statoPartecipazione;
	}

	public void setStatoPartecipazione(String statoPartecipazione) {
		this.statoPartecipazione = statoPartecipazione;
	}

	public float getOreLavoro() {
		return oreLavoro;
	}

	public void setOreLavoro(float oreLavoro) {
		this.oreLavoro = oreLavoro;
	}

	public Progetto getProgetto() {
		return progetto;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public void addTask(Attivita task) {
		assignedTasksList.add(task);
	}
	
}

