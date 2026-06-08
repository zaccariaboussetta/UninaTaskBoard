package entities;

import java.time.LocalDate;

public class AttivitaSviluppo extends Attivita{
	private String tipologiaSviluppo;
	private String linguaggioProgrammazione;
	private String nomeBranch;
	private Progetto progetto;

	//Reperimento dati dal DB;
	public AttivitaSviluppo(int idAttivita, String descrizione, LocalDate dataCreazione, LocalDate scadenza,
			String statoAvanzamento, String tipologiaSviluppo, String linguaggioProgrammazione, String nomeBranch,
			Progetto progetto) {
		super(idAttivita, descrizione, dataCreazione, scadenza, statoAvanzamento);
		this.tipologiaSviluppo = tipologiaSviluppo;
		this.linguaggioProgrammazione = linguaggioProgrammazione;
		this.nomeBranch = nomeBranch;
		this.progetto = progetto;
	}
	
	//Inserimento dati nel DB; 
	public AttivitaSviluppo(String descrizione, LocalDate scadenza, String tipologiaSviluppo,
			String linguaggioProgrammazione, String nomeBranch, Progetto progetto) {
		super(descrizione, scadenza);
		this.tipologiaSviluppo = tipologiaSviluppo;
		this.linguaggioProgrammazione = linguaggioProgrammazione;
		this.nomeBranch = nomeBranch;
		this.progetto = progetto;
	}

	public String getTipologiaSviluppo() {
		return tipologiaSviluppo;
	}

	public void setTipologiaSviluppo(String tipologiaSviluppo) {
		this.tipologiaSviluppo = tipologiaSviluppo;
	}

	public String getLinguaggioProgrammazione() {
		return linguaggioProgrammazione;
	}

	public void setLinguaggioProgrammazione(String linguaggioProgrammazione) {
		this.linguaggioProgrammazione = linguaggioProgrammazione;
	}

	public String getNomeBranch() {
		return nomeBranch;
	}

	public void setNomeBranch(String nomeBranch) {
		this.nomeBranch = nomeBranch;
	}

	public Progetto getProgetto() {
		return progetto;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}
	
	
}
