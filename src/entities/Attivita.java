package entities;

import java.time.LocalDate;

public class Attivita {
	private int idAttivita;
	private String descrizione;
	private LocalDate dataCreazione;
	private LocalDate scadenza;
	private String statoAvanzamento;
	
	//Costruttore per reperimento dati dal DB;
	public Attivita(int idAttivita, String descrizione, LocalDate dataCreazione, LocalDate scadenza,
			String statoAvanzamento) {
		this.idAttivita = idAttivita;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.scadenza = scadenza;
		this.statoAvanzamento = statoAvanzamento;
	}

	//Costruttore per inserimento dati nel DB;
	public Attivita(String descrizione, LocalDate scadenza) {
		this.descrizione = descrizione;
		this.scadenza = scadenza;
	}

	
	
	public int getIdAttivita() {
		return idAttivita;
	}

	public void setIdAttivita(int idAttivita) {
		this.idAttivita = idAttivita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public LocalDate getScadenza() {
		return scadenza;
	}

	public void setScadenza(LocalDate scadenza) {
		this.scadenza = scadenza;
	}

	public String getStatoAvanzamento() {
		return statoAvanzamento;
	}

	public void setStatoAvanzamento(String statoAvanzamento) {
		this.statoAvanzamento = statoAvanzamento;
	}
	
	
}
