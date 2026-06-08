package entities;
import java.time.LocalDate;

public class Membro {
	private String ruolo;
	private LocalDate dataAdesione;
	private String statoPartecipazione;
	private float oreLavoro;
	private Progetto progetto;
	private Utente utente;
	
	public Membro(String ruolo, LocalDate dataAdesione, String statoPartecipazione, Progetto progetto, Utente utente) {
		super();
		this.ruolo = ruolo;
		this.dataAdesione = dataAdesione;
		this.statoPartecipazione = statoPartecipazione;
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
	
	
}
